/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Rene2
 */
public abstract class DialogosDeRespuesta {
    public static void mostrarDlgInvalido(Component parentComponent,String mensaje){
        JOptionPane.showMessageDialog(parentComponent, mensaje, "Entrada Invalida", JOptionPane.WARNING_MESSAGE);
    }
    public static void mostrarDlg_ErrorEnLaBD(Component parentComponent,Exception ex){
        JOptionPane.showMessageDialog(parentComponent, "Fallo en la conexión con el servidor: \n"+ex.getMessage(),"Error en el Servidor" , JOptionPane.ERROR_MESSAGE);
    }
    public static void mostrarDlgExito(Component parentComponent,String mensaje){
        JOptionPane.showMessageDialog(parentComponent, mensaje, "Éxito en la operación ", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
