/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class Classe {
    private int id_classe;
    private String nom;
    
    //Clés étrangères
    private AnneeScolaire annee;
    private Ecole ecole;
    private Niveau niveau;
    
    
    //Constructeur par défaut
    public Classe(){
        nom="";
        annee=null;
        ecole=null;
        niveau=null;
        
    }
    //Constructeur surcchargé
    public Classe(int _id,String _nom, AnneeScolaire _annee, Ecole _ecole, Niveau _niveau){
        id_classe=_id;
        nom=_nom;
        annee=_annee;
        ecole=_ecole;
        niveau=_niveau;
           
    }


    //Getters setters

    public int getId_classe() {
        return id_classe;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public AnneeScolaire getAnnee() {
        return annee;
    }

    public void setAnnee(AnneeScolaire annee) {
        this.annee = annee;
    }

    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }
    
    public void afficher(){
        System.out.println("Id:"+id_classe);
        System.out.println("Nom:"+nom);
        System.out.println("Annee Scolaire:"+annee.getId_anneeScolaire());
        System.out.println("Ecole:"+ecole.getNom());
        System.out.println("Niveau:"+niveau.getNom());
    }
    
}
