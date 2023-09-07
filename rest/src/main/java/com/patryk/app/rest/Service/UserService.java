package com.patryk.app.rest.Service;

import com.patryk.app.rest.Model.UserRole;
import com.patryk.app.rest.Model.User;
import com.patryk.app.rest.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final String USER_NOT_FOUND_MSG = "user with email %s not found.";
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public User save(RegistrationRequest registrationRequest) {

        User user = new User(registrationRequest.getName(),
                registrationRequest.getEmail(),
                registrationRequest.getPassword(),
                UserRole.USER);

        boolean userExist = usersRepository.findByEmail(user.getEmail()).isPresent();
        if(userExist) {
            throw new IllegalStateException("Email already exist");
        }

        boolean nameExist = usersRepository.findByName(user.getName()).isPresent();
        if(nameExist) {
            throw new IllegalStateException("Name exist");
        }

        //boolean passwordCorrect = user.getPassword().equals(registrationRequest.getRepeatPassword());
        //if(passwordCorrect) {
        //    user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        //}
        //else {
        //    throw new IllegalStateException("Password not correct");
        //}

        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usersRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}


