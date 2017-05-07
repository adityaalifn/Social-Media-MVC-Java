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
public abstract class Chat {
    private int id;
    private ArrayList<String> message;

    public Chat(int id) {
        this.id = id;
        this.message = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList getMessage() {
        return message;
    }

    public void setMessage(ArrayList message) {
        this.message = message;
    }
}
