/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modele;

/**
 *
 * @author kevin
 * @param <T>
 */
public interface Comparable<T> {
    
    /**
     * retourne true si les champs de l'objet référencé est égal à ceux de l'objet en param
     * retourne false sinon
     * @param obj
     * @return 
     */

    @Override
    public boolean equals(Object obj);
    
}
