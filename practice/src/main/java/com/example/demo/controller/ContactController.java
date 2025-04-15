
package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.ContactForm;
import com.example.demo.service.ContactService;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    //お問合せページへ
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contactForm", new ContactForm());

        return "contact";
    }

    //お問い合わせ内容をフォームに登録
    @PostMapping("/contact")
    public String contact(@Validated @ModelAttribute("contactForm") ContactForm contactForm, BindingResult errorResult,
            HttpServletRequest request) {

        if (errorResult.hasErrors()) {
            return "contact";
        }

        HttpSession session = request.getSession();
        session.setAttribute("contactForm", contactForm);
        return "redirect:/contact/confirm";

    }

    //お問い合わせ内容の確認
    @GetMapping("/contact/confirm")
    public String confirm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();

        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
        model.addAttribute("contactForm", contactForm);
        return "confirmation";
    }

    //お問合せ内容の保存
    @PostMapping("/contact/register")
    public String register(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");

        contactService.saveContact(contactForm);

        return "redirect:/contact/complete";
    }

    //お問い合わせ完了画面へ
    @GetMapping("/contact/complete")
    public String complete(Model model, HttpServletRequest request) {

        if (request.getSession(false) == null) {
            return "redirect:/contact";
        }

        HttpSession session = request.getSession();
        ContactForm contactForm = (ContactForm) session.getAttribute("contactForm");
        model.addAttribute("contactForm", contactForm);

        session.invalidate();

        return "completion";
    }

}