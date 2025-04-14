package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.form.EditForm;
import com.example.demo.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void saveContact(ContactForm contactForm) {
        Contact contact = new Contact();
        
        contact.setLastName(contactForm.getLastName());
        contact.setFirstName(contactForm.getFirstName());
        contact.setEmail(contactForm.getEmail());
        contact.setPhone(contactForm.getPhone());
        contact.setZipCode(contactForm.getZipCode());
        contact.setAddress(contactForm.getAddress());
        contact.setBuildingName(contactForm.getBuildingName());
        contact.setContactType(contactForm.getContactType());
        contact.setBody(contactForm.getBody());
        
        contactRepository.save(contact);

    }
    
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
    

    @Override
    public Contact getContactById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact not found"));
    }
    
    @Override
    public void updateContact(EditForm editForm) {
        // IDを基に既存のContactを取得
        Contact existingContact = contactRepository.findById(editForm.getId())
            .orElseThrow(() -> new RuntimeException("Contact not found"));

        // 必要なフィールドを上書き
        if (editForm.getLastName() != null) {
            existingContact.setLastName(editForm.getLastName());
        }
        if (editForm.getFirstName() != null) {
            existingContact.setFirstName(editForm.getFirstName());
        }
        if (editForm.getEmail() != null) {
            existingContact.setEmail(editForm.getEmail());
        }
        if (editForm.getPhone() != null) {
            existingContact.setPhone(editForm.getPhone());
        }
        if (editForm.getZipCode() != null) {
            existingContact.setZipCode(editForm.getZipCode());
        }
        if (editForm.getAddress() != null) {
            existingContact.setAddress(editForm.getAddress());
        }
        if (editForm.getBuildingName() != null) {
            existingContact.setBuildingName(editForm.getBuildingName());
        }
        if (editForm.getContactType() != null) {
            existingContact.setContactType(editForm.getContactType());
        }
        if (editForm.getBody() != null) {
            existingContact.setBody(editForm.getBody());
        }

        // 更新されたデータを保存
        contactRepository.save(existingContact);
    }
    
    @Override
    public void deleteContactById(Long id) {
        // IDを参照してデータを削除する
        contactRepository.deleteById(id);
    }


}
