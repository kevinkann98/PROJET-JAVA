/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controleur;

import Modele.*;
import java.sql.*;

/**
 *
 * @author kevin
 * @param <T>
 */
public abstract class DAO <T>{
    protected Connection con=Connexion.con; //Connexion à la bdd
    
    /**
     * Retourne toutes les colonnes
     * @return
     */
    public abstract T all();
    
    /**
     * Recup une colonne via l'id
     * @param id
     * @return
     */
    public abstract T find(int id);
    
    /**
     * Créer une entrée à une bdd par rapport à l'objet
     * @param obj
     * @return
     */
    public abstract T create(T obj);
    
    /**
     *
     * @param obj
     */
    public abstract void delete(T obj);
    
    /**
     *
     * @param obj
     * @return
     */
    public abstract T update(T obj);
}
