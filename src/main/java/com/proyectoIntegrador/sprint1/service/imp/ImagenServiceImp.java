package com.proyectoIntegrador.sprint1.service.imp;

import com.proyectoIntegrador.sprint1.exception.BadRequestException;
import com.proyectoIntegrador.sprint1.exception.NotFoundException;
import com.proyectoIntegrador.sprint1.model.Imagen;
import com.proyectoIntegrador.sprint1.model.Producto;
import com.proyectoIntegrador.sprint1.repository.ImagenRepository;
import com.proyectoIntegrador.sprint1.service.ImagenService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImagenServiceImp implements ImagenService {
    private final ImagenRepository imagenRepository;
    private final ProductoServiceImp productoServiceImp;

    public ImagenServiceImp(ImagenRepository imagenRepository, ProductoServiceImp productoServiceImp) {
        this.imagenRepository = imagenRepository;
        this.productoServiceImp = productoServiceImp;
    }

    @Override
    public List<Imagen> allImagen() {
        return imagenRepository.findAll();
    }

    @Override
    public Imagen getByIdImagen(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public Imagen saveImagen(Imagen imagen) {
        emptyAttributesValidation(imagen);
        productoServiceImp.existByIdValidation(imagen.getProducto().getId());
        return imagenRepository.save(imagen);
    }

    @Override
    public void deleteImagen(Long id) {
        Imagen imagen = existByIdValidation(id);
//      Validacion si hay productos relacionados con esta imagen
        imagenRepository.deleteById(id);
    }

    @Override
    public Imagen updateImagen(Imagen imagen) {
        existByIdValidation(imagen.getId());
        emptyAttributesValidation(imagen);
        productoServiceImp.existByIdValidation(imagen.getProducto().getId());
        return imagenRepository.save(imagen);
    }

    public Imagen existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id de la imagen");
        return imagenRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Imagen con id " + id + " no encontrada"));
    }

    private void emptyAttributesValidation(Imagen imagen) {
        String titulo = imagen.getTitulo();
        String url = imagen.getUrl();
        Producto producto = imagen.getProducto();
        if(titulo == null || titulo.equals(""))
            throw new BadRequestException("La imagen debe contener un titulo");
        if(url == null || url.equals(""))
            throw new BadRequestException("La imagen debe contener una url");
        if(producto == null)
            throw new BadRequestException("La imagen debe pertenecer a un producto");
    }
}
