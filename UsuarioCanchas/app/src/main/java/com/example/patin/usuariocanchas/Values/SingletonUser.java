package com.example.patin.usuariocanchas.Values;

import com.example.patin.usuariocanchas.Model.User;

import java.util.Date;

public  class SingletonUser {
    public static User user = null;

    /**
     *
     * @return A instance of USER
     */
    public static User getInstance(){
        if(user!=null){
            return SingletonUser.user;
        }
        return new User();
    }

    /**
     *
     * @param email
     * @param name
     * @param surname
     * @param nickname
     * @param score
     */
    public static User createUser(String email,String password, String name, String surname, String nickname, Double score, String birthdate){
        SingletonUser.user = new User(email,password,name,surname,nickname,birthdate);
        SingletonUser.user.setScore(score);
        return SingletonUser.user;
    }
}
