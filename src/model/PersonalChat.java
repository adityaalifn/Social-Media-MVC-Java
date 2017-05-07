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
public class PersonalChat extends Chat{
    private ArrayList<Account> account;

    public PersonalChat(Account acc1, Account acc2, int id) {
        super(id);
        account = new ArrayList();
        this.account.add(acc1);
        this.account.add(acc2);
    }

    public ArrayList<Account> getAccount() {
        return account;
    }

    public void setAccount(ArrayList<Account> account) {
        this.account = account;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public ArrayList<String> getMessage() {
        return super.getMessage();
    }    
}
