package com.dh.digitalbooking.service;

import com.dh.digitalbooking.dto.FavoritoDto;
import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.UsuarioRequestDto;
import com.dh.digitalbooking.dto.UsuarioResponseDto;
import com.dh.digitalbooking.model.Producto;
import com.dh.digitalbooking.model.Usuario;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDto> allUsuario();
    UsuarioResponseDto getByIdUsuario(Long id, UserDetailsDto userDetailsDto);
    Usuario findByEmail(String email);
    Usuario saveUsuario(UsuarioRequestDto usuarioRequestDto);
    void deleteUsuario(Long id);
    UsuarioResponseDto updateUsuario(UsuarioResponseDto usuarioResponseDto);
    void handleFav(FavoritoDto favoritoDto, UserDetailsDto userDetailsDto);
}
