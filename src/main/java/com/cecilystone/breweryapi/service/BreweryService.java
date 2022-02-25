package com.cecilystone.breweryapi.service;

import com.cecilystone.breweryapi.model.Brewery;
import org.springframework.http.ResponseEntity;

public interface BreweryService {

    ResponseEntity<Brewery[]> searchBreweries(String query);

    ResponseEntity<Brewery> getBrewery(String id);

}