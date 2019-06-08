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
public class Inscription {
    private int id_inscription;
    
    private Classe classe;
    private Personne personne; 
    
    public Inscription(){
        id_inscription=0;
        classe=null;
        personne=null;
        
    }

    public Inscription(int id_inscription, Classe classe, Personne personne) {
        this.id_inscription = id_inscription;
        this.classe = classe;
        this.personne = personne;
    }
    
    /**
     *Afficher une inscription
     */
    public void afficher(){
        System.out.println(id_inscription);
        System.out.println(classe.getId_classe()+" "+classe.getNom());
        System.out.println(personne.getId()+" "+personne.getNom());
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
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }
    
    
}
