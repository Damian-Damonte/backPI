package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.model.TipoPolitica;
import com.dh.digitalbooking.repository.TipoPoliticaRepository;
import com.dh.digitalbooking.service.TipoPoliticaService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TipoPoliticaServiceImp implements TipoPoliticaService {
    private final TipoPoliticaRepository tipoPoliticaRepository;

    public TipoPoliticaServiceImp(TipoPoliticaRepository tipoPoliticaRepository) {
        this.tipoPoliticaRepository = tipoPoliticaRepository;
    }


    @Override
    public List<TipoPolitica> allTipoPolitica() {
        return tipoPoliticaRepository.findAll();
    }

    @Override
    public TipoPolitica getByIdTipoPolitica(Long id) {
        return existByIdValidation(id);
    }

    @Override
    public TipoPolitica saveTipoPolitica(TipoPolitica tipoPolitica) {
        String nombre = tipoPolitica.getNombre();
        nombreValidation(nombre);

        if(tipoPoliticaRepository.findByNombre(nombre).isPresent())
            throw new BadRequestException("Ya existe un tipo de politica con el nombre '" + nombre + "'");

        return tipoPoliticaRepository.save(tipoPolitica);
    }

    @Override
    public void deleteTipoPolitica(Long id) {
        TipoPolitica tipoPolitica = existByIdValidation(id);
        if(!(tipoPolitica.getPoliticas().isEmpty()))
            throw new BadRequestException("No puede eliminar el tipo de politica con id " + id + " ya que hay politicas registradas de este tipo");
        tipoPoliticaRepository.deleteById(id);
    }

    @Override
    public TipoPolitica updateTipoPolitica(TipoPolitica tipoPolitica) {
        Long id = tipoPolitica.getId();
        String nombre = tipoPolitica.getNombre();

        nombreValidation(nombre);
        existByIdValidation(id);

        TipoPolitica tipoPoliticaByNombre = tipoPoliticaRepository.findByNombre(nombre).orElse(null);
        if(tipoPoliticaByNombre != null && !(tipoPoliticaByNombre.getId().equals(id)))
            throw new BadRequestException("Ya existe el tipo de politica llamado '" + nombre + "'");


        return tipoPoliticaRepository.save(tipoPolitica);
    }

    public TipoPolitica existByIdValidation(Long id) {
        if(id == null)
            throw new BadRequestException("Debe enviar el id del tipo de politica");
        return tipoPoliticaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Tipo de politica con id " + id + " no encontrado"));
    }

    private void nombreValidation(String nombre) {
        if(nombre == null || nombre.equals(""))
            throw new BadRequestException("El tipo de politica debe contener un nombre");
        if(nombre.length() > 100)
            throw new BadRequestException("El nombre del tipo de politica no debe tener mas de 45 caracteres");
    }
}
