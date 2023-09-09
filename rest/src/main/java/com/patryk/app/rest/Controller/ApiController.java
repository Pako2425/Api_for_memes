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

    @GetMapping(value = "/sign_in")
    public String viewSignInPage()
    {
        return "signInForm";
    }

    @GetMapping(value = "/terms_and_conditions")
    public String viewTermsAndConditionsPage() {
        return "termsAndConditions";
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

        String form = "registerSuceeded";

        try {
            userService.save(registrationRequest);
        }
        catch(IllegalStateException e) {
            String message = e.getMessage();
            System.out.println(message);

            if(message.equals("Email already exist")) {
                form = "emailAlreadyInUse";
                System.out.println(message);
            }

            else if(message.equals("Name already exist")) {
                form = "nameAlreadyInUse";
            }

            else if(message.equals("Password not correct")) {
                form = "wrongPassword";
            }

            else {
                form = "homePage";
            }

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
