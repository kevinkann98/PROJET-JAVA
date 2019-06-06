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
                int id_annee=rs.getInt("id_annee");
                int id_ecole=rs.getInt("id_ecole");
                int id_niveau=rs.getInt("id_niveau");
                               
                
                //Instancier une annee              
                AnneeScolaireDAO anneescolairedao=new AnneeScolaireDAO();
                annee=anneescolairedao.find(id_annee);
                
                
                //Instancier un niveau
                NiveauDAO niveaudao=new NiveauDAO();
                niveau=niveaudao.find(id_niveau);
       
                //Instancier l'ecole à l'aide de l'id
                EcoleDAO ecoledao=new EcoleDAO();
                ecole=ecoledao.find(id_ecole);
                            
                
                //Instancier la classe puis la retourner
                classe=new Classe(id,nom,annee,ecole,niveau);
                
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Non trouvé...");
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
            
            PreparedStatement prepare=con.prepareStatement("INSERT INTO classe (nom,id_annee,id_ecole,id_niveau) VALUES(?,?,?,?)");
            prepare.setString(1,nom);
            prepare.setInt(2,id_annee);
            prepare.setInt(3,id_ecole);
            prepare.setInt(4,id_niveau);
            
            prepare.executeUpdate();
            
            if(prepare!=null){
                
            
            //Retourner la personne avec l'id
           rs=stmt.executeQuery("SELECT id_classe FROM classe WHERE nom='"+nom+"' AND id_annee="+id_annee);

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
            
            rss=stmt.executeUpdate("UPDATE classe SET nom='"+classe.getNom()+"' WHERE id_classe="+classe.getId_classe());
            
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
           
            
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            
            ResultSet rs1=stmt.executeQuery("SELECT * FROM classe");
            
            while(rs1.next()){
                int id=rs1.getInt("id_classe");
                                
                //Instancier la classe puis l'ajouter à l'Array de toutes les classes
                Classe classe=new Classe();
                classe=this.find(id);
                
                all.add(classe);
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClasseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
              
    }
    
    
}
