package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.country.CountryFullDTO;
import com.dh.digitalbooking.dto.country.CountryNoIdDTO;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Country;
import com.dh.digitalbooking.mapper.CountryMapper;
import com.dh.digitalbooking.repository.CountryRepository;
import com.dh.digitalbooking.service.CountryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final CityService cityService;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper, @Lazy CityService cityService) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
        this.cityService = cityService;
    }

    @Override
    public List<CountryFullDTO> getAllCountries() {
        return countryMapper.listCountryToCountryFullDTO(countryRepository.findAll());
    }

    @Override
    public CountryFullDTO getCountryById(Long id) {
        return countryMapper.countryToContryFullDTO(existByIdValidation(id));
    }

    @Override
    public CountryFullDTO saveCountry(CountryNoIdDTO countryNoIdDTO) {
        String name = countryNoIdDTO.name();
        if(countryRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already a country with the name '" + name + "'");
        Country country = countryMapper.countryNoIdDTOToCountry(countryNoIdDTO);

        return countryMapper.countryToContryFullDTO(countryRepository.save(country));
    }

    @Override
    public void deleteCountry(Long id) {
        existByIdValidation(id);
        if(cityService.existsCityByCountryId(id))
            throw new BadRequestException("You cannot delete the country with id " + id + " because there are cities registered in that country");
        countryRepository.deleteById(id);
    }

    @Override
    public CountryFullDTO updateCountry(CountryFullDTO countryFullDTO) {
        Long id = countryFullDTO.id();
        String name = countryFullDTO.name();
        existByIdValidation(id);

        Country countryByName = countryRepository.findByName(name).orElse(null);
        if(countryByName != null && !(countryByName.getId().equals(id)))
            throw new BadRequestException("There is already a country with the name '" + name + "'");

        Country country = countryMapper.countryFullDTOToCountry(countryFullDTO);
        return countryMapper.countryToContryFullDTO(countryRepository.save(country));
    }

    public Country existByIdValidation(Long id) {
        return countryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Country with id " + id + " not found"));
    }
}
