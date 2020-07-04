package com.lilas.telcellandroidtest.service;

import com.lilas.telcellandroidtest.model.Person;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofiteAPIInterface {
    @GET("/getAll")
    Call<List<Person>> getCurrentWeather();
}
