package com.springboot.examples.repository;

import com.springboot.examples.model.Contact;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ContactRepository {
    private List<Contact> contacts = new ArrayList<>();

    public UUID saveContact(Contact contact) {
        contacts.add(contact);
        return contact.getId();
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact updateContact(Contact contact , int index) {
        contacts.set(index, contact);
        return contact;
    }

    public void deleteContact(int index) {
        contacts.remove(index);
    }
}
