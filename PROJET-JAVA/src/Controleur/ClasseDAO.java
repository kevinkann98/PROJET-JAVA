/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import static Modele.Connexion.*;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class ClasseDAO extends DAO<Classe>{
    
    public ClasseDAO() throws ClassNotFoundException, SQLException{
        super();
    }
    
    @Override
    public Classe find(int id) {
        Classe classe=new Classe();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs=stmt.executeQuery("SELECT * FROM classe WHERE id_classe="+id);
            
            if(rs.first()){
                rs.getInt("id_classe");
                rs.getString("nom");
                rs.getInt("id_annee");
                rs.getInt("id_ecole");
                rs.getInt("id_niveau");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classe;
        
    }

    @Override
    public Classe create(Classe classe) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            String nom=classe.getNom();
            int id_annee=classe.getAnnee().getId_anneeScolaire();
            int id_ecole=classe.getEcole().getId_ecole();
            int id_niveau=classe.getNiveau().getId_niveau();
            
            PreparedStatement prepare=con.prepareStatement("INSERT INTO classe VALUES (nom,id_annee,id_ecole,id_niveau) VALUES(?,?,?,?)");
            prepare.setString(1,nom);
            prepare.setInt(2,id_annee);
            prepare.setInt(3,id_ecole);
            prepare.setInt(4,id_niveau);
            
            prepare.executeUpdate();
            
            if(prepare!=null){
            System.out.println("Classe ajoutée dans la base.");
            
            //Retourner la personne avec l'id
           rs=stmt.executeQuery("SELECT id_classe FROM classe WHERE nom="+nom+"AND id_annee="+id_annee+"AND id_niveau="+id_niveau);

           if(rs.first()){
               int id=rs.getInt("id_classe");
               classe.setId_classe(id);
           }
        }
        
        else{
            throw new SQLException();       
        }
   
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return classe;
        
    }

    @Override
    public void delete(Classe obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Classe update(Classe obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Classe> all() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
