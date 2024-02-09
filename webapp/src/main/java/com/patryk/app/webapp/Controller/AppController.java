package com.patryk.app.webapp.Controller;

import com.dropbox.core.DbxException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.patryk.app.webapp.Model.Like;
import com.patryk.app.webapp.Model.Meme;
import com.patryk.app.webapp.Repository.LikesRepository;
import com.patryk.app.webapp.Repository.MemesRepository;
import com.patryk.app.webapp.Service.RegistrationDAO;
import com.patryk.app.webapp.Service.RegistrationService;
import com.patryk.app.webapp.Service.*;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;


@Controller
@AllArgsConstructor
public class AppController {

    private final RegistrationService registrationService;
    private final PaginationService paginationService;
    private final UploadMemeService uploadMemeService;
    private final AdminPanelService adminPanelService;
    private final SecurityService securityService;

    private final LikesRepository likesRepository;
    private final MemesRepository memesRepository;

    private final DropboxCommunicationService dropboxCommunicationService;

    private static final String MAIN_PAGE = "mainPage";
    private static final String RANDOM_PAGE = "randomPage";
    private static final String REGISTER_PAGE_FORM = "registerForm";
    private static final String SIGN_IN_PAGE_FORM = "signInForm";
    private static final String TERMS_AND_CONDITIONS_FORM = "termsAndConditions";
    private static final String POST_MEME_FORM = "postMemeForm";
    private static final String ADMIN_PANEL_USERS_LIST_PAGE = "admin_usersList";
    private static final String USER_EDIT_PAGE = "userEdit";
    private static final String ADMIN_PANEL_MEMES_LIST_PAGE = "admin_memesList";
    private static final String MEME_EDIT_PAGE = "memeEdit";
    private static final String ADMIN_PANEL_PAGE = "adminPanel";

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

    @GetMapping(value = "/login")
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
    public String showMainPage(@RequestParam(defaultValue = "0", name="page") int page, Model model, Authentication authentication) {
        securityService.authenticate(authentication, model);
        paginationService.showMainPage(page, model);
        return MAIN_PAGE;
    }

    @GetMapping(value = "/token")
    public String generateToken() throws JsonProcessingException {
        return dropboxCommunicationService.generateNewAccessToken();
    }

    @GetMapping(value = "/random")
    public String showRandomPage(Model model, Authentication authentication) {
        securityService.authenticate(authentication, model);
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
        adminPanelService.showUser(user_id, model);
        return USER_EDIT_PAGE;
    }

    @GetMapping(value = "/admin/memes")
    public String showMemesList(Model model) {
        adminPanelService.showMemesList(model);
        return ADMIN_PANEL_MEMES_LIST_PAGE;
    }

    @GetMapping(value = "/admin/memes/edit/{meme_id}")
    public String memeEdit(@PathVariable long meme_id, Model model) {
        adminPanelService.showMeme(meme_id, model);
        return MEME_EDIT_PAGE;
    }

    @GetMapping(value = "/admin")
    public String showAdminPanel() {
        return ADMIN_PANEL_PAGE;
    }

    @GetMapping(value = "/meme/{meme_id}")
    public String showMeme(@PathVariable long meme_id, Model model, Authentication authentication) {
        securityService.authenticate(authentication, model);
        paginationService.showDetailedPost(meme_id, model);
        return "detailedMeme";
    }

    @PostMapping(value = "/admin_users_status_update")
    public String handleAdminUsersActions(@RequestParam("id") long userId,
                                     @RequestParam("unlock") boolean unlock,
                                     Model model
                                     ) {

        adminPanelService.updateUserStatus(userId, unlock, model);
        return "redirect:/admin/users/edit/" + userId;
    }

    @PostMapping(value = "/admin_memes_status_update")
    public String handleAdminMemesAction(@RequestParam("id") long memeId,
                                         @RequestParam("unlock") boolean unlock,
                                         Model model) {

        adminPanelService.updateMemeStatus(memeId, unlock, model);
        return "redirect:/admin/memes/edit/" + memeId;
    }

    @PostMapping(value = "/process_register")
    public String handleRegisterData(@ModelAttribute RegistrationDAO registrationDAO) {

        RegistrationDataStatus registrationDataStatus = registrationService.register(registrationDAO);
        return REGISTER_PROCESS_RESPONSE_MAP.get(registrationDataStatus);
    }

    @PostMapping(value = "/post_meme")
    public String uploadImage(@ModelAttribute UploadedMemeDAO uploadedMemeDAO)  throws IOException, DbxException {
        uploadMemeService.saveMeme(uploadedMemeDAO);
        return "redirect:/";
    }

    @PostMapping(value = "/ui_actions_favorite")
    public String handleUiFavoriteActions(@ModelAttribute UiFavoriteActionDAO uiFavoriteActionDAO) {
        long memeId = uiFavoriteActionDAO.getMemeId();
        long userId = uiFavoriteActionDAO.getUserId();

        System.out.println(memeId + " : " + userId);

        Optional<Like> like = likesRepository.findByMemeIdAndUserId(memeId, userId);

        System.out.println(like.isPresent());

        if(like.isEmpty()) {
            System.out.println("Try to create new like object");
            Like newLike = new Like(memeId, userId);
            System.out.println("Created?");
            //likesRepository.save(new Like(memeId, userId));
            likesRepository.save(newLike);
            System.out.println("Like is empty, save new like");
            Meme meme = memesRepository.getReferenceById(memeId);
            meme.setLikesNumber(meme.getLikesNumber() + 1);
            memesRepository.save(meme);
            System.out.println("Add like number");
        }
        else {
            likesRepository.deleteById(like.get().getId());
            System.out.println("Like is present, delete like");
            Meme meme = memesRepository.getReferenceById(memeId);
            meme.setLikesNumber(meme.getLikesNumber() - 1);
            memesRepository.save(meme);
            System.out.println("Minus like number");
        }
        return "redirect:" + uiFavoriteActionDAO.getUrl();
    }
    //@PostMapping(value="/meme/{meme_id}")
    //public String showMeme()
}
