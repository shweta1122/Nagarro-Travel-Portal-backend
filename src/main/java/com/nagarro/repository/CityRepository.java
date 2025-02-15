package com.nagarro.repository;

import java.util.List;

import com.nagarro.model.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	
	List<City> findByStateId(Integer stateId);

}
