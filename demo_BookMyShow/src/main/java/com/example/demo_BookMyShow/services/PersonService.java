package com.example.demo_BookMyShow.services;

import com.example.demo_BookMyShow.Model.Person;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PersonService {

    private Map<String, Person> personMap;

    public PersonService(){
        personMap = new HashMap<>();
    }

    public Person createPerson(@NonNull final String name) {
        String personId = UUID.randomUUID().toString();
        Person person = new Person(personId, name);
        personMap.put(personId, person);
        return person;
    }

    public Person getPersonById(@NonNull final String customerId) {
        Person person = personMap.get(customerId);
        return person;
    }
}
