/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Connection;
import java.sql.Statement;
import Vue.LoginPage;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class Personne {
    private static Statement stmt=null;
    
    
    private int id_personne;
    private String nom;
    private String prenom;
    private String type;
    
    public Personne(){
        id_personne=0;
        nom="";
        prenom="";
        type="";     
    }
    
    /**
     *
     * @param id 
     * @param _nom 
     * @param _prenom
     * @param _type
     */
    public Personne(int id, String _nom, String _prenom, String _type){
        id_personne=id;
        nom=_nom;
        prenom=_prenom;
        type=_type;              
    }
    
    //Afficher les infos de la personne
    public void afficher(){
        System.out.println("Voici la personne: ");
        System.out.println(id_personne);
        System.out.println(nom);
        System.out.println(prenom);
        System.out.println(type);
        
    }
    
    /**
     * Recup toutes les personnes inscrites de la bdd
     * @throws SQLException
     */
    public static void getAllPersonnes() throws SQLException{
        try{
            //Appel au module de connexion
            
            stmt=Connexion.getCon().createStatement();
            ResultSet rs=stmt.executeQuery("SELECT * FROM personne");
            
            System.out.println("Voici les personnes:");
            while(rs.next()){
                
                System.out.println(rs.getString("nom"));
            }
            
        }
        catch(SQLException e){
            System.out.println("Aucune personne n'est présente dans la base.");
            
        }
        
    }
    
    /**
     * Ajouter la personne dans la table personne de la bdd
     * @param personne de type Personne
     * @throws java.lang.ClassNotFoundException
     * @throws SQLException
     */
    
    
    
    
    
    //getters setters
    public int getId(){
        return id_personne;
    }
    
    public String getNom(){
        return nom;
    }
    
    public String getPrenom(){
        return prenom;
    }
    
    public String getType(){
        return type;
    }
    
    public void setId(int id){
        id_personne=id;
    }
    
}
