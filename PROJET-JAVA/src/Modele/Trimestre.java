/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

import java.sql.Date;

/**
 *Un trimestre est marqué par un numéro (1,2 ou 3), caractérisé par une date de début, de fin et est lié à une année scolaire
 * @author kevin
 */
public class Trimestre {
    private int id_trimestre;
    private int numero;
    private Date debut;
    private Date fin;
    private AnneeScolaire annee; //Clé étrangère d'une année
            
    
    public Trimestre(){
        id_trimestre=0;
        numero=0;
        debut=new Date(01/01/2019);
        fin=new Date(01/01/2019);
        annee=null;
        
    }

    public Trimestre(int id_trimestre, int numero, Date debut, Date fin, AnneeScolaire annee) {
        this.id_trimestre = id_trimestre;
        this.numero = numero;
        this.debut = debut;
        this.fin = fin;
        this.annee = annee;
    }
    
    /**
     *Affiche le trimestre
     */
    public void afficher(){
        System.out.println("Trimestre "+numero+" au "+debut+"-"+fin);
    }
    
    @Override
    public boolean equals(Object trimestre){
        return trimestre instanceof Trimestre &&
                ((Trimestre) trimestre).annee.equals(this.annee)&&
                ((Trimestre) trimestre).debut.equals(this.debut)&&
                ((Trimestre) trimestre).fin.equals(this.fin)&&
                ((Trimestre) trimestre).numero==this.numero;
                
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

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public AnneeScolaire getAnnee() {
        return annee;
    }

    public void setAnnee(AnneeScolaire annee) {
        this.annee = annee;
    }
    
    
    
}
