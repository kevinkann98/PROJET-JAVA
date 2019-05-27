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
    
    //Constructeur surchargé
    public AnneeScolaire(int anneeSco){
        id_anneeScolaire=anneeSco;
    }

    public int getId_anneeScolaire() {
        return id_anneeScolaire;
    }

    public void setId_anneeScolaire(int id_anneeScolaire) {
        this.id_anneeScolaire = id_anneeScolaire;
    }
    
    
    
}
