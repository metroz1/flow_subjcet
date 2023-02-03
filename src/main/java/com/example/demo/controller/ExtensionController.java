package com.example.demo.controller;

import com.example.demo.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("/extensions")
public class ExtensionController {

    private final ExtensionService extensionService;
    @GetMapping("/basics")
    public ResponseEntity<?> getBasicExtensionList() {

        return extensionService.getBasicExtensionList();
    }

    @PostMapping("/basics")
    public ResponseEntity<?> createBasicExtension(String name) {

        return extensionService.createBasicExtension(name);
    }

    @DeleteMapping("/basics")
    public ResponseEntity<?> deleteBasicExtension(String name) {

        return extensionService.deleteBasicExtension(name);
    }

    @GetMapping("/customs")
    public ResponseEntity<?> getCustomExtensionList() {

        return extensionService.getCustomExtensionList();
    }

    @PostMapping("/customs")
    public ResponseEntity<?> createCustomExtension(String name) {

        return extensionService.createCustomExtension(name);
    }

    @DeleteMapping("/customs")
    public ResponseEntity<?> deleteCustomExtension(String name) {

        return extensionService.deleteCustomExtension(name);
    }

}
