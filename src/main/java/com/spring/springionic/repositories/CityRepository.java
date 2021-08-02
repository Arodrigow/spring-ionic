package com.spring.springionic.repositories;

import java.util.List;

import com.spring.springionic.domain.City;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{
    
    @Transactional(readOnly = true)
    @Query("SELECT obj FROM City obj WHERE obj.state.id = :state_id ORDER BY obj.name")
    public List<City> findCities(@Param("state_id") Integer state_id);
}
