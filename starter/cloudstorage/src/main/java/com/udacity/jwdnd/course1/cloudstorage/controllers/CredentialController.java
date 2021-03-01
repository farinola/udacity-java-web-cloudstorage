package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/credentials")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    public CredentialController(CredentialService credentialService,
                                UserService userService,
                                EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping
    public ModelAndView createOrEdit(@ModelAttribute("credentialForm") Credential credential) {
        User activeUser = userService.getActiveUser();
        credential.setUserId(activeUser.getId());
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), activeUser.getSalt()));
        credentialService.createOrUpdate(credential);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("{id}/delete")
    public ModelAndView delete(@PathVariable int id) {
        this.credentialService.delete(id);
        return new ModelAndView("redirect:/home");
    }
}
