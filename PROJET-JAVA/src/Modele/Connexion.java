/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import Vue.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class Connexion {
    
    //Retourne vrai si connexion reussie sinon false
    static boolean connectionOk(String username, String password, String server, String database) throws ClassNotFoundException{
          Class.forName("com.mysql.jdbc.Driver");
          Connection con = null;
        
        try{
            
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+database+"", username, password);
            return con!=null;
            
        }
        catch(SQLException e){
            return false;         
        }
        finally{
            if(con!=null){
                try{
                    //Fermer la connexion
                    con.close();
                }
                catch(SQLException e){
                    
                }
                
            }
        }
        
    }
    
    //Verification des éléments de connexion à la bdd
    public static boolean getConnection(String username, String password, String serveur, String bdd){
        try {
            //Verif de connexion
            return connectionOk(username,password,serveur,bdd);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
