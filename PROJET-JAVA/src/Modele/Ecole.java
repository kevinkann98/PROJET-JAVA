/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author kevin
 */
public class Ecole {
    private int id_ecole;
    private String nom;

    public Ecole() {
        id_ecole=0;
        nom="";
    }

    public Ecole(int id_ecole, String nom) {
        this.id_ecole = id_ecole;
        this.nom = nom;
    }
    
    //Getters setters

    public int getId_ecole() {
        return id_ecole;
    }

    public void setId_ecole(int id_ecole) {
        this.id_ecole = id_ecole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Override
    public boolean equals(Object ecole){
        
        return ecole instanceof Ecole &&
                ((Ecole) ecole).id_ecole==this.id_ecole &&
                ((Ecole) ecole).nom.equals(this.nom);
        
    }
    
    
    
    
}
