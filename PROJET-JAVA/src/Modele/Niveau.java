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
public class Niveau {
    private int id_niveau;
    private String nom;
    
    public Niveau(){
        
    }
    
    public Niveau(int id, String _nom){
        id_niveau=id;
        nom=_nom;
        
    }
    
    //Getters setters

    public int getId_niveau() {
        return id_niveau;
    }

    public void setId_niveau(int id_niveau) {
        this.id_niveau = id_niveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    @Override
    public boolean equals(Object niveau){
        return niveau instanceof Niveau &&
                ((Niveau) niveau).id_niveau==this.id_niveau &&
                ((Niveau) niveau).nom.equals(this.nom);
    }
}
