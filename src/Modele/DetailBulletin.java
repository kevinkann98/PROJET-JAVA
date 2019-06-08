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
public class DetailBulletin {
    private int id_detail;
    private String appreciation;
    
    //Clés étrangères
    private Bulletin bulletin;
    private Enseignement enseignement;
    
    public DetailBulletin(){
        id_detail=0;
        appreciation="";
        bulletin=null;
        enseignement=null;
    }

    public DetailBulletin(int id_detail, String appreciation, Bulletin bulletin, Enseignement enseignement) {
        this.id_detail = id_detail;
        this.appreciation = appreciation;
        this.bulletin = bulletin;
        this.enseignement = enseignement;
    }

    //Getters setters
    
    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public Bulletin getBulletin() {
        return bulletin;
    }

    public void setBulletin(Bulletin bulletin) {
        this.bulletin = bulletin;
    }

    public Enseignement getEnseignement() {
        return enseignement;
    }

    public void setEnseignement(Enseignement enseignement) {
        this.enseignement = enseignement;
    }
    
    
    
    
    
}
