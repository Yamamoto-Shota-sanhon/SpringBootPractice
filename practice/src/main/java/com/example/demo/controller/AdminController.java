package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Admin;
import com.example.demo.form.AdminForm;
import com.example.demo.service.AdminService;

@Controller

public class AdminController {

    @Autowired
    private AdminService adminService;

    //管理者登録画面へ
    @GetMapping("/admin/signup")
    public String signupForm(Model model) {
        model.addAttribute("adminForm", new AdminForm());
        return "signup"; //登録画面へ
    }

    //管理者登録
    @PostMapping("/admin/signup")
    public String processSignup(@Valid AdminForm adminForm, BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }
        // データベースに管理者登録
        Admin admin = new Admin();
        admin.setFirstName(adminForm.getFirstName());
        admin.setLastName(adminForm.getLastName());
        admin.setEmail(adminForm.getEmail());
        admin.setPassword(adminForm.getPassword());
        adminService.saveAdmin(admin);

        return "redirect:/admin/signin";//登録完了ごサインインページへ
    }

}
