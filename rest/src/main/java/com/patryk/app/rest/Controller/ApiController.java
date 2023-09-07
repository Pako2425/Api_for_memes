package com.patryk.app.rest.Controller;

import com.patryk.app.rest.Repository.UsersRepository;
import com.patryk.app.rest.Service.RegistrationRequest;
import com.patryk.app.rest.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ApiController {

    private final UserService userService;
    private final String FOLDER_PATH = "C:/Users/Lenovo/Desktop/files/";

    @GetMapping(value = "/")
    public String showHomePage() {
        return "homePage";
    }

    @GetMapping(value = "/register")
    public String showRegisterPage() { return "registerForm"; }

    /*
    @GetMapping(value = "/sign_in")
    public String viewSignInPage()
    {
        return "signInForm";
    }

    @GetMapping(value = "/terms_and_conditions")
    public String viewTermsAndConditionsPage() {
        return "termsAndConditions";
    }

    @GetMapping(value = "/fileSystem/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadImage(@PathVariable Long fileId) throws IOException {
        Optional<FileData> fileData = filesRepository.findById(fileId);
        String filePath = fileData.get().getFilePath();
        byte[] meme = Files.readAllBytes(new File(filePath).toPath());

        return meme;
    }

    public boolean isRegisterDataCorrect(UserRegisterFormData userRegisterFormData) {
        boolean isEmailAlreadyInUse = false;
        boolean isNameAlreadyInUse = false;
        boolean isPasswordCorrect = false;

        for(User user : usersRepository.findAll()) {
            if (user.getEmail().equals(userRegisterFormData.getEmail())) {
                isEmailAlreadyInUse = true;
            }
            if (user.getUsername().equals(userRegisterFormData.getName())) {
                isNameAlreadyInUse = true;
            }
            if (userRegisterFormData.getPassword().equals(userRegisterFormData.getRepeatPassword())) {
                isPasswordCorrect = true;
            }
        }

        if(!isEmailAlreadyInUse && !isNameAlreadyInUse && isPasswordCorrect) {
            return true;
        }
        else {
            return false;
        }
    }

    */
    @PostMapping(value = "/process_register")
    public String handleRegisterData(@ModelAttribute(value = "userRegisterFormData")
                                     RegistrationRequest registrationRequest) {
        userService.save(registrationRequest);
        return "registerSuceeded";
    }
    /*

    @PostMapping(value = "/process_register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        registerService.register(registerRequest);
        return "register succesful";
    }

    @PostMapping(value = "/process_register")
    public String handleRegisterData(@ModelAttribute(value="userRegisterFormData")
                                         UserRegisterFormData userRegisterFormData) {

        if(isRegisterDataCorrect(userRegisterFormData)) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userRegisterFormData.getPassword());

            User user = new User(userRegisterFormData.getName(), userRegisterFormData.getEmail(), encodedPassword, UserRole.USER);
            usersRepository.save(user);

            return "registerSuceeded";
        }
        else {
            return "registerForm";
        }
    }

    */

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
