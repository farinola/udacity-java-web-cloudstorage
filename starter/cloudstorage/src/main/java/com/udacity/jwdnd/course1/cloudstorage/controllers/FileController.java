package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("files")
public class FileController {
    @PostMapping
    public ModelAndView create(@ModelAttribute File file, Model model) {
        return new ModelAndView("redirect:/home");
    }

    @PostMapping("/{id}")
    public ModelAndView edit(@ModelAttribute File file, Model model) {
        return new ModelAndView("redirect:/home");
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@ModelAttribute File file) {
        return new ModelAndView("redirect:/home");
    }
}
