package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("files")
public class FileController {
    private final FileService fileService;
    private final UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping("/view/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> view(@PathVariable String filename) {
        Resource file = fileService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\""
                ).body(file);
    }

    @PostMapping
    public ModelAndView create(@RequestParam("fileUpload") MultipartFile file) {
        int userId = userService.getActiveUser().getId();
        fileService.create(file, userId);
        return new ModelAndView("redirect:/home");
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id) {
        this.fileService.delete(id);
        return new ModelAndView("redirect:/home");
    }
}
