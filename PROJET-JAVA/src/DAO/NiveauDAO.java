/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import Modele.Niveau;
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
public class NiveauDAO extends DAO<Niveau>{
    
    public NiveauDAO() throws ClassNotFoundException, SQLException {
        super();
    }

    @Override
    public ArrayList<Niveau> all() {
        ArrayList<Niveau> niveaux=new ArrayList<Niveau>();
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1=stmt.executeQuery("SELECT * FROM niveau");
            
            while(rs1.first()){ //recuperer les id puis utiliser methode find pour recuperer les champs associés
                int id_niveau=rs1.getInt("id_niveau");
                
                Niveau niveau=new Niveau();
                niveau=this.find(id_niveau);
                
                niveaux.add(niveau);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NiveauDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return niveaux;
    }

    @Override
    public Niveau find(int id) {
        
        Niveau niveau=new Niveau();
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmt.executeQuery("SELECT * FROM niveau WHERE id_niveau="+id);
            
            if(rs.first()){
                String nom=rs.getString("nom");               
                niveau=new Niveau(id,nom);
            }
            else
                throw new SQLException();
            
        } catch (SQLException ex) {
            System.out.println("Non trouvé...");
        }
        return niveau;
    }

    @Override
    public Niveau create(Niveau obj) {
        try{
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            PreparedStatement prepare=con.prepareStatement("INSERT INTO niveau(nom) VALUES(?)");
            prepare.setString(1, obj.getNom());
            prepare.executeUpdate();
            
            if(prepare!=null){
                rs=stmt.executeQuery("SELECT id_niveau FROM niveau WHERE nom="+obj.getNom());
                if(rs.first()){
                    
                    int id_niveau=rs.getInt("id_niveau");
                    obj.setId_niveau(id_niveau);
                    
                }
                                
            }else{
                throw new SQLException();                   
            }
            
        }
        
        catch(SQLException e){
            System.out.println("OUPS");
        }
        
        return obj;
    }

    @Override
    public void delete(Niveau obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("DELETE FROM niveau WHERE id_niveau="+obj.getId_niveau());
            if(rss!=0){
                System.out.println("Le ninveau "+obj.getNom()+"a été supprimé");
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
        
    }

    @Override
    public Niveau update(Niveau obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("UPDATE niveau SET nom="+obj.getNom());
            if(rss!=0){
                obj=this.find(obj.getId_niveau());
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
           
        }
        return obj;
    }
    
}
