package org.example.service;

import org.example.model.exception.InvalidDataException;
import org.example.model.request.CreateUserRequest;

public interface UserAccountService {
    Long createUser(CreateUserRequest request) throws InvalidDataException;
}
