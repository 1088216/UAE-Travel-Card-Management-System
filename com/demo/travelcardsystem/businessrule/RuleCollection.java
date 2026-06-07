// Source code is decompiled from a .class file using FernFlower decompiler (from Intellij IDEA).
package com.demo.travelcardsystem.businessrule;

import java.util.HashSet;
import java.util.Set;
import lombok.Generated;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class RuleCollection {
   private Double maxFare;
   private Set<Rule> rules = new HashSet();

   public void addRules(Rule rule) {
      this.rules.add(rule);
   }

   @Generated
   public RuleCollection() {
   }

   @Generated
   public Double getMaxFare() {
      return this.maxFare;
   }

   @Generated
   public Set<Rule> getRules() {
      return this.rules;
   }

   @Generated
   public void setMaxFare(final Double maxFare) {
      this.maxFare = maxFare;
   }

   @Generated
   public void setRules(final Set<Rule> rules) {
      this.rules = rules;
   }

   @Generated
   public boolean equals(final Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof RuleCollection)) {
         return false;
      } else {
         RuleCollection other = (RuleCollection)o;
         if (!other.canEqual(this)) {
            return false;
         } else {
            Object this$maxFare = this.getMaxFare();
            Object other$maxFare = other.getMaxFare();
            if (this$maxFare == null) {
               if (other$maxFare != null) {
                  return false;
               }
            } else if (!this$maxFare.equals(other$maxFare)) {
               return false;
            }

            Object this$rules = this.getRules();
            Object other$rules = other.getRules();
            if (this$rules == null) {
               if (other$rules != null) {
                  return false;
               }
            } else if (!this$rules.equals(other$rules)) {
               return false;
            }

            return true;
         }
      }
   }

   @Generated
   protected boolean canEqual(final Object other) {
      return other instanceof RuleCollection;
   }

   @Generated
   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      Object $maxFare = this.getMaxFare();
      result = result * 59 + ($maxFare == null ? 43 : $maxFare.hashCode());
      Object $rules = this.getRules();
      result = result * 59 + ($rules == null ? 43 : $rules.hashCode());
      return result;
   }

   @Generated
   public String toString() {
      Double var10000 = this.getMaxFare();
      return "RuleCollection(maxFare=" + var10000 + ", rules=" + String.valueOf(this.getRules()) + ")";
   }
}
