package com.example.demo_BookMyShow.controllers;

import com.example.demo_BookMyShow.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class PersonController {

    private PersonService personService;

    public String createPerson(@NonNull final String name) {
        return personService.createPerson(name).getPersonId();
    }

}
