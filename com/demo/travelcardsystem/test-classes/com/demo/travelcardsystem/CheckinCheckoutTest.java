package com.demo.travelcardsystem;

import lombok.extern.slf4j.Slf4j;

import com.demo.travelcardsystem.constant.TransportType;
import com.demo.travelcardsystem.entity.TravelCard;
import com.demo.travelcardsystem.model.request.SwipeRequest;
import com.demo.travelcardsystem.repository.InMemoryCardTransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CheckinCheckoutTest extends IntegrationTest {
   @Autowired
   private MockMvc mockMvc;
   @Autowired
   private ObjectMapper objectMapper;
   @Autowired
   private InMemoryCardTransactionRepository inMemoryCardTransactionRepository;
   @Autowired
   private TravelHelperTest travelHelperTest;

   @Slf4j
public class CheckinCheckoutTest extends IntegrationTest {
    // ... inside the test method ...
    
    // CHANGE THIS:
    // System.out.println("Card balance after journey-2 is -> " + inMemoryCardTransactionRepository.findCardByCardNumber(travelCard.getCardNumber()).getBalance());
    
    // TO THIS:
    log.info("Card balance after journey-2 is -> {}", inMemoryCardTransactionRepository.findCardByCardNumber(travelCard.getCardNumber()).getBalance());
}
   @BeforeEach
   public void resetRepository() {
      this.inMemoryCardTransactionRepository.clearTravelCardStore();
   }

   @DisplayName("User take few trips and check balance at end of the trip")
   @Test
   public void user_take_trip_and_check_balance() throws Exception {
      TravelCard travelCard = this.travelHelperTest.directUserRegistration("1A101", (double)30.0F);
      SwipeRequest swipeRequest = null;
      swipeRequest = this.travelHelperTest.prepareSwipeRequest(travelCard.getCardNumber(), "Algubaiba", TransportType.TRAIN);
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/swipe", new Object[0]).contentType("application/json").content(this.objectMapper.writeValueAsString(swipeRequest))).andExpect(MockMvcResultMatchers.status().isOk());
      swipeRequest = this.travelHelperTest.prepareSwipeRequest(travelCard.getCardNumber(), "Jumeirah", TransportType.TRAIN);
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/swipe", new Object[0]).contentType("application/json").content(this.objectMapper.writeValueAsString(swipeRequest))).andExpect(MockMvcResultMatchers.status().isOk());
      log.info("Card balance after journey-1 is -> {}", this.inMemoryCardTransactionRepository.findCardByCardNumber(travelCard.getCardNumber()).getBalance());
      swipeRequest = this.travelHelperTest.prepareSwipeRequest(travelCard.getCardNumber(), "Jumeirah", TransportType.BUS);
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/swipe", new Object[0]).contentType("application/json").content(this.objectMapper.writeValueAsString(swipeRequest))).andExpect(MockMvcResultMatchers.status().isOk());
      swipeRequest = this.travelHelperTest.prepareSwipeRequest(travelCard.getCardNumber(), "Bur Dubai", TransportType.BUS);
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/swipe", new Object[0]).contentType("application/json").content(this.objectMapper.writeValueAsString(swipeRequest))).andExpect(MockMvcResultMatchers.status().isOk());
      log.info("Card balance after journey-2 is -> {}", this.inMemoryCardTransactionRepository.findCardByCardNumber(travelCard.getCardNumber()).getBalance());
      swipeRequest = this.travelHelperTest.prepareSwipeRequest(travelCard.getCardNumber(), "Bur Dubai", TransportType.TRAIN);
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/swipe", new Object[0]).contentType("application/json").content(this.objectMapper.writeValueAsString(swipeRequest))).andExpect(MockMvcResultMatchers.status().isOk());
      swipeRequest = this.travelHelperTest.prepareSwipeRequest(travelCard.getCardNumber(), "Deirah", TransportType.TRAIN);
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/swipe", new Object[0]).contentType("application/json").content(this.objectMapper.writeValueAsString(swipeRequest))).andExpect(MockMvcResultMatchers.status().isOk());
      this.mockMvc.perform(MockMvcRequestBuilders.get("/api/card/{cardNumber}", new Object[]{travelCard.getCardNumber()})).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.balance", new Object[0]).value("23.45"));
   }
}
