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
    
    public PersonneDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    
    @Override
    
    public Personne find(int id) {
        Personne personne=new Personne();
        try{
            Connexion.stmt=this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            Connexion.rs=Connexion.getStmt().executeQuery("SELECT * FROM personne WHERE id_personne="+id); //Execution de la requête
            
            if(rs.first()){ //Instancier un objet Personne
                personne=new Personne();
                
            }
        }
        catch(SQLException e){
        }
        
        return personne;
        
        
    }

    @Override
    public Personne create(Personne personne) { 
        try {
                       
        //Crée une personne et l'ajoute dans la bdd       
        //Crer un id 
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        
        PreparedStatement prepare=con.prepareStatement("INSERT INTO personne(nom,prenom,type) VALUES(?,?,?)");
        prepare.setString(1, personne.getNom());
        prepare.setString(2, personne.getPrenom());
        prepare.setString(3, personne.getType());
        
        prepare.executeUpdate();
        
        if(prepare!=null){
            System.out.println("Personne ajoutée dans la base.");
        }
        else{
            throw new SQLException();       
        }
              
        //Retourner la personne avec l'id
       rs=stmt.executeQuery("SELECT id_personne FROM personne WHERE nom="+personne.getNom()+"AND prenom="+personne.getPrenom()+"AND type="+personne.getType());
       
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
