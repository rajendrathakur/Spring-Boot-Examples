package com.springboot.examples.service;

import com.springboot.examples.model.Contact;

import java.util.List;
import java.util.UUID;

public interface ContactService {
    public UUID saveContact(Contact contact);

    public Contact getContact(UUID id);

    public Contact updateContact(Contact contact, UUID id);

    public void deleteContact(UUID id);

    public List<Contact> fetchContacts();
}
