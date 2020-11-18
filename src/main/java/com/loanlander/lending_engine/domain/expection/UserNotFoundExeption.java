package com.loanlander.lending_engine.domain.expection;

public class UserNotFoundExeption extends RuntimeException {
    public UserNotFoundExeption(String  username){
        super("User with username: "+username+"Not Found");
    }
}
