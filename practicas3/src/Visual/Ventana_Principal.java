/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visual;

import entidades.*;
import Utiles.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import org.hibernate.mapping.Collection;

/**
 *
 * @author Nadia
 */
public class Ventana_Principal extends javax.swing.JFrame {

    public Usuario usuarioActual;

    private boolean ordenar_de_forma_asendente_Locales;
    private boolean ordenar_de_forma_asendente_Encargados;
    private boolean ordenar_de_forma_asendente_MediosBasicos;

    public ArrayList<Local> locales_existentes;
    public ArrayList<Local> locales_en_tabla;

    public ArrayList<Encargado> encargados_existentes_de_local;
    public ArrayList<Encargado> encargados_en_tabla;

    public ArrayList<Medios_basicos> mediosBasicos_existentes_de_local;
    public ArrayList<Medios_basicos> mediosBasicos_en_tabla;

    /**
     * Creates new form Panel
     */
    public Ventana_Principal() {
        initComponents();
        setLocationRelativeTo(null);

        locales_existentes = new ArrayList<>();
        locales_en_tabla = new ArrayList<>();
        encargados_existentes_de_local = new ArrayList<>();
        encargados_en_tabla = new ArrayList<>();
        mediosBasicos_existentes_de_local = new ArrayList<>();
        mediosBasicos_en_tabla = new ArrayList<>();

        ordenar_de_forma_asendente_Locales = true;
        ordenar_de_forma_asendente_Encargados = true;
        ordenar_de_forma_asendente_MediosBasicos = true;

        ButtonGroup bg_ordenar_MedioBasico = new ButtonGroup();
        bg_ordenar_MedioBasico.add(MI_RB_Ordenar_Articulo_Por_ID);
        bg_ordenar_MedioBasico.add(MI_RB_Ordenar_Articulo_Por_Nombre);
        MI_RB_Ordenar_Articulo_Por_ID.setSelected(true);

        ButtonGroup bg_ordenar_Encargados = new ButtonGroup();
        bg_ordenar_Encargados.add(MI_RB_Ordenar_Encargado_Por_Apellidos);
        bg_ordenar_Encargados.add(MI_RB_Ordenar_Encargado_Por_Cargo);
        bg_ordenar_Encargados.add(MI_RB_Ordenar_Encargado_Por_Correo);
        bg_ordenar_Encargados.add(MI_RB_Ordenar_Encargado_Por_Direccion);
        bg_ordenar_Encargados.add(MI_RB_Ordenar_Encargado_Por_Telefono);
        bg_ordenar_Encargados.add(MI_RB_Ordenar_Encargado_Por_Nombre);
        MI_RB_Ordenar_Encargado_Por_Nombre.setSelected(true);

        final Ventana_Principal estaVentana = this;
        UtilesVisual.ponerIconoDeVentana_Principal(this);

        T_Local.addMouseListener(new MouseInputListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //System.out.println("algo 1111111111111111111111");
                if (T_Local.getSelectedRow() != -1) {
                    try {
                        Local l = estaVentana.locales_en_tabla.get(T_Local.getSelectedRow());
                        estaVentana.encargados_existentes_de_local = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_Encargados(l)));
                        Medios_basicos M[] = Referencias.BD.obtenerTodos_MediosBasicos(l);
                        estaVentana.mediosBasicos_existentes_de_local = new ArrayList<>(Arrays.asList(M));
                        actualizarTabla_Medios_Basicos(estaVentana.mediosBasicos_existentes_de_local);

                        actualizarTabla_Encargados(estaVentana.encargados_existentes_de_local);
                    } catch (Exception ex) {
                        DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(estaVentana, ex);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

    }

    public void actualizar(Usuario u) throws Exception {
        this.usuarioActual = u;
        ordenar_de_forma_asendente_Locales = true;
        
        ordenar_de_forma_asendente_Encargados = true;
        ordenar_de_forma_asendente_MediosBasicos = true;
        
        UtilesVisual.cambiarIconoDeDireccion(B_Invertir_Orden_Locales,this.ordenar_de_forma_asendente_Locales);
        UtilesVisual.cambiarIconoDeDireccion(B_Invertir_Orden_Encargados,this.ordenar_de_forma_asendente_Encargados);
        UtilesVisual.cambiarIconoDeDireccion(B_Revertir_Orden_MediosBasicos,this.ordenar_de_forma_asendente_MediosBasicos);
        
        actualizar_seguridad(u);

        TI_Filtro_Locales.setText("");
        TI_Filtros_Encargados.setText("");
        TI_Filtros_MediosBasicos.setText("");

        this.locales_existentes = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_Local()));
        actualizarTabla_Local(this.locales_existentes);

    }

    public void actualizar_seguridad(Usuario u) throws Exception {
        boolean esADmin = Referencias.BD.esAdministrador(u);
        MI_Administrador_Usuario.setVisible(esADmin);
    }

    public void actualizar_Encargado(Encargado e) throws Exception {
        TI_Filtro_Locales.setText("");
        TI_Filtros_Encargados.setText("");
        TI_Filtros_MediosBasicos.setText("");

        this.encargados_existentes_de_local.remove(e);
        this.encargados_existentes_de_local.add(e);
        actualizarTabla_Encargados(this.encargados_existentes_de_local);
    }

    private ArrayList<Local> ordenar_Local(ArrayList<Local> L) {
        L.sort(new Comparator<Local>() {
            @Override
            public int compare(Local o1, Local o2) {
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });
        if (!ordenar_de_forma_asendente_Locales) {
            Collections.reverse(L);
        }
        return L;
    }

    private ArrayList<Encargado> ordenar_Encargados(ArrayList<Encargado> L) {
        L.sort(new Comparator<Encargado>() {
            @Override
            public int compare(Encargado o1, Encargado o2) {
                if (MI_RB_Ordenar_Encargado_Por_Apellidos.isSelected()) {
                    return o1.getApellidos().compareToIgnoreCase(o2.getApellidos());
                }
                if (MI_RB_Ordenar_Encargado_Por_Cargo.isSelected()) {
                    return o1.getCargo().compareToIgnoreCase(o2.getCargo());
                }
                if (MI_RB_Ordenar_Encargado_Por_Correo.isSelected()) {
                    return o1.getCorreo().compareToIgnoreCase(o2.getCorreo());
                }
                if (MI_RB_Ordenar_Encargado_Por_Direccion.isSelected()) {
                    return o1.getDireccion().compareToIgnoreCase(o2.getDireccion());
                }

                if (MI_RB_Ordenar_Encargado_Por_Telefono.isSelected()) {
                    return new Integer(o1.getPhone()).compareTo(o2.getPhone());
                }
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });
        if (!ordenar_de_forma_asendente_Encargados) {
            Collections.reverse(L);
        }
        return L;
    }

    private ArrayList<Medios_basicos> ordenar_Medios_basicos(ArrayList<Medios_basicos> L) {
        L.sort(new Comparator<Medios_basicos>() {
            @Override
            public int compare(Medios_basicos o1, Medios_basicos o2) {
                if (MI_RB_Ordenar_Articulo_Por_ID.isSelected()) {
                    return new Integer(o1.getId()).compareTo(o2.getId());
                }
                return o1.getNombre().compareToIgnoreCase(o2.getNombre());
            }
        });

        if (!ordenar_de_forma_asendente_MediosBasicos) {
            Collections.reverse(L);
        }
        return L;
    }

    private void actualizarTabla_Local(ArrayList<Local> L) throws Exception {
        L = ordenar_Local(L);
        this.locales_en_tabla = L;
        int cantidad = L.size();
        String Tiulos[] = {"Local"};
        Object O[][] = new Object[cantidad][Tiulos.length];
        for (int i = 0; i < cantidad; i++) {
            O[i][0] = L.get(i).getNombre();
        }
        T_Local.setModel(new DefaultTableModel(O, Tiulos) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        });

        if (cantidad > 0) {
            T_Local.addRowSelectionInterval(0, 0);
            Local l = L.get(0);
            this.encargados_existentes_de_local = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_Encargados(l)));
            this.mediosBasicos_existentes_de_local = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_MediosBasicos(l)));
        } else {
            this.encargados_existentes_de_local = new ArrayList<>();

            this.mediosBasicos_existentes_de_local = new ArrayList<>();

        }
        actualizarTabla_Encargados(this.encargados_existentes_de_local);
        actualizarTabla_Medios_Basicos(this.mediosBasicos_existentes_de_local);

    }

    private void actualizarTabla_Encargados(ArrayList<Encargado> L) {
        L = ordenar_Encargados(L);
        this.encargados_en_tabla = L;
        int cantidad = L.size();
        String Tiulos[] = {"Nombre", "Apellidos", "Cargo"};
        Object O[][] = new Object[cantidad][Tiulos.length];
        for (int i = 0; i < cantidad; i++) {
            Encargado e = L.get(i);
            O[i][0] = e.getNombre();
            O[i][1] = e.getApellidos();
            O[i][2] = e.getCargo();
        }
        T_Encargados.setModel(new DefaultTableModel(O, Tiulos) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        });
    }

    private void actualizarTabla_Medios_Basicos(ArrayList<Medios_basicos> L) {
        L = ordenar_Medios_basicos(L);
        int cantidad = L.size();
        this.mediosBasicos_en_tabla = L;
        String Tiulos[] = {"ID", "Nombre"};
        Object O[][] = new Object[cantidad][Tiulos.length];
        for (int i = 0; i < cantidad; i++) {
            Medios_basicos m = L.get(i);
            O[i][0] = m.getId();
            O[i][1] = m.getNombre();
        }
        T_Medios_Basicos.setModel(new DefaultTableModel(O, Tiulos) {
            public boolean isCellEditable(int row, int column) {
                return false;//This causes all cells to be not editable
            }
        });
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
        TI_Filtro_Locales = new javax.swing.JTextField();
        B_Filtrar_Locales = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        T_Local = new javax.swing.JTable();
        B_Agregar_Local = new javax.swing.JButton();
        B_Modificar_Local = new javax.swing.JButton();
        B_Invertir_Orden_Locales = new javax.swing.JButton();
        B_Eliminar_Local = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        T_Encargados = new javax.swing.JTable();
        TI_Filtros_Encargados = new javax.swing.JTextField();
        B_Filtrar_Encargados = new javax.swing.JButton();
        B_Invertir_Orden_Encargados = new javax.swing.JButton();
        B_Detalles_Encargado = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TI_Filtros_MediosBasicos = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        T_Medios_Basicos = new javax.swing.JTable();
        B_Filtrar_MediosBasicos = new javax.swing.JButton();
        B_Revertir_Orden_MediosBasicos = new javax.swing.JButton();
        B_Agregar_Encargado = new javax.swing.JButton();
        B_Eliminar_Encargado = new javax.swing.JButton();
        B_Editar_Encargado = new javax.swing.JButton();
        B_Agregar_MedioBasico = new javax.swing.JButton();
        B_Eliminar_MedioBasico = new javax.swing.JButton();
        B_Modificar_MedioBasico = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        M_Usuario = new javax.swing.JMenu();
        MI_Administrador_Usuario = new javax.swing.JMenuItem();
        MI_Cambiar_Contrasenna = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MI_Cerrar_Sesion = new javax.swing.JMenuItem();
        M_Articulos = new javax.swing.JMenu();
        MI_Agregar_Articulo = new javax.swing.JMenuItem();
        MI_Eliminar_Articulo = new javax.swing.JMenuItem();
        MI_Modificar_Articulo = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        MI_RB_Ordenar_Articulo_Por_Nombre = new javax.swing.JRadioButtonMenuItem();
        MI_RB_Ordenar_Articulo_Por_ID = new javax.swing.JRadioButtonMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MI_ChB_Filtrar_Articulo_Por_Nombre = new javax.swing.JCheckBoxMenuItem();
        MI_ChB_Filtrar_Articulo_Por_ID = new javax.swing.JCheckBoxMenuItem();
        M_Local = new javax.swing.JMenu();
        MI_Agregar_Local = new javax.swing.JMenuItem();
        MI_Eliminar_Local = new javax.swing.JMenuItem();
        MI_Modificar_Local = new javax.swing.JMenuItem();
        M_Encargado = new javax.swing.JMenu();
        MI_Agregar_Encargado = new javax.swing.JMenuItem();
        MI_Eliminar_Encargado = new javax.swing.JMenuItem();
        MI_Modificar_Encargado = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        MI_RB_Ordenar_Encargado_Por_Nombre = new javax.swing.JRadioButtonMenuItem();
        MI_RB_Ordenar_Encargado_Por_Apellidos = new javax.swing.JRadioButtonMenuItem();
        MI_RB_Ordenar_Encargado_Por_Correo = new javax.swing.JRadioButtonMenuItem();
        MI_RB_Ordenar_Encargado_Por_Direccion = new javax.swing.JRadioButtonMenuItem();
        MI_RB_Ordenar_Encargado_Por_Telefono = new javax.swing.JRadioButtonMenuItem();
        MI_RB_Ordenar_Encargado_Por_Cargo = new javax.swing.JRadioButtonMenuItem();
        jMenu4 = new javax.swing.JMenu();
        MI_ChB_Filtrar_Encargado_Por_Nombre = new javax.swing.JCheckBoxMenuItem();
        MI_ChB_Filtrar_Encargado_Por_Apellidos = new javax.swing.JCheckBoxMenuItem();
        MI_ChB_Filtrar_Encargado_Por_Correo = new javax.swing.JCheckBoxMenuItem();
        MI_ChB_Filtrar_Encargado_Por_Direccion = new javax.swing.JCheckBoxMenuItem();
        MI_ChB_Filtrar_Encargado_Por_Telefono = new javax.swing.JCheckBoxMenuItem();
        MI_ChB_Filtrar_Encargado_Por_Cargo = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Panel Principal");
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TI_Filtro_Locales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TI_Filtro_LocalesActionPerformed(evt);
            }
        });
        jPanel1.add(TI_Filtro_Locales, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 34, 370, 35));

        B_Filtrar_Locales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/buscar copia reducido.png"))); // NOI18N
        B_Filtrar_Locales.setToolTipText("Buscar según la entrada ");
        B_Filtrar_Locales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Filtrar_LocalesActionPerformed(evt);
            }
        });
        jPanel1.add(B_Filtrar_Locales, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 45, 40));

        T_Local.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        T_Local.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(T_Local);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 80, 470, 440));

        B_Agregar_Local.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agregar copia.png"))); // NOI18N
        B_Agregar_Local.setToolTipText("Agregar un nuevo local ");
        B_Agregar_Local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Agregar_LocalActionPerformed(evt);
            }
        });
        jPanel1.add(B_Agregar_Local, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 45, 40));

        B_Modificar_Local.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/editar copia.png"))); // NOI18N
        B_Modificar_Local.setToolTipText("Editar el local seleccionado ");
        B_Modificar_Local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Modificar_LocalActionPerformed(evt);
            }
        });
        jPanel1.add(B_Modificar_Local, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 45, 40));

        B_Invertir_Orden_Locales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/arriba double reducida.png"))); // NOI18N
        B_Invertir_Orden_Locales.setToolTipText("Cambiar dirección del orden de los datos mostrados en la tabla ");
        B_Invertir_Orden_Locales.setMargin(new java.awt.Insets(2, 2, 2, 2));
        B_Invertir_Orden_Locales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Invertir_Orden_LocalesActionPerformed(evt);
            }
        });
        jPanel1.add(B_Invertir_Orden_Locales, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 45, 40));

        B_Eliminar_Local.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/papelera copia.png"))); // NOI18N
        B_Eliminar_Local.setToolTipText("Eliminar el local seleccionado ");
        B_Eliminar_Local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Eliminar_LocalActionPerformed(evt);
            }
        });
        jPanel1.add(B_Eliminar_Local, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 45, 40));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Locales de la Empresa ");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 465, -1));

        T_Encargados.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        T_Encargados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, "", null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nombre", "Apellidos", "Cargo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(T_Encargados);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 510, 100));

        TI_Filtros_Encargados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TI_Filtros_EncargadosActionPerformed(evt);
            }
        });
        jPanel1.add(TI_Filtros_Encargados, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 34, 409, 35));

        B_Filtrar_Encargados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/buscar copia reducido.png"))); // NOI18N
        B_Filtrar_Encargados.setToolTipText("Buscar según la entrada ");
        B_Filtrar_Encargados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Filtrar_EncargadosActionPerformed(evt);
            }
        });
        jPanel1.add(B_Filtrar_Encargados, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 30, 45, 40));

        B_Invertir_Orden_Encargados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/abajo double copia.png"))); // NOI18N
        B_Invertir_Orden_Encargados.setToolTipText("Cambiar dirección del orden de los datos mostrados en la tabla ");
        B_Invertir_Orden_Encargados.setMaximumSize(new java.awt.Dimension(60, 48));
        B_Invertir_Orden_Encargados.setMinimumSize(new java.awt.Dimension(60, 48));
        B_Invertir_Orden_Encargados.setPreferredSize(new java.awt.Dimension(60, 48));
        B_Invertir_Orden_Encargados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Invertir_Orden_EncargadosActionPerformed(evt);
            }
        });
        jPanel1.add(B_Invertir_Orden_Encargados, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 30, 45, 40));

        B_Detalles_Encargado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/ojo copia.png"))); // NOI18N
        B_Detalles_Encargado.setToolTipText("Mostrar los detalles del encargado seleccionado ");
        B_Detalles_Encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Detalles_EncargadoActionPerformed(evt);
            }
        });
        jPanel1.add(B_Detalles_Encargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 30, 45, 40));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Encargados ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 0, 500, -1));

        jLabel3.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Medios Básicos ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 200, 500, -1));

        TI_Filtros_MediosBasicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TI_Filtros_MediosBasicosActionPerformed(evt);
            }
        });
        jPanel1.add(TI_Filtros_MediosBasicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 240, 409, 35));

        T_Medios_Basicos.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        T_Medios_Basicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(T_Medios_Basicos);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 290, 510, 230));

        B_Filtrar_MediosBasicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/buscar copia reducido.png"))); // NOI18N
        B_Filtrar_MediosBasicos.setToolTipText("Buscar según la entrada ");
        B_Filtrar_MediosBasicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Filtrar_MediosBasicosActionPerformed(evt);
            }
        });
        jPanel1.add(B_Filtrar_MediosBasicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 236, 45, 40));

        B_Revertir_Orden_MediosBasicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/arriba double reducida.png"))); // NOI18N
        B_Revertir_Orden_MediosBasicos.setToolTipText("Cambiar dirección del orden de los datos mostrados en la tabla ");
        B_Revertir_Orden_MediosBasicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Revertir_Orden_MediosBasicosActionPerformed(evt);
            }
        });
        jPanel1.add(B_Revertir_Orden_MediosBasicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 236, 45, 40));

        B_Agregar_Encargado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agregar copia.png"))); // NOI18N
        B_Agregar_Encargado.setToolTipText("Agregar un nuevo encargado a este local ");
        B_Agregar_Encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Agregar_EncargadoActionPerformed(evt);
            }
        });
        jPanel1.add(B_Agregar_Encargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 80, 45, 40));

        B_Eliminar_Encargado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/papelera copia.png"))); // NOI18N
        B_Eliminar_Encargado.setToolTipText("Eliminar el encargado seleccionado ");
        B_Eliminar_Encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Eliminar_EncargadoActionPerformed(evt);
            }
        });
        jPanel1.add(B_Eliminar_Encargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 120, 45, 40));

        B_Editar_Encargado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/editar copia.png"))); // NOI18N
        B_Editar_Encargado.setToolTipText("Editar el encargado seleccionado ");
        B_Editar_Encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Editar_EncargadoActionPerformed(evt);
            }
        });
        jPanel1.add(B_Editar_Encargado, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 160, 45, 40));

        B_Agregar_MedioBasico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/agregar copia.png"))); // NOI18N
        B_Agregar_MedioBasico.setToolTipText("Agregar un nuevo medio básico a este local ");
        B_Agregar_MedioBasico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Agregar_MedioBasicoActionPerformed(evt);
            }
        });
        jPanel1.add(B_Agregar_MedioBasico, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 290, 45, 40));

        B_Eliminar_MedioBasico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/papelera copia.png"))); // NOI18N
        B_Eliminar_MedioBasico.setToolTipText("Eliminar el medio básico seleccionado ");
        B_Eliminar_MedioBasico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Eliminar_MedioBasicoActionPerformed(evt);
            }
        });
        jPanel1.add(B_Eliminar_MedioBasico, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 330, 45, 40));

        B_Modificar_MedioBasico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imgs/editar copia.png"))); // NOI18N
        B_Modificar_MedioBasico.setToolTipText("Editar el medio básico seleccionado ");
        B_Modificar_MedioBasico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_Modificar_MedioBasicoActionPerformed(evt);
            }
        });
        jPanel1.add(B_Modificar_MedioBasico, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 370, 45, 40));

        M_Usuario.setText("Usuario");

        MI_Administrador_Usuario.setText("Administrador Usuario");
        MI_Administrador_Usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Administrador_UsuarioActionPerformed(evt);
            }
        });
        M_Usuario.add(MI_Administrador_Usuario);

        MI_Cambiar_Contrasenna.setText("Cambiar Contraseña");
        MI_Cambiar_Contrasenna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Cambiar_ContrasennaActionPerformed(evt);
            }
        });
        M_Usuario.add(MI_Cambiar_Contrasenna);
        M_Usuario.add(jSeparator1);

        MI_Cerrar_Sesion.setText("Cerrar Sesión");
        MI_Cerrar_Sesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Cerrar_SesionActionPerformed(evt);
            }
        });
        M_Usuario.add(MI_Cerrar_Sesion);

        jMenuBar1.add(M_Usuario);

        M_Articulos.setText("Articulos");

        MI_Agregar_Articulo.setText("Agregar Artículo");
        MI_Agregar_Articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Agregar_ArticuloActionPerformed(evt);
            }
        });
        M_Articulos.add(MI_Agregar_Articulo);

        MI_Eliminar_Articulo.setText("Eliminar Artículo");
        MI_Eliminar_Articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Eliminar_ArticuloActionPerformed(evt);
            }
        });
        M_Articulos.add(MI_Eliminar_Articulo);

        MI_Modificar_Articulo.setText("Modificar Artículo");
        MI_Modificar_Articulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Modificar_ArticuloActionPerformed(evt);
            }
        });
        M_Articulos.add(MI_Modificar_Articulo);

        jMenu1.setText("Ordenar Por");

        MI_RB_Ordenar_Articulo_Por_Nombre.setSelected(true);
        MI_RB_Ordenar_Articulo_Por_Nombre.setText("Nombre");
        MI_RB_Ordenar_Articulo_Por_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Articulo_Por_NombreActionPerformed(evt);
            }
        });
        jMenu1.add(MI_RB_Ordenar_Articulo_Por_Nombre);

        MI_RB_Ordenar_Articulo_Por_ID.setSelected(true);
        MI_RB_Ordenar_Articulo_Por_ID.setText("ID");
        MI_RB_Ordenar_Articulo_Por_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Articulo_Por_IDActionPerformed(evt);
            }
        });
        jMenu1.add(MI_RB_Ordenar_Articulo_Por_ID);

        M_Articulos.add(jMenu1);

        jMenu2.setText("Filtrar Por");

        MI_ChB_Filtrar_Articulo_Por_Nombre.setSelected(true);
        MI_ChB_Filtrar_Articulo_Por_Nombre.setText("Nombre");
        MI_ChB_Filtrar_Articulo_Por_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Articulo_Por_NombreActionPerformed(evt);
            }
        });
        jMenu2.add(MI_ChB_Filtrar_Articulo_Por_Nombre);

        MI_ChB_Filtrar_Articulo_Por_ID.setSelected(true);
        MI_ChB_Filtrar_Articulo_Por_ID.setText("ID");
        MI_ChB_Filtrar_Articulo_Por_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Articulo_Por_IDActionPerformed(evt);
            }
        });
        jMenu2.add(MI_ChB_Filtrar_Articulo_Por_ID);

        M_Articulos.add(jMenu2);

        jMenuBar1.add(M_Articulos);

        M_Local.setText("Locales");

        MI_Agregar_Local.setText("Agregar Local");
        MI_Agregar_Local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Agregar_LocalActionPerformed(evt);
            }
        });
        M_Local.add(MI_Agregar_Local);

        MI_Eliminar_Local.setText("Eliminar Local");
        MI_Eliminar_Local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Eliminar_LocalActionPerformed(evt);
            }
        });
        M_Local.add(MI_Eliminar_Local);

        MI_Modificar_Local.setText("Modificar Local");
        MI_Modificar_Local.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Modificar_LocalActionPerformed(evt);
            }
        });
        M_Local.add(MI_Modificar_Local);

        jMenuBar1.add(M_Local);

        M_Encargado.setText("Encargados");

        MI_Agregar_Encargado.setText("Agregar Encargado");
        MI_Agregar_Encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Agregar_EncargadoActionPerformed(evt);
            }
        });
        M_Encargado.add(MI_Agregar_Encargado);

        MI_Eliminar_Encargado.setText("Eliminar Encargado");
        MI_Eliminar_Encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Eliminar_EncargadoActionPerformed(evt);
            }
        });
        M_Encargado.add(MI_Eliminar_Encargado);

        MI_Modificar_Encargado.setText("Editar Encargado");
        MI_Modificar_Encargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_Modificar_EncargadoActionPerformed(evt);
            }
        });
        M_Encargado.add(MI_Modificar_Encargado);

        jMenu3.setText("Ordenar Por");

        MI_RB_Ordenar_Encargado_Por_Nombre.setSelected(true);
        MI_RB_Ordenar_Encargado_Por_Nombre.setText("Nombre");
        MI_RB_Ordenar_Encargado_Por_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Encargado_Por_NombreActionPerformed(evt);
            }
        });
        jMenu3.add(MI_RB_Ordenar_Encargado_Por_Nombre);

        MI_RB_Ordenar_Encargado_Por_Apellidos.setSelected(true);
        MI_RB_Ordenar_Encargado_Por_Apellidos.setText("Apellidos");
        MI_RB_Ordenar_Encargado_Por_Apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Encargado_Por_ApellidosActionPerformed(evt);
            }
        });
        jMenu3.add(MI_RB_Ordenar_Encargado_Por_Apellidos);

        MI_RB_Ordenar_Encargado_Por_Correo.setSelected(true);
        MI_RB_Ordenar_Encargado_Por_Correo.setText("Correo");
        MI_RB_Ordenar_Encargado_Por_Correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Encargado_Por_CorreoActionPerformed(evt);
            }
        });
        jMenu3.add(MI_RB_Ordenar_Encargado_Por_Correo);

        MI_RB_Ordenar_Encargado_Por_Direccion.setSelected(true);
        MI_RB_Ordenar_Encargado_Por_Direccion.setText("Direccion");
        MI_RB_Ordenar_Encargado_Por_Direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Encargado_Por_DireccionActionPerformed(evt);
            }
        });
        jMenu3.add(MI_RB_Ordenar_Encargado_Por_Direccion);

        MI_RB_Ordenar_Encargado_Por_Telefono.setSelected(true);
        MI_RB_Ordenar_Encargado_Por_Telefono.setText("Telefono");
        MI_RB_Ordenar_Encargado_Por_Telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Encargado_Por_TelefonoActionPerformed(evt);
            }
        });
        jMenu3.add(MI_RB_Ordenar_Encargado_Por_Telefono);

        MI_RB_Ordenar_Encargado_Por_Cargo.setSelected(true);
        MI_RB_Ordenar_Encargado_Por_Cargo.setText("Cargo");
        MI_RB_Ordenar_Encargado_Por_Cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_RB_Ordenar_Encargado_Por_CargoActionPerformed(evt);
            }
        });
        jMenu3.add(MI_RB_Ordenar_Encargado_Por_Cargo);

        M_Encargado.add(jMenu3);

        jMenu4.setText("Filtrar Por");

        MI_ChB_Filtrar_Encargado_Por_Nombre.setSelected(true);
        MI_ChB_Filtrar_Encargado_Por_Nombre.setText("Nombre");
        MI_ChB_Filtrar_Encargado_Por_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Encargado_Por_NombreActionPerformed(evt);
            }
        });
        jMenu4.add(MI_ChB_Filtrar_Encargado_Por_Nombre);

        MI_ChB_Filtrar_Encargado_Por_Apellidos.setSelected(true);
        MI_ChB_Filtrar_Encargado_Por_Apellidos.setText("Apellidos");
        MI_ChB_Filtrar_Encargado_Por_Apellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Encargado_Por_ApellidosActionPerformed(evt);
            }
        });
        jMenu4.add(MI_ChB_Filtrar_Encargado_Por_Apellidos);

        MI_ChB_Filtrar_Encargado_Por_Correo.setSelected(true);
        MI_ChB_Filtrar_Encargado_Por_Correo.setText("Correo");
        MI_ChB_Filtrar_Encargado_Por_Correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Encargado_Por_CorreoActionPerformed(evt);
            }
        });
        jMenu4.add(MI_ChB_Filtrar_Encargado_Por_Correo);

        MI_ChB_Filtrar_Encargado_Por_Direccion.setSelected(true);
        MI_ChB_Filtrar_Encargado_Por_Direccion.setText("Direccion");
        MI_ChB_Filtrar_Encargado_Por_Direccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Encargado_Por_DireccionActionPerformed(evt);
            }
        });
        jMenu4.add(MI_ChB_Filtrar_Encargado_Por_Direccion);

        MI_ChB_Filtrar_Encargado_Por_Telefono.setSelected(true);
        MI_ChB_Filtrar_Encargado_Por_Telefono.setText("Telefono");
        MI_ChB_Filtrar_Encargado_Por_Telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Encargado_Por_TelefonoActionPerformed(evt);
            }
        });
        jMenu4.add(MI_ChB_Filtrar_Encargado_Por_Telefono);

        MI_ChB_Filtrar_Encargado_Por_Cargo.setSelected(true);
        MI_ChB_Filtrar_Encargado_Por_Cargo.setText("Cargo");
        MI_ChB_Filtrar_Encargado_Por_Cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MI_ChB_Filtrar_Encargado_Por_CargoActionPerformed(evt);
            }
        });
        jMenu4.add(MI_ChB_Filtrar_Encargado_Por_Cargo);

        M_Encargado.add(jMenu4);

        jMenuBar1.add(M_Encargado);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MI_Administrador_UsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Administrador_UsuarioActionPerformed
        try {
            Referencias.ventana_administrar_usuarios.actualizar(usuarioActual);
            Referencias.ventana_administrar_usuarios.setVisible(true);
            setVisible(false);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }

    }//GEN-LAST:event_MI_Administrador_UsuarioActionPerformed

    private void MI_Eliminar_ArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Eliminar_ArticuloActionPerformed
        intentarEliminarMedioBasico();

    }//GEN-LAST:event_MI_Eliminar_ArticuloActionPerformed

    private void MI_Modificar_ArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Modificar_ArticuloActionPerformed
        intentarModificarMedioBasico();
    }//GEN-LAST:event_MI_Modificar_ArticuloActionPerformed

    private void MI_Agregar_ArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Agregar_ArticuloActionPerformed
        intentarAgregarMedioBasico();
    }//GEN-LAST:event_MI_Agregar_ArticuloActionPerformed

    private void MI_Cerrar_SesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Cerrar_SesionActionPerformed
        //System.exit(0);
        Referencias.ventana_reguistro.resetear();
        setVisible(false);
        Referencias.ventana_reguistro.setVisible(true);
    }//GEN-LAST:event_MI_Cerrar_SesionActionPerformed

    private void MI_Eliminar_LocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Eliminar_LocalActionPerformed
        intentarElimnarLocal();
    }//GEN-LAST:event_MI_Eliminar_LocalActionPerformed

    private void MI_Agregar_LocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Agregar_LocalActionPerformed
        intentarAgregarLocal();
    }//GEN-LAST:event_MI_Agregar_LocalActionPerformed

    private void TI_Filtro_LocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TI_Filtro_LocalesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TI_Filtro_LocalesActionPerformed

    private void MI_Agregar_EncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Agregar_EncargadoActionPerformed
        intentarAgregarEncargado();
    }//GEN-LAST:event_MI_Agregar_EncargadoActionPerformed

    private void TI_Filtros_EncargadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TI_Filtros_EncargadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TI_Filtros_EncargadosActionPerformed

    private void TI_Filtros_MediosBasicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TI_Filtros_MediosBasicosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TI_Filtros_MediosBasicosActionPerformed

    private void B_Agregar_LocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Agregar_LocalActionPerformed
        intentarAgregarLocal();
    }//GEN-LAST:event_B_Agregar_LocalActionPerformed

    private void B_Modificar_LocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Modificar_LocalActionPerformed
        intentarModificarLocal();
    }//GEN-LAST:event_B_Modificar_LocalActionPerformed

    private void MI_Modificar_LocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Modificar_LocalActionPerformed
        intentarModificarLocal();
    }//GEN-LAST:event_MI_Modificar_LocalActionPerformed

    private void B_Eliminar_LocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Eliminar_LocalActionPerformed
        intentarElimnarLocal();
    }//GEN-LAST:event_B_Eliminar_LocalActionPerformed

    private void B_Invertir_Orden_LocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Invertir_Orden_LocalesActionPerformed
        try {
            this.ordenar_de_forma_asendente_Locales = !this.ordenar_de_forma_asendente_Locales;
            UtilesVisual.cambiarIconoDeDireccion(B_Invertir_Orden_Locales,this.ordenar_de_forma_asendente_Locales);
            actualizarTabla_Local(this.locales_en_tabla);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_B_Invertir_Orden_LocalesActionPerformed

    private void B_Filtrar_LocalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Filtrar_LocalesActionPerformed
        try {
            final String texto = TI_Filtro_Locales.getText().trim().toLowerCase();
            if (texto.isEmpty()) {

                this.locales_existentes = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_Local()));
                actualizarTabla_Local(this.locales_existentes);

            } else {
                final ArrayList<Local> seleccionados = new ArrayList<>();
                this.locales_existentes.forEach(v -> {
                    if (v.getNombre().toLowerCase().contains(texto)) {
                        seleccionados.add(v);
                    }
                });
                actualizarTabla_Local(seleccionados);
            }
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }

    }//GEN-LAST:event_B_Filtrar_LocalesActionPerformed

    private void B_Agregar_MedioBasicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Agregar_MedioBasicoActionPerformed
        intentarAgregarMedioBasico();
    }//GEN-LAST:event_B_Agregar_MedioBasicoActionPerformed

    private void B_Modificar_MedioBasicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Modificar_MedioBasicoActionPerformed
        intentarModificarMedioBasico();
    }//GEN-LAST:event_B_Modificar_MedioBasicoActionPerformed

    private void B_Eliminar_MedioBasicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Eliminar_MedioBasicoActionPerformed
        intentarEliminarMedioBasico();
    }//GEN-LAST:event_B_Eliminar_MedioBasicoActionPerformed

    private void B_Revertir_Orden_MediosBasicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Revertir_Orden_MediosBasicosActionPerformed
        try {
            this.ordenar_de_forma_asendente_MediosBasicos = !this.ordenar_de_forma_asendente_MediosBasicos;
            UtilesVisual.cambiarIconoDeDireccion(B_Revertir_Orden_MediosBasicos,this.ordenar_de_forma_asendente_MediosBasicos);
            actualizarTabla_Medios_Basicos(this.mediosBasicos_en_tabla);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_B_Revertir_Orden_MediosBasicosActionPerformed

    private void MI_RB_Ordenar_Articulo_Por_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Articulo_Por_NombreActionPerformed
        try {

            actualizarTabla_Medios_Basicos(this.mediosBasicos_en_tabla);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_MI_RB_Ordenar_Articulo_Por_NombreActionPerformed

    private void MI_RB_Ordenar_Articulo_Por_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Articulo_Por_IDActionPerformed
        try {

            actualizarTabla_Medios_Basicos(this.mediosBasicos_en_tabla);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_MI_RB_Ordenar_Articulo_Por_IDActionPerformed

    private void B_Filtrar_MediosBasicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Filtrar_MediosBasicosActionPerformed
        aplicarFiltro_MediosBasicos();
    }//GEN-LAST:event_B_Filtrar_MediosBasicosActionPerformed

    private void MI_ChB_Filtrar_Articulo_Por_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Articulo_Por_NombreActionPerformed
        aplicarFiltro_MediosBasicos();
    }//GEN-LAST:event_MI_ChB_Filtrar_Articulo_Por_NombreActionPerformed

    private void MI_ChB_Filtrar_Articulo_Por_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Articulo_Por_IDActionPerformed
        aplicarFiltro_MediosBasicos();
    }//GEN-LAST:event_MI_ChB_Filtrar_Articulo_Por_IDActionPerformed

    private void B_Agregar_EncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Agregar_EncargadoActionPerformed
        intentarAgregarEncargado();
    }//GEN-LAST:event_B_Agregar_EncargadoActionPerformed

    private void B_Editar_EncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Editar_EncargadoActionPerformed
        intentarEditarEncargado();
    }//GEN-LAST:event_B_Editar_EncargadoActionPerformed

    private void MI_Modificar_EncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Modificar_EncargadoActionPerformed
        intentarEditarEncargado();
    }//GEN-LAST:event_MI_Modificar_EncargadoActionPerformed

    private void B_Eliminar_EncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Eliminar_EncargadoActionPerformed
        intentarEliminarEncargado();
    }//GEN-LAST:event_B_Eliminar_EncargadoActionPerformed

    private void MI_Eliminar_EncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Eliminar_EncargadoActionPerformed
        intentarEliminarEncargado();
    }//GEN-LAST:event_MI_Eliminar_EncargadoActionPerformed

    private void B_Invertir_Orden_EncargadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Invertir_Orden_EncargadosActionPerformed
        try {
            this.ordenar_de_forma_asendente_Encargados = !this.ordenar_de_forma_asendente_Encargados;
            UtilesVisual.cambiarIconoDeDireccion(B_Invertir_Orden_Encargados,this.ordenar_de_forma_asendente_Encargados);
            actualizarTabla_Encargados(this.encargados_en_tabla);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_B_Invertir_Orden_EncargadosActionPerformed

    private void MI_RB_Ordenar_Encargado_Por_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Encargado_Por_NombreActionPerformed
        mandarAOrdenarEncargadosEnTabla();
    }//GEN-LAST:event_MI_RB_Ordenar_Encargado_Por_NombreActionPerformed

    private void MI_RB_Ordenar_Encargado_Por_ApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Encargado_Por_ApellidosActionPerformed
        mandarAOrdenarEncargadosEnTabla();
    }//GEN-LAST:event_MI_RB_Ordenar_Encargado_Por_ApellidosActionPerformed

    private void MI_RB_Ordenar_Encargado_Por_CorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Encargado_Por_CorreoActionPerformed
        mandarAOrdenarEncargadosEnTabla();
    }//GEN-LAST:event_MI_RB_Ordenar_Encargado_Por_CorreoActionPerformed

    private void MI_RB_Ordenar_Encargado_Por_DireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Encargado_Por_DireccionActionPerformed
        mandarAOrdenarEncargadosEnTabla();
    }//GEN-LAST:event_MI_RB_Ordenar_Encargado_Por_DireccionActionPerformed

    private void MI_RB_Ordenar_Encargado_Por_TelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Encargado_Por_TelefonoActionPerformed
        mandarAOrdenarEncargadosEnTabla();
    }//GEN-LAST:event_MI_RB_Ordenar_Encargado_Por_TelefonoActionPerformed

    private void MI_RB_Ordenar_Encargado_Por_CargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_RB_Ordenar_Encargado_Por_CargoActionPerformed
        mandarAOrdenarEncargadosEnTabla();
    }//GEN-LAST:event_MI_RB_Ordenar_Encargado_Por_CargoActionPerformed

    private void MI_ChB_Filtrar_Encargado_Por_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Encargado_Por_NombreActionPerformed
        aplicarFiltro_Encargados();
    }//GEN-LAST:event_MI_ChB_Filtrar_Encargado_Por_NombreActionPerformed

    private void MI_ChB_Filtrar_Encargado_Por_ApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Encargado_Por_ApellidosActionPerformed
        aplicarFiltro_Encargados();
    }//GEN-LAST:event_MI_ChB_Filtrar_Encargado_Por_ApellidosActionPerformed

    private void MI_ChB_Filtrar_Encargado_Por_CorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Encargado_Por_CorreoActionPerformed
        aplicarFiltro_Encargados();
    }//GEN-LAST:event_MI_ChB_Filtrar_Encargado_Por_CorreoActionPerformed

    private void MI_ChB_Filtrar_Encargado_Por_DireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Encargado_Por_DireccionActionPerformed
        aplicarFiltro_Encargados();
    }//GEN-LAST:event_MI_ChB_Filtrar_Encargado_Por_DireccionActionPerformed

    private void MI_ChB_Filtrar_Encargado_Por_TelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Encargado_Por_TelefonoActionPerformed
        aplicarFiltro_Encargados();
    }//GEN-LAST:event_MI_ChB_Filtrar_Encargado_Por_TelefonoActionPerformed

    private void MI_ChB_Filtrar_Encargado_Por_CargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_ChB_Filtrar_Encargado_Por_CargoActionPerformed
        aplicarFiltro_Encargados();
    }//GEN-LAST:event_MI_ChB_Filtrar_Encargado_Por_CargoActionPerformed

    private void B_Filtrar_EncargadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Filtrar_EncargadosActionPerformed
        aplicarFiltro_Encargados();
    }//GEN-LAST:event_B_Filtrar_EncargadosActionPerformed

    private void MI_Cambiar_ContrasennaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MI_Cambiar_ContrasennaActionPerformed
        try {
            Referencias.dialogo_cambiar_contrasenna.setLocationRelativeTo(this);
            Referencias.dialogo_cambiar_contrasenna.resetear(usuarioActual);
            Referencias.dialogo_cambiar_contrasenna.setVisible(true);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_MI_Cambiar_ContrasennaActionPerformed

    private void B_Detalles_EncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_Detalles_EncargadoActionPerformed
        try {
            if (T_Local.getSelectedRow() != -1) {
                Local l = this.locales_en_tabla.get(T_Local.getSelectedRow());

                if (T_Encargados.getSelectedRow() != -1) {
                    Encargado e = this.encargados_en_tabla.get(T_Encargados.getSelectedRow());

                    Referencias.dialogo_detalles_encargado.actualizar(e);
                    Referencias.dialogo_detalles_encargado.setVisible(true);

                } else {
                    DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un encargado en la tabla ");
                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }//GEN-LAST:event_B_Detalles_EncargadoActionPerformed

    private void aplicarFiltro_Encargados() {
        try {
            final String texto = TI_Filtros_Encargados.getText().trim().toLowerCase();
            if (texto.isEmpty()) {
                if (T_Local.getSelectedRow() != -1) {
                    Local l = locales_en_tabla.get(T_Local.getSelectedRow());

                    this.encargados_existentes_de_local = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_Encargados(l)));
                    actualizarTabla_Encargados(this.encargados_existentes_de_local);

                } else {
                    DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
                }

            } else {
                final ArrayList<Encargado> seleccionados = new ArrayList<>();
                this.encargados_existentes_de_local.forEach(v -> {
                    if (MI_ChB_Filtrar_Encargado_Por_Nombre.isSelected()
                            && v.getNombre().toLowerCase().contains(texto)) {
                        seleccionados.add(v);
                        return;
                    }
                    if (MI_ChB_Filtrar_Encargado_Por_Apellidos.isSelected()
                            && v.getApellidos().toLowerCase().contains(texto)) {
                        seleccionados.add(v);
                        return;
                    }
                    if (MI_ChB_Filtrar_Encargado_Por_Cargo.isSelected()
                            && v.getCargo().toLowerCase().contains(texto)) {
                        seleccionados.add(v);
                        return;
                    }
                    if (MI_ChB_Filtrar_Encargado_Por_Correo.isSelected()
                            && v.getCorreo().toLowerCase().contains(texto)) {
                        seleccionados.add(v);
                        return;
                    }
                    if (MI_ChB_Filtrar_Encargado_Por_Direccion.isSelected()
                            && v.getDireccion().toLowerCase().contains(texto)) {
                        seleccionados.add(v);
                        return;
                    }

                    if (MI_ChB_Filtrar_Encargado_Por_Telefono.isSelected()
                            && (v.getPhone() + "").equals(texto)) {
                        seleccionados.add(v);

                    }
                });
                actualizarTabla_Encargados(seleccionados);
            }
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void mandarAOrdenarEncargadosEnTabla() {
        try {

            actualizarTabla_Encargados(this.encargados_en_tabla);
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarEliminarEncargado() {
        try {
            if (T_Local.getSelectedRow() != -1) {
                Local l = locales_en_tabla.get(T_Local.getSelectedRow());

                if (T_Encargados.getSelectedRow() != -1) {

                    if (JOptionPane.showConfirmDialog(this, "Desea eliminar el Encargado seleccionado?", "Advertencia", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        Encargado e = this.encargados_en_tabla.get(T_Encargados.getSelectedRow());

                        Referencias.BD.eliminarEncargado(e);
                        TI_Filtro_Locales.setText("");
                        TI_Filtros_Encargados.setText("");
                        TI_Filtros_MediosBasicos.setText("");

                        this.encargados_existentes_de_local.remove(e);

                        actualizarTabla_Encargados(this.encargados_existentes_de_local);

                        DialogosDeRespuesta.mostrarDlgExito(this, "Encargado eliminado con éxito  ");
                    }

                } else {
                    DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un encargado en la tabla ");
                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarEditarEncargado() {
        try {
            if (T_Local.getSelectedRow() != -1) {
                Local l = this.locales_en_tabla.get(T_Local.getSelectedRow());

                if (T_Encargados.getSelectedRow() != -1) {
                    Encargado e = this.encargados_en_tabla.get(T_Encargados.getSelectedRow());

                    Referencias.dialogo_editar_Encargado.resetear(e);
                    Referencias.dialogo_editar_Encargado.setVisible(true);

                } else {
                    DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un encargado en la tabla ");
                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarAgregarEncargado() {
        try {
            if (T_Local.getSelectedRow() != -1) {
                Local l = this.locales_en_tabla.get(T_Local.getSelectedRow());
                if (Validaciones.validar_se_Puede_Agregar_encargado(this, l)) {
                    Referencias.dialogo_agregar_Encargado.resetear(l);
                    Referencias.dialogo_agregar_Encargado.setVisible(true);
                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void aplicarFiltro_MediosBasicos() {
        try {
            final String texto = TI_Filtros_MediosBasicos.getText().trim().toLowerCase();
            if (texto.isEmpty()) {
                if (T_Local.getSelectedRow() != -1) {
                    Local l = locales_en_tabla.get(T_Local.getSelectedRow());

                    this.mediosBasicos_existentes_de_local = new ArrayList<>(Arrays.asList(Referencias.BD.obtenerTodos_MediosBasicos(l)));
                    actualizarTabla_Medios_Basicos(this.mediosBasicos_existentes_de_local);

                } else {
                    DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
                }

            } else {
                final ArrayList<Medios_basicos> seleccionados = new ArrayList<>();
                this.mediosBasicos_existentes_de_local.forEach(v -> {
                    if (MI_ChB_Filtrar_Articulo_Por_Nombre.isSelected()
                            && v.getNombre().toLowerCase().contains(texto)) {
                        seleccionados.add(v);
                        return;
                    }
                    if (MI_ChB_Filtrar_Articulo_Por_ID.isSelected()
                            && (v.getId() + "").equals(texto)) {
                        seleccionados.add(v);

                    }
                });
                actualizarTabla_Medios_Basicos(seleccionados);
            }
        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarEliminarMedioBasico() {
        try {
            if (T_Local.getSelectedRow() != -1) {
                Local l = locales_en_tabla.get(T_Local.getSelectedRow());

                if (T_Medios_Basicos.getSelectedRow() != -1) {

                    if (JOptionPane.showConfirmDialog(this, "Desea eliminar el Medio Básico seleccionado?", "Advertencia", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                        Medios_basicos m = mediosBasicos_en_tabla.get(T_Medios_Basicos.getSelectedRow());

                        Referencias.BD.eliminarMedioBasico(m);
                        TI_Filtro_Locales.setText("");
                        TI_Filtros_Encargados.setText("");
                        TI_Filtros_MediosBasicos.setText("");

                        this.mediosBasicos_existentes_de_local.remove(m);

                        actualizarTabla_Medios_Basicos(this.mediosBasicos_existentes_de_local);

                        DialogosDeRespuesta.mostrarDlgExito(this, "Medio Básico eliminado con éxito  ");
                    }

                } else {
                    DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un Medio Básico en la tabla ");
                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarModificarMedioBasico() {
        try {
            if (T_Local.getSelectedRow() != -1) {
                Local l = locales_en_tabla.get(T_Local.getSelectedRow());

                if (T_Medios_Basicos.getSelectedRow() != -1) {
                    Medios_basicos m = mediosBasicos_en_tabla.get(T_Medios_Basicos.getSelectedRow());

                    String medioBasiocModificado = JOptionPane.showInputDialog(this, "Escriba el nombre del Medio Basico modificado ", m.getNombre());//"Modificar Local",JOptionPane.QUESTION_MESSAGE
                    if (medioBasiocModificado != null && Validaciones.validar_es_modificar_MedioBasico_correcto(this, medioBasiocModificado)) {
                        m.setNombre(medioBasiocModificado);
                        Medios_basicos medioModificado = Referencias.BD.modificarMedioBasico(m);

                        TI_Filtro_Locales.setText("");
                        TI_Filtros_Encargados.setText("");
                        TI_Filtros_MediosBasicos.setText("");

                        this.mediosBasicos_existentes_de_local.remove(m);
                        this.mediosBasicos_existentes_de_local.add(medioModificado);
                        actualizarTabla_Medios_Basicos(this.mediosBasicos_existentes_de_local);

                        DialogosDeRespuesta.mostrarDlgExito(this, "Medio Básico modificado con éxito  ");

                    }

                } else {
                    DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un Medio Básico en la tabla ");
                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarAgregarMedioBasico() {
        try {
            if (T_Local.getSelectedRow() != -1) {

                String nombreLocal = JOptionPane.showInputDialog(this, "Escriba el nombre del Medio Básico a agregar ", "Agregar Medio Básico", JOptionPane.QUESTION_MESSAGE);
                if (nombreLocal != null && Validaciones.validar_es_nuevo_MedioBasico_correcto(this, nombreLocal)) {
                    Local l = this.locales_en_tabla.get(T_Local.getSelectedRow());
                    Medios_basicos medioNuevo = Referencias.BD.agregarMedioBasico(l, nombreLocal);
                    DialogosDeRespuesta.mostrarDlgExito(this, "Medio Básico agregado con éxito");
                    TI_Filtro_Locales.setText("");
                    TI_Filtros_Encargados.setText("");
                    TI_Filtros_MediosBasicos.setText("");
                    this.mediosBasicos_existentes_de_local.add(medioNuevo);
                    actualizarTabla_Medios_Basicos(this.mediosBasicos_existentes_de_local);

                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarAgregarLocal() {
        try {
            String nombreLocal = JOptionPane.showInputDialog(this, "Escriba el nombre del Local a agregar ", "Agregar Local", JOptionPane.QUESTION_MESSAGE);
            if (nombreLocal != null && Validaciones.validar_es_nuevo_Local_correcto(this, nombreLocal)) {
                Local localNuevo = Referencias.BD.agregarLocal(nombreLocal);
                DialogosDeRespuesta.mostrarDlgExito(this, "Local agregado con éxito");
                TI_Filtro_Locales.setText("");
                TI_Filtros_Encargados.setText("");
                TI_Filtros_MediosBasicos.setText("");
                this.locales_existentes.add(localNuevo);
                actualizarTabla_Local(this.locales_existentes);
                //actualizarTabla_Local(Referencias.BD.obtenerTodos_Local());
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarModificarLocal() {
        try {
            if (T_Local.getSelectedRow() != -1) {
                Local l = locales_en_tabla.get(T_Local.getSelectedRow());

                String nombreLocal = JOptionPane.showInputDialog(this, "Escriba el nombre del Local modificado ", l.getNombre());//"Modificar Local",JOptionPane.QUESTION_MESSAGE
                if (nombreLocal != null && Validaciones.validar_es_modificar_Local_correcto(this, l, nombreLocal)) {
                    l.setNombre(nombreLocal);
                    Local localModificado = Referencias.BD.modificarLocal(l);

                    TI_Filtro_Locales.setText("");
                    TI_Filtros_Encargados.setText("");
                    TI_Filtros_MediosBasicos.setText("");

                    this.locales_existentes.remove(l);
                    this.locales_existentes.add(localModificado);
                    actualizarTabla_Local(this.locales_existentes);

                    DialogosDeRespuesta.mostrarDlgExito(this, "Local modificado con éxito  ");

                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

    private void intentarElimnarLocal() {
        try {
            if (T_Local.getSelectedRow() != -1) {
                if (JOptionPane.showConfirmDialog(this, "Desea eliminar el Local seleccionado?", "Advertencia", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
                    Local localSeleccionado = locales_en_tabla.get(T_Local.getSelectedRow());

                    Referencias.BD.eliminarLocal(localSeleccionado);
                    TI_Filtro_Locales.setText("");
                    TI_Filtros_Encargados.setText("");
                    TI_Filtros_MediosBasicos.setText("");

                    this.locales_existentes.remove(localSeleccionado);

                    actualizarTabla_Local(this.locales_existentes);

                    DialogosDeRespuesta.mostrarDlgExito(this, "Local eliminado con éxito  ");
                }

            } else {
                DialogosDeRespuesta.mostrarDlgInvalido(this, "Tiene que seleccionar un local en la tabla ");
            }

        } catch (Exception ex) {
            DialogosDeRespuesta.mostrarDlg_ErrorEnLaBD(this, ex);
        }
    }

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
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_Agregar_Encargado;
    private javax.swing.JButton B_Agregar_Local;
    private javax.swing.JButton B_Agregar_MedioBasico;
    private javax.swing.JButton B_Detalles_Encargado;
    private javax.swing.JButton B_Editar_Encargado;
    private javax.swing.JButton B_Eliminar_Encargado;
    private javax.swing.JButton B_Eliminar_Local;
    private javax.swing.JButton B_Eliminar_MedioBasico;
    private javax.swing.JButton B_Filtrar_Encargados;
    private javax.swing.JButton B_Filtrar_Locales;
    private javax.swing.JButton B_Filtrar_MediosBasicos;
    private javax.swing.JButton B_Invertir_Orden_Encargados;
    private javax.swing.JButton B_Invertir_Orden_Locales;
    private javax.swing.JButton B_Modificar_Local;
    private javax.swing.JButton B_Modificar_MedioBasico;
    private javax.swing.JButton B_Revertir_Orden_MediosBasicos;
    private javax.swing.JMenuItem MI_Administrador_Usuario;
    private javax.swing.JMenuItem MI_Agregar_Articulo;
    private javax.swing.JMenuItem MI_Agregar_Encargado;
    private javax.swing.JMenuItem MI_Agregar_Local;
    private javax.swing.JMenuItem MI_Cambiar_Contrasenna;
    private javax.swing.JMenuItem MI_Cerrar_Sesion;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Articulo_Por_ID;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Articulo_Por_Nombre;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Encargado_Por_Apellidos;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Encargado_Por_Cargo;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Encargado_Por_Correo;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Encargado_Por_Direccion;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Encargado_Por_Nombre;
    private javax.swing.JCheckBoxMenuItem MI_ChB_Filtrar_Encargado_Por_Telefono;
    private javax.swing.JMenuItem MI_Eliminar_Articulo;
    private javax.swing.JMenuItem MI_Eliminar_Encargado;
    private javax.swing.JMenuItem MI_Eliminar_Local;
    private javax.swing.JMenuItem MI_Modificar_Articulo;
    private javax.swing.JMenuItem MI_Modificar_Encargado;
    private javax.swing.JMenuItem MI_Modificar_Local;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Articulo_Por_ID;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Articulo_Por_Nombre;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Encargado_Por_Apellidos;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Encargado_Por_Cargo;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Encargado_Por_Correo;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Encargado_Por_Direccion;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Encargado_Por_Nombre;
    private javax.swing.JRadioButtonMenuItem MI_RB_Ordenar_Encargado_Por_Telefono;
    private javax.swing.JMenu M_Articulos;
    private javax.swing.JMenu M_Encargado;
    private javax.swing.JMenu M_Local;
    private javax.swing.JMenu M_Usuario;
    private javax.swing.JTextField TI_Filtro_Locales;
    private javax.swing.JTextField TI_Filtros_Encargados;
    private javax.swing.JTextField TI_Filtros_MediosBasicos;
    private javax.swing.JTable T_Encargados;
    private javax.swing.JTable T_Local;
    private javax.swing.JTable T_Medios_Basicos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
