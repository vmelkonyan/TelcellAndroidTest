package com.lilas.telcellandroidtest.callback;

import com.lilas.telcellandroidtest.model.Person;

import java.util.List;

public interface GetPersonListCallback {
    void onSuccess(List<Person> personList);

    void onFailure(String message);
}
