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
public class Personne {
    private int id_personne;
    private String nom;
    private String prenom;
    private String type;
    
    public Personne(int id, String _nom, String _prenom, String _type){
        id_personne=id;
        nom=_nom;
        prenom=_prenom;
        type=_type;
               
    }
    
}
