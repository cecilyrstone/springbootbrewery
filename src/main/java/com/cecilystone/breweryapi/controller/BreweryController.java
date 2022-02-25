package com.cecilystone.breweryapi.controller;

import com.cecilystone.breweryapi.exception.ResponseEntityErrorException;
import com.cecilystone.breweryapi.model.Brewery;
import com.cecilystone.breweryapi.payload.ApiResponse;
import com.cecilystone.breweryapi.service.BreweryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/breweries")
public class BreweryController {

    private final BreweryService breweryService;

    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @ExceptionHandler(ResponseEntityErrorException.class)
    public ResponseEntity<ApiResponse> handleExceptions(ResponseEntityErrorException exception) {
        return exception.getApiResponse();
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<Brewery[]> searchBreweries(@PathVariable(name = "query") String query) {
        return breweryService.searchBreweries(query);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Brewery> getBrewery(@PathVariable(name = "id") String id) {
        return breweryService.getBrewery(id);
    }
}
