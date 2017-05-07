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
public class GroupChat extends Chat{
    Group group;

    public GroupChat(Group group, int id) {
        super(id);
        this.group = group;
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public ArrayList getMessage() {
        return super.getMessage();
    }

    public Group getGroupChat() {
        return group;
    }

    public void setGroupChat(Group group) {
        this.group = group;
    }
}