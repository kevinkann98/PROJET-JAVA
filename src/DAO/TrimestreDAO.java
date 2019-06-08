/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import Modele.AnneeScolaire;
import static Modele.Connexion.*;
import Modele.Trimestre;
import java.sql.Date;
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
public class TrimestreDAO extends DAO<Trimestre>{
    
    public TrimestreDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<Trimestre> all() {
        ArrayList<Trimestre> all=new ArrayList<Trimestre>();
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1=stmt.executeQuery("SELECT * FROM trimestre");
            
            while(rs1.next()){
                int id=rs1.getInt("id_trimestre");
                
                Trimestre trimestre=new Trimestre();
                trimestre=this.find(id);
                
                all.add(trimestre);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TrimestreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
    }

    @Override
    public Trimestre find(int id) {
        Trimestre trimestre=new Trimestre();
        AnneeScolaire annee=new AnneeScolaire();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmt.executeQuery("SELECT * FROM trimestre WHERE id_trimestre="+id);
            
            if(rs.first()){
                int numero=rs.getInt("numero");
                Date debut=rs.getDate("debut");
                Date fin=rs.getDate("fin");
                
                //Instancier l'année
                int id_annee=rs.getInt("id_annee");
                annee=new AnneeScolaire(id_annee);
                
                trimestre=new Trimestre(id,numero,debut,fin,annee);
            }
            
        } catch (SQLException ex) {
            System.out.println("Non trouvé...");
        }
        
        return trimestre;
        
    }

    @Override
    public Trimestre create(Trimestre obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            PreparedStatement prepare=con.prepareStatement("INSERT INTO trimestre (numero,debut,fin,id_annee) VALUES (?,?,?,?)");
            
            prepare.setInt(1, obj.getNumero());
            prepare.setDate(2, obj.getDebut());
            prepare.setDate(3, obj.getFin());
            prepare.setInt(4, obj.getAnnee().getId_anneeScolaire());
            
            prepare.executeUpdate();
            
            if(prepare!=null){
                rs=stmt.executeQuery("SELECT id_trimestre FROM trimestre WHERE numero="+obj.getNumero()+"debut='"+obj.getDebut()+"'fin='"+obj.getFin()+"'id_annee="+obj.getAnnee().getId_anneeScolaire());
                if(rs!=null){
                    int id=rs.getInt("id_trimestre");                   
                    obj.setId_trimestre(id);
                }
            }
            
            else
                throw new SQLException();
        } catch (SQLException ex) {
            System.out.println("OUPS");
        }
        
        return obj;
        
    }

    @Override
    public void delete(Trimestre obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("DELETE from trimestre WHERE id_trimestre="+obj.getId_trimestre());
            
            if(rss!=0){
                System.out.println("Le trimestre "+obj.getId_trimestre()+" de l'année "+obj.getAnnee().getId_anneeScolaire()+" a été supprimé.");
                
            }
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
    }

    @Override
    public Trimestre update(Trimestre obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("UPDATE trimestre SET numero="+obj.getNumero()+", debut='"+obj.getDebut()+"', fin='"+obj.getFin()+"', id_annee="+obj.getAnnee().getId_anneeScolaire());
            
            if(rss!=0){
                obj=this.find(obj.getId_trimestre());
                
            }
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
        
        
        return obj;
    }
    
}
