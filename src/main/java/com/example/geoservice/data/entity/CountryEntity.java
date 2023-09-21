package com.example.geoservice.data.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "geo")
public class CountryEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "country_name", unique = true, nullable = false)
    private String countryName;

    @Column(name = "country_code", unique = true, nullable = false, length = 2)
    private String countryCode;

    @Column(name = "C", columnDefinition = "jsonb")
    @JdbcTypeCode(SqlTypes.JSON)
    private List<List<List<List<Double>>>> C;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<List<List<List<Double>>>> getC() {
        return C;
    }

    public void setC(List<List<List<List<Double>>>> C) {
        this.C = C;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryEntity that = (CountryEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(countryName, that.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryName);
    }
}
