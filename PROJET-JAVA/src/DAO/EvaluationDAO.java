/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import static Modele.Connexion.*;
import Modele.DetailBulletin;
import Modele.Evaluation;
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
public class EvaluationDAO extends DAO<Evaluation>{
    
    public EvaluationDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<Evaluation> all() {
        ArrayList<Evaluation> all= new ArrayList<Evaluation>();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);           
            ResultSet rs1=stmt.executeQuery("SELECT * FROM evaluation");
            while(rs1.next()){
                
                int id=rs1.getInt("id_evaluation");
                Evaluation evaluation=new Evaluation();
                
                evaluation=this.find(id);
                all.add(evaluation);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
    }

    @Override
    public Evaluation find(int id) {
        Evaluation evaluation=new Evaluation();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs=stmt.executeQuery("SELECT * FROM evaluation WHERE id_evaluation="+id);
            if(rs.first()){
                int note=rs.getInt("note");
                String appreciation=rs.getString("appreciation");
                int id_detail=rs.getInt("id_detail");
                
                //Instancier le detail de bulletin 
                DetailBulletin detailbulletin=new DetailBulletin();
                DetailBulletinDAO detailbulletindao=new DetailBulletinDAO();
                
                detailbulletin=detailbulletindao.find(id_detail);
                
                
            }
            else
                throw new SQLException();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EvaluationDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evaluation;
        
    }

    @Override
    public Evaluation create(Evaluation obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            PreparedStatement prepare=con.prepareStatement("INSERT INTO evaluation (note,appreciation,id_detail) VALUES (?,?,?)");
            
            prepare.setInt(1, obj.getNote());
            prepare.setString(2, obj.getAppreciation());
            prepare.setInt(3, obj.getDetail().getId_detail());
            prepare.executeUpdate();
            
            if(prepare!=null){
                rs=stmt.executeQuery("SELECT id_evaluation WHERE note="+obj.getNote()+"AND appreciation='"+obj.getAppreciation()+"' AND id_detail="+obj.getDetail().getId_detail());
                
                if(rs.first()){
                    int id=rs.getInt("id_evaluation");
                    obj.setId_evaluation(id);
                }
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
        
        return obj;
    }

    @Override
    public void delete(Evaluation obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("DELETE FROM evaluation WHERE id_evaluation="+obj.getId_evaluation());
            
            if(rss!=0){
                System.out.println("L'evaluation"+obj.getId_evaluation()+"a été supprimé.");
                
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
    }

    @Override
    public Evaluation update(Evaluation obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rss=stmt.executeUpdate("UPDATE evaluation SET note="+obj.getNote()+", appreciation='"+obj.getAppreciation()+"', id_detail="+obj.getDetail().getId_detail());
        
            if(rss!=0){
                obj=find(obj.getId_evaluation());
            }
            else
                throw new SQLException();
            
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
        
        return obj;
    }
    
}
