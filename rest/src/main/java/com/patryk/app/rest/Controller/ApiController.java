package com.patryk.app.rest.Controller;

import com.patryk.app.rest.Model.Meme;
import com.patryk.app.rest.Repository.MemesRepository;
import com.patryk.app.rest.Service.PaginationService;
import com.patryk.app.rest.Service.RegistrationDAO;
import com.patryk.app.rest.Service.RegistrationService;
import com.patryk.app.rest.Service.UploadMemeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class ApiController {

    private final RegistrationService REGISTRATION_SERVICE;
    private final PaginationService PAGINATION_SERVICE;
    private final UploadMemeService UPLOAD_MEME_SERVICE;

    private final String MAIN_PAGE = "mainPage";
    private final String REGISTER_PAGE_FORM = "registerForm";
    private final String SIGN_IN_PAGE_FORM = "signInForm";
    private final String TERMS_AND_CONDITIONS_FORM = "termsAndConditions";
    private final String REGISTER_SUCCEEDED_FORM = "registrationSucceeded";
    private final String EMAIL_ALREADY_IN_USE_FORM = "emailAlreadyInUse";
    private final String NAME_ALREADY_IN_USE_FORM = "nameAlreadyInUse";
    private final String WRONG_PASSWORD_FORM = "wrongPassword";
    private final String SOMETHING_WENT_WRONG = "somethingWentWrong";
    private final String POST_MEME_FORM = "postMemeForm";


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
    public String show(@RequestParam(defaultValue = "0", name="page") int page, Model model) {

        return PAGINATION_SERVICE.showMainPage(page, model);
    }

    @PostMapping(value = "/process_register")
    public String handleRegisterData(@ModelAttribute(value = "userRegisterFormData")
                                     RegistrationDAO registrationDAO) {

        return REGISTRATION_SERVICE.register(registrationDAO);
    }

    @PostMapping(value = "/post_meme")
    public String uploadImage(@RequestParam("title") String name,
                            @RequestParam("image") MultipartFile image) throws IOException {

        return UPLOAD_MEME_SERVICE.postMeme(name, image);
    }

}
