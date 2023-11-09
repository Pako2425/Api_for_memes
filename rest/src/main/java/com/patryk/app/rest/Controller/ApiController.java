package com.patryk.app.rest.Controller;

import com.patryk.app.rest.Model.Meme;
import com.patryk.app.rest.Model.User;
import com.patryk.app.rest.Repository.MemesRepository;
import com.patryk.app.rest.Repository.UsersRepository;
import com.patryk.app.rest.Service.RegistrationDAO;
import com.patryk.app.rest.Service.RegistrationService;
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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
@AllArgsConstructor
public class ApiController {

    private final RegistrationService REGISTRATION_SERVICE;
    private final MemesRepository MEMES_REPOSITORY;
    private final UsersRepository USERS_REPOSITORY;

    private final String MAIN_PAGE = "mainPage";
    private final String RANDOM_PAGE = "randomPage";
    private final String REGISTER_PAGE_FORM = "registerForm";
    private final String SIGN_IN_PAGE_FORM = "signInForm";
    private final String TERMS_AND_CONDITIONS_FORM = "termsAndConditions";
    private final String REGISTER_SUCCEEDED_FORM = "registrationSucceeded";
    private final String EMAIL_ALREADY_IN_USE_FORM = "emailAlreadyInUse";
    private final String NAME_ALREADY_IN_USE_FORM = "nameAlreadyInUse";
    private final String WRONG_PASSWORD_FORM = "wrongPassword";
    private final String SOMETHING_WENT_WRONG = "somethingWentWrong";

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

    @GetMapping(value = "/")
    public String showMainPage(@RequestParam(defaultValue = "0", name="page") int page, Model model) {

        int pageSize = 1;
        Page<Meme> memesPage = MEMES_REPOSITORY.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC, "id"));

        int totalPages = memesPage.getTotalPages();

        int maxPageLinks = 5;
        int firstPage = Math.max(0, page - (maxPageLinks / 2));
        int lastPage = Math.min(totalPages - 1, firstPage + (maxPageLinks - 1));
        firstPage = Math.max(0, lastPage - maxPageLinks + 1);

        model.addAttribute("memes", memesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("lastPage", lastPage);

        return MAIN_PAGE;
    }

    @GetMapping(value = "/random")
    public String showRandomPage(Model model) {
        String USER_NOT_FOUND_MSG = "user with name %s not found.";

        long totalElements = MEMES_REPOSITORY.count();
        long randomMemeId = ThreadLocalRandom.current().nextLong(totalElements);
        Page<Meme> randomMemePage = MEMES_REPOSITORY.findAll(PageRequest.of(Long.valueOf(randomMemeId).intValue(), 1));
        model.addAttribute("randomMeme", randomMemePage.getContent());

        return "randomPage";
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
