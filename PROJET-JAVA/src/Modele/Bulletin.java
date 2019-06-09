/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *Un bulletin contient un id, une appreciation, appartient à un trimestre et à uns inscription
 * @author kevin
 */
public class Bulletin {
    private int id_bulletin;
    private String appreciation;  
    private final Trimestre trimestre;
    private final Inscription inscription;

    //Constructeur par défaut
    public Bulletin(){
        id_bulletin=0;
        appreciation="";
        trimestre=null;
        inscription=null;
        
    }
    
    //Constructeur surchargé
    public Bulletin(int id, String _appreciation, Trimestre tri, Inscription _inscription){
        id_bulletin=id;
        appreciation=_appreciation;
        trimestre=tri;
        inscription=_inscription;
    }
    
    /**
     * Affiche le bulletin en console
     */
    public void afficher(){
        System.out.println("Voici le bulletin:");
        System.out.println(id_bulletin);
        System.out.println(appreciation);
        trimestre.afficher();
        inscription.afficher();
    }
    
    
    @Override
    public boolean equals(Object bulletin){
       return bulletin instanceof Bulletin &&
               ((Bulletin) bulletin).appreciation.equals(this.appreciation)&&
               ((Bulletin) bulletin).id_bulletin==this.id_bulletin &&
               ((Bulletin) bulletin).inscription.equals(this.inscription)&&
               ((Bulletin) bulletin).trimestre.equals(this.trimestre);    
    }
    
    
    //Getters setters
    
    public int getId_bulletin() {
        return id_bulletin;
    }
    
    public void setId_bulletin(int id){
        id_bulletin=id;
    }

    public String getAppreciation() {
        return appreciation;
    }
    
    public void setAppreciation(String appreciation){
        this.appreciation=appreciation;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public Inscription getInscription() {
        return inscription;
    }
    
    
    
    
    
}
