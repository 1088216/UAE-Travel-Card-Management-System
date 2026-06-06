package com.demo.travelcardsystem.service.util;

import com.demo.travelcardsystem.businessrule.Rule;
import com.demo.travelcardsystem.businessrule.TravelStrategy;
import com.demo.travelcardsystem.entity.Journey;
import java.util.Comparator;
import java.util.function.Predicate;
import lombok.Generated;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class FareCalculator {
   private @NonNull TravelStrategy travelStrategy;
   private Comparator<Rule> ruleComparator = (firstRule, secondRule) -> {
      if (firstRule.getChargeableFare() < secondRule.getChargeableFare()) {
         return -1;
      } else {
         return firstRule.getChargeableFare() > secondRule.getChargeableFare() ? 1 : 0;
      }
   };

  public Double calculate(Journey journey) {
        Predicate<Rule> rulePredicate = rule -> rule.isRuleSatisfied(journey);

        // Safely figure out which rule will be applicable using orElseThrow
        Rule applicableRule = travelStrategy.getRuleCollection().getRules()
                .stream()
                .filter(rulePredicate)
                .min(ruleComparator)
                .orElseThrow(() -> new IllegalStateException("No applicable fare rule found for this journey"));

        // finally, return the chargeable fare
        return applicableRule.getChargeableFare();
    }

   @Generated
   public @NonNull TravelStrategy getTravelStrategy() {
      return this.travelStrategy;
   }

   @Generated
   public Comparator<Rule> getRuleComparator() {
      return this.ruleComparator;
   }

   @Generated
   public void setTravelStrategy(final @NonNull TravelStrategy travelStrategy) {
      if (travelStrategy == null) {
         throw new NullPointerException("travelStrategy is marked non-null but is null");
      } else {
         this.travelStrategy = travelStrategy;
      }
   }

   @Generated
   public void setRuleComparator(final Comparator<Rule> ruleComparator) {
      this.ruleComparator = ruleComparator;
   }

   @Generated
   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof FareCalculator)) {
         return false;
      } else {
         FareCalculator other = (FareCalculator)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$travelStrategy = this.getTravelStrategy();
            Object other$travelStrategy = other.getTravelStrategy();
            if (this$travelStrategy == null) {
               if (other$travelStrategy != null) {
                  return false;
               }
            } else if (!this$travelStrategy.equals(other$travelStrategy)) {
               return false;
            }

            Object this$ruleComparator = this.getRuleComparator();
            Object other$ruleComparator = other.getRuleComparator();
            if (this$ruleComparator == null) {
               if (other$ruleComparator != null) {
                  return false;
               }
            } else if (!this$ruleComparator.equals(other$ruleComparator)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(final Object other) {
      return other instanceof FareCalculator;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $travelStrategy = this.getTravelStrategy();
      result = result * 59 + ($travelStrategy == null ? 43 : $travelStrategy.hashCode());
      Object $ruleComparator = this.getRuleComparator();
      result = result * 59 + ($ruleComparator == null ? 43 : $ruleComparator.hashCode());
      return result;
   }

   @Generated
   public String toString() {
      String var10000 = String.valueOf(this.getTravelStrategy());
      return "FareCalculator(travelStrategy=" + var10000 + ", ruleComparator=" + String.valueOf(this.getRuleComparator()) + ")";
   }

   @Generated
   public FareCalculator(final @NonNull TravelStrategy travelStrategy) {
      if (travelStrategy == null) {
         throw new NullPointerException("travelStrategy is marked non-null but is null");
      } else {
         this.travelStrategy = travelStrategy;
      }
   }
}
