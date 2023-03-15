package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Producto;
import com.dh.digitalbooking.model.Reserva;
import com.dh.digitalbooking.model.Usuario;
import com.dh.digitalbooking.repository.ReservaRepository;
import com.dh.digitalbooking.service.ReservaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservaServiceImp implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final ProductoServiceImp productoServiceImp;
    private final UsuarioServiceImp usuarioServiceImp;

    public ReservaServiceImp(ReservaRepository reservaRepository, ProductoServiceImp productoServiceImp, UsuarioServiceImp usuarioServiceImp) {
        this.reservaRepository = reservaRepository;
        this.productoServiceImp = productoServiceImp;
        this.usuarioServiceImp = usuarioServiceImp;
    }

    @Override
    public List<Reserva> allReserva() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva getByIdReseva(Long id) {
        return existByIdValidation(id);
    }

    @Override
    @Transactional
    public Reserva saveReserva(Reserva reserva, UserDetailsDto userDetailsDto) {
        Long reservaUserId = reserva.getUsuario().getId();

        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!reservaUserId.equals(userDetailsDto.getUserId()))
                throw new BadRequestException("La información del usuario proporcionado no coincide con el usuario actualmente autenticado");
        }

        checkAvailability(reserva, true);
        return getReserva(reserva);
    }

    @Override
    @Transactional
    public void deleteReserva(Long id) {
        existByIdValidation(id);
        reservaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Reserva updateReserva(Reserva reserva) {
        existByIdValidation(reserva.getId());
        checkAvailability(reserva, false);
        return getReserva(reserva);
    }

    private Reserva getReserva(Reserva reserva) {
        datesValidation(reserva);
        Producto producto = productoServiceImp.existByIdValidation(reserva.getProducto().getId());
        producto.getReservas().add(reserva);
        reserva.setProducto(producto);

        Usuario usuario = usuarioServiceImp.existByIdValidation(reserva.getUsuario().getId());
        usuario.setCiudad(reserva.getCiudadUsuario());
        usuario.getReservas().add(reserva);
        reserva.setUsuario(usuario);

        return reservaRepository.save(reserva);
    }

    public Reserva existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la reserva");
        return reservaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Reserva con id " + id + " no encontrada"));
    }

    private void checkAvailability(Reserva reserva, boolean save) {
        int count = reservaRepository.countReservasByDates(
                reserva.getProducto().getId(),
                reserva.getCheckIn(),
                reserva.getCheckOut()
        );
        if(count > 1)
            throw new BadRequestException("Producto no disponible en las fechas ingresadas");
        if(save && count > 0)
            throw new BadRequestException("Producto no disponible en las fechas ingresadas");
        if(!save && count == 1) {
            Reserva reservaByDates = reservaRepository.findReservaByDates(
                    reserva.getProducto().getId(),
                    reserva.getCheckIn(),
                    reserva.getCheckOut()
            );
            if(!(reservaByDates.getId().equals(reserva.getId())))
                throw new BadRequestException("Producto no disponible en las fechas ingresadas");
        }
    }

    private void datesValidation(Reserva reserva) {
        LocalDate checkIn = reserva.getCheckIn();
        LocalDate checkOut = reserva.getCheckOut();

        if(checkIn.isAfter(checkOut))
            throw new BadRequestException("La fecha de ingreso deber anterior a la fecha de finalización");
    }
}
