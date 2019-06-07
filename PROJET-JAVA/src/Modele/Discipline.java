/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *Une discipline (maths, physique, java...)
 * @author kevin
 */
public class Discipline {
    private int id_discipline;
    private String nom;
    
    public Discipline(){
        id_discipline=0;
        nom="";
    }

    public Discipline(int id_discipline, String nom) {
        this.id_discipline = id_discipline;
        this.nom = nom;
    }

    //Getters setters
    public int getId_discipline() {
        return id_discipline;
    }

    public void setId_discipline(int id_discipline) {
        this.id_discipline = id_discipline;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
    
}
