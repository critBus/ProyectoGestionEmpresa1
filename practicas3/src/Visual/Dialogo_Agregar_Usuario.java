/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Utiles.*;
import entidades.*;

/**
 *
 * @author Rene2
 */
public class Dialogo_Agregar_Usuario extends javax.swing.JDialog {

    /**
     * Creates new form Dialogo_Agregar_Usuario2
     */
    public Dialogo_Agregar_Usuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        UtilesVisual.ponerIconoDeVentana_Principal(this);
    }

    public void resetear() {
        TB_Confirmar.setText("");
        TB_Contrasenna.setText("");
        TB_Usuario.setText("");
        CB_Permisos.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        CB_Permisos = new javax.swing.JComboBox<>();
        TB_Usuario = new javax.swing.JTextField();
        B_Aceptar = new javax.swing.JButton();
        B_Cancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        TB_Contrasenna = new javax.swing.JPasswordField();
        TB_Confirmar = new javax.swing.JPasswordField();

        setTitle("Agregar Usuario");
        setMaximumSize(new java.awt.Dimension(420, 300));
        setMinimumSize(new java.awt.Dimension(420, 300));
        setPreferredSize(new java.awt.Dimension(420, 300));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel2.setText("Usuario:");
        jLabel2.setToolTipText("");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel3.setText("Contraseña:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel4.setText("Permisos:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        CB_Permisos.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        CB_Permisos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "USUARIO", "ADMINISTRADOR", " " }));
        getContentPane().add(CB_Permisos, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 170, 250, 35));
        getContentPane().add(TB_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 15, 250, 35));

        B_Aceptar.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        B_Aceptar.setText("Aceptar");
        B_Aceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_AceptarActionPerformed(evt);
            }
        });
        getContentPane().add(B_Aceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 180, -1));

        B_Cancelar.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        B_Cancelar.setText("Atras");
        B_Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_CancelarActionPerformed(evt);
            }
        });
        getContentPane().add(B_Cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 180, -1));

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 24)); // NOI18N
        jLabel5.setText("Confirmar:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));
        getContentPane().add(TB_Contrasenna, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 65, 250, 35));
        getContentPane().add(TB_Confirmar, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 115, 250, 35));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_AceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_AceptarActionPerformed
        try {
            String username=TB_Usuario.getText();
            String contrasenna=TB_Contrasenna.getText();
            String confirmar=TB_Confirmar.getText();
            if(Validaciones.validar_es_nuevo_usuario_correcto(rootPane, username, contrasenna, confirmar)){
                TipoDeRol rol=TipoDeRol.valueOf(CB_Permisos.getSelectedItem()+"");
                Usuario nuevo=Referencias.BD.agregarUsuario(username, contrasenna, rol);
                
                Referencias.ventana_administrar_usuarios.actualizar_con_Usuario(nuevo);
                setVisible(false);
                
                DialogosDeRespuesta.mostrarDlgExito(this, "Usuario agregado con éxito  ");
            }
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_B_AceptarActionPerformed

    private void B_CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_CancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_B_CancelarActionPerformed

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
            java.util.logging.Logger.getLogger(Dialogo_Agregar_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Agregar_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Agregar_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Agregar_Usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialogo_Agregar_Usuario dialog = new Dialogo_Agregar_Usuario(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton B_Cancelar;
    private javax.swing.JComboBox<String> CB_Permisos;
    private javax.swing.JPasswordField TB_Confirmar;
    private javax.swing.JPasswordField TB_Contrasenna;
    private javax.swing.JTextField TB_Usuario;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
