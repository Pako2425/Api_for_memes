package com.patryk.app.rest.Controller;

import com.patryk.app.rest.Model.Meme;
import com.patryk.app.rest.Repository.MemesRepository;
import com.patryk.app.rest.Service.RegistrationDAO;
import com.patryk.app.rest.Service.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

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

    //@GetMapping(value = "/")
    //public String showHomePage() {
    //
    //    return HOME_PAGE_FORM;
    //}

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

    /*@GetMapping(value = "/watch", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] downloadImage() throws IOException {
        String filePath = "D:/memes/a.jpg";
        return Files.readAllBytes(new File(filePath).toPath());
    }*/

    @GetMapping(value = "/")
    public String show(@RequestParam(defaultValue = "0", name="page") int page, Model model) {
        int pageSize = 10;
        Page<Meme> memesPage = MEMES_REPOSITORY.findAll(PageRequest.of(page, 1));

        model.addAttribute("memes", memesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", memesPage.getTotalPages());

        int totalPages = memesPage.getTotalPages();
        int maxPageLinks = 5;

        int firstPage = Math.max(0, page - (maxPageLinks / 2));
        int lastPage = Math.min(totalPages - 1, firstPage + (maxPageLinks - 1));
        firstPage = Math.max(0, lastPage - maxPageLinks + 1);

        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);

        return "image";
    }

    @PostMapping(value = "/process_register")
    public String handleRegisterData(@ModelAttribute(value = "userRegisterFormData")
                                     RegistrationDAO registrationDAO) {

        return REGISTRATION_SERVICE.register(registrationDAO);
    }

    @PostMapping(value = "/post_meme")
    public String uploadImage(@RequestParam("title") String name,
                            @RequestParam("image") MultipartFile image) throws IOException {
        Meme meme = new Meme();
        meme.setTitle(name);
        String filePath = "D:/memes/" + name + ".jpg";
        meme.setFilePath(filePath);

        MEMES_REPOSITORY.save(meme);

        image.transferTo(new File(filePath));

        return "homePage";
    }

}
