/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Tabul
 */
public class Group {
    private int id;
    private String name;
    private Account admin;
    private ArrayList<Account> groupMember;
    
    public Group(int id, String name, Account admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
        groupMember = new ArrayList();
        this.groupMember.add(admin);
    }

    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAdmin() {
        return admin;
    }

    public void setAdmin(Account admin) {
        this.admin = admin;
    }

    public ArrayList<Account> getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(ArrayList<Account> groupMember) {
        this.groupMember = groupMember;
    }
    
    
}
