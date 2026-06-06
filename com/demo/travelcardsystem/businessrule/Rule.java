package com.demo.travelcardsystem.businessrule;

import com.demo.travelcardsystem.constant.TransportType;
import com.demo.travelcardsystem.entity.Journey;
import com.demo.travelcardsystem.entity.ZonePair;
import java.util.HashSet;
import java.util.Set;
import lombok.Generated;

public class Rule {
   private TransportType transportType;
   private Double chargeableFare;
   private final Set<ZonePair> zonePairSet = new HashSet();

   public synchronized void addZonePair(ZonePair zonePair) {
      this.zonePairSet.add(zonePair);
   }

   public boolean isRuleSatisfied(Journey journey) {
      return this.zonePairSet.stream().anyMatch((zonePair) -> zonePair.checkIfJourneyMatchToThisZonePair(journey)) || journey.getTransportType().equals(this.getTransportType()) && this.zonePairSet.isEmpty();
   }

   @Generated
   public Rule() {
   }

   @Generated
   public TransportType getTransportType() {
      return this.transportType;
   }

   @Generated
   public Double getChargeableFare() {
      return this.chargeableFare;
   }

   @Generated
   public Set<ZonePair> getZonePairSet() {
      return this.zonePairSet;
   }

   @Generated
   public void setTransportType(final TransportType transportType) {
      this.transportType = transportType;
   }

   @Generated
   public void setChargeableFare(final Double chargeableFare) {
      this.chargeableFare = chargeableFare;
   }

   @Generated
   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof Rule)) {
         return false;
      } else {
         Rule other = (Rule)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$chargeableFare = this.getChargeableFare();
            Object other$chargeableFare = other.getChargeableFare();
            if (this$chargeableFare == null) {
               if (other$chargeableFare != null) {
                  return false;
               }
            } else if (!this$chargeableFare.equals(other$chargeableFare)) {
               return false;
            }

            Object this$transportType = this.getTransportType();
            Object other$transportType = other.getTransportType();
            if (this$transportType == null) {
               if (other$transportType != null) {
                  return false;
               }
            } else if (!this$transportType.equals(other$transportType)) {
               return false;
            }

            Object this$zonePairSet = this.getZonePairSet();
            Object other$zonePairSet = other.getZonePairSet();
            if (this$zonePairSet == null) {
               if (other$zonePairSet != null) {
                  return false;
               }
            } else if (!this$zonePairSet.equals(other$zonePairSet)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(final Object other) {
      return other instanceof Rule;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $chargeableFare = this.getChargeableFare();
      result = result * 59 + ($chargeableFare == null ? 43 : $chargeableFare.hashCode());
      Object $transportType = this.getTransportType();
      result = result * 59 + ($transportType == null ? 43 : $transportType.hashCode());
      Object $zonePairSet = this.getZonePairSet();
      result = result * 59 + ($zonePairSet == null ? 43 : $zonePairSet.hashCode());
      return result;
   }

   @Generated
   public String toString() {
      String var10000 = String.valueOf(this.getTransportType());
      return "Rule(transportType=" + var10000 + ", chargeableFare=" + this.getChargeableFare() + ", zonePairSet=" + String.valueOf(this.getZonePairSet()) + ")";
   }
}
