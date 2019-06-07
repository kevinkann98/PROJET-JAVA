/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import Modele.Bulletin;
import static Modele.Connexion.*;
import Modele.DetailBulletin;
import Modele.Enseignement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Joue le rôle de classe intermédiaire entre la table detailbulletin de la BD et la classe DetailBulletin
 *Gère les requêtes
 * @author kevin
 */
public class DetailBulletinDAO extends DAO<DetailBulletin>{

    public DetailBulletinDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<DetailBulletin> all() {
        ArrayList<DetailBulletin> all=new ArrayList<DetailBulletin>();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);           
            ResultSet rs1=stmt.executeQuery("SELECT * FROM detailbulletin");
            
            while(rs1.next()){
                int id=rs1.getInt("id_detail");               
                DetailBulletin bulletin=new DetailBulletin();
                bulletin=this.find(id);               
                all.add(bulletin);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }

    @Override
    public DetailBulletin find(int id) {
        DetailBulletin detailbulletin=new DetailBulletin();
        
        try {  
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs=stmt.executeQuery("SELECT * FROM detailbulletin WHERE id_detail="+id);
            if(rs.first()){
                String appreciation=rs.getString("appreciation");
                int id_bulletin=rs.getInt("id_bulletin");
                int id_enseignement=rs.getInt("id_enseignement");
                
                //Instanciation du bulletin correspondant
                
                Bulletin bulletin=new Bulletin();
                BulletinDAO bulletindao=new BulletinDAO();
                bulletin=bulletindao.find(id_bulletin);
                
                //Instanciation de l'enseignement correspondant
                
                Enseignement enseignement=new Enseignement();
                EnseignementDAO enseignementDAO=new EnseignementDAO();
                enseignement=enseignementDAO.find(id_enseignement);
                
                //Instanciation de l'objet puis retourner
                detailbulletin=new DetailBulletin(id,appreciation,bulletin,enseignement);
                
            }
            else
                throw new SQLException();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return detailbulletin;
        
    }

    @Override
    public DetailBulletin create(DetailBulletin obj) {
        try {  
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            PreparedStatement prepare=con.prepareStatement("INSERT INTO detailbulletin(appreciation,id_bulletin,id_enseignement) VALUES (?,?,?)");
        
            prepare.setString(1, obj.getAppreciation());
            prepare.setInt(2, obj.getBulletin().getId_bulletin());
            prepare.setInt(3,obj.getEnseignement().getId_enseignement());
            prepare.executeUpdate();
            
            if(prepare!=null){
                rs=stmt.executeQuery("SELECT id_detail FROM detailbulletin WHERE id_bulletin="+obj.getBulletin().getId_bulletin()+"AND id_enseignement="+obj.getEnseignement().getId_enseignement());
           
                if(rs.first()){
                    int id=rs.getInt("id_detail");
                    obj.setId_detail(id);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
        
    }

    @Override
    public void delete(DetailBulletin obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs=stmt.executeQuery("DELETE FROM detailbulleint WHERE id_detail="+obj.getId_detail());
            if(rs.first()){
                System.out.println("Le detail de bulletin a été supprimé du bulletin "+obj.getBulletin().getId_bulletin());
            }
            else
                throw new SQLException();
            
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public DetailBulletin update(DetailBulletin obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("UPDATE detailbulletin SET id_bulletin="+obj.getBulletin().getId_bulletin()+", id_enseignement="+obj.getEnseignement().getId_enseignement());
        
            if(rss!=0){
                obj=this.find(obj.getId_detail());
            }
            else throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(DetailBulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }
  
}
