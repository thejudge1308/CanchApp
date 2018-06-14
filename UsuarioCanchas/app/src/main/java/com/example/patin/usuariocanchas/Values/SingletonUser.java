package com.example.patin.usuariocanchas.Values;

import com.example.patin.usuariocanchas.Model.User;

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
    public static User createUser(String email, String name, String surname, String nickname, Double score){
        SingletonUser.user = new User(email,name,surname,nickname,score);
        return SingletonUser.user;
    }
}
