package com.dh.digitalbooking.service.imp;

import com.dh.digitalbooking.dto.country.CountryFullDto;
import com.dh.digitalbooking.dto.country.CountryRequest;
import com.dh.digitalbooking.exception.BadRequestException;
import com.dh.digitalbooking.exception.NotFoundException;
import com.dh.digitalbooking.entity.Country;
import com.dh.digitalbooking.mapper.CountryMapper;
import com.dh.digitalbooking.repository.CountryRepository;
import com.dh.digitalbooking.service.CountryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CityServiceImpl cityServiceImpl;
    private final CountryMapper countryMapper;

    public CountryServiceImpl(CountryRepository countryRepository, @Lazy CityServiceImpl cityServiceImpl, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.cityServiceImpl = cityServiceImpl;
        this.countryMapper = countryMapper;
    }

    @Override
    public List<CountryFullDto> getAllCountries() {
        return countryRepository.findAll().stream().map(countryMapper::countryToCountryFull).toList();
    }

    @Override
    public CountryFullDto getCountryById(Long id) {
        return countryMapper.countryToCountryFull(countryExistsById(id));
    }

    @Override
    @Transactional
    public CountryFullDto saveCountry(CountryRequest countryRequest) {
        String name = countryRequest.name();
        if (countryRepository.findByName(name).isPresent())
            throw new BadRequestException("There is already a country with the name '" + name + "'");
        return countryMapper.countryToCountryFull(countryRepository.save(
                Country.builder().name(countryRequest.name()).build()));
    }

    @Override
    @Transactional
    public void deleteCountry(Long id) {
        this.countryExistsById(id);
        if (cityServiceImpl.existsCityByCountryId(id))
            throw new BadRequestException("You cannot delete the country with id " + id + " because there are cities registered in that country");
        countryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public CountryFullDto updateCountry(Long id, CountryRequest countryRequest) {
        String name = countryRequest.name();
        Country country = countryExistsById(id);
        Country countryByName = countryRepository.findByName(name).orElse(null);
        if (countryByName != null && !(countryByName.getId().equals(id)))
            throw new BadRequestException("There is already a country with the name '" + name + "'");
        country.setName(name);
        return countryMapper.countryToCountryFull(country);
    }

    @Override
    public Country countryExistsById(Long id) {
        return countryRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Country with id " + id + " not found"));
    }
}
