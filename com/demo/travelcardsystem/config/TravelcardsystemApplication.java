package com.demo.travelcardsystem.config;

import com.demo.travelcardsystem.businessrule.RuleCollection;
import com.demo.travelcardsystem.businessrule.TravelStrategy;
import com.demo.travelcardsystem.constant.Zone;
import com.demo.travelcardsystem.entity.Station;
import com.demo.travelcardsystem.entity.TravelCard;
import com.demo.travelcardsystem.entity.TravelCardObserver;
import com.demo.travelcardsystem.repository.InMemoryCardTransactionRepository;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
   scanBasePackages = {"com.demo.travelcardsystem"}
)
public class TravelcardsystemApplication {
   public TravelcardsystemApplication() {
   }

   public static void main(String[] args) {
      SpringApplication.run(TravelcardsystemApplication.class, args);
   }

   @Bean
   public TravelCard getTravelCard(TravelCardObserver travelCardObserver) {
      TravelCard travelCard = new TravelCard();
      travelCard.registerObserver(travelCardObserver);
      return travelCard;
   }

   @Bean
   public RuleCollection loadAllTravelStrategy(TravelStrategy travelStrategy) {
      return travelStrategy.loadAllBusinessRules();
   }

   @Bean
   public Boolean loadAllStation(InMemoryCardTransactionRepository inMemoryCardTransactionRepository) {
      Set<Station> stations = new HashSet();
      stations.add(new Station("Algubaiba", new HashSet(Arrays.asList(Zone.ONE))));
      stations.add(new Station("Jumeirah", new HashSet(Arrays.asList(Zone.ONE, Zone.TWO))));
      stations.add(new Station("Bur Dubai", new HashSet(Arrays.asList(Zone.THREE))));
      stations.add(new Station("Deirah", new HashSet(Arrays.asList(Zone.TWO))));
      return inMemoryCardTransactionRepository.addAllStationsToStationStore(stations);
   }

   @Bean
   public Boolean loadInitialCards(InMemoryCardTransactionRepository inMemoryCardTransactionRepository) {
      TravelCard firstTravelCard = new TravelCard();
      firstTravelCard.setCardNumber("A101");
      firstTravelCard.setBalance((double)30.0F);
      TravelCard secondTravelCard = new TravelCard();
      secondTravelCard.setCardNumber("B201");
      secondTravelCard.setBalance((double)50.0F);
      inMemoryCardTransactionRepository.registerNewCard(firstTravelCard);
      inMemoryCardTransactionRepository.registerNewCard(secondTravelCard);
      return true;
   }
}
