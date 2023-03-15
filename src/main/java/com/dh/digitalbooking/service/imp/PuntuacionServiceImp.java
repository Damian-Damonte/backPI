package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Producto;
import com.dh.digitalbooking.model.Puntuacion;
import com.dh.digitalbooking.model.Usuario;
import com.dh.digitalbooking.repository.PuntuacionRepository;
import com.dh.digitalbooking.service.PuntuacionService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PuntuacionServiceImp implements PuntuacionService {
    private final PuntuacionRepository puntuacionRepository;
    private final ProductoServiceImp productoServiceImp;
    private final UsuarioServiceImp usuarioServiceImp;

    public PuntuacionServiceImp(PuntuacionRepository puntuacionRepository, ProductoServiceImp productoServiceImp, UsuarioServiceImp usuarioServiceImp) {
        this.puntuacionRepository = puntuacionRepository;
        this.productoServiceImp = productoServiceImp;
        this.usuarioServiceImp = usuarioServiceImp;
    }

    @Override
    public List<Puntuacion> allPuntuacion() {
        return puntuacionRepository.findAll();
    }

    @Override
    public Puntuacion getByIdPuntuacion(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Puntuacion savePuntuacion(Puntuacion puntuacion, UserDetailsDto userDetailsDto) {
        Long puntuacionUserId = puntuacion.getUsuario().getId();

        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!puntuacionUserId.equals(userDetailsDto.getUserId()))
                throw new BadRequestException("La información del usuario proporcionado no coincide con el usuario actualmente autenticado");
        }

        return getPuntuacion(puntuacion);
    }

    @Override
    public void deletePuntuacion(Long id) {
        Puntuacion puntuacion = existByIdValidation(id);
        puntuacionRepository.deleteById(id);
        puntuacionRepository.updatePromedioPuntuacionProducto(puntuacion.getProducto().getId());
    }

    @Override
    public Puntuacion updatePuntuacion(Puntuacion puntuacion) {
        existByIdValidation(puntuacion.getId());
        return getPuntuacion(puntuacion);
    }

    private Puntuacion getPuntuacion(Puntuacion puntuacion) {
        Producto producto = productoServiceImp.existByIdValidation(puntuacion.getProducto().getId());
        Usuario usuario = usuarioServiceImp.existByIdValidation(puntuacion.getUsuario().getId());
        puntuacion.setProducto(producto);
        puntuacion.setUsuario(usuario);
        Puntuacion puntuacionSave = puntuacionRepository.save(puntuacion);
        puntuacionRepository.updatePromedioPuntuacionProducto(producto.getId());
        return puntuacionSave;
    }

    public Puntuacion existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la puntuacion");
        return puntuacionRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Puntuacion con id " + id + " no encontrada"));
    }
}
