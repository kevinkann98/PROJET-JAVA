/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *Une note précise, avec sa valeur et son appréciation.Elle est rattachée à un détail de bulletin, donc à un bulletin, donc à un trimestre. 
 * @author kevin
 */
public class Evaluation {
    private int id_evaluation;
    private float note;
    private String appreciation;
       
    private DetailBulletin detail; //Rattachée à un detail de bulletin donc à un bulletin

    public Evaluation() {
        id_evaluation=0;
        note=0;
        appreciation="";
        detail=null;
    }

    public Evaluation(int id_evaluation, int note, String appreciation, DetailBulletin detail) {
        this.id_evaluation = id_evaluation;
        this.note = note;
        this.appreciation = appreciation;
        this.detail = detail;
    }

    //Getters setters
    public int getId_evaluation() {
        return id_evaluation;
    }

    public void setId_evaluation(int id_evaluation) {
        this.id_evaluation = id_evaluation;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public DetailBulletin getDetail() {
        return detail;
    }

    public void setDetail(DetailBulletin detail) {
        this.detail = detail;
    }
    
    
}
