/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;

/**
 *
 * @author Tabul
 */
public class Database {
    private String server = "jdbc:mysql://localhost:3306/socialmedia";
    private String dbuser = "root";
    private String dbpasswd = "";
    private Statement statement = null;
    private Connection connection = null;
    private Application app;
    
    public void connect(){
        try{
            connection = DriverManager.getConnection(server, dbuser, dbpasswd);
            statement = connection.createStatement();
        }
        catch (Exception e){
            throw new IllegalArgumentException("Connection Error!");
        }
    }
    
    public void saveAccount(Account ac){
        try{
            String query = "INSERT INTO account (username, password, name, email) VALUES "
                    + "('" + ac.getUsername() + "', "
                    + "'" + ac.getPassword() + "', "
                    + "'" + ac.getName() + "', "
                    + "'" + ac.getEmail() + "')";
            statement.execute(query);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Connection error while saving account!");
        }
    }
    
    public void saveStatus (Status s){
        try{
            String query = "INSERT INTO statuses (username, message) VALUES "
                    + "('" + s.getAcc().getUsername() + "', "
                    + "'" + s.getMessage() + "')";
            statement.execute(query);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Connection error while saving status!");
        }
    }
    
    public void saveGroup(Group gr){
        try{
            String query = "INSERT INTO groups (id, name, admin, member) VALUES "
                    + "'" + gr.getId() + ", "
                    + "'" + gr.getName()+ "', "
                    + "'" + gr.getAdmin().getUsername() + "', "
                    + "'" + gr.getAdmin().getUsername() + "')";
            statement.execute(query);
        }
        catch (Exception e){
            throw new IllegalArgumentException("Connection error while saving group!");
        }
    }
        
    public ArrayList<Account> loadAccount(){
        try{
            ArrayList<Account> listAccount = new ArrayList<>();
            String query = "select * from Account";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Account a = new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
                listAccount.add(a);
            }
            return listAccount;
        }
        catch (Exception e){
            throw new IllegalArgumentException("Connection error while loading account!");
        }
    } 
    
    public ArrayList<Status> loadStatus(){
        try{
            ArrayList<Status> listStatus = new ArrayList();
            String query = "SELECT * FROM statuses";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                Account a = app.getAccount(rs.getString(1));
                Status s = new Status(a, rs.getString(2));
                listStatus.add(s);
            }
            return listStatus;
        }
        catch (Exception e){
            throw new IllegalArgumentException("Connection error while loading status!");
        }
    }
}
