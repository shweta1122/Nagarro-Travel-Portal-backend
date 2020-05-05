package com.nagarro.repository;

import java.util.List;

import com.nagarro.model.State;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface StateRepository  extends JpaRepository<State, Integer> {
	
	List<State> findByCountryId(Integer countryId);

}
