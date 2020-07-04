package com.lilas.telcellandroidtest.repo;

import com.lilas.telcellandroidtest.callback.GetPersonListCallback;
import com.lilas.telcellandroidtest.model.Person;
import com.lilas.telcellandroidtest.service.RetrofitClientInstance;
import com.lilas.telcellandroidtest.service.RetrofiteAPIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepo {

    public void getPersonList(GetPersonListCallback getPersonListCallback) {
        RetrofiteAPIInterface retrofiteAPIInterface = RetrofitClientInstance.getClient().create(RetrofiteAPIInterface.class);
        Call<List<Person>> call3 = retrofiteAPIInterface.getCurrentWeather();
        call3.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {

                if (response.body() != null) {
                    getPersonListCallback.onSuccess(response.body());
                } else {
                    getPersonListCallback.onFailure("Body is null");
                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                call.cancel();
                getPersonListCallback.onFailure(t.getMessage());
            }
        });
    }

}
