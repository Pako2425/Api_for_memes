package com.patryk.app.rest.Controller;

import com.patryk.app.rest.Repository.UsersRepository;
import com.patryk.app.rest.Service.RegistrationDataStatus;
import com.patryk.app.rest.Service.RegistrationRequest;
import com.patryk.app.rest.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ApiController {

    private final UserService userService;
    private final String FOLDER_PATH = "C:/Users/Lenovo/Desktop/files/";

    private final String homePage_form = "homePage";
    private final String registerPage_form = "registerForm";
    private final String signInPage_form = "signInForm";
    private final String termsAndConditions_form = "termsAndConditions";
    private final String registerSucceded_form = "registerSuceeded";
    private final String emailAlreadyInUse_form = "emailAlreadyInUse";
    private final String nameAlreadyInUse_form = "nameAlreadyInUse";
    private final String wrongPassword_form = "wrongPassword";
    private final String somethingWentWrong_form = "somethingWentWrong";

    @GetMapping(value = "/")
    public String showHomePage() {

        return homePage_form;
    }

    @GetMapping(value = "/register")
    public String showRegisterPage() {

        return registerPage_form;
    }

    @GetMapping(value = "/sign_in")
    public String viewSignInPage() {

        return signInPage_form;
    }

    @GetMapping(value = "/terms_and_conditions")
    public String viewTermsAndConditionsPage() {

        return termsAndConditions_form;
    }

    /*
    @GetMapping(value = "/fileSystem/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadImage(@PathVariable Long fileId) throws IOException {
        Optional<FileData> fileData = filesRepository.findById(fileId);
        String filePath = fileData.get().getFilePath();
        byte[] meme = Files.readAllBytes(new File(filePath).toPath());

        return meme;
    }
    */

    @PostMapping(value = "/process_register")
    public String handleRegisterData(@ModelAttribute(value = "userRegisterFormData")
                                     RegistrationRequest registrationRequest) {

        RegistrationDataStatus registrationDataStatus = userService.registerDataCheck(registrationRequest);
        String form;

        if(registrationDataStatus == RegistrationDataStatus.SUCCESS) {
            userService.save(registrationRequest);
            form = registerSucceded_form;
        }
        else if(registrationDataStatus == RegistrationDataStatus.EMAIL_ALREADY_EXIST) {
            form = emailAlreadyInUse_form;
        }
        else if(registrationDataStatus == RegistrationDataStatus.NAME_ALREADY_EXIST) {
            form = nameAlreadyInUse_form;
        }
        else if(registrationDataStatus == RegistrationDataStatus.PASSWORD_NOT_CORRECT) {
            form = wrongPassword_form;
        }
        else {
            form = somethingWentWrong_form;
        }

        return form;
    }

    /*
    @PostMapping(value = "/fileSystem")
    public Long uploadImage(@RequestParam MultipartFile multipartFile) throws IOException {
        String filePath = FOLDER_PATH + multipartFile.getOriginalFilename();
        FileData fileData = new FileData();
        fileData.setName(multipartFile.getName());
        fileData.setType(multipartFile.getContentType());
        fileData.setFilePath(filePath);
        filesRepository.save(fileData);

        multipartFile.transferTo(new File(filePath));

        return fileData.getId();
    }*/

}
