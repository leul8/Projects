package com.example.checkfx;

public interface AuthenticationService {
    boolean authenticate(String username, String password) throws ClassNotFoundException;
}

