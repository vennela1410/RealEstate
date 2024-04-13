package com.softwareverification.realestate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softwareverification.realestate.entity.HomeEntity;

public interface HomeRepository extends JpaRepository<HomeEntity, Integer>{

	
}
