/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import static Modele.Connexion.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class BulletinDAO extends DAO<Bulletin>{
    
    public BulletinDAO() throws ClassNotFoundException, SQLException{
        super();
    }

    @Override
    public ArrayList<Bulletin> all() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bulletin find(int id) {
        Bulletin bulletin=new Bulletin();
        Trimestre trimestre=new Trimestre();
        Inscription inscription=new Inscription();
        
        
        try {
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            
            rs=stmt.executeQuery("SELECT * FROM bulletin WHERE id="+id);
            
            if(rs.next()){
                String appreciation=rs.getString("appreciation");
                int id_trimestre=rs.getInt("id_trimestre");
                int id_inscription=rs.getInt("id_inscription");
                
                //Instancier un trimestre
                ResultSet rs1=stmt.executeQuery("SELECT * FROM bulletin WHERE id_trimestre="+id_trimestre);
                if(rs1.first()){
                    int numero=rs1.getInt("numero");
                    int fin=rs1.getInt("fin");
                    int debut=rs1.getInt("debut");     
                    
                    int id_annee=rs1.getInt("id_annee");
                    AnneeScolaire annee=new AnneeScolaire(id_annee);
                    
                    trimestre=new Trimestre(id_trimestre,numero,fin,debut,annee);                   
                }
                
                //Instancier une inscription
                rs1=stmt.executeQuery("SELECT * FROM inscription WHERE id_inscription="+id_inscription);
                if(rs1.first()){
                    int id_classe=rs1.getInt("id_classe");
                    int id_personne=rs1.getInt("id_personne");
                    
                    Classe classe=new Classe();
                    Personne personne=new Personne();
                    //Instancier une classe de l'inscription
                    ResultSet rs2=stmt.executeQuery("SELECT * FROM classe WHERE id_classe="+id_classe);
                    if(rs2.first()){
                        Ecole ecole=new Ecole();
                        Niveau niveau=new Niveau();
                        
                        String nom_classe=rs2.getString("nom");
                        
                        int id_annee=rs2.getInt("id_annee");
                        AnneeScolaire annee=new AnneeScolaire(id_annee); //Instanciation de l'annee
                        
                        int id_ecole=rs2.getInt("id_ecole");
                        ResultSet rs3=stmt.executeQuery("SELECT * FROM ecole WHERE id_ecole="+id_ecole);
                        if(rs3.first()){
                            String nom_ecole=rs3.getString("nom");
                            ecole=new Ecole(id_ecole,nom_ecole);           //Instanciation de l'ecole associée                 
                        }
                        
                        int id_niveau=rs2.getInt("id_niveau");
                        rs3=stmt.executeQuery("SELECT * FROM niveau WHERE id_niveau="+id_niveau);
                        if(rs3.first()){
                            String nom_niveau=rs3.getString("nom");
                            niveau=new Niveau(id_niveau,nom_niveau);
                        }
                        
                        //Instanciation de la classe associée à l'inscription
                        classe=new Classe(id_classe,nom_classe,annee,ecole,niveau);
                        
                        
                    }
                    
                    //Instancier une personne associée à l'inscription
                    rs2=stmt.executeQuery("SELECT * FROM personne WHERE id_personne="+id_personne);
                    if(rs2.first()){
                        String nom=rs.getString("nom");
                        String prenom=rs.getString("prenom");
                        String type=rs.getString("type");
                        personne=new Personne(id_personne,nom,prenom,type);
                
                    }
                    
                    inscription=new Inscription(id_inscription,classe,personne);
                }
                
                //Instanciation du bulletin
                bulletin=new Bulletin(id,appreciation,trimestre,inscription);
            }
            
            
            
        } catch (SQLException ex) {
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
                rs=stmt.executeQuery("SELECT id_bulletin FROM bulletin WHERE id_trimestre="+id_trimestre+","+"AND id_inscription="+id_inscription);
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
    public void delete(Bulletin obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Bulletin update(Bulletin obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
