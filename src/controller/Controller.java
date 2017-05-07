/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import model.*;
import view.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Tabul
 */
public class Controller extends MouseAdapter implements ActionListener{
    private Application model;
    private View view;
    
    public Controller(Application model){
        this.model = model;
        Login ml = new Login();
        ml.setVisible(true);
        ml.addListener((ActionListener) this);
        view = ml;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (view instanceof Login){
            Login ml = (Login) view;
            if (source.equals(ml.getBtnLogin())){
                try{
                    model.setSession(model.login(ml.getTfUsername().getText(), ml.getTfPassword().getText()));
                    model.setSessionActive(true);
                    JOptionPane.showMessageDialog(ml, "Welcome Back, " + model.getSession().getName() + "!");
                    MenuUser mu = new MenuUser();
                    mu.setVisible(true);
                    mu.addListener(this);
                    ml.dispose();
//                    if (loadTableFriends == false){
//                        loadTable(friendList);
//                    }
                    view = mu;
                    mu.setTextWelcome("Hello, " + model.getSession().getName() + "!");
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(ml,e.getMessage());
                }
            }
            else if (source.equals(ml.getBtnSignUp())){
                Register rg = new Register();
                rg.setVisible(true);
                rg.addListener(this);
                ml.dispose();
                view = rg;
            }
        }
        else if (view instanceof Register){
            Register rg = (Register) view;
            if (source.equals(rg.getBtnSignUp())){
                try{
                    model.registerAccount(rg.getTfUsername().getText(), rg.getTfPassword().getText(), rg.getTfUsername().getText(), rg.getTfUsername().getText());
                    JOptionPane.showMessageDialog(rg, "Account Succesfully Registered!");
                    Login lg = new Login();
                    lg.setVisible(true);
                    lg.addListener(this);
                    rg.dispose();
                    view = lg;
                }   
                catch (Exception e){
                    rg.viewErrorMsg(e.getMessage());
                }
            }
            else if (source.equals(rg.getBtnBack())){
                Login lg = new Login();
                lg.setVisible(true);
                lg.addListener(this);
                rg.dispose();
                view = lg;
            }
        }
        else if (view instanceof MenuUser){
            MenuUser mu = (MenuUser) view;
            if (source.equals(mu.getBtnLogout())){
                int ch = JOptionPane.showConfirmDialog(mu, "Are you sure, " + model.getSession().getName());
                if (ch == 0){
                    model.logout();
                    Login lg = new Login();
                    lg.setVisible(true);
                    lg.addListener(this);
                    mu.dispose();
                    view = lg;
                }
            }
            else if (source.equals(mu.getBtnFriends())){
                Friends f = new Friends();
                f.setVisible(true);
                f.addListener(this);
                mu.dispose();
                view = f;
            }
            else if (source.equals(mu.getBtnHomeUser())){
                HomeUser hu = new HomeUser();
                hu.setVisible(true);
                hu.addListener(this);
                mu.dispose();
                view = hu;
            }
            else if (source.equals(mu.getBtnGroup())){
                Groups gv = new Groups();
                gv.setVisible(true);
                gv.addListener(this);
                mu.dispose();
                view = gv;
            }
            else if (source.equals(mu.getBtnProfile())){
                Profile p = new Profile();
                p.setVisible(true);
                p.addListener(this);
                p.getTfNama().setText(model.getSession().getName());
                p.getTfUsername().setText(model.getSession().getUsername());
                p.getTfEmail().setText(model.getSession().getEmail());
                p.getPfPassword().setText(model.getSession().getPassword());
            
                mu.dispose();
                view = p;

            }
        }
        else if (view instanceof Friends){
            Friends f = (Friends) view;
            DefaultTableModel friendList = (DefaultTableModel) f.getTableFriendList().getModel();
            for (int i = 0; i < friendList.getRowCount(); i++){
                friendList.removeRow(i);
            }
            for (Account a : model.getSession().getFriends()){
                friendList.addRow(new Object[]{"foto", a.getUsername(), a.getName()});
            }
            if (source.equals(f.getBtnAddFriends())){
                try{
                    model.addFriends(f.getTfUsername().getText());
                    JOptionPane.showMessageDialog(f, "Succesfully added " + model.getAccount(f.getTfUsername().getText()).getName());
                    friendList.addRow(new Object[]{"foto", f.getTfUsername().getText(), model.getAccount(f.getTfUsername().getText()).getName()});
                }
                catch(Exception e){
                    f.viewErrorMsg(e.getMessage());
                }
            }
            else if (source.equals(f.getBtnBack())){
                MenuUser mu = new MenuUser();
                mu.setVisible(true);
                mu.addListener(this);
                f.dispose();
                view = mu;
            }
            else if (source.equals(f.getBtnSendChat())){

            }
        }
        else if (view instanceof HomeUser){
            HomeUser hu = (HomeUser) view;
            DefaultTableModel postedStatus = (DefaultTableModel) hu.getTableStatus().getModel();
            for (int i = 0; i < postedStatus.getRowCount(); i++){
                postedStatus.removeRow(i);
            }
            for (Status s : model.getListStatus()){
                postedStatus.addRow(new Object[]{s.getAcc().getName(), s.getMessage()});
            }
            if (source.equals(hu.getBtnBack())){
                MenuUser mu = new MenuUser();
                mu.setVisible(true);
                mu.addListener(this);
                hu.dispose();
                view = mu;
            }
            else if (source.equals(hu.getBtnPost())){
                model.postStatus(hu.getTextStatus().getText());
                JOptionPane.showMessageDialog(hu, "Succesfully posted your status!");
                postedStatus.addRow(new Object[]{model.getSession().getName(), hu.getTextStatus().getText()});
            }
        }
        else if (view instanceof Groups){
            Groups gr = (Groups) view;
            if (source.equals(gr.getBtnBack())){
                MenuUser mu = new MenuUser();
                mu.setVisible(true);
                mu.addListener(this);
                gr.dispose();
                view = mu;
            }
        }
        else if (view instanceof Profile){
            Profile p = (Profile) view;
//            p.getTfNama().setText(model.getSession().getName());
//            p.getTfUsername().setText(model.getSession().getUsername());
//            p.getTfEmail().setText(model.getSession().getEmail());
//            p.getPfPassword().setText(model.getSession().getPassword());
            if (source.equals(p.getBtnBack())){
                MenuUser mu = new MenuUser();
                mu.setVisible(true);
                mu.addListener(this);
                p.dispose();
                view = mu;
            }
            else if (source.equals(p.getBtnEditProfile())){
                model.getSession().setEmail(p.getTfEmail().getText());
                model.getSession().setUsername(p.getTfUsername().getText());
                model.getSession().setPassword(p.getPfPassword().getText());
                model.getSession().setName(p.getTfNama().getText());
            }
        }
    }    
}
