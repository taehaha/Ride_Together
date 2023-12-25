package com.ridet.ridetogether.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchOpenResponseDTO {
    private Boolean success;
    private int numOfRide;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public int getNumOfRide() {
        return numOfRide;
    }

    public void setNumOfRide(int numOfRide) {
        this.numOfRide = numOfRide;
    }
}
