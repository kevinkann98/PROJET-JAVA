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
public class Bulletin {
    private final int id_bulletin;
    private final String appreciation;  
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
    
    public int getId_bulletin() {
        return id_bulletin;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public Trimestre getTrimestre() {
        return trimestre;
    }

    public Inscription getInscription() {
        return inscription;
    }
    
    
    
}
