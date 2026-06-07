package com.demo.travelcardsystem;

import com.demo.travelcardsystem.entity.TravelCard;
import com.demo.travelcardsystem.repository.InMemoryCardTransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.stream.Stream;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

class RechargeTravelCardTests extends IntegrationTest {
   @Autowired
   private MockMvc mockMvc;
   @Autowired
   private ObjectMapper objectMapper;
   @Autowired
   private InMemoryCardTransactionRepository inMemoryCardTransactionRepository;
   @Autowired
   private TravelHelperTest travelHelperTest;

   RechargeTravelCardTests() {
   }

   private static Stream<Arguments> usersGenerator() {
      return Stream.of(Arguments.of(new Object[]{"1AE101", 30}), Arguments.of(new Object[]{"1AE102", 40}));
   }

   @BeforeEach
   public void resetRepository() {
      this.inMemoryCardTransactionRepository.clearTravelCardStore();
   }

   @DisplayName("Service is Up and Running")
   @Test
   public void check_if_ping_is_working() throws Exception {
      this.mockMvc.perform(MockMvcRequestBuilders.get("/api/card/ping", new Object[0])).andExpect(MockMvcResultMatchers.status().isOk());
   }

   @DisplayName("User try to register himself successfully")
   @ParameterizedTest
   @MethodSource({"usersGenerator"})
   public void register_user_in_the_system(String cardNumber, double amount) throws Exception {
      TravelCard travelCard = new TravelCard();
      travelCard.setCardNumber(cardNumber);
      travelCard.setBalance(amount);
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/register", new Object[0]).contentType("application/json").content(this.objectMapper.writeValueAsString(travelCard))).andExpect(MockMvcResultMatchers.status().isOk());
      AssertionsForClassTypes.assertThat(this.inMemoryCardTransactionRepository.findCardByCardNumber(cardNumber)).isEqualTo(travelCard);
   }

   @DisplayName("User try to recharge a invalid card. System throws INVALID_CARD exception")
   @Test
   public void register_user_with_invalid_card_number() throws Exception {
      TravelCard travelCard = new TravelCard();
      travelCard.setCardNumber((String)null);
      travelCard.setBalance((double)30.0F);
      String errorMsg = this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/register", new Object[0]).contentType("application/json").content(this.objectMapper.writeValueAsString(travelCard))).andExpect(MockMvcResultMatchers.status().isNotAcceptable()).andReturn().getResolvedException().getMessage();
      Assertions.assertEquals("This card is Invalid. Please use a valid card", errorMsg);
      // In user_cannot_be_registered_if_card_number_already_exist method:
    // I CHANGED THIS:
    // assertEquals(errorMsg, "This card is Invalid. Please use a valid card");
   }

   @DisplayName("Users are able to recharge the card successfully")
   @ParameterizedTest
   @MethodSource({"usersGenerator"})
   public void users_are_able_to_recharge_the_card_successfully(String cardNumber, double amount) throws Exception {
      this.travelHelperTest.directUserRegistration(cardNumber, (double)0.0F);
      this.mockMvc.perform(MockMvcRequestBuilders.post("/api/card/recharge/{rechargeAmount}", new Object[]{amount}).contentType("application/json").content(cardNumber)).andExpect(MockMvcResultMatchers.status().isOk());
      Assertions.assertEquals(amount, inMemoryCardTransactionRepository.findCardByCardNumber(cardNumber).getBalance());
    // In users_are_able_to_recharge_the_card_successfully method:
    // I CHANGED THIS:
    // assertEquals(inMemoryCardTransactionRepository.findCardByCardNumber(cardNumber).getBalance(), amount);
   }
}