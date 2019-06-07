/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Modele.AnneeScolaire;
import Modele.Connexion;
import static Modele.Connexion.*;
import Modele.Personne;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Joue le rôle de classe intermédiaire entre la table anneescolaire de la BD et la classe AnneeScolaire
 * Gère les requêtes
 * @author kevin
 */
public class AnneeScolaireDAO extends DAO<AnneeScolaire> {
    
    
    public AnneeScolaireDAO() throws ClassNotFoundException, SQLException{
        super();
    }
    
        @Override
    public AnneeScolaire find(int id) {
        AnneeScolaire annee=new AnneeScolaire();
        
        try {            
            
            stmt=this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmt.executeQuery("SELECT * FROM anneescolaire WHERE id_annee="+id);
            
            if(rs.first()){
                int id_annee=rs.getInt("id_annee");
                annee=new AnneeScolaire(id_annee);
                return annee;
            }
            else{
                throw new SQLException();
            }
            
        } catch (SQLException ex) {
            System.out.println("Non trouvé...");
            return annee;
        }
        
    }

    @Override
    public ArrayList<AnneeScolaire> all() {
        
        ArrayList<AnneeScolaire>all=new ArrayList<AnneeScolaire>();
        try {
            Statement stmt=this.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1=stmt.executeQuery("SELECT * FROM anneescolaire");
            
            while(rs1.next()){
                int id=rs1.getInt("id_annee");
                AnneeScolaire annee=new AnneeScolaire();
                annee=this.find(id);
                
                all.add(annee);               
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AnneeScolaireDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
    }

    @Override
    public AnneeScolaire create(AnneeScolaire annee) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            PreparedStatement prepare=con.prepareStatement("INSERT INTO anneescolaire (id_annee) VALUES (?)");
            prepare.setInt(1, annee.getId_anneeScolaire());
            prepare.executeUpdate();
            
            if(prepare!=null){ //Recuperer l'id
                rs=stmt.executeQuery("SELECT id_annee FROM anneescolaire WHERE id_annee="+annee.getId_anneeScolaire());
               if(rs.first()){
                   annee.setId_anneeScolaire(rs.getInt("id_annee"));
                   return annee;
               }
               else
                   throw new SQLException();
            }
            else
                throw new SQLException();
            
            
        } catch (SQLException ex) {
            System.out.println("OUPS");
            return annee;
        }
        
    }

    @Override
    public void delete(AnneeScolaire annee) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            rss=stmt.executeUpdate("DELETE FROM anneescolaire WHERE id_annee="+annee.getId_anneeScolaire());
            if(rss!=0){
                System.out.println(annee.getId_anneeScolaire()+"a été supprimé.");
            }
            else throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(PersonneDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public AnneeScolaire update(AnneeScolaire obj) {
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rss=stmt.executeUpdate("UPDATE anneescolaire SET id_annee="+obj.getId_anneeScolaire());
            
            if(rss!=0){
                obj=this.find(obj.getId_anneeScolaire());
                return obj;
                
            }
            else{
                throw new SQLException();
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée");
            return obj;
        }
        
    }

    
}
