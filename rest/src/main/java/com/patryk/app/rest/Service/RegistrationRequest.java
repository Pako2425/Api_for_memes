package com.patryk.app.rest.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegistrationRequest {
    private final String email;
    private final String name;
    private final String password;
    private final String repeatPassword;
}
