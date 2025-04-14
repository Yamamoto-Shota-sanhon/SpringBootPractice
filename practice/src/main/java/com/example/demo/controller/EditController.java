package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Contact;
import com.example.demo.form.EditForm;
import com.example.demo.service.ContactService;

@Controller
public class EditController {

    @Autowired
    private ContactService contactService;

    //登録内容の一覧を表示する
    @GetMapping("/admin/contacts")
    public String showContacts(Model model) {
        model.addAttribute("contacts", contactService.getAllContacts()); // データをモデルに追加
        return "contactlist";
    }

    //IDをもとに詳細を表示する
    @GetMapping("/admin/contacts/{id}")
    public String getContactDetail(@PathVariable Long id, Model model) {
        Contact contact = contactService.getContactById(id);
        model.addAttribute("contact", contact);
        return "details";
    }

    //編集画面にデータを引き渡す
    @GetMapping("/admin/contacts/{id}/edit")
    public String editContactDetail(@PathVariable Long id, Model model) {
        // IDに基づいて既存のデータを取得
        Contact contact = contactService.getContactById(id);

        //フォームデータに詰め直して渡す
        EditForm editForm = new EditForm();
        editForm.setLastName(contact.getLastName());
        editForm.setFirstName(contact.getFirstName());
        editForm.setEmail(contact.getEmail());
        editForm.setPhone(contact.getPhone());
        editForm.setZipCode(contact.getZipCode());
        editForm.setAddress(contact.getAddress());
        editForm.setBuildingName(contact.getBuildingName());
        editForm.setContactType(contact.getContactType());
        editForm.setBody(contact.getBody());

        model.addAttribute("editForm", editForm); //モデルに追加
        model.addAttribute("contactId", contact.getId()); //IDを追加

        return "edit"; // 編集ページへ
    }

    //編集したデータを渡す
    @PostMapping("/admin/contacts/{id}/edit")
    public String saveEditContact(@PathVariable Long id, @ModelAttribute("editForm") EditForm editForm) {
        contactService.updateContact(editForm); // サービスにエディットフォームのデータを渡す
        return "redirect:/admin/contacts"; // 編集後は一覧へ
    }

    //登録内容の削除
    @PostMapping("/admin/contacts/{id}/delete")
    public String deleteData(@PathVariable Long id) {
        //リポジトリを使ってデータを削除
        contactService.deleteContactById(id);

        return "redirect:/admin/contacts";//削除後は一覧へ
    }

}
