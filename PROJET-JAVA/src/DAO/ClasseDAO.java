/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
        
        AnneeScolaire annee=new AnneeScolaire();
        Ecole ecole=new Ecole();
        Niveau niveau=new Niveau();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs=stmt.executeQuery("SELECT * FROM classe WHERE id_classe="+id);
            
            if(rs.first()){
                
                String nom=rs.getString("nom");
                
                //Instancier une annee
                int id_annee=rs.getInt("id_annee");
                annee=new AnneeScolaire(id_annee);
       
                //Instancier une ecole
                int id_ecole=rs.getInt("id_ecole");
                ResultSet rs1=stmt.executeQuery("SELECT * FROM ecole WHERE id_ecole="+id_ecole);
                if(rs1.first()){
                    String nom_ecole= rs1.getString("nom");
                    ecole=new Ecole(id_ecole,nom_ecole);
                }
                
                //Instancier un niveau
                int id_niveau=rs.getInt("id_niveau");
                rs1=stmt.executeQuery("SELECT * FROM niveau WHERE id_niveau="+id_niveau);
                if(rs1.first()){
                    String nom_niveau=rs1.getString("nom");
                    niveau=new Niveau(id_niveau,nom_niveau);               
                    
                }
                
                //Instancier la classe puis la retourner
                classe=new Classe(id,nom,annee,ecole,niveau);
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
                
            
            //Retourner la personne avec l'id
           rs=stmt.executeQuery("SELECT id_classe FROM classe WHERE nom='"+nom+"'AND id_annee='"+id_annee+"'AND id_niveau='"+id_niveau+"'");

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
    public void delete(Classe classe) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            rss=stmt.executeUpdate("DELETE FROM classe WHERE id_classe="+classe.getId_classe());
            
            if(rss!=0){
                System.out.println(classe.getId_classe()+classe.getNom()+" a ete supprimé.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @Override
    public Classe update(Classe classe) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            rss=stmt.executeUpdate("UPDATE classe SET nom="+classe.getNom()+"WHERE id_classe="+classe.getId_classe());
            
            if(rss!=0){
                classe=find(classe.getId_classe());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return classe;
               
    }

    @Override
    public ArrayList<Classe> all() {
        ArrayList<Classe> all= new ArrayList<Classe>();
        try {
            
            Classe classe=new Classe();
            
            AnneeScolaire annee=new AnneeScolaire();
            Ecole ecole=new Ecole();
            Niveau niveau=new Niveau();
            
            ResultSet rs1;
            
            
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            rs1=stmt.executeQuery("SELECT * FROM classe");
            
            while(rs1.next()){
                int id=rs1.getInt("id_classe");
                
                
                //Instancier la classe puis l'Array de liste de toutes les classes trouvées
                classe=new Classe();
                classe=this.find(id);
                
                all.add(classe);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
              
    }
    
    
}
