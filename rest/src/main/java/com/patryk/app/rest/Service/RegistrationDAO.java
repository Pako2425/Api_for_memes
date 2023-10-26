package com.patryk.app.rest.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@AllArgsConstructor
public class RegistrationDAO {
    private final String email;
    private final String name;
    private final String password;
    private final String repeatedPassword;
}
