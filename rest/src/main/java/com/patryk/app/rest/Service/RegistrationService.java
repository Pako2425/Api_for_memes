package com.patryk.app.rest.Service;

import com.patryk.app.rest.Model.User;
import com.patryk.app.rest.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor

public class RegistrationService {

    private final UsersRepository USERS_REPOSITORY;
    private final UserService USER_SERVICE;

    private final Map<RegistrationDataStatus, String> RESPONSE_MAP = Map.of(
            RegistrationDataStatus.EMAIL_ALREADY_EXIST, "emailAlreadyInUse",
            RegistrationDataStatus.NAME_ALREADY_EXIST, "nameAlreadyInUse",
            RegistrationDataStatus.PASSWORD_NOT_CORRECT, "wrongPassword",
            RegistrationDataStatus.SUCCESS, "registerSuceeded",
            RegistrationDataStatus.SOMETHING_WENT_WRONG, "somethingWentWrong"
    );

    public RegistrationDataStatus registerDataCheck(RegistrationDAO registrationDAO) {
        List<User> users = USERS_REPOSITORY.findAllByNameOrEmail(registrationDAO.getName(), registrationDAO.getEmail());

        if(users.isEmpty()) {
            if(registrationDAO.getPassword().equals(registrationDAO.getRepeatedPassword())) {
                return RegistrationDataStatus.SUCCESS;
            }
            else {
                return RegistrationDataStatus.PASSWORD_NOT_CORRECT;
            }
        }
        else {
            if(users.stream().anyMatch(user -> user.getEmail().equals(registrationDAO.getEmail()))) {
                return RegistrationDataStatus.EMAIL_ALREADY_EXIST;
            }
            else if(users.stream().anyMatch(user -> user.getUsername().equals(registrationDAO.getName()))) {
                return RegistrationDataStatus.NAME_ALREADY_EXIST;
            }
            else {
                return RegistrationDataStatus.SOMETHING_WENT_WRONG;
            }
        }
    }

    public String register(RegistrationDAO registrationDAO) {
        RegistrationDataStatus registrationDataStatus = registerDataCheck(registrationDAO);

        if(registrationDataStatus == RegistrationDataStatus.SUCCESS) {
            USER_SERVICE.save(registrationDAO);
        }

        return RESPONSE_MAP.get(registrationDataStatus);
    }
}
