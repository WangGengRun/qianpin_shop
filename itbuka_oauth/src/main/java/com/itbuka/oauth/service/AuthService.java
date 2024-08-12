package com.itbuka.oauth.service;

import java.util.Map;

public interface AuthService {
    Map login(String username, String password, String clientId, String clientSecret,Integer ttl);
}

