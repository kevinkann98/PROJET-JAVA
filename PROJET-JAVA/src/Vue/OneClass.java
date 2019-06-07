/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vue;

import DAO.InscriptionDAO;
import DAO.PersonneDAO;
import Modele.Classe;
import Modele.Inscription;
import Modele.Personne;
import static Vue.Classes.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *Affiche une classe spécifique, permet aussi d'ajouter et supprimer des étudiants de la classe
 * @author kevin
 */
public class OneClass extends javax.swing.JFrame {

    Classe classe=new Classe();
    
    Inscription inscription=new Inscription();
    InscriptionDAO inscriptiondao;
    
    ArrayList<Inscription> inscriptions= new ArrayList<Inscription>();
    private DefaultTableModel modelClass;
    
    /** Creates new form OneClass */
    public OneClass(Classe classe) {
        initComponents();
        this.classe=classe;
        
        modelClass=(DefaultTableModel) jTable1.getModel();      
        className.setText(classe.getNom()+" "+classe.getNiveau().getNom()+" "+classe.getAnnee().getId_anneeScolaire());
      
        fillStudents();
        
        
        
    }
    
    /**
     *
     */
    public void fillStudents(){
        
        try {
            //Remplir le tableau avec les étudiants de la classe
            
            //On récupère d'abord les inscriptions
            inscriptiondao=new InscriptionDAO();
            inscriptions=inscriptiondao.all();
            
            
            //Récupérer les étudiants de la classe
            for(int i=0;i<inscriptions.size();i++){
                inscriptions.get(i).afficher();
                if(inscriptions.get(i).getClasse().getId_classe()==classe.getId_classe()){
                    int id_personne=inscriptions.get(i).getPersonne().getId();
                    
                    PersonneDAO personnedao=new PersonneDAO();
                    Personne personne=new Personne();
                    personne=personnedao.find(id_personne); //Instanciation de la personne en la cherchant dans la bdd
                    
                    
                    
                    Object []infos={inscriptions.get(i).getId_inscription(), personne.getPrenom(), personne.getNom()};
                    
                    modelClass.insertRow(jTable1.getRowCount(), infos);                   
                    
                }
            }
         
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(OneClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        className = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        className.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N

        jTable1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identifiant d'inscription", "Nom", "Prénom"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Retirer de la classe");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText("Identifiant de l'étudiant");

        jButton2.setText("Ajouter dans la classe");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGap(405, 405, 405)
                .addComponent(className)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(className)
                .addGap(74, 74, 74)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //Retirer un etudiant de la classe (inscription à supprimer de la bdd)
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:      
        
        
        if(jTable1.getSelectedRow()==-1){//Si aucune ligne est selectionnee
            if(modelClass.getRowCount()==0){
                JOptionPane.showMessageDialog(rootPane, "Le tableau est vide.");
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Selectionner une ligne.");                
            }
            
        }
        else{
            try {
                inscription=new Inscription();
                inscriptiondao=new InscriptionDAO();
                
                int currentRow=jTable1.getSelectedRow();
                int id_inscription=(int) modelClass.getValueAt(currentRow, 0); //Récupérer l'id d'inscription à supprimer
                String nom=(String) modelClass.getValueAt(currentRow, 1);
                String prenom=(String) modelClass.getValueAt(currentRow, 2);
                
                inscription=inscriptiondao.find(id_inscription);
                
                //Demande de confirmation
                int confirm=JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment retirer "+nom+" "+prenom+" de la classe ?");
                
                if(confirm==JOptionPane.YES_OPTION){                   
                    inscriptiondao.delete(inscription); //Enlever de la bdd
                    modelClass.removeRow(currentRow);
                    
                    ///on met à jour l'arraylist d'inscriptions
                    inscriptions=inscriptiondao.all();
                }
                
                
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(OneClass.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    
    //Ajouter un étudiant dans la classe
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            // TODO add your handling code here:
            inscription=new Inscription();           
            inscriptiondao=new InscriptionDAO();
            Personne personne=new Personne();
            PersonneDAO personnedao=new PersonneDAO();
            
            int id_inscription=0;
            
            //Récupérer la classe et l'étudiant sélectionné
            int id_personne=Integer.parseInt(jTextField1.getText());
            
            for(int i=0;i<inscriptions.size();i++){
                if(inscriptions.get(i).getClasse().getId_classe()==classe.getId_classe() && inscriptions.get(i).getPersonne().getId()==id_personne)
                    throw new SQLException();
            }
            
            if(personnedao.find(id_personne)==null)
                throw new SQLException();
            
                    
            else{
                personne=personnedao.find(id_personne);
                
                if(!personne.getType().equals("etudiant")){
                    JOptionPane.showMessageDialog(rootPane, personne.getNom()+" "+personne.getPrenom()+"n'est pas un étudiant");

                }
                else{
                  

                    //Demander confirmation
                    int confirm=JOptionPane.showConfirmDialog(null, "Ajouter "+personne.getNom()+" "+personne.getPrenom()+" ?");
                    if(confirm==JOptionPane.YES_OPTION){
                        //Instancier une inscription
                        inscription=new Inscription(id_inscription,classe,personne);
                        inscription=inscriptiondao.create(inscription);

                        Object [] student={personne.getId(),personne.getNom(),personne.getPrenom()};
                        modelClass.insertRow(jTable1.getRowCount(), student);
                        
                        inscriptions=inscriptiondao.all();

                    }  
                }
                
        }
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Il semblerait qu'une erreur soit survenue.");
        }
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel className;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}
