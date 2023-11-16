package com.patryk.app.rest.Controller;

import com.patryk.app.rest.Model.Meme;
import com.patryk.app.rest.Model.User;
import com.patryk.app.rest.Repository.MemesRepository;
import com.patryk.app.rest.Repository.UsersRepository;
import com.patryk.app.rest.Service.RegistrationDAO;
import com.patryk.app.rest.Service.RegistrationService;
import com.patryk.app.rest.Service.*;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import java.util.Map;


@Controller
@AllArgsConstructor
public class ApiController {

    private final UsersRepository USERS_REPOSITORY;

    private final RegistrationService registrationService;
    private final PaginationService paginationService;
    private final UploadMemeService uploadMemeService;

    private static final String MAIN_PAGE = "mainPage";
    private static final String RANDOM_PAGE = "randomPage";
    private static final String REGISTER_PAGE_FORM = "registerForm";
    private static final String SIGN_IN_PAGE_FORM = "signInForm";
    private static final String TERMS_AND_CONDITIONS_FORM = "termsAndConditions";
    private static final String POST_MEME_FORM = "postMemeForm";

    private static final Map<RegistrationDataStatus, String> REGISTER_PROCESS_RESPONSE_MAP = Map.of(
            RegistrationDataStatus.EMAIL_ALREADY_EXIST, "emailAlreadyInUse",
            RegistrationDataStatus.NAME_ALREADY_EXIST, "nameAlreadyInUse",
            RegistrationDataStatus.PASSWORD_NOT_CORRECT, "wrongPassword",
            RegistrationDataStatus.SUCCESS, "registrationSucceeded",
            RegistrationDataStatus.SOMETHING_WENT_WRONG, "somethingWentWrong"
    );

    @GetMapping(value = "/register")
    public String showRegisterPage() {

        return REGISTER_PAGE_FORM;
    }

    @GetMapping(value = "/sign_in")
    public String viewSignInPage() {

        return SIGN_IN_PAGE_FORM;
    }

    @GetMapping(value = "/terms_and_conditions")
    public String viewTermsAndConditionsPage() {

        return TERMS_AND_CONDITIONS_FORM;
    }

    @GetMapping(value = "/add_meme")
    public String post() {
        return POST_MEME_FORM;
    }

    @GetMapping(value = "/")
    public String showMainPage(@RequestParam(defaultValue = "0", name="page") int page, Model model) {

        paginationService.showMainPage(page, model);
        return MAIN_PAGE;
    }

    @GetMapping(value = "/random")
    public String showRandomPage(Model model) {

        paginationService.showRandomPage(model);
        return RANDOM_PAGE;
    }

    @GetMapping(value = "/admin/users")
    public String showUsersList(Model model) {
        List<User> users = USERS_REPOSITORY.findAll();
        model.addAttribute("users", users);
        return "admin_usersList";
    }

    @GetMapping(value = "/admin/users/edit/{user_id}")
    public String userEdit(@PathVariable long user_id, Model model) {
        Optional<User> user = USERS_REPOSITORY.findById(user_id);
        if(user.isPresent()) {
            model.addAttribute("user", user.get());
            return "userEdit";
        }
        return "error";
    }

    @PostMapping(value = "/admin_users_data_update")
    public String handleAdminActions(@RequestParam("id") long userId,
                                     @RequestParam("unlock") boolean unlock,
                                     Model model
                                     ) {

        User userToUpdate = USERS_REPOSITORY.getReferenceById(userId);
        if(unlock) {
            userToUpdate.setLocked(false);
        }
        else {
            userToUpdate.setLocked(true);
        }
        User updatedUser = USERS_REPOSITORY.save(userToUpdate);
        System.out.println(updatedUser.getLocked());
        model.addAttribute("user", updatedUser);
        System.out.println("Hi");
        return "userEdit";
    }


    @PostMapping(value = "/process_register")
    public String handleRegisterData(@ModelAttribute RegistrationDAO registrationDAO) {

        RegistrationDataStatus registrationDataStatus = registrationService.register(registrationDAO);
        return REGISTER_PROCESS_RESPONSE_MAP.get(registrationDataStatus);
    }

    @PostMapping(value = "/post_meme")
    public String uploadImage(@ModelAttribute UploadedMemeDAO uploadedMemeDAO)  throws IOException {
        uploadMemeService.postMeme(uploadedMemeDAO);
        return "redirect:/";
    }
}
