package com.patryk.app.rest.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RegistrationDAO {
    private String email;
    private String name;
    private String password;
    private String repeatedPassword;
}
