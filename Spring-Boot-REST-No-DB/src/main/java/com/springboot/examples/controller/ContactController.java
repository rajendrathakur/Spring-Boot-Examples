package com.springboot.examples.controller;

import com.springboot.examples.model.Contact;
import com.springboot.examples.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ContactController {
    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable UUID id) {
        Contact contact = contactService.getContact(id);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @PostMapping("/contact")
    public ResponseEntity<UUID> saveContact(@RequestBody Contact contact) {
        UUID uuid = contactService.saveContact(contact);
        return new ResponseEntity<UUID>(uuid, HttpStatus.CREATED);
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@RequestBody Contact contact, @PathVariable UUID id) {
        return new ResponseEntity<>(contactService.updateContact(contact, id), HttpStatus.OK);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity deleteContact(@PathVariable UUID id) {
        contactService.deleteContact(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/contact")
    public ResponseEntity<List<Contact>> fetchContacts() {
        return new ResponseEntity<>(contactService.fetchContacts(), HttpStatus.OK);
    }
}
