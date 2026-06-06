package com.demo.travelcardsystem.businessrule;

import com.demo.travelcardsystem.constant.TransportType;
import com.demo.travelcardsystem.constant.Zone;
import com.demo.travelcardsystem.entity.ZonePair;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lombok.Generated;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class TravelStrategy {
   private @NonNull RuleCollection ruleCollection;
   public Consumer<Double> anyWhereInZoneOneStrategy = (chargeableAmount) -> {
      Rule rule = new Rule();
      rule.setChargeableFare(chargeableAmount);
      ZonePair zonePair = new ZonePair(Zone.ONE, Zone.ONE);
      rule.addZonePair(zonePair);
      this.ruleCollection.addRules(rule);
   };
   public Consumer<Double> anyOneZoneOutsideZoneOneStrategy = (chargeableAmount) -> {
      Rule rule = new Rule();
      rule.setChargeableFare(chargeableAmount);
      rule.addZonePair(new ZonePair(Zone.TWO, Zone.TWO));
      rule.addZonePair(new ZonePair(Zone.THREE, Zone.THREE));
      this.ruleCollection.addRules(rule);
   };
   public Consumer<Double> anyTwoZoneIncludingZoneOneStrategy = (chargeableAmount) -> {
      Rule rule = new Rule();
      rule.setChargeableFare(chargeableAmount);
      rule.addZonePair(new ZonePair(Zone.ONE, Zone.TWO));
      rule.addZonePair(new ZonePair(Zone.TWO, Zone.ONE));
      rule.addZonePair(new ZonePair(Zone.ONE, Zone.THREE));
      rule.addZonePair(new ZonePair(Zone.THREE, Zone.ONE));
      this.ruleCollection.addRules(rule);
   };
   public Consumer<Double> anyTwoZoneExcludingZoneOneStrategy = (chargeableAmount) -> {
      Rule rule = new Rule();
      rule.setChargeableFare(chargeableAmount);
      rule.addZonePair(new ZonePair(Zone.TWO, Zone.THREE));
      rule.addZonePair(new ZonePair(Zone.THREE, Zone.TWO));
      this.ruleCollection.addRules(rule);
   };
   public Consumer<Double> anyThreeZoneStrategy = (chargeableAmount) -> {
      Rule rule = new Rule();
      rule.setChargeableFare(chargeableAmount);
      this.ruleCollection.addRules(rule);
   };
   public BiConsumer<Double, TransportType> anyJourneyByBus = (chargeableAmount, transType) -> {
      Rule rule = new Rule();
      rule.setChargeableFare(chargeableAmount);
      rule.setTransportType(transType);
      this.ruleCollection.addRules(rule);
   };

   public RuleCollection loadAllBusinessRules() {
      this.anyWhereInZoneOneStrategy.accept((double)2.5F);
      this.anyOneZoneOutsideZoneOneStrategy.accept((double)2.0F);
      this.anyTwoZoneIncludingZoneOneStrategy.accept((double)3.0F);
      this.anyTwoZoneExcludingZoneOneStrategy.accept((double)2.25F);
      this.anyThreeZoneStrategy.accept(3.2);
      this.anyJourneyByBus.accept(1.8, TransportType.BUS);
      this.ruleCollection.setMaxFare(3.2);
      return this.ruleCollection;
   }

   @Generated
   public @NonNull RuleCollection getRuleCollection() {
      return this.ruleCollection;
   }

   @Generated
   public Consumer<Double> getAnyWhereInZoneOneStrategy() {
      return this.anyWhereInZoneOneStrategy;
   }

   @Generated
   public Consumer<Double> getAnyOneZoneOutsideZoneOneStrategy() {
      return this.anyOneZoneOutsideZoneOneStrategy;
   }

   @Generated
   public Consumer<Double> getAnyTwoZoneIncludingZoneOneStrategy() {
      return this.anyTwoZoneIncludingZoneOneStrategy;
   }

   @Generated
   public Consumer<Double> getAnyTwoZoneExcludingZoneOneStrategy() {
      return this.anyTwoZoneExcludingZoneOneStrategy;
   }

   @Generated
   public Consumer<Double> getAnyThreeZoneStrategy() {
      return this.anyThreeZoneStrategy;
   }

   @Generated
   public BiConsumer<Double, TransportType> getAnyJourneyByBus() {
      return this.anyJourneyByBus;
   }

   @Generated
   public void setRuleCollection(final @NonNull RuleCollection ruleCollection) {
      if (ruleCollection == null) {
         throw new NullPointerException("ruleCollection is marked non-null but is null");
      } else {
         this.ruleCollection = ruleCollection;
      }
   }

   @Generated
   public void setAnyWhereInZoneOneStrategy(final Consumer<Double> anyWhereInZoneOneStrategy) {
      this.anyWhereInZoneOneStrategy = anyWhereInZoneOneStrategy;
   }

   @Generated
   public void setAnyOneZoneOutsideZoneOneStrategy(final Consumer<Double> anyOneZoneOutsideZoneOneStrategy) {
      this.anyOneZoneOutsideZoneOneStrategy = anyOneZoneOutsideZoneOneStrategy;
   }

   @Generated
   public void setAnyTwoZoneIncludingZoneOneStrategy(final Consumer<Double> anyTwoZoneIncludingZoneOneStrategy) {
      this.anyTwoZoneIncludingZoneOneStrategy = anyTwoZoneIncludingZoneOneStrategy;
   }

   @Generated
   public void setAnyTwoZoneExcludingZoneOneStrategy(final Consumer<Double> anyTwoZoneExcludingZoneOneStrategy) {
      this.anyTwoZoneExcludingZoneOneStrategy = anyTwoZoneExcludingZoneOneStrategy;
   }

   @Generated
   public void setAnyThreeZoneStrategy(final Consumer<Double> anyThreeZoneStrategy) {
      this.anyThreeZoneStrategy = anyThreeZoneStrategy;
   }

   @Generated
   public void setAnyJourneyByBus(final BiConsumer<Double, TransportType> anyJourneyByBus) {
      this.anyJourneyByBus = anyJourneyByBus;
   }

   @Generated
   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof TravelStrategy)) {
         return false;
      } else {
         TravelStrategy other = (TravelStrategy)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$ruleCollection = this.getRuleCollection();
            Object other$ruleCollection = other.getRuleCollection();
            if (this$ruleCollection == null) {
               if (other$ruleCollection != null) {
                  return false;
               }
            } else if (!this$ruleCollection.equals(other$ruleCollection)) {
               return false;
            }

            Object this$anyWhereInZoneOneStrategy = this.getAnyWhereInZoneOneStrategy();
            Object other$anyWhereInZoneOneStrategy = other.getAnyWhereInZoneOneStrategy();
            if (this$anyWhereInZoneOneStrategy == null) {
               if (other$anyWhereInZoneOneStrategy != null) {
                  return false;
               }
            } else if (!this$anyWhereInZoneOneStrategy.equals(other$anyWhereInZoneOneStrategy)) {
               return false;
            }

            Object this$anyOneZoneOutsideZoneOneStrategy = this.getAnyOneZoneOutsideZoneOneStrategy();
            Object other$anyOneZoneOutsideZoneOneStrategy = other.getAnyOneZoneOutsideZoneOneStrategy();
            if (this$anyOneZoneOutsideZoneOneStrategy == null) {
               if (other$anyOneZoneOutsideZoneOneStrategy != null) {
                  return false;
               }
            } else if (!this$anyOneZoneOutsideZoneOneStrategy.equals(other$anyOneZoneOutsideZoneOneStrategy)) {
               return false;
            }

            Object this$anyTwoZoneIncludingZoneOneStrategy = this.getAnyTwoZoneIncludingZoneOneStrategy();
            Object other$anyTwoZoneIncludingZoneOneStrategy = other.getAnyTwoZoneIncludingZoneOneStrategy();
            if (this$anyTwoZoneIncludingZoneOneStrategy == null) {
               if (other$anyTwoZoneIncludingZoneOneStrategy != null) {
                  return false;
               }
            } else if (!this$anyTwoZoneIncludingZoneOneStrategy.equals(other$anyTwoZoneIncludingZoneOneStrategy)) {
               return false;
            }

            Object this$anyTwoZoneExcludingZoneOneStrategy = this.getAnyTwoZoneExcludingZoneOneStrategy();
            Object other$anyTwoZoneExcludingZoneOneStrategy = other.getAnyTwoZoneExcludingZoneOneStrategy();
            if (this$anyTwoZoneExcludingZoneOneStrategy == null) {
               if (other$anyTwoZoneExcludingZoneOneStrategy != null) {
                  return false;
               }
            } else if (!this$anyTwoZoneExcludingZoneOneStrategy.equals(other$anyTwoZoneExcludingZoneOneStrategy)) {
               return false;
            }

            Object this$anyThreeZoneStrategy = this.getAnyThreeZoneStrategy();
            Object other$anyThreeZoneStrategy = other.getAnyThreeZoneStrategy();
            if (this$anyThreeZoneStrategy == null) {
               if (other$anyThreeZoneStrategy != null) {
                  return false;
               }
            } else if (!this$anyThreeZoneStrategy.equals(other$anyThreeZoneStrategy)) {
               return false;
            }

            Object this$anyJourneyByBus = this.getAnyJourneyByBus();
            Object other$anyJourneyByBus = other.getAnyJourneyByBus();
            if (this$anyJourneyByBus == null) {
               if (other$anyJourneyByBus != null) {
                  return false;
               }
            } else if (!this$anyJourneyByBus.equals(other$anyJourneyByBus)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(final Object other) {
      return other instanceof TravelStrategy;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $ruleCollection = this.getRuleCollection();
      result = result * 59 + ($ruleCollection == null ? 43 : $ruleCollection.hashCode());
      Object $anyWhereInZoneOneStrategy = this.getAnyWhereInZoneOneStrategy();
      result = result * 59 + ($anyWhereInZoneOneStrategy == null ? 43 : $anyWhereInZoneOneStrategy.hashCode());
      Object $anyOneZoneOutsideZoneOneStrategy = this.getAnyOneZoneOutsideZoneOneStrategy();
      result = result * 59 + ($anyOneZoneOutsideZoneOneStrategy == null ? 43 : $anyOneZoneOutsideZoneOneStrategy.hashCode());
      Object $anyTwoZoneIncludingZoneOneStrategy = this.getAnyTwoZoneIncludingZoneOneStrategy();
      result = result * 59 + ($anyTwoZoneIncludingZoneOneStrategy == null ? 43 : $anyTwoZoneIncludingZoneOneStrategy.hashCode());
      Object $anyTwoZoneExcludingZoneOneStrategy = this.getAnyTwoZoneExcludingZoneOneStrategy();
      result = result * 59 + ($anyTwoZoneExcludingZoneOneStrategy == null ? 43 : $anyTwoZoneExcludingZoneOneStrategy.hashCode());
      Object $anyThreeZoneStrategy = this.getAnyThreeZoneStrategy();
      result = result * 59 + ($anyThreeZoneStrategy == null ? 43 : $anyThreeZoneStrategy.hashCode());
      Object $anyJourneyByBus = this.getAnyJourneyByBus();
      result = result * 59 + ($anyJourneyByBus == null ? 43 : $anyJourneyByBus.hashCode());
      return result;
   }

   @Generated
   public String toString() {
      String var10000 = String.valueOf(this.getRuleCollection());
      return "TravelStrategy(ruleCollection=" + var10000 + ", anyWhereInZoneOneStrategy=" + String.valueOf(this.getAnyWhereInZoneOneStrategy()) + ", anyOneZoneOutsideZoneOneStrategy=" + String.valueOf(this.getAnyOneZoneOutsideZoneOneStrategy()) + ", anyTwoZoneIncludingZoneOneStrategy=" + String.valueOf(this.getAnyTwoZoneIncludingZoneOneStrategy()) + ", anyTwoZoneExcludingZoneOneStrategy=" + String.valueOf(this.getAnyTwoZoneExcludingZoneOneStrategy()) + ", anyThreeZoneStrategy=" + String.valueOf(this.getAnyThreeZoneStrategy()) + ", anyJourneyByBus=" + String.valueOf(this.getAnyJourneyByBus()) + ")";
   }

   @Generated
   public TravelStrategy(final @NonNull RuleCollection ruleCollection) {
      if (ruleCollection == null) {
         throw new NullPointerException("ruleCollection is marked non-null but is null");
      } else {
         this.ruleCollection = ruleCollection;
      }
   }
}
