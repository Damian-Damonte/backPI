package com.dh.digitalbooking.controller;

import com.dh.digitalbooking.dto.UsuarioResponseDto;
import com.dh.digitalbooking.service.imp.UsuarioServiceImp;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(
            description = "Al eliminar un usuario se eliminarán automaticamente las reservas y puntuaciones relacionadas a este"
    )
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioServiceImp.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Operation(
            description = "Se pueden actualizar todos los atributos excepto la contraseña. No es necesario enviar las reservas, ya que de todas formas no se pueden actualizar a través del usuario."
    )
    public ResponseEntity<UsuarioResponseDto> updatePais(
            @RequestBody @Valid UsuarioResponseDto usuarioResponseDto) {
        return ResponseEntity.ok(usuarioServiceImp.updateUsuario(usuarioResponseDto));
    }
}
