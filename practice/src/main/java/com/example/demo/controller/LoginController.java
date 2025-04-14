package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.service.AdminService;

@Controller
public class LoginController {

    @Autowired
    private AdminService adminService;

    //ログイン画面へ
    @GetMapping("/admin/signin")
    public String signinForm(Model model) {
        model.addAttribute("loginForm", new LoginForm()); // LoginFormをビューに渡す

        return "signin";
    }

    //Eメールアドレスとパスワードによるログイン機能
    @PostMapping("/admin/signin")
    public String processSignin(LoginForm loginForm, Model model) {

        boolean isValidUser = adminService.authenticateAdmin(loginForm.getEmail(), loginForm.getPassword());

        if (isValidUser) {
            // ログイン成功
            return "redirect:/admin/contacts"; //一覧へ
        } else {
            // ログイン失敗
            model.addAttribute("error", "メールアドレスまたはパスワードが間違っています。");
            return "signin"; // ログイン画面再表示
        }
    }
}
