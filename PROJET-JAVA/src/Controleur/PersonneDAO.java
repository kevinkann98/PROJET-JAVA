/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import static Modele.Connexion.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class PersonneDAO extends DAO<Personne>{

    @Override
    
    public Personne find(int id) {
        Personne personne=new Personne();
        try{
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmt.executeQuery("SELECT * FROM personne WHERE id_personne="+id); //Execution de la requête
            
            if(rs.first()){ //Instancier un objet Personne
                personne=new Personne();
                
            }
        }
        catch(SQLException e){
            e.printStackTrace();
            
        }
        
        return personne;
        
        
    }

    @Override
    public Personne create(Personne personne) { 
        try {
            String _nom=personne.getNom();
            String _prenom=personne.getPrenom();
            String _type=personne.getType();
            
        //Crée une personne et l'ajoute dans la bdd       
        //Crer un id 
        stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        rss=stmt.executeUpdate("INSERT INTO personne(nom,prenom,type) VALUES(_nom,_prenom,_type)");
       
        
        //Retourner la personne avec l'id
       rs=stmt.executeQuery("SELECT id_personne from personne WHERE nom="+_nom+"AND prenom="+_prenom+"AND type="+_type);
       
       if(rs.first()){
           int id=rs.getInt("id_personne");
           personne.setId(id);
       }
        
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return personne;
        
        
    }

    @Override
    public void delete(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Personne update(Personne obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    
    public Personne all() {
        
        return null;
       
    }
    
    
}
