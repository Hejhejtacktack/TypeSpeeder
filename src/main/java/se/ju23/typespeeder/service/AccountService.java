package se.ju23.typespeeder.service;

import se.ju23.typespeeder.exception.AccountCreationException;

public interface AccountService {
    void build() throws AccountCreationException;
    void create(String accountName, String username, String password) throws AccountCreationException;
    void remove(String username);
}
