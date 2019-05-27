/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import Vue.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author kevin
 */
public class Connexion {
    public static Connection con=null;
    public static Statement stmt=null;
    public static ResultSet rs=null;
    public static int rss=0;
    private static String serveur;
    private static String bdd;
    private static String username;
    private static String password;  
       
    

    /**
     * Retourne true si connexion reussie sinon false
     * @return
     * @throws ClassNotFoundException
     */
    static public boolean connectionOk() throws ClassNotFoundException{
             
                
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bdd+"", username, password);
            System.out.println("Connexion à la base "+bdd+" OK");            
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
    
    /**
     *
     * @param sql Requete sql en param
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public static void executeSQL(String sql)throws SQLException, ClassNotFoundException{
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bdd, username, password);
            
            stmt=con.createStatement();
            int rs=stmt.executeUpdate(sql);//Retourne le nbr de colonne affecté par le changement
            
            
            if(rs!=0){
                System.out.println("Ajoute OK");
            }
            else{ //Si aucune ligne est maj, lancer l'exception
                throw new SQLException();
            }
            
        }
        catch(SQLException e){
            System.out.println("OUPS");
            
        }
    } 
    
    
    
    
    
    //Getter setters
    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        Connexion.con = con;
    }

    public static String getServeur() {
        return serveur;
    }

    public static void setServeur(String serveur) {
        Connexion.serveur = serveur;
    }

    public static String getBdd() {
        return bdd;
    }

    public static void setBdd(String bdd) {
        Connexion.bdd = bdd;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Connexion.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Connexion.password = password;
    }  
}

