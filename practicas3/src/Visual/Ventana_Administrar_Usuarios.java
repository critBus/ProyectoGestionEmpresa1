/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import Utiles.*;
import entidades.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rene2
 */
public class Ventana_Administrar_Usuarios extends javax.swing.JFrame {

    public Usuario usuarioActual;
    private boolean ordenar_de_forma_asendente;

    public ArrayList<Usuario> entidades_existentes;
    public ArrayList<Usuario> entidades_en_tabla;

    /**
     * Creates new form Vantana_Administrar_Usuarios
     */
    public Ventana_Administrar_Usuarios() {
        initComponents();
        this.ordenar_de_forma_asendente = true;

        this.entidades_en_tabla = new ArrayList<>();
        this.entidades_existentes = new ArrayList<>();

        ButtonGroup bg_ordenar = new ButtonGroup();
        bg_ordenar.add(MI_RB_Ordenar_Por_Permisos);
        bg_ordenar.add(MI_RB_Ordenar_Por_Username);
        MI_RB_Ordenar_Por_Username.setSelected(true);

        setLocationRelativeTo(null);
        
        UtilesVisual.ponerIconoDeVentana_Principal(this);
    }

    public void actualizar(Usuario u) throws Exception {
        this.usuarioActual = u;

        boolean esADmin = Referencias.BD.esAdministrador(u);
//        if (!esADmin) {
//            DialogosDeRespuesta.mostrarDlgInvalido(this, "No tiene autorización ");
//            return;
//        }

        TI_Filtro.setText("");
        
        this.ordenar_de_forma_asendente = true;
        UtilesVisual.cambiarIconoDeDireccion(B_Invertir_Orden,this.ordenar_de_forma_asendente);

        this.entidades_existentes = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_Usuarios()));
        actualizarTabla(this.entidades_existentes);

    }

    public void actualizar_con_Usuario(Usuario u) throws Exception {
        this.entidades_existentes.remove(u);
        this.entidades_existentes.add(u);
        actualizarTabla(this.entidades_existentes);
    }

    private String getPermisosStr(Usuario u) {
        String r = "";
        Rol R[] = u.rol.toArray();
        for (int i = 0; i < R.length; i++) {
            r += (i != 0 ? "," : "") + R[i].getNombre();
        }
        return r;
    }

    private ArrayList<Usuario> ordenar(ArrayList<Usuario> L) {
        L.sort(new Comparator<Usuario>() {
            @Override
            public int compare(Usuario o1, Usuario o2) {
                if (MI_RB_Ordenar_Por_Permisos.isSelected()) {
                    return getPermisosStr(o1).compareToIgnoreCase(getPermisosStr(o2));
                }

                return o1.getUsername().compareToIgnoreCase(o2.getUsername());
            }
        });
        if (!ordenar_de_forma_asendente) {
            Collections.reverse(L);
        }
        return L;
    }

    private void actualizarTabla(ArrayList<Usuario> L) {
        L = ordenar(L);
        this.entidades_en_tabla = L;
        int cantidad = L.size();
        String Tiulos[] = {"Username", "Permisos"};
        Object O[][] = new Object[cantidad][Tiulos.length];
        for (int i = 0; i < cantidad; i++) {
            Usuario e = L.get(i);
            O[i][0] = e.getUsername();
            O[i][1] = getPermisosStr(e);

        }
        Tabla.setModel(new DefaultTableModel(O, Tiulos) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        });
    }

    private void aplicarFiltro() {
        try {
            final String texto = TI_Filtro.getText().trim().toLowerCase();
            if (texto.isEmpty()) {

                this.entidades_existentes = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_Usuarios()));
                actualizarTabla(this.entidades_existentes);

            } else {
                final ArrayList<Usuario> seleccionados = new ArrayList<>();
                this.entidades_existentes.forEach(v -> {
                    if (MI_ChB_Filtrar_Por_Username.isSelected()
                            && v.getUsername().toLowerCase().contains(texto)) {
                        seleccionados.add(v);
                        return;
                    }
                    if (MI_ChB_Filtrar_Por_Permisos.isSelected()
                            && getPermisosStr(v).toLowerCase().contains(texto)) {
                        seleccionados.add(v);

                    }

                });
                actualizarTabla(seleccionados);
            }
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void mandarAOrdenarTabla() {
        try {

            actualizarTabla(this.entidades_en_tabla);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarEliminar() {
        try {
            if (Tabla.getSelectedRow() != -1) {
                if (JOptionPane.showConfirmDialog(this, "Desea eliminar el Usuario seleccionado?", "Advertencia", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    Usuario usuarioSeleccionado = entidades_en_tabla.get(Tabla.getSelectedRow());
                    if (usuarioActual.getId() != usuarioSeleccionado.getId()) {
                        Referencias.BD.eliminarUsuario(usuarioSeleccionado);
                        TI_Filtro.setText("");

                        this.entidades_existentes.remove(usuarioSeleccionado);

                        actualizarTabla(this.entidades_existentes);

                        DialogosDeRespuesta.mostrarDlgExito(this, "Usuario eliminado con éxito  ");
                    } else {
                        DialogosDeRespuesta.mostrarDlgInvalido(this, "No se puede eliminar el usuario en sesión en este momento ");
                    }

                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un Usuario en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarAgregar() {
        try {
            Referencias.dialogo_agregar_Usuario.resetear();
            Referencias.dialogo_agregar_Usuario.setVisible(true);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarModificar() {
        try {
            if (Tabla.getSelectedRow() != -1) {
                Usuario usuarioSeleccionado = entidades_en_tabla.get(Tabla.getSelectedRow());

                Referencias.dialogo_editar_usuario.resetear(usuarioActual, usuarioSeleccionado);
                Referencias.dialogo_editar_usuario.setVisible(true);

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un Usuario en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        B_Modificar = new javax.swing.JButton();
        B_Eliminar = new javax.swing.JButton();
        B_Agregar = new javax.swing.JButton();
        TI_Filtro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        B_Filtrar = new javax.swing.JButton();
        B_Invertir_Orden = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        M_Principal = new javax.swing.JMenu();
        MI_Cambiar_Contrasenna = new javax.swing.JMenuItem();
        MI_Atras = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MI_Cerrar_Sesion = new javax.swing.JMenuItem();
        M_Usuarios = new javax.swing.JMenu();
        MI_Agregar_Usuario = new javax.swing.JMenuItem();
        MI_Eliminar_Usuario = new javax.swing.JMenuItem();
        MI_Modificar_Usuario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MI_RB_Ordenar_Por_Username = new javax.swing.JRadioButtonMenuItem();
        MI_RB_Ordenar_Por_Permisos = new javax.swing.JRadioButtonMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MI_ChB_Filtrar_Por_Username = new javax.swing.JCheckBoxMenuItem();
        MI_ChB_Filtrar_Por_Permisos = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrar Usuarios");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabla.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Local"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(Tabla);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 470, 440));

        B_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/editar copia.png"))); // NOI18N
        B_Modificar.setToolTipText("Editar el usuario seleccionado ");
        B_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_ModificarActionPerformed(evt);
            }
        });
        jPanel1.add(B_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 45, 40));

        B_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/papelera copia.png"))); // NOI18N
        B_Eliminar.setToolTipText("Eliminar el usuario seleccionado ");
        B_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_EliminarActionPerformed(evt);
            }
        });
        jPanel1.add(B_Eliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 45, 40));

        B_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agregar copia.png"))); // NOI18N
        B_Agregar.setToolTipText("Agregar un nuevo usuario ");
        B_Agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_AgregarActionPerformed(evt);
            }
        });
        jPanel1.add(B_Agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 45, 40));

        TI_Filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TI_FiltroActionPerformed(evt);
            }
        });
        jPanel1.add(TI_Filtro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 34, 370, 35));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Usuarios de la Empresa ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 465, -1));

        B_Filtrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/buscar copia reducido.png"))); // NOI18N
        B_Filtrar.setToolTipText("Buscar según la entrada");
        B_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_FiltrarActionPerformed(evt);
            }
        });
        jPanel1.add(B_Filtrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 45, 40));

        B_Invertir_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/abajo double copia.png"))); // NOI18N
        B_Invertir_Orden.setToolTipText("Cambiar dirección del orden de los datos mostrados en la tabla ");
        B_Invertir_Orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Invertir_OrdenActionPerformed(evt);
            }
        });
        jPanel1.add(B_Invertir_Orden, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 45, 40));

        M_Principal.setText("Menu");

        MI_Cambiar_Contrasenna.setText("Cambiar Contraseña");
        MI_Cambiar_Contrasenna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Cambiar_ContrasennaActionPerformed(evt);
            }
        });
        M_Principal.add(MI_Cambiar_Contrasenna);

        MI_Atras.setText("Atras");
        MI_Atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_AtrasActionPerformed(evt);
            }
        });
        M_Principal.add(MI_Atras);
        M_Principal.add(jSeparator1);

        MI_Cerrar_Sesion.setText("Cerrar Sesión");
        MI_Cerrar_Sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Cerrar_SesionActionPerformed(evt);
            }
        });
        M_Principal.add(MI_Cerrar_Sesion);

        jMenuBar1.add(M_Principal);

        M_Usuarios.setText("Usuarios");

        MI_Agregar_Usuario.setText("Agregar Usuario");
        MI_Agregar_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Agregar_UsuarioActionPerformed(evt);
            }
        });
        M_Usuarios.add(MI_Agregar_Usuario);

        MI_Eliminar_Usuario.setText("Eliminar Usuario");
        MI_Eliminar_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Eliminar_UsuarioActionPerformed(evt);
            }
        });
        M_Usuarios.add(MI_Eliminar_Usuario);

        MI_Modificar_Usuario.setText("Modificar Usuario");
        MI_Modificar_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Modificar_UsuarioActionPerformed(evt);
            }
        });
        M_Usuarios.add(MI_Modificar_Usuario);

        jMenu2.setText("Ordenar Por");

        MI_RB_Ordenar_Por_Username.setSelected(true);
        MI_RB_Ordenar_Por_Username.setText("Username");
        MI_RB_Ordenar_Por_Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Por_UsernameActionPerformed(evt);
            }
        });
        jMenu2.add(MI_RB_Ordenar_Por_Username);

        MI_RB_Ordenar_Por_Permisos.setSelected(true);
        MI_RB_Ordenar_Por_Permisos.setText("Permisos");
        MI_RB_Ordenar_Por_Permisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Por_PermisosActionPerformed(evt);
            }
        });
        jMenu2.add(MI_RB_Ordenar_Por_Permisos);

        M_Usuarios.add(jMenu2);

        jMenu1.setText("Filtrar Por");

        MI_ChB_Filtrar_Por_Username.setSelected(true);
        MI_ChB_Filtrar_Por_Username.setText("Username");
        MI_ChB_Filtrar_Por_Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Por_UsernameActionPerformed(evt);
            }
        });
        jMenu1.add(MI_ChB_Filtrar_Por_Username);

        MI_ChB_Filtrar_Por_Permisos.setSelected(true);
        MI_ChB_Filtrar_Por_Permisos.setText("Permisos");
        MI_ChB_Filtrar_Por_Permisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Por_PermisosActionPerformed(evt);
            }
        });
        jMenu1.add(MI_ChB_Filtrar_Por_Permisos);

        M_Usuarios.add(jMenu1);

        jMenuBar1.add(M_Usuarios);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_ModificarActionPerformed
        intentarModificar();
    }//GEN-LAST:event_B_ModificarActionPerformed

    private void B_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_EliminarActionPerformed
        intentarEliminar();
    }//GEN-LAST:event_B_EliminarActionPerformed

    private void B_AgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_AgregarActionPerformed
        intentarAgregar();
    }//GEN-LAST:event_B_AgregarActionPerformed

    private void TI_FiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TI_FiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TI_FiltroActionPerformed

    private void B_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_FiltrarActionPerformed
        aplicarFiltro();
    }//GEN-LAST:event_B_FiltrarActionPerformed

    private void B_Invertir_OrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Invertir_OrdenActionPerformed
        try {
            this.ordenar_de_forma_asendente = !this.ordenar_de_forma_asendente;
            UtilesVisual.cambiarIconoDeDireccion(B_Invertir_Orden,this.ordenar_de_forma_asendente);
            actualizarTabla(this.entidades_en_tabla);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_B_Invertir_OrdenActionPerformed

    private void MI_AtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_AtrasActionPerformed
        try {
            Referencias.ventana_principal.actualizar_seguridad(usuarioActual);
            Referencias.ventana_principal.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_MI_AtrasActionPerformed

    private void MI_RB_Ordenar_Por_UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Por_UsernameActionPerformed
        mandarAOrdenarTabla();
    }//GEN-LAST:event_MI_RB_Ordenar_Por_UsernameActionPerformed

    private void MI_RB_Ordenar_Por_PermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Por_PermisosActionPerformed
        mandarAOrdenarTabla();
    }//GEN-LAST:event_MI_RB_Ordenar_Por_PermisosActionPerformed

    private void MI_ChB_Filtrar_Por_UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Por_UsernameActionPerformed
        aplicarFiltro();
    }//GEN-LAST:event_MI_ChB_Filtrar_Por_UsernameActionPerformed

    private void MI_ChB_Filtrar_Por_PermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Por_PermisosActionPerformed
        aplicarFiltro();
    }//GEN-LAST:event_MI_ChB_Filtrar_Por_PermisosActionPerformed

    private void MI_Eliminar_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Eliminar_UsuarioActionPerformed
        intentarEliminar();
    }//GEN-LAST:event_MI_Eliminar_UsuarioActionPerformed

    private void MI_Agregar_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Agregar_UsuarioActionPerformed
        intentarAgregar();
    }//GEN-LAST:event_MI_Agregar_UsuarioActionPerformed

    private void MI_Cerrar_SesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Cerrar_SesionActionPerformed
        Referencias.ventana_reguistro.resetear();
        setVisible(false);
        Referencias.ventana_reguistro.setVisible(true);
    }//GEN-LAST:event_MI_Cerrar_SesionActionPerformed

    private void MI_Cambiar_ContrasennaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Cambiar_ContrasennaActionPerformed
        try {
            Referencias.dialogo_cambiar_contrasenna.setLocationRelativeTo(this);
            Referencias.dialogo_cambiar_contrasenna.resetear(usuarioActual);
            Referencias.dialogo_cambiar_contrasenna.setVisible(true);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_MI_Cambiar_ContrasennaActionPerformed

    private void MI_Modificar_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Modificar_UsuarioActionPerformed
        intentarModificar();
    }//GEN-LAST:event_MI_Modificar_UsuarioActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana_Administrar_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Administrar_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Administrar_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Administrar_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Administrar_Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Agregar;
    private javax.swing.JButton B_Eliminar;
    private javax.swing.JButton B_Filtrar;
    private javax.swing.JButton B_Invertir_Orden;
    private javax.swing.JButton B_Modificar;
    private javax.swing.JMenuItem MI_Agregar_Usuario;
    private javax.swing.JMenuItem MI_Atras;
    private javax.swing.JMenuItem MI_Cambiar_Contrasenna;
    private javax.swing.JMenuItem MI_Cerrar_Sesion;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Por_Permisos;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Por_Username;
    private javax.swing.JMenuItem MI_Eliminar_Usuario;
    private javax.swing.JMenuItem MI_Modificar_Usuario;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Por_Permisos;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Por_Username;
    private javax.swing.JMenu M_Principal;
    private javax.swing.JMenu M_Usuarios;
    private javax.swing.JTextField TI_Filtro;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
