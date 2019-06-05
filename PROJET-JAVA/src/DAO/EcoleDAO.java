/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import static Modele.Connexion.*;
import Modele.Ecole;
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
public class EcoleDAO extends DAO<Ecole>{
    
    public EcoleDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<Ecole> all() {
        ArrayList<Ecole> all=new ArrayList<Ecole>();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1=stmt.executeQuery("SELECT * FROM ecole");
            
            while(rs1.first()){
                int id=rs1.getInt("id_ecole");
                Ecole ecole=new Ecole();
                ecole=this.find(id);
                
                all.add(ecole);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
        
    }

    @Override
    public Ecole find(int id) {
        Ecole ecole=new Ecole();
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("SELECT * FROM ecole WHERE id_ecole="+id);
            
            if(rs.first()){
                String nom=rs.getString("nom");              
                ecole=new Ecole(id,nom);
            }
            else
                throw new SQLException();
            
        } catch (SQLException ex) {
            System.out.println("Non trouvé...");
        }
        
        return ecole;
    }

    @Override
    public Ecole create(Ecole obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            PreparedStatement prepare=con.prepareStatement("INSERT INTO ecole(nom) VALUES(?)");
            prepare.setString(1, obj.getNom());
            prepare.executeUpdate();
            
            if(prepare!=null){
                rs=stmt.executeQuery("SELECT id_ecole WHERE nom="+obj.getNom());
                if(rs.first()){
                    int id=rs.getInt("id_ecole");
                    obj.setId_ecole(id);
                }
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }

    @Override
    public void delete(Ecole obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("DELETE FROM ecole WHERE id_ecole="+obj.getId_ecole());
            
            if(rss!=0){
                System.out.println("L'école "+obj.getNom()+" a été supprimé.");
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Ecole update(Ecole obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("UPDATE ecole SET nom="+obj.getNom());
            
            if(rss!=0){
                obj=this.find(obj.getId_ecole());
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(EcoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }
    
    
}
