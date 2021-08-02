package com.spring.springionic.resources;

import java.util.List;
import java.util.stream.Collectors;

import com.spring.springionic.domain.City;
import com.spring.springionic.domain.State;
import com.spring.springionic.dto.CityDTO;
import com.spring.springionic.dto.StateDTO;
import com.spring.springionic.services.CityService;
import com.spring.springionic.services.StateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/states")
public class StateResource {
    @Autowired
    private StateService service;
    @Autowired
    private CityService cityService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StateDTO>> findAll(){
        List<State> list = service.findAll();
        List<StateDTO> listDto = list.stream().map(obj -> new StateDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @RequestMapping(value="/{stateId}/cities" ,method = RequestMethod.GET)
    public ResponseEntity<List<CityDTO>> findCities(@PathVariable Integer stateId){
        List<City> list = cityService.findByState(stateId);
        List<CityDTO> listDto = list.stream().map(obj -> new CityDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

}
