package com.demo.travelcardsystem.controller;
import com.demo.travelcardsystem.model.request.CardRegistrationRequest;
import com.demo.travelcardsystem.model.request.SwipeRequest;
import com.demo.travelcardsystem.model.response.TravelCardResponse;
import com.demo.travelcardsystem.service.TravellerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/card")
@AllArgsConstructor
@CrossOrigin
@Tag(name = "Fare Card Management API", description = "Endpoints for registering, recharging, and swiping Al-Naqel transit cards")
public class TravellerController {

    private TravellerService travellerService;

    @Operation(summary = "Check service status", description = "Pings the service to ensure it is up and running.")
    @ApiResponse(responseCode = "200", description = "Service is active")
    @GetMapping(value = "/ping")
    public String pingMe() {
        return "Service is UP and Running";
    }

    @Operation(summary = "Register a new travel card", description = "Registers a new card with an initial balance.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Card successfully registered"),
        @ApiResponse(responseCode = "406", description = "Card is invalid or already registered")
    })
    @PostMapping(value = "/register")
    public void registerNewUser(@RequestBody CardRegistrationRequest cardRegistrationRequest) {
        travellerService.registerNewCard(cardRegistrationRequest);
    }

    @Operation(summary = "Recharge an existing card", description = "Adds funds to a registered travel card.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Card successfully recharged"),
        @ApiResponse(responseCode = "406", description = "Invalid recharge amount or card not found")
    })
    @PostMapping(value = "/recharge/{rechargeAmount}")
    public void rechargeTheCard(
            @Parameter(description = "The card number to recharge", required = true) @RequestBody String cardNumber, 
            @Parameter(description = "The amount to add to the card balance", required = true) @PathVariable double rechargeAmount) {
        travellerService.rechargeTheCard(cardNumber, rechargeAmount);
    }

    @Operation(summary = "Process a card swipe", description = "Handles tapping in and tapping out at transit stations. Applies maximum fare hold on entry and calculates actual fare on exit.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Swipe processed successfully. Returns updated card and transit status."),
        @ApiResponse(responseCode = "400", description = "Invalid station or transport type provided"),
        @ApiResponse(responseCode = "406", description = "Insufficient balance for journey")
    })
    @PostMapping(value = "/swipe")
    public TravelCardResponse swipeCard(@RequestBody SwipeRequest swipeRequest) {
        return travellerService.swipeCard(swipeRequest);
    }

    @Operation(summary = "Get card details", description = "Retrieves the current balance and transit status of a specific card.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved card details"),
        @ApiResponse(responseCode = "406", description = "Card not found")
    })
    @GetMapping(value = "/{cardNumber}")
    public TravelCardResponse checkCardDetail(
            @Parameter(description = "The card number to look up", required = true) @PathVariable String cardNumber) {
        return travellerService.checkCardDetail(cardNumber);
    }

    @Operation(summary = "List all registered cards", description = "Returns a list of all card numbers currently registered in the system.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of cards")
    @GetMapping
    public List<String> fetchAllCard() {
        return travellerService.fetchAllCard();
    }
}