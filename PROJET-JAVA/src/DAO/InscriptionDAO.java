/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.DAO.con;
import Modele.AnneeScolaire;
import Modele.Classe;
import static Modele.Connexion.*;
import Modele.Ecole;
import Modele.Inscription;
import Modele.Niveau;
import Modele.Personne;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Joue le rôle de classe intermédiaire entre la table inscription de la BD et la classe Inscription
 * Gère les requêtes
 * @author kevin
 */
public class InscriptionDAO extends DAO<Inscription>{
    
    public InscriptionDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<Inscription> all() {
        ArrayList<Inscription> all=new ArrayList<Inscription>();
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs1=stmt.executeQuery("SELECT * FROM inscription");
            
            while(rs1.next()){
                int id=rs1.getInt("id_inscription");
                
                Inscription inscription=new Inscription();
                inscription=this.find(id);
                
                all.add(inscription);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(InscriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return all;
    }

    @Override
    public Inscription find(int id) {
        
        Inscription inscription=new Inscription();
        Classe classe=new Classe();
        Personne personne=new Personne();
        
        try{
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);            
            rs=stmt.executeQuery("SELECT * FROM inscription WHERE id_inscription="+id);

            if(rs.first()){

                int id_classe=rs.getInt("id_classe");
                int id_personne=rs.getInt("id_personne");


                //Instancier une classe de l'inscription
                ClasseDAO classedao=new ClasseDAO();
                classe=classedao.find(id_classe);

                //Instancier une personne associée à l'inscription
                PersonneDAO personnedao=new PersonneDAO();
                personne=personnedao.find(id_personne);
                
                inscription=new Inscription(id,classe,personne);


            }
        }
        catch (ClassNotFoundException | SQLException ex) {
                   Logger.getLogger(InscriptionDAO.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        return inscription;
    }

    @Override
    public Inscription create(Inscription obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            PreparedStatement prepare=con.prepareStatement("INSERT INTO inscription (id_classe,id_personne)VALUES(?,?)");
            prepare.setInt(1, obj.getClasse().getId_classe());
            prepare.setInt(2, obj.getPersonne().getId());
            prepare.executeUpdate();
            
            if(prepare!=null){
                rs=stmt.executeQuery("SELECT id_inscription WHERE id_classe="+obj.getClasse().getId_classe()+" AND id_personne="+obj.getPersonne().getId());
            
                if(rs.first()){
                    int id=rs.getInt("id_inscription");
                    obj.setId_inscription(id);
                    
                }
            }
            
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
        
        return obj;
        
    }

    @Override
    public void delete(Inscription obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("DELETE FROM inscription WHERE id_inscription="+obj.getId_inscription());
            
            if(rss!=0){
                System.out.println("L'élève "+obj.getPersonne().getNom()+" a été supprimé de la classe"+obj.getClasse().getNom());
            }
            else
                throw new SQLException();
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
    }

    @Override
    public Inscription update(Inscription obj) {
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            rss=stmt.executeUpdate("UPDATE inscription SET id_classe="+obj.getClasse().getId_classe()+", id_personne="+obj.getPersonne().getId());
       
            if(rss!=0){
                obj=this.find(obj.getId_inscription());
                
            }
        } catch (SQLException ex) {
            System.out.println("Aucune ligne affectée...");
        }
        
        return obj;
    }
    
}
