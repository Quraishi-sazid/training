package com.example.firebaseuploadimage;

public class User {
    public int userId;
    public String userName;
    public String userAddress;

    public User() {
    }
    
    public User(int userId, String userName, String userAddress) {
        this.userId = userId;
        this.userName = userName;
        this.userAddress=userAddress;
    }
}
