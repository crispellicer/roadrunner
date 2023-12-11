package com.svalero.roadrunner.api;

import com.svalero.roadrunner.domain.Race;
import com.svalero.roadrunner.domain.Registration;
import com.svalero.roadrunner.domain.RegistrationRequestDto;
import com.svalero.roadrunner.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RoadRunnerApiInterface {

    // Races
    @GET("races")
    Call<List<Race>> getRaces();

    @GET("races/{raceId}")
    Call<Race> getRace(@Path("raceId") long raceId);

    @POST("races")
    Call<Race> addRace(@Body Race race);

    @DELETE("races/{raceId}")
    Call<Void> deleteRace(@Path("raceId") long raceId);

    @PUT("/races/{raceId}")
    Call<Race> modifyRace(@Path("raceId") long id, @Body Race race);


    // Registrations
    @GET("users/{userId}/registrations")
    Call<List<Registration>> getRegistrationsByUser(@Path("userId") long userId);

    @POST("races/{raceId}/registrations")
    Call<Registration> addRegistration(@Path("raceId") long raceId, @Body long userId);

    @DELETE("registrations/{registrationId}")
    Call<Void> deleteRegistration(@Path("registrationId") long registrationId);

    //Users
    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") long userId);
}
