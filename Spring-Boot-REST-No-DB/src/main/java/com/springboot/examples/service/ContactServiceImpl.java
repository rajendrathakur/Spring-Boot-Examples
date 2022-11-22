package com.springboot.examples.service;

import com.springboot.examples.model.Contact;
import com.springboot.examples.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact updateContact(Contact contact, UUID id) {
        return contactRepository.updateContact(contact, findIndexById(id));
    }

    @Override
    public void deleteContact(UUID id) {
        contactRepository.deleteContact(findIndexById(id));
    }

    @Override
    public List<Contact> fetchContacts() {
        return contactRepository.getContacts();
    }


    @Override
    public UUID saveContact(Contact contact) {
        return contactRepository.saveContact(contact);
    }

    @Override
    public Contact getContact(UUID id) {
        return contactRepository.getContact(findIndexById(id));
    }

    private int findIndexById(UUID id) {
        return IntStream.range(0, contactRepository.getContacts().size())
                .filter(index -> contactRepository.getContacts().get(index).getId().equals(id))
                .findFirst()
                .orElse(0);
    }
}
