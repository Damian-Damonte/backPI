package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.ReservaDto;
import com.dh.digitalbooking.dtoMapper.ReservaDtoMapper;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Ciudad;
import com.dh.digitalbooking.model.Producto;
import com.dh.digitalbooking.model.Reserva;
import com.dh.digitalbooking.repository.ReservaRepository;
import com.dh.digitalbooking.service.ReservaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservaServiceImp implements ReservaService {
    private final ReservaRepository reservaRepository;
    private final ReservaDtoMapper mapper;
    private final ProductoServiceImp productoServiceImp;

    public ReservaServiceImp(ReservaRepository reservaRepository, ReservaDtoMapper mapper, ProductoServiceImp productoServiceImp) {
        this.reservaRepository = reservaRepository;
        this.mapper = mapper;
        this.productoServiceImp = productoServiceImp;
    }

    @Override
    public List<ReservaDto> allReserva() {
        return mapper.toListReservaDto(reservaRepository.findAll());
    }

    @Override
    public ReservaDto getByIdReseva(Long id) {
        return mapper.toResevaDto(existByIdValidation(id));
    }

    @Override
    public ReservaDto saveReserva(ReservaDto reservaDto) {
        Reserva reserva = mapper.toReserva(reservaDto);
        Producto producto = productoServiceImp.existByIdValidation(reserva.getProducto().getId());
        producto.getReservas().add(reserva);
        reserva.setProducto(producto);

        return mapper.toResevaDto(reservaRepository.save(reserva));
    }

    @Override
    public void deleteReserva(Long id) {
        existByIdValidation(id);
        reservaRepository.deleteById(id);
    }

    @Override
    public ReservaDto updateReserva(ReservaDto reservaDto) {
        return null;
    }

    public Reserva existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la reserva");
        return reservaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Reserva con id " + id + " no encontrada"));
    }
}
