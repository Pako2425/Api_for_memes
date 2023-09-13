package com.patryk.app.rest.Service;

import com.patryk.app.rest.Model.UserRole;
import com.patryk.app.rest.Model.User;
import com.patryk.app.rest.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final String USER_NOT_FOUND_MSG = "user with name %s not found.";
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RegistrationDataStatus registerDataCheck(RegistrationRequest registrationRequest) {

        if(usersRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            return RegistrationDataStatus.EMAIL_ALREADY_EXIST;
        }
        else if(usersRepository.findByName(registrationRequest.getName()).isPresent()) {
            return RegistrationDataStatus.NAME_ALREADY_EXIST;
        }
        else if(!registrationRequest.getPassword().equals(registrationRequest.getRepeatPassword())) {
            return RegistrationDataStatus.PASSWORD_NOT_CORRECT;
        }
        else {
            return RegistrationDataStatus.SUCCESS;
        }
    }

    public User save(RegistrationRequest registrationRequest) throws IllegalStateException {
        User user = new User(registrationRequest.getName(),
                registrationRequest.getEmail(),
                bCryptPasswordEncoder.encode(registrationRequest.getPassword()),
                UserRole.USER);

        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return usersRepository.findByName(userName).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, userName)));
    }
}


