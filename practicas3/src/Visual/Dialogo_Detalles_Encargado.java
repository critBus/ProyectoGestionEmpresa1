/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import entidades.*;
import Utiles.*;

/**
 *
 * @author Rene2
 */
public class Dialogo_Detalles_Encargado extends javax.swing.JDialog {

    public Encargado encagadoActual;

    /**
     * Creates new form Dialogo_Detalles_Encargado
     */
    public Dialogo_Detalles_Encargado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        UtilesVisual.ponerIconoDeVentana_Principal(this);
    }

    public void actualizar(Encargado e) {
        //this.localActual=l;
        this.encagadoActual = e;
        T_Apellidos.setText(e.getApellidos());
        T_Cargo.setText(e.getCargo());
        T_Correo.setText(e.getCorreo());
        T_Direccion.setText(e.getDireccion());
        T_Nombre.setText(e.getNombre());
        T_Telefono.setText(e.getPhone() + "");
        T_Local.setText(e.getLocal().getNombre());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        T_Nombre = new javax.swing.JTextField();
        T_Correo = new javax.swing.JTextField();
        T_Telefono = new javax.swing.JTextField();
        B_Aceptar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        T_Apellidos = new javax.swing.JTextField();
        T_Local = new javax.swing.JTextField();
        T_Cargo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        T_Direccion = new javax.swing.JTextField();

        setTitle("Detalles Encargado");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel2.setText("Correo:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel6.setText("Telefono:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        T_Nombre.setEditable(false);
        jPanel1.add(T_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 15, 250, 35));

        T_Correo.setEditable(false);
        jPanel1.add(T_Correo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 65, 250, 35));

        T_Telefono.setEditable(false);
        jPanel1.add(T_Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 115, 250, 35));

        B_Aceptar.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        B_Aceptar.setText("Atras");
        B_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_AceptarActionPerformed(evt);
            }
        });
        jPanel1.add(B_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 165, 180, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel5.setText("Cargo:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel4.setText("Local:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel3.setText("Apellidos:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, -1, -1));

        T_Apellidos.setEditable(false);
        jPanel1.add(T_Apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 15, 260, 35));

        T_Local.setEditable(false);
        jPanel1.add(T_Local, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 65, 260, 35));

        T_Cargo.setEditable(false);
        jPanel1.add(T_Cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 115, 260, 35));

        jLabel8.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel8.setText("Direccion:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        T_Direccion.setEditable(false);
        jPanel1.add(T_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 165, 250, 35));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_AceptarActionPerformed
        setVisible(false);

    }//GEN-LAST:event_B_AceptarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Detalles_Encargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Detalles_Encargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Detalles_Encargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Detalles_Encargado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialogo_Detalles_Encargado dialog = new Dialogo_Detalles_Encargado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Aceptar;
    private javax.swing.JTextField T_Apellidos;
    private javax.swing.JTextField T_Cargo;
    private javax.swing.JTextField T_Correo;
    private javax.swing.JTextField T_Direccion;
    private javax.swing.JTextField T_Local;
    private javax.swing.JTextField T_Nombre;
    private javax.swing.JTextField T_Telefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
