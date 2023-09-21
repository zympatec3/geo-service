package com.example.geoservice.controller;

import com.example.geoservice.data.entity.CountryEntity;
import com.example.geoservice.domain.Country;
import com.example.geoservice.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAllCountries().stream()
                .map(entity -> new Country(entity.getCountryName(), entity.getCountryCode(), entity.getC()))
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        CountryEntity countryEntity = countryService.addCountry(country);
        Country createdCountry = new Country(countryEntity.getCountryName(), countryEntity.getCountryCode(), countryEntity.getC());
        return new ResponseEntity<>(createdCountry, HttpStatus.CREATED);
    }

    @PatchMapping("/{countryCode}")
    public ResponseEntity<Country> updateCountryName(@PathVariable String countryCode, @RequestParam String newName) {
        try {
            CountryEntity updatedEntity = countryService.updateCountryName(countryCode, newName);
            Country updatedCountry = new Country(updatedEntity.getCountryName(), updatedEntity.getCountryCode(), updatedEntity.getC());
            return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

