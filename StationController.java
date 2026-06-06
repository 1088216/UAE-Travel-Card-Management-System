package com.demo.travelcardsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/stations")
@CrossOrigin
@Tag(name = "Stations API", description = "Endpoints for retrieving transit stations and zone information")
public class StationController {

    @Operation(summary = "List all transit stations", description = "Retrieves a comprehensive list of all transit stations along with their associated fare zones.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of stations")
    @GetMapping
    public List<Map<String, Object>> getAllStationsAndZones() {
        // Mock data representing the stations and their respective zones
        return Arrays.asList(
                createStation("Central Station", 1),
                createStation("Downtown Hub", 1),
                createStation("University Campus", 2),
                createStation("Airport Terminal", 3),
                createStation("Tech Park", 3)
        );
    }

    private Map<String, Object> createStation(String name, int zone) {
        Map<String, Object> station = new HashMap<>();
        station.put("stationName", name);
        station.put("zoneId", zone);
        return station;
    }
}