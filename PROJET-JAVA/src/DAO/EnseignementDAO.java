/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import Modele.Classe;
import static DAO.Connexion.*;
import Modele.Discipline;
import Modele.Enseignement;
import Modele.Personne;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Joue le rôle de classe intermédiaire entre la table enseignement de la BD et la classe Enseignement
 * Gère les requêtes
 * @author kevin
 */
public class EnseignementDAO extends DAO<Enseignement>{
    
    public EnseignementDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<Enseignement> all() {
        ArrayList<Enseignement> all=new ArrayList<Enseignement>();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1=stmt.executeQuery("SELECT * FROM enseignement");
            
            while(rs1.next()){
                int id=rs1.getInt("id_enseignement");
                Enseignement enseignement=new Enseignement();
                
                enseignement=this.find(id);
                all.add(enseignement);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnseignementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
        
    }

    @Override
    public Enseignement find(int id) {
        Enseignement enseignement=new Enseignement();
        
        Classe classe=new Classe();
        Personne personne=new Personne();
        Discipline discipline=new Discipline();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmt.executeQuery("SELECT * FROM enseignement WHERE id_enseignement="+id);
            
            if(rs.first()){
                //Récuperer l'id de la classe et ses champs 
                int id_classe=rs.getInt("id_classe");
                int id_personne=rs.getInt("id_personne");
                int id_discipline=rs.getInt("id_discipline");
                
                //Instancier la classe
                ClasseDAO classedao=new ClasseDAO();
                classe=classedao.find(id_classe);
                
                //Instancier l'enseignant
                PersonneDAO personnedao=new PersonneDAO();
                personne=personnedao.find(id_personne);
                
                //Instancier la discipline
                DisciplineDAO disciplinedao=new DisciplineDAO();
                discipline=disciplinedao.find(id_discipline);  
                
                //Instancier l'enseignement puis le retourner
                enseignement=new Enseignement(id,classe,personne,discipline);
                
                
                
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EnseignementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return enseignement;
    }

    @Override
    public Enseignement create(Enseignement obj) {
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            PreparedStatement prepare=con.prepareStatement("INSERT INTO enseignement(id_classe,id_personne,id_discipline) VALUES(?,?,?)");
            
            prepare.setInt(1, obj.getClasse().getId_classe());
            prepare.setInt(2, obj.getPersonne().getId());
            prepare.setInt(3, obj.getDiscipline().getId_discipline());
            prepare.executeUpdate(); //Execution de la requête
            
            
           rs=stmt.executeQuery("SELECT id_enseignement FROM enseignement WHERE id_classe="+obj.getClasse().getId_classe()+"AND id_personne="+obj.getPersonne().getId()+"AND id_discipline="+obj.getDiscipline().getId_discipline());
       
           if(rs.first()){
               int id=rs.getInt("id_enseignement");
               obj.setId_enseignement(id);
               
           }
        } catch (SQLException ex) {
            Logger.getLogger(EnseignementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }

    @Override
    public void delete(Enseignement obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rs=stmt.executeQuery("DELETE FROM enseignement WHERE id_enseignement="+obj.getId_enseignement());
            
            if(rs.first()){
                System.out.println("L'enseignement "+obj.getDiscipline().getNom()+" enseigné par "+obj.getPersonne().getNom()+"a étét supprimé.");
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(EnseignementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    @Override
    public Enseignement update(Enseignement obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("UPDATE enseignement SET id_classe="+obj.getClasse().getId_classe()+", id_personne="+obj.getPersonne().getId()+", id_discipline="+obj.getDiscipline().getId_discipline());
            
            if(rss!=0){
                obj=this.find(obj.getId_enseignement());
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            Logger.getLogger(EnseignementDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return obj;
    }
    
}
