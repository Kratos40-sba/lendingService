package com.loanlander.lending_engine.domain.expection;

public class UserNotFoundExeption extends RuntimeException {
    public UserNotFoundExeption(Long user_id){
        super("User with Id: "+user_id+"Not Found");
    }
}
