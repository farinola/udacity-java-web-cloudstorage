package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping
    public ModelAndView createOrEdit(@ModelAttribute("noteForm") Note note) {
        User activeUser = userService.getActiveUser();
        note.setUserId(activeUser.getId());
        noteService.createOrUpdateNote(note);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable int id) {
        this.noteService.deleteNoteById(id);
        return new ModelAndView("redirect:/home");
    }
}
