package com.example.geoservice.service;

import com.example.geoservice.data.CountryRepository;
import com.example.geoservice.data.entity.CountryEntity;
import com.example.geoservice.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<CountryEntity> getAllCountries() {
        return countryRepository.findAll();
    }

    @Transactional
    public CountryEntity addCountry(Country country) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCountryName(country.countryName());
        countryEntity.setCountryCode(country.countryCode());
        countryEntity.setC(country.C());
        return countryRepository.save(countryEntity);
    }

    @Transactional
    public CountryEntity updateCountryName(String countryCode, String newName) {
        Optional<CountryEntity> countryEntityOptional = countryRepository.findByCountryCode(countryCode);
        if (countryEntityOptional.isPresent()) {
            CountryEntity countryEntity = countryEntityOptional.get();
            countryEntity.setCountryName(newName);
            return countryRepository.save(countryEntity);
        }
        throw new RuntimeException("Country with code " + countryCode + " not found.");
    }
}
