package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.UsuarioResponseDto;
import com.dh.digitalbooking.model.Pais;
import com.dh.digitalbooking.service.imp.UsuarioServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioServiceImp usuarioServiceImp;

    public UsuarioController(UsuarioServiceImp usuarioServiceImp) {
        this.usuarioServiceImp = usuarioServiceImp;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> getAll() {
        return ResponseEntity.ok(usuarioServiceImp.allUsuario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(usuarioServiceImp.getByIdUsuario(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioServiceImp.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<UsuarioResponseDto> updatePais(
            @RequestBody @Valid UsuarioResponseDto usuarioResponseDto) {
        return ResponseEntity.ok(usuarioServiceImp.updateUsuario(usuarioResponseDto));
    }
}
