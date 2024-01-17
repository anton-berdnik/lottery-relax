package org.example.service;

import org.example.model.entity.UserAccount;
import org.example.model.exception.InvalidDataException;
import org.example.model.request.CreateUserRequest;
import org.example.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserAccountService implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    public DefaultUserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public Long createUser(CreateUserRequest request) throws InvalidDataException {
        validateCreateRequest(request);
        UserAccount userAccount = new UserAccount()
                .setLogin(request.getLogin())
                .setPassport(request.getPassport())
                .setPassword(request.getPassword());
        return userAccountRepository.save(userAccount).getId();
    }

    private void validateCreateRequest(CreateUserRequest request) throws InvalidDataException {
        if ((request.getLogin() == null || request.getLogin().isBlank()) ||
                (request.getPassword() == null || request.getPassword().isBlank()) ||
                (request.getPassport() == null || request.getPassport().isBlank())
        ) {
            throw new InvalidDataException("Data is incorrect");
        }
        if (userAccountRepository.existsByLogin(request.getLogin())) {
            throw new InvalidDataException("Such user is already created");
        }
    }
}
