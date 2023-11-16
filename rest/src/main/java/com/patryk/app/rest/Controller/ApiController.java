package com.patryk.app.rest.Controller;

import com.patryk.app.rest.Model.User;
import com.patryk.app.rest.Repository.UsersRepository;
import com.patryk.app.rest.Service.RegistrationDAO;
import com.patryk.app.rest.Service.RegistrationService;
import com.patryk.app.rest.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Map;


@Controller
@AllArgsConstructor
public class ApiController {
    
    private final RegistrationService registrationService;
    private final PaginationService paginationService;
    private final UploadMemeService uploadMemeService;
    private final AdminPanelService adminPanelService;

    private static final String MAIN_PAGE = "mainPage";
    private static final String RANDOM_PAGE = "randomPage";
    private static final String REGISTER_PAGE_FORM = "registerForm";
    private static final String SIGN_IN_PAGE_FORM = "signInForm";
    private static final String TERMS_AND_CONDITIONS_FORM = "termsAndConditions";
    private static final String POST_MEME_FORM = "postMemeForm";
    private static final String ADMIN_PANEL_USERS_LIST_PAGE = "admin_usersList";
    private static final String USER_EDIT_PAGE = "userEdit";
    private static final String USER_EDIT_ERROR_PAGE = "userEditError";

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
        adminPanelService.showUsersList(model);
        return ADMIN_PANEL_USERS_LIST_PAGE;
    }

    @GetMapping(value = "/admin/users/edit/{user_id}")
    public String userEdit(@PathVariable long user_id, Model model) {
        OperationResultDAO result = adminPanelService.showUser(user_id, model);
        if(result.isSucess()) {
            return USER_EDIT_PAGE;
        }
        else {
            return USER_EDIT_ERROR_PAGE;
        }
    }

    @PostMapping(value = "/admin_users_status_update")
    public String handleAdminActions(@RequestParam("id") long userId,
                                     @RequestParam("unlock") boolean unlock,
                                     Model model
                                     ) {

        adminPanelService.updateUserStatus(userId, unlock, model);
        return "redirect:/admin/users/edit/" + userId;
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
