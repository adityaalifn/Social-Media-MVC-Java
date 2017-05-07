/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Image;
import java.util.ArrayList;

/**
 *
 * @author Tabul
 */
public class Account {
    private String username;
    private String password;
    private String name;
    private String email;
    private ArrayList<Account> friends;
    private Image profilePicture;

    public Account(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        friends = new ArrayList();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<Account> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<Account> friends) {
        this.friends = friends;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }
    
    public void addFriends(Account a){
        if (!friends.contains(a)){
            friends.add(a);
        }
    }
    
    public void removeFriend(String username){
        friends.stream().filter((a) -> (a.getUsername().equals(username))).forEachOrdered((a) -> {
            friends.remove(a);
        });
    }
}
