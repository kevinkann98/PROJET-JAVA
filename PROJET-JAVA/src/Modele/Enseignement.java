/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *Un enseignement d'une discipline est dispensé par un enseignant à une classe
 * @author kevin
 */
public class Enseignement {
    private int id_enseignement;
    private Classe classe;
    private Personne personne;
    private Discipline discipline;
    
    public Enseignement(){
        id_enseignement=0;
        classe=null;
        personne=null;
        discipline=null;
    }

    public Enseignement(int id_enseignement, Classe classe, Personne personne, Discipline discipline) {
        this.id_enseignement = id_enseignement;
        this.classe = classe;
        this.personne = personne;
        this.discipline = discipline;
    }

    //Getters setters
    public int getId_enseignement() {
        return id_enseignement;
    }

    public void setId_enseignement(int id_enseignement) {
        this.id_enseignement = id_enseignement;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    
    
    
}
