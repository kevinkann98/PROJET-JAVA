/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import Vue.LoginPage;
import javax.swing.JFrame;
/**
 *
 * @author kevin
 */
public class ObjetInexistant extends SQLException {
    public void message(){
         System.out.println("Objet inexistant dans la base");
    }
    
}
