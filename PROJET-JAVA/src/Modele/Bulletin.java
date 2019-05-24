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
    private int id_bulletin;
    private String appreciation;
    private Trimestre id_trimestre;
    private Inscription id_inscription;
    
    
    public Bulletin(int id, String _appreciation, Trimestre tri, Inscription inscription){
        id_bulletin=id;
        appreciation=_appreciation;
        id_trimestre=tri;
        id_inscription=inscription;
    }
    
    
}
