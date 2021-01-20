package com.mobius.surya.androidtask;

import java.util.List;

/**
 * Created by Surya Pavan on 19,January,2021
 */
public class CouponDetails {



    String id;
    String valid_until;
    String ribbon_msg;
    int wager_to_release_ratio_numerator;
    int wager_to_release_ratio_denominator;
    int wager_bonus_expiry;
    String code;
    List<Slabs> slabsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValid_until() {
        return valid_until;
    }

    public void setValid_until(String valid_until) {
        this.valid_until = valid_until;
    }

    public String getRibbon_msg() {
        return ribbon_msg;
    }

    public void setRibbon_msg(String ribbon_msg) {
        this.ribbon_msg = ribbon_msg;
    }

    public int getWager_to_release_ratio_numerator() {
        return wager_to_release_ratio_numerator;
    }

    public void setWager_to_release_ratio_numerator(int wager_to_release_ratio_numerator) {
        this.wager_to_release_ratio_numerator = wager_to_release_ratio_numerator;
    }

    public int getWager_to_release_ratio_denominator() {
        return wager_to_release_ratio_denominator;
    }

    public void setWager_to_release_ratio_denominator(int wager_to_release_ratio_denominator) {
        this.wager_to_release_ratio_denominator = wager_to_release_ratio_denominator;
    }

    public int getWager_bonus_expiry() {
        return wager_bonus_expiry;
    }

    public void setWager_bonus_expiry(int wager_bonus_expiry) {
        this.wager_bonus_expiry = wager_bonus_expiry;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Slabs> getSlabsList() {
        return slabsList;
    }

    public void setSlabsList(List<Slabs> slabsList) {
        this.slabsList = slabsList;
    }
}
