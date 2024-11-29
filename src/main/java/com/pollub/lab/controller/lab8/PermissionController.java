package com.pollub.lab.controller.lab8;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @GetMapping("/user")
    public ResponseEntity<String> userAccess() {
        return ResponseEntity.ok("USER content");
    }

    @GetMapping("/admin")
    public ResponseEntity<String> adminAccess() {
        return ResponseEntity.ok("ADMIN content");
    }
}