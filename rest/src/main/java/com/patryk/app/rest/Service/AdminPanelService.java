package com.patryk.app.rest.Service;

import com.patryk.app.rest.Model.User;
import com.patryk.app.rest.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminPanelService {
    private final UsersRepository usersRepository;

    public void showUsersList(Model model) {
        List<User> users = usersRepository.findAll();
        model.addAttribute("users", users);
    }

    public OperationResultDAO showUser(long userId, Model model) {
        Optional<User> user = usersRepository.findById(userId);
        if(user.isEmpty()) {
            return new OperationResultDAO(false, "User dont exist.");
        }
        model.addAttribute("user", user.get());
        return new OperationResultDAO(true, "User found.");
    }

}
