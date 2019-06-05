/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modele.*;
import static Modele.Connexion.*;
import java.sql.*;
import java.util.ArrayList;
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
            rs=Connexion.getStmt().executeQuery("SELECT * FROM personne WHERE id_personne="+id); //Execution de la requête
            
            if(rs.first()){ //Instancier un objet Personne
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                String type=rs.getString("type");
                
                personne=new Personne(id,nom,prenom,type);
                
            }
        }
        catch(SQLException e){
            System.out.println("Non trouvé...");
        }
        
        return personne;
        
        
    }

    @Override
    public Personne create(Personne personne) { 
        try {
                                  
       
        Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        
        PreparedStatement prepare=con.prepareStatement("INSERT INTO personne(nom,prenom,type) VALUES(?,?,?)");
        prepare.setString(1, personne.getNom());
        prepare.setString(2, personne.getPrenom());
        prepare.setString(3, personne.getType());
        
        prepare.executeUpdate();
        
        if(prepare!=null){
           
            //Retourner la personne avec l'id
           rs=stmt.executeQuery("SELECT id_personne FROM personne WHERE nom='"+personne.getNom()+"'AND prenom='"+personne.getPrenom()+"'AND type='"+personne.getType()+"'");

           if(rs.first()){              
               int id=rs.getInt("id_personne");
               personne.setId(id);          
               
           }
        }       
        else{
            throw new SQLException();       
        }
  
        
        } catch (SQLException ex) {
            System.out.println("OUPS");
            
        }
        return personne;       
    }

    
    @Override
    public void delete(Personne obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            rss=stmt.executeUpdate("DELETE FROM personne WHERE id_personne="+obj.getId());
            if(rss!=0){
                System.out.println(obj.getId()+obj.getNom()+"a été supprimé.");
            }
            else throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public Personne update(Personne obj) {
        try{
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE); 
            rss=stmt.executeUpdate("UPDATE personne SET nom='"+obj.getNom()+"',prenom='"+obj.getPrenom()+"',type='"+obj.getType()+"' WHERE id_personne="+obj.getId()); //modifier le nom à partir de l'id
            
            if(rss!=0){                
                obj=this.find(obj.getId()); //retourner l'objet modifié
                
            }
            else{
                
                throw new SQLException();
            }
            
            
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée");
        }
        return obj;
        
    }

    public ArrayList<Personne> all(String type) {
        
        ArrayList<Personne> all= new ArrayList<Personne>();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);           
            ResultSet rs1=stmt.executeQuery("SELECT * FROM personne");
            
            while(rs1.next()){
                int id=rs1.getInt("id_personne");                
                
                Personne personne=new Personne();
                personne=this.find(id);
                all.add(personne);   
            }
                     
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;       
    }

    @Override
    public ArrayList<Personne> all() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
