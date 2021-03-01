package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final CredentialService credentialService;
    private final FileService fileService;
    private final NoteService noteService;

    public HomeController(CredentialService credentialService,
                          FileService fileService,
                          NoteService noteService) {
        this.credentialService = credentialService;
        this.fileService = fileService;
        this.noteService = noteService;
    }

    @ModelAttribute("storedCredentials")
    public List<Credential> credentials() {
        return this.credentialService.getAllCredentials();
    }

    @ModelAttribute("notes")
    public List<Note> notes() {
        return this.noteService.getAll();
    }

    @ModelAttribute("files")
    public List<File> files() {
        return this.fileService.getAllFiles();
    }

    @GetMapping
    public String home(@ModelAttribute("noteForm") Note noteForm,
                       @ModelAttribute("credentialForm") Credential credentialForm) {
        return "home";
    }
}
