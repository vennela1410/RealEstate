package com.softwareverification.realestate.repository;

import com.softwareverification.realestate.entity.HomeOwnerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeOwnersRepository extends JpaRepository<HomeOwnerEntity, Integer>{

}
