package com.patryk.app.rest.Controller;

import com.patryk.app.rest.Model.Meme;
import com.patryk.app.rest.Repository.MemesRepository;
import com.patryk.app.rest.Service.RegistrationDAO;
import com.patryk.app.rest.Service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class ApiController {

    private final RegistrationService REGISTRATION_SERVICE;
    private final MemesRepository MEMES_REPOSITORY;

    private final String HOME_PAGE_FORM = "homePage";
    private final String REGISTER_PAGE_FORM = "registerForm";
    private final String SIGN_IN_PAGE_FORM = "signInForm";
    private final String TERMS_AND_CONDITIONS_FORM = "termsAndConditions";
    private final String REGISTER_SUCCEEDED_FORM = "registrationSucceeded";
    private final String EMAIL_ALREADY_IN_USE_FORM = "emailAlreadyInUse";
    private final String NAME_ALREADY_IN_USE_FORM = "nameAlreadyInUse";
    private final String WRONG_PASSWORD_FORM = "wrongPassword";
    private final String SOMETHING_WENT_WRONG = "somethingWentWrong";

    @GetMapping(value = "/")
    public String showHomePage() {

        return HOME_PAGE_FORM;
    }

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
        return "postMeme";
    }

    @PostMapping(value = "/process_register")
    public String handleRegisterData(@ModelAttribute(value = "userRegisterFormData")
                                     RegistrationDAO registrationDAO) {

        return REGISTRATION_SERVICE.register(registrationDAO);
    }

    @PostMapping(value = "/post_meme")
    public String uploadImage(@RequestParam("title") String name,
                            @RequestParam("image") MultipartFile image) throws IOException {
        String filePath = "D:/memes/" + image.getOriginalFilename();
        Meme meme = new Meme();
        meme.setTitle(name);
        meme.setFilePath(filePath);

        MEMES_REPOSITORY.save(meme);

        image.transferTo(new File(filePath));

        return "homePage";
    }

}
