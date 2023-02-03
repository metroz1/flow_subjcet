package com.example.demo.controller;

import com.example.demo.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/extensions")
public class ExtensionController {

    private final ExtensionService extensionService;
    @GetMapping("/")
    public ResponseEntity<?> getExtensionList() {

        return extensionService.getExtensionList();
    }

}
