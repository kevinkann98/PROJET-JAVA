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
public class AnneeScolaire {
    private int id_anneeScolaire;
    
    public AnneeScolaire(){
        id_anneeScolaire=0;
    }
    //Constructeur surchargé
    public AnneeScolaire(int anneeSco){
        id_anneeScolaire=anneeSco;
    }

    
    //Getters setters
    public int getId_anneeScolaire() {
        return id_anneeScolaire;
    }

    public void setId_anneeScolaire(int id_anneeScolaire) {
        this.id_anneeScolaire = id_anneeScolaire;
    }
    
    public boolean equals(Object annee){
        return annee instanceof AnneeScolaire &&
               ((AnneeScolaire)annee).id_anneeScolaire==this.id_anneeScolaire;
    }
    
    
    
}