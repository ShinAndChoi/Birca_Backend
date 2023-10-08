package com.app.birca.controller;

import com.app.birca.dto.request.InsertIdolGroupRequest;
import com.app.birca.dto.request.InsertIdolRequest;
import com.app.birca.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/admin/save/idolGroup")
    public void saveIdolGroup(@ModelAttribute InsertIdolGroupRequest request) throws IOException {
        adminService.saveIdolGroup(request);
    }

    @PostMapping("/admin/save/idol")
    public void saveIdol(@ModelAttribute InsertIdolRequest request) throws IOException {
        adminService.saveIdol(request);
    }

}
