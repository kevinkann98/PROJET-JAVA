/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *Un élève est une personne qui est inscrit à une classe pour l'année de cette classe, laquelle est caractérisée par son niveau
 * @author kevin
 */
public class Inscription {
    private int id_inscription;
    
    private Classe classe;
    private Personne eleve; 
    
    public Inscription(){
        id_inscription=0;
        classe=null;
        eleve=null;
        
    }

    public Inscription(int id_inscription, Classe classe, Personne personne) {
        this.id_inscription = id_inscription;
        this.classe = classe;
        this.eleve = personne;
    }
    
    /**
     *Afficher une inscription
     */
    public void afficher(){
        System.out.println(id_inscription);
        System.out.println(classe.getId_classe()+" "+classe.getNom());
        System.out.println(eleve.getId()+" "+eleve.getNom());
    }
    
    //Getters setters

    public int getId_inscription() {
        return id_inscription;
    }

    public void setId_inscription(int id_inscription) {
        this.id_inscription = id_inscription;
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Personne getPersonne() {
        return eleve;
    }

    public void setPersonne(Personne personne) {
        this.eleve = personne;
    }
    
    
}
