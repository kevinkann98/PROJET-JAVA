/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modele.*;
import static Modele.Connexion.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author kevin
 * @param <T>
 */
public abstract class DAO<T>{
      
    protected static Connection con=null;
     
    /**
     * Cree une connection 
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DAO() throws ClassNotFoundException, SQLException{
     Class.forName("com.mysql.jdbc.Driver");
     con=DriverManager.getConnection("jdbc:mysql://localhost:3306/"+Connexion.getBdd(), Connexion.getUsername(), Connexion.getPassword());   
}
    
    /**
     * Retourne toutes les colonnes
     * @return
     */
    public abstract ArrayList<T> all();
    
    /**
     * Recup une colonne via l'id
     * @param id id
     * @return
     */
    public abstract T find(int id);
    
    /**
     * Créer une entrée à une bdd par rapport à l'objet
     * @param obj objet de n'importe quel type
     * @return
     */
    public abstract T create(T obj);
    
    /**
     * Supprimme une ligne d'une table via l'objet en param
     * @param obj objet de n'importe quel type
     */
    public abstract void delete(T obj);
    
    /**
     *
     * @param obj objet de n'importe quel type
     * @return
     */
    public abstract T update(T obj);
}
