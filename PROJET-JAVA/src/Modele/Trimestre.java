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
public class Trimestre {
    private int id_trimestre;
    private int numero;
    private int debut;
    private int fin;
    private AnneeScolaire annee; //Clé étrangère d'une année
            
    
    public Trimestre(){
        id_trimestre=0;
        numero=0;
        debut=0;
        fin=0;
        annee=null;
        
    }

    public Trimestre(int id_trimestre, int numero, int debut, int fin, AnneeScolaire annee) {
        this.id_trimestre = id_trimestre;
        this.numero = numero;
        this.debut = debut;
        this.fin = fin;
        this.annee = annee;
    }
    
    //Getters setters

    public int getId_trimestre() {
        return id_trimestre;
    }

    public void setId_trimestre(int id_trimestre) {
        this.id_trimestre = id_trimestre;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getDebut() {
        return debut;
    }

    public void setDebut(int debut) {
        this.debut = debut;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }

    public AnneeScolaire getAnnee() {
        return annee;
    }

    public void setAnnee(AnneeScolaire annee) {
        this.annee = annee;
    }
    
    
    
}
