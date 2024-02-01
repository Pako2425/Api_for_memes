package com.patryk.app.webapp.Service;

import com.patryk.app.webapp.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@AllArgsConstructor
public class SecurityService {
    public void authenticate(Authentication authentication, Model model) throws NullPointerException {
        boolean isAuthenticated = authentication != null && authentication.isAuthenticated();
        if(isAuthenticated) {
            long userId = ((User)authentication.getPrincipal()).getId();
            model.addAttribute("userId", userId);
        }
        model.addAttribute("isAuthenticated", isAuthenticated);
    }
}
