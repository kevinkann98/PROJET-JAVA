/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Modele.AnneeScolaire;
import Modele.Connexion;
import static Modele.Connexion.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class AnneeScolaireDAO extends DAO<AnneeScolaire> {
    
    
    public AnneeScolaireDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<AnneeScolaire> all() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public AnneeScolaire create(AnneeScolaire annee) {
        try {
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            PreparedStatement prepare=con.prepareStatement("INSERT INTO anneescolaire (id_annee) VALUES (?)");
            prepare.setInt(1, annee.getId_anneeScolaire());
            prepare.executeUpdate();
            
            if(prepare!=null){
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
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
