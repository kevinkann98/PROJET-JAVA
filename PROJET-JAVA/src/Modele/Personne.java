/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import static DAO.Connexion.*;
import java.sql.Connection;
import java.sql.Statement;
import Vue.LoginPage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *Une personne est soit un enseignant soit un étudiant
 * @author kevin
 */
public class Personne implements Comparable<Personne>{
    
       
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
     * @param id id 
     * @param _nom nom
     * @param _prenom prenom
     * @param _type type
     */
    public Personne(int id, String _nom, String _prenom, String _type){
        id_personne=id;
        nom=_nom;
        prenom=_prenom;
        type=_type;              
    }
    

    /**
     Affiche les caractéristiques de la personne
     */
    public void afficher(){
        System.out.println("Voici la personne: ");
        System.out.println(id_personne);
        System.out.println(nom);
        System.out.println(prenom);
        System.out.println(type);
        
    }
    
    
    /**
     * 
     * @param personne
     * @return
     */
    @Override
    public boolean equals(Object personne){
        
        return personne instanceof Personne && 
                ((Personne)personne).id_personne==this.id_personne &&
                ((Personne)personne).nom.equals(this.nom) && 
                ((Personne)personne).prenom.equals(this.prenom) &&
                ((Personne)personne).type.equals(this.type);
                   
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id_personne;
        hash = 97 * hash + Objects.hashCode(this.nom);
        hash = 97 * hash + Objects.hashCode(this.prenom);
        hash = 97 * hash + Objects.hashCode(this.type);
        return hash;
    }
    
    
    
    
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
