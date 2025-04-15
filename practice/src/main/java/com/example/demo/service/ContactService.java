package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Contact;
import com.example.demo.form.ContactForm;
import com.example.demo.form.EditForm;

public interface ContactService {
    
    void saveContact(ContactForm contactform);
    
    List<Contact> getAllContacts();
    
    Contact getContactById(Long id);
    
    void updateContact(EditForm editform);
    
    void deleteContactById(Long id);
    
}
