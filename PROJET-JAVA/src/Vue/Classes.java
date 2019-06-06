/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import DAO.ClasseDAO;
import Modele.AnneeScolaire;
import Modele.Classe;
import Modele.Ecole;
import Modele.Niveau;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kevin
 */
public class Classes extends javax.swing.JFrame {
    
    ArrayList<Classe> classes=new ArrayList();
    ClasseDAO classeDAO;
    Classe classe=new Classe();
    AddClass addClass=new AddClass();
    
    static ArrayList<Niveau> allLevels=new ArrayList<Niveau>();
    static ArrayList<AnneeScolaire>allYears=new ArrayList<AnneeScolaire>();
    
    static DefaultTableModel modelClass;

    /**
     * Creates new form Classes
     */
    public Classes(){
        initComponents();          
        modelClass=(DefaultTableModel) jTable1.getModel();
        addClass=new AddClass(); //ON récupère tous les niveaux, annees associés.
        fillClasses();
        
        
    }
    
    /**
     * Remplit le tableau de classes avec celles trouvées dans la bdd
     */
    public void fillClasses(){
        try {          
            classeDAO= new ClasseDAO();
            
            classes=classeDAO.all(); //On récupère toutes les lignes de la table classe
            pack();
            
            for(int i=0;i<classes.size();i++){
                int id=classes.get(i).getId_classe();
                String nom=classes.get(i).getNom();
                
                String niveau=classes.get(i).getNiveau().getNom();
                int annee=classes.get(i).getAnnee().getId_anneeScolaire();
                String ecole=classes.get(i).getEcole().getNom();
                
                //Cree l'object à mettre dans le model
                Object[]cls={id,nom,annee,ecole,niveau};
                
                modelClass.insertRow(modelClass.getRowCount(), cls);
                
                classes.get(i).afficher();
                
                
                
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Il semblerait qu'une erreur soit survenue.");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Retour");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.setText("Rechercher");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Identifiant", "Nom", "Année Scolaire", "Ecole", "Niveau"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Ajouter");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Supprimer");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Modifier");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Afficher les élèves");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jButton1)
                        .addGap(262, 262, 262)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1070, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jButton2)
                        .addGap(152, 152, 152)
                        .addComponent(jButton3)
                        .addGap(175, 175, 175)
                        .addComponent(jButton4)
                        .addGap(150, 150, 150)
                        .addComponent(jButton5)))
                .addContainerGap(305, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 463, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(227, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        /**
     *
     * @param id_annee
     * @return
     */
    public static AnneeScolaire findYear(int id_annee){
        AnneeScolaire annee=new AnneeScolaire();
        for(int i=0;i<allYears.size();i++){
                if(allYears.get(i).getId_anneeScolaire()==id_annee)
                    annee=new AnneeScolaire(allYears.get(i).getId_anneeScolaire());
                else{
                    System.out.println("oups");
                }
            }
        return annee;
        
    }
    
    /**
     *
     * @param id_niveau
     * @return
     */
    public static Niveau findLevel(String nom_niveau){
        
        Niveau niveau=new Niveau();
        for(int i=0;i<allLevels.size();i++){
                if(allLevels.get(i).getNom().equals(nom_niveau)){
                    System.out.println("hello");
                    niveau=new Niveau(allLevels.get(i).getId_niveau(),allLevels.get(i).getNom());
                }
                    
                else
                    System.out.println("Niveau inexistant");
                

            } 
        
        return niveau;
    }
    
    
    
    //Retour
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        MenuPrincipal menu=new MenuPrincipal();
        menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    //Ajouter une classe
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        addClass.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    //Supprimer une classe
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         try {
             
            if(jTable1.getSelectedRow()==-1){//Si aucune ligne est selectionnee
                if(modelClass.getRowCount()==0){
                    JOptionPane.showMessageDialog(rootPane, "Le tableau est vide.");                  
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Selectionner une ligne.");
                }
                
            }
            else{
                
                // TODO add your handling code here:
                classeDAO=new ClasseDAO();
                classe=new Classe();

                int currentRow=jTable1.getSelectedRow();

                int id=(int) modelClass.getValueAt(currentRow,0);//Récupèrer l'id de la case sélectionnée
                
                System.out.println(id);
                classe=classeDAO.find(id); //Trouver la personne dans la bdd avec l'id

                
                //Demande de confirmation
                int confirm=JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer "+classe.getNom()+" "+classe.getNiveau().getNom()+" ?");
                if(confirm==JOptionPane.YES_OPTION){                   
                    classeDAO.delete(classe); //Enlever de la bdd
                    modelClass.removeRow(currentRow);                            
                }

            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Une erreur est survenue");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     *Instancie et retourne une classe en selectionnant sa ligne dans le tableau
     */
    public Classe selectClass(){
        
        Classe classe=new Classe();
        //On récup toutes la ligne puis instanciation à partir de l'arraylist.
            int currentRow=jTable1.getSelectedRow();
            
            int id=(int)modelClass.getValueAt(currentRow,0);
            
            
            String nom=(String)modelClass.getValueAt(currentRow, 1);
            
            String nom_annee=(String)modelClass.getValueAt(currentRow, 2);   
            int id_annee=Integer.parseInt(nom_annee);
            AnneeScolaire annee=new AnneeScolaire(id_annee);
                    
            String nom_ecole=(String)modelClass.getValueAt(currentRow, 3);
            Ecole ecole=new Ecole(1,nom_ecole);
            
            String nom_niveau=(String)modelClass.getValueAt(currentRow, 4);
            Niveau niveau=findLevel(nom_niveau);
            
            //Instanciation de la classe modifiée
            classe=new Classe(id,nom,annee,ecole,niveau);
            
            return classe;
        
    }
    
    //Modifier une classe
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            // TODO add your handling code here:
            
            classe=new Classe();
            classeDAO=new ClasseDAO();
            
            //Si aucune ligne n'est sélectionnée...
            if(jTable1.getSelectedRow()==-1){
                if(modelClass.getRowCount()==0){
                    JOptionPane.showMessageDialog(rootPane, "Le tableau est vide.");
                }
                else{
                    JOptionPane.showMessageDialog(rootPane, "Sélectionner une ligne.");
                }
            }
            
            else{
                classe=selectClass();
                     
                System.out.println("classe modifiee:");
                classe.afficher();
          
            
            if(classe.equals(classeDAO.update(classe))){           
                JOptionPane.showMessageDialog(rootPane, "Modification effectuée avec succès.");  
            }
            else{
                System.out.println("oups");
            }
            
            
            
        }
            
            } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Il semblerait qu'une erreur soit survenue.");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    
    //Afficher les élèves de la classe sélectionnée
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        
        //Si aucune ligne n'est sélectionnée...
        if(jTable1.getSelectedRow()==-1){
            if(modelClass.getRowCount()==0){
                JOptionPane.showMessageDialog(rootPane, "Le tableau est vide.");
            }
            else{
                JOptionPane.showMessageDialog(rootPane, "Sélectionner une ligne.");
            }
        }
        else{
            classe=selectClass();
            OneClass oneclass=new OneClass(classe);
            oneclass.setVisible(true);
        }
             
        
    }//GEN-LAST:event_jButton5ActionPerformed

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
