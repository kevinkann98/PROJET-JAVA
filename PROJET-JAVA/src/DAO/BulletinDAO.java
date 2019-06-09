/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modele.*;
import static DAO.Connexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Joue le rôle de classe intermédiaire entre la table bulletin de la BD et la classe Bulletin
 * Gère les requêtes
 * @author kevin
 */
public class BulletinDAO extends DAO<Bulletin>{
    
    public BulletinDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    public ArrayList<Bulletin> all() {
        ArrayList<Bulletin> all= new ArrayList<>();
        
        try {         
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);          
            ResultSet rs1=stmt.executeQuery("SELECT * FROM bulletin");           
            while(rs1.next()){
                int id=rs1.getInt("id_bulletin");                                
                //Instancier la classe puis l'ajouter à l'Array de toutes les classes
                Bulletin bulletin=new Bulletin();
                bulletin=this.find(id);
                
                all.add(bulletin);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
    }

    @Override
    public Bulletin find(int id) {
        Bulletin bulletin=new Bulletin();
        Trimestre trimestre=new Trimestre();
        Inscription inscription=new Inscription();
        
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);            
            rs=stmt.executeQuery("SELECT * FROM bulletin WHERE id_bulletin="+id);
            
            if(rs.next()){
                String appreciation=rs.getString("appreciation");
                int id_trimestre=rs.getInt("id_trimestre");
                int id_inscription=rs.getInt("id_inscription");
                
                //Instancier un trimestre
                TrimestreDAO trimestredao=new TrimestreDAO();
                trimestre=trimestredao.find(id_trimestre);
                              
                //Instancier une inscription
                InscriptionDAO inscriptiondao=new InscriptionDAO();
                inscription=inscriptiondao.find(id_inscription);
                
                //Instancier le bulletin à retourner
                bulletin=new Bulletin(id,appreciation,trimestre,inscription);
            }
            
            
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bulletin;
        
    }

    @Override
    public Bulletin create(Bulletin bulletin) {
        Statement stmt;
        
        String appreciation=bulletin.getAppreciation();
        int id_trimestre=bulletin.getTrimestre().getId_trimestre();
        int id_inscription=bulletin.getInscription().getId_inscription();
        
        try {
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            PreparedStatement prepare=con.prepareStatement("INSERT INTO bulletin (appreciation,id_trimestre,id_inscription) VALUES (?,?,?)");
            
            prepare.setString(1, appreciation);
            prepare.setInt(2,id_trimestre);
            prepare.setInt(3,id_inscription);
            
            prepare.executeUpdate();
            
            if(prepare!=null){
                System.out.println("Bulletin ajouté dans la base de données.");
                
                //recuperer l'id
                rs=stmt.executeQuery("SELECT id_bulletin FROM bulletin WHERE id_trimestre="+id_trimestre+" AND id_inscription="+id_inscription);
                if(rs.first()){
                    int id_bulletin=rs.getInt("id_bulletin");                
                    bulletin.setId_bulletin(id_bulletin);
                }
                
                
            }else{
                throw new SQLException();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bulletin;
        
    }

    @Override
    public void delete(Bulletin bulletin) {
        try {
            stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs=stmt.executeQuery("DELETE bulletin WHERE id_bulletin="+bulletin.getId_bulletin());
            
            if(rs.first()){
                System.out.println("Le bulletin numéro "+bulletin.getId_bulletin()+bulletin+"a été supprimé.");
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(BulletinDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override //One ne modifie que l'appréciation!!
    public Bulletin update(Bulletin bulletin) {
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rss=stmt.executeUpdate("UPDATE bulletin SET appreciation='"+bulletin.getAppreciation()+"'");
            
            if(rss!=0){
                bulletin=this.find(bulletin.getId_bulletin());
                
            }
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée");
        }
        
        return bulletin;
    }
    
}
