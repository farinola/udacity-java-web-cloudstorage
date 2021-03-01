package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialService {
    private final CredentialMapper credentialMapper;

    public CredentialService(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    public List<Credential> getAllCredentials() {
        return credentialMapper.getAll();
    }

    public int createOrUpdate(Credential credential) {
        if (credential.getId() != null) {
            return credentialMapper.update(credential);
        }
        return credentialMapper.insert(credential);
    }

    public void delete(int id) {
        credentialMapper.delete(id);
    }
}
