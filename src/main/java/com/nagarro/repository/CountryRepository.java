package com.nagarro.repository;

import com.nagarro.model.Country;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
