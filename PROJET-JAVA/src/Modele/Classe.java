/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author kevin
 */
public class Classe {
    private int id_classe;
    private String nom;
    private AnneeScolaire id_annee;
    private Ecole id_ecole;
    private Niveau id_niveau;
    
    public Classe(String _nom, AnneeScolaire _annee, Ecole _ecole, Niveau _niveau){
        nom=_nom;
        id_annee=_annee;
        id_ecole=_ecole;
        id_niveau=_niveau;
        
        
        
    }

    //Getters setters
    public int getId_classe() {
        return id_classe;
    }

    public String getNom() {
        return nom;
    }

    public AnneeScolaire getId_annee() {
        return id_annee;
    }

    public Ecole getId_ecole() {
        return id_ecole;
    }

    public Niveau getId_niveau() {
        return id_niveau;
    }

    public void setId_classe(int id_classe) {
        this.id_classe = id_classe;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId_annee(AnneeScolaire id_annee) {
        this.id_annee = id_annee;
    }

    public void setId_ecole(Ecole id_ecole) {
        this.id_ecole = id_ecole;
    }

    public void setId_niveau(Niveau id_niveau) {
        this.id_niveau = id_niveau;
    }
    
    
    
    
    //Execute une requête et recupère les données de la table
    public void getClasse(ResultSet rs) throws SQLException{
        StringBuffer buffer= new StringBuffer();
        
        while(rs.next()){
            buffer.append("ID:").append(rs.getInt("id_classe"));
            
            
        
    }
        
    }
    
}
