package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.pais.PaisDTO;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Pais;
import com.dh.digitalbooking.mapper.PaisMapper;
import com.dh.digitalbooking.repository.PaisRepository;
import com.dh.digitalbooking.service.PaisService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PaisServiceImp implements PaisService {

    private final PaisRepository paisRepository;
    private final PaisMapper paisMapper;

    public PaisServiceImp(PaisRepository paisRepository, PaisMapper paisMapper) {
        this.paisRepository = paisRepository;
        this.paisMapper = paisMapper;
    }

    @Override
    public List<PaisDTO> allPais() {
        return paisMapper.listPaisToPaisDTO(paisRepository.findAll());
    }

    @Override
    public PaisDTO getByIdPais(Long id) {
        return paisMapper.paisToPaisDTO(existByIdValidation(id));
    }

    @Override
    public PaisDTO savePais(PaisDTO paisDTO) {
        Pais pais = paisMapper.paisDTOtoPais(paisDTO);
        String nombre = pais.getNombre();
        emptyNombreValidation(nombre);
        if(paisRepository.findByNombre(nombre).isPresent())
            throw new BadRequestException("Ya existe un pais con el nombre '" + nombre + "'");

        return paisMapper.paisToPaisDTO(paisRepository.save(pais));
    }

    @Override
    public void deletePais(Long id) {
        Pais pais = existByIdValidation(id);
        if(!(pais.getCiudades().isEmpty()))
            throw new BadRequestException("No puede eliminar el pais con id " + id + " ya que hay ciudades registradas en dicho pais");

        paisRepository.deleteById(id);
    }

    @Override
    public PaisDTO updatePais(PaisDTO paisDTO) {
        Pais pais = paisMapper.paisDTOtoPais(paisDTO);
        Long id = pais.getId();
        String nombre = pais.getNombre();

        emptyNombreValidation(nombre);
        existByIdValidation(id);

        Pais paisByNombre = paisRepository.findByNombre(nombre).orElse(null);
        if(paisByNombre != null && !(paisByNombre.getId().equals(id)))
            throw new BadRequestException("Ya existe un pais con el nombre '" + nombre + "'");

        return paisMapper.paisToPaisDTO(paisRepository.save(pais));
    }

    public Pais existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id del pais");
        return paisRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Pais con id " + id + " no encontrado"));
    }

    private void emptyNombreValidation(String nombre) {
        if(nombre == null || nombre.equals(""))
            throw new BadRequestException("El pais debe contener un nombre");
    }
}
