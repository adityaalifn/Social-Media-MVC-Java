/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tabul
 */
public class Application {
    private ArrayList<Account> listAccount;
    private ArrayList<Group> listGroup;
    private ArrayList<Status> listStatus;
    private ArrayList<Chat> listChat;
    Database db;
    private Account session;
    private boolean sessionActive;
    
    public Application(){
        listAccount = new ArrayList();
        listGroup = new ArrayList();
        listStatus = new ArrayList();
        listChat = new ArrayList();
        db = new Database();
        db.connect();
        listAccount = db.loadAccount();
        //listStatus = db.loadStatus();
        session = null;
        sessionActive = false;
    }
    
    
    //register function
    public void registerAccount(String username, String password, String name, String email){
        if (getAccount(username) == null){
            Account acc = new Account(username, password, name, email);
            listAccount.add(acc);
            db.saveAccount(acc);
        }
        else{
            throw new IllegalStateException("Username already exist!");
        }
    }
    
    
    //login function
    public Account getAccount(String username){
        for (Account ac : listAccount){
            if (ac.getUsername().equals(username)){
                return ac;
            }
        }
        return null;
    }
    
    public boolean validation(Account ac, String password){
        if (ac.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public Account login(String username, String password){
        if (validation(getAccount(username),password)){
            this.sessionActive = true;
            return getAccount(username);
        }
        else{
            throw new IllegalStateException("Wrong username or password!");
        }
    }
    
    //logout function
    public void logout(){
        this.session = null;
        this.sessionActive = false;
    }
    
    
    //group function
    public void createGroup(String name){
        int id = listGroup.size()+1;
        Group gr = new Group(id, name, session);
        listGroup.add(gr);
    }
    
    public void eraseGroup(int id){
       for (Group g : listGroup){
           if (g.getId() == id){
               if (g.getAdmin() == session){
                   listGroup.remove(g);
                   break;
               }
               else{
                   throw new IllegalStateException("You're not admin in this group!");
               }
           }
           else{
                   throw new IllegalStateException("No group selected");
           }
       }
    }
    
    
    //friends function
    public boolean checkFriends(String username){
        for (Account f : session.getFriends()){
            if (f.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    
    public void addFriends(String username){
        if (!checkFriends(username)){
            for (Account a : listAccount){
                if (session.getUsername().equals(username)){
                    throw new IllegalStateException("That is your own account!");
                }
                else if (a.getUsername().equals(username)){
                    session.addFriends(a);
                    a.addFriends(session);
                    PersonalChat pc = new PersonalChat(session, a, listChat.size()+1);
                    listChat.add(pc);
                    break;
                }
            }
        }
        else{
            throw new IllegalStateException("User already your friend!");
        }
    }
    
    public void removeFriends(String username){
        session.removeFriend(username);
    }
    
    
    //personal chat function
    public Chat getChat(Account receiver){
        for (Chat c : listChat){
            if (c instanceof PersonalChat){
                if ((((PersonalChat) c).getAccount().contains(session)) && (((PersonalChat) c).getAccount().contains(receiver))){
                    return c;
                }
            }
        }
        return null;
    }
    
    public void sendChat(Account receiver, String message){
        for (Chat c : listChat){
            if (c instanceof PersonalChat){
                if ((((PersonalChat) c).getAccount().contains(session)) && (((PersonalChat) c).getAccount().contains(receiver))){
                    ((PersonalChat) c).getMessage().add(session.getName() + ":");
                    ((PersonalChat) c).getMessage().add(message);
                    ((PersonalChat) c).getMessage().add(" ");
                    break;
                }
            }
        }
    }
    
    public void showChat(Account receiver){
        for (Object msg : getChat(receiver).getMessage()){
            System.out.println(msg);
        }
    }
    
    //post function
    public void postStatus(String message){
        Status s = new Status(session, message);
        listStatus.add(s);
        //db.saveStatus(s);
    }
    
    
    
    public ArrayList<Account> getListAccount() {
        return listAccount;
    }

    public ArrayList<Group> getListGroup() {
        return listGroup;
    }

    public ArrayList<Status> getListStatus() {
        return listStatus;
    }

    public Account getSession() {
        return session;
    }

    public boolean isSessionActive() {
        return sessionActive;
    }

    public void setSessionActive(boolean sessionActive) {
        this.sessionActive = sessionActive;
    }

    public void setListAccount(ArrayList<Account> listAccount) {
        this.listAccount = listAccount;
    }

    public void setListGroup(ArrayList<Group> listGroup) {
        this.listGroup = listGroup;
    }

    public void setListStatus(ArrayList<Status> listStatus) {
        this.listStatus = listStatus;
    }

    public void setSession(Account session) {
        this.session = session;
    }
    
    
     
}
