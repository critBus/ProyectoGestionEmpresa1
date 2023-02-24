/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import Visual.*;


/**
 *
 * @author Rene2
 */
public class Referencias {
    public static Conexion BD=new Conexion();
    public static Registro ventana_reguistro=new Registro();
    public static Ventana_Principal ventana_principal=new Ventana_Principal();
    public static Dialogo_Agregar_Encargado dialogo_agregar_Encargado=new Dialogo_Agregar_Encargado(ventana_principal, true);
    public static Dialogo_Editar_Encargado dialogo_editar_Encargado=new Dialogo_Editar_Encargado(ventana_principal, true);
    public static Ventana_Administrar_Usuarios ventana_administrar_usuarios=new Ventana_Administrar_Usuarios();
    public static Dialogo_Agregar_Usuario dialogo_agregar_Usuario=new Dialogo_Agregar_Usuario(ventana_administrar_usuarios, true);
    public static Dialogo_Cambiar_Contrasenna dialogo_cambiar_contrasenna=new Dialogo_Cambiar_Contrasenna(ventana_administrar_usuarios, true);
    public static Dialogo_Editar_Usuario dialogo_editar_usuario=new Dialogo_Editar_Usuario(ventana_administrar_usuarios,true);
    public static Dialogo_Detalles_Encargado dialogo_detalles_encargado=new Dialogo_Detalles_Encargado(ventana_principal,true);
    
    
    
}
