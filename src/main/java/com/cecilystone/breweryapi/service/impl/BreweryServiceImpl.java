package com.cecilystone.breweryapi.service.impl;

import com.cecilystone.breweryapi.model.Brewery;
import com.cecilystone.breweryapi.service.BreweryService;
import com.cecilystone.breweryapi.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BreweryServiceImpl implements BreweryService {
    @Override
    public ResponseEntity<Brewery[]> searchBreweries(String query) {
        RestTemplate restTemplate = new RestTemplate();
        Brewery[] breweries = restTemplate
                .getForObject(AppConstants.BREWERY_DB_CONNECTION + "search?query=" + query, Brewery[].class);

        return new ResponseEntity<>(breweries, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Brewery> getBrewery(String id) {
        RestTemplate restTemplate = new RestTemplate();
        Brewery brewery = restTemplate
                .getForObject(AppConstants.BREWERY_DB_CONNECTION + id, Brewery.class);
        return new ResponseEntity<>(brewery, HttpStatus.OK);
    }
}
