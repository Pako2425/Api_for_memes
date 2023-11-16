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
    private final BCryptPasswordEncoder B_CRYPT_PASSWORD_ENCODER;

    public User save(RegistrationDAO registrationDAO) throws IllegalStateException {
        User user = new User(registrationDAO.getName(),
                registrationDAO.getEmail(),
                B_CRYPT_PASSWORD_ENCODER.encode(registrationDAO.getPassword()),
                UserRole.USER);

        return usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return usersRepository.findByName(userName).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, userName)));
    }
}
