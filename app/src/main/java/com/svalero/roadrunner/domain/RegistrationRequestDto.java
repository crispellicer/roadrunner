package com.svalero.roadrunner.domain;

public class RegistrationRequestDto {
    private long raceId;
    private long userId;

    public long getRaceId() {
        return raceId;
    }

    public void setRaceId(long raceId) {
        this.raceId = raceId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RegistrationRequestDto(long raceId, long userId) {
        this.raceId = raceId;
        this.userId = userId;
    }

}
