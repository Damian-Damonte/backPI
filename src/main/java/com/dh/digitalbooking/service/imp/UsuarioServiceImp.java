package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.FavoritoDto;
import com.dh.digitalbooking.dto.UserDetailsDto;
import com.dh.digitalbooking.dto.UsuarioRequestDto;
import com.dh.digitalbooking.dto.UsuarioResponseDto;
import com.dh.digitalbooking.dtoMapper.UsuarioResponseDtoMapper;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.Producto;
import com.dh.digitalbooking.model.Rol;
import com.dh.digitalbooking.model.Usuario;
import com.dh.digitalbooking.repository.RoleRepository;
import com.dh.digitalbooking.repository.UsuarioRepository;
import com.dh.digitalbooking.service.UsuarioService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioResponseDtoMapper usuarioResponseMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ProductoServiceImp productoServiceImp;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, UsuarioResponseDtoMapper usuarioResponseMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository, ProductoServiceImp productoServiceImp) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioResponseMapper = usuarioResponseMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.productoServiceImp = productoServiceImp;
    }

    @Override
    public List<UsuarioResponseDto> allUsuario() {
        return usuarioResponseMapper.toListUsuarioResponseDto(usuarioRepository.findAll());
    }

    @Override
    public UsuarioResponseDto getByIdUsuario(Long id, UserDetailsDto userDetailsDto) {
        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!id.equals(userDetailsDto.getUserId()))
                throw new BadRequestException("La información del usuario proporcionado no coincide con el usuario actualmente autenticado");
        }
        return usuarioResponseMapper.toUsuarioResponseDto(existByIdValidation(id));
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Usuario con email " + email + " no encontrado"));
    }

    @Override
    public Usuario saveUsuario(UsuarioRequestDto usuarioRequestDto) {
        emailValidation(usuarioRequestDto.getEmail());

        Rol userRole = roleRepository.findByNombre("ROLE_USER").orElseThrow(
                () -> new NotFoundException("Rol no encontrado"));

        Usuario usuario = new Usuario(
                usuarioRequestDto.getNombre(),
                usuarioRequestDto.getApellido(),
                usuarioRequestDto.getEmail(),
                passwordEncoder.encode(usuarioRequestDto.getPassword()),
                userRole
        );

        return usuarioRepository.save(usuario);
    }

    @Override
    public void deleteUsuario(Long id) {
        Usuario usuario = existByIdValidation(id);
        usuario.getFavoritos().clear();
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDto updateUsuario(UsuarioResponseDto usuarioResponseDto) {
        Usuario usuario = existByIdValidation(usuarioResponseDto.getId());
        String email = usuarioResponseDto.getEmail();

        Usuario usuarioByEmail = usuarioRepository.findByEmail(email).orElse(null);
        if (usuarioByEmail != null && !(usuarioByEmail.getId().equals(usuarioResponseDto.getId())))
            throw new BadRequestException("El email '" + email + "' ya se encuentra registrado");

        usuario.setNombre(usuarioResponseDto.getNombre());
        usuario.setApellido(usuarioResponseDto.getApellido());
        usuario.setEmail(email);
        usuario.setCiudad(usuarioResponseDto.getCiudad());
        usuario.setRol(usuarioResponseDto.getRol());

        return usuarioResponseMapper.toUsuarioResponseDto(usuarioRepository.save(usuario));
    }

    @Override
    public void handleFav(FavoritoDto favoritoDto, UserDetailsDto userDetailsDto) {
        Long userId = favoritoDto.getUsuarioId();

        if (!userDetailsDto.getUserRol().equals("ROLE_ADMIN")) {
            if (!userId.equals(userDetailsDto.getUserId()))
                throw new BadRequestException("La información del usuario proporcionado no coincide con el usuario actualmente autenticado");
        }

        Producto producto = productoServiceImp.existByIdValidation(favoritoDto.getProductoId());
        Usuario usuario = this.existByIdValidation(userId);

        boolean isFav = usuario.getFavoritos().contains(producto);
        if (isFav) {
            usuario.removeFav(producto);
        } else {
            usuario.addFav(producto);
        }
        usuarioRepository.save(usuario);
    }

    public Usuario existByIdValidation(Long id) {
        if (id == null)
            throw new BadRequestException("Debe enviar el id del usuario");
        return usuarioRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Usuario con id " + id + " no encontrado"));
    }

    private void emailValidation(String email) {
        if (usuarioRepository.findByEmail(email).isPresent())
            throw new BadRequestException("El email '" + email + "' ya se encuentra registrado");
    }
}
