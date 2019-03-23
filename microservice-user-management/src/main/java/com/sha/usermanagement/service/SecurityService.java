package com.sha.usermanagement.service;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
