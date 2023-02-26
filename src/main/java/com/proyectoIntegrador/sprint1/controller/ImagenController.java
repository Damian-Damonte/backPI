package com.proyectoIntegrador.sprint1.controller;

import com.proyectoIntegrador.sprint1.model.Imagen;
import com.proyectoIntegrador.sprint1.service.imp.ImagenServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/imagenes")
public class ImagenController {
    private final ImagenServiceImp imagenServiceImp;

    public ImagenController(ImagenServiceImp imagenServiceImp) {
        this.imagenServiceImp = imagenServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<Imagen>> getAllImagen() {
        return ResponseEntity.ok(imagenServiceImp.allImagen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> getByIdImagen(@PathVariable("id") Long id) {
        return ResponseEntity.ok(imagenServiceImp.getByIdImagen(id));
    }

    @PostMapping
    public ResponseEntity<Imagen> saveImagen(@RequestBody Imagen imagen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenServiceImp.saveImagen(imagen));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagen(@PathVariable Long id) {
        imagenServiceImp.deleteImagen(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Imagen> updateImagen(@RequestBody Imagen imagen) {
        return ResponseEntity.ok(imagenServiceImp.updateImagen(imagen));
    }
}
