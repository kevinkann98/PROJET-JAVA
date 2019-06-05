/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import static Modele.Connexion.*;
import Modele.Discipline;
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
public class DisciplineDAO extends DAO<Discipline>{
    
    public DisciplineDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<Discipline> all() {
        ArrayList<Discipline> all=new ArrayList<Discipline>();
        try { 
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1=stmt.executeQuery("SELECT * FROM discipline");
            
            while(rs1.first()){
                int id=rs1.getInt("id_discipline");
                Discipline discipline=new Discipline();
                
                discipline=this.find(id);
                all.add(discipline);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DisciplineDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
    }

    @Override
    public Discipline find(int id) {
        Discipline discipline=new Discipline();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmt.executeQuery("SELECT * FROM discipline WHERE id_discipline="+id);
            if(rs.first()){
                String nom=rs.getString("nom");
                
                discipline=new Discipline(id,nom);
            }
            
        } catch (SQLException ex) {
            System.out.println("Non trouvé...");
        }
        
        return discipline;
        
    }

    @Override
    public Discipline create(Discipline obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            PreparedStatement prepare=con.prepareStatement("INSERT INTO discipline (nom) VALUES(?)");
            
            prepare.setString(1, obj.getNom());
            prepare.executeUpdate();
            
            if(prepare!=null){
                rs=stmt.executeQuery("SELECT id_discipline FROM discipline WHERE nom='"+obj.getNom()+"'");
                if(rs.first()){
                    int id=rs.getInt("id_discipline");
                    obj.setId_discipline(id);
                    
                }
                
            }
        } catch (SQLException ex) {
            System.out.println("OUPS");
        }
        
        return obj;
    }

    @Override
    public void delete(Discipline obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("DELETE from discipline WHERE id_discipline="+obj.getId_discipline());
            
            if(rss!=0){
                System.out.println("La discipline"+obj.getNom()+"a été supprimé.");
            }
            else
                throw new SQLException();
            
            
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
        
    }

    @Override
    public Discipline update(Discipline obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("UPDATE discipline SET nom='"+obj.getNom()+"'");
            
            if(rss!=0){
                obj=this.find(obj.getId_discipline());
            }
            else
                throw new SQLException();
            
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
        
        return obj;
        
    }
    
}
