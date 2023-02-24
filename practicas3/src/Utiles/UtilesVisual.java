/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rene2
 */
public abstract class UtilesVisual {
    //public static void agregarEvento_AlTerminarElClick(Action){}

    public static void cambiarIconoDeDireccion(JButton boton, boolean arriba) {
        try {
            Class rfe = Referencias.BD.getClass();
            ImageIcon iconoArriba = new ImageIcon(rfe.getResource("../imgs/arriba double reducida.png"));
            ImageIcon iconoAbajo = new ImageIcon(rfe.getResource("../imgs/abajo double copia.png"));

            boton.setIcon(arriba ? iconoArriba : iconoAbajo);
        } catch (Exception ex) {
           
            try {
                File f=new File("../src/imgs/arriba double reducida.png");
                URL url = new URL(f.toURI().toURL().toString());
                
                
                ImageIcon iconoArriba = new ImageIcon(url);
                f=new File("../src/imgs/abajo double copia.png");
                url = new URL(f.toURI().toURL().toString());
                ImageIcon iconoAbajo = new ImageIcon(url);
                
                boton.setIcon(arriba ? iconoArriba : iconoAbajo);
            } catch (MalformedURLException ex1) {
                Logger.getLogger(UtilesVisual.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public static void ponerIconoDeVentana_Principal(Window w) {
        try {
            Class rfe = Referencias.BD.getClass();
            Image iconoVentana = Toolkit.getDefaultToolkit().getImage(rfe.getResource("../imgs/Sin título-1 ajustado 30.png"));

            w.setIconImage(iconoVentana);
        } catch (Exception ex) {
            try {
                //            Class rfe = Referencias.BD.getClass();
//            ClassLoader cl = rfe.getClassLoader();
//            URL url = cl.getResource("../imgs/Sin título-1 ajustado 30.png");
//            System.out.println("url="+url);
//ImageIcon iconoArriba = new ImageIcon(url);
//            Toolkit tlk=Toolkit.getDefaultToolkit();
//            System.out.println("tlk="+tlk);
//            Image iconoVentana = tlk.getImage(url);
                File f=new File("../src/imgs/Sin título-1 ajustado 30.png");
                //System.out.println("existe="+f.exists());
                //System.out.println("f.getAbsolutePath()="+f.getAbsolutePath());
                URL url = new URL(f.toURI().toURL().toString());
                ImageIcon icono = new ImageIcon(url);
                Image iconoVentana = icono.getImage();

                w.setIconImage(iconoVentana);
            } catch (MalformedURLException ex1) {
                ex.printStackTrace();
                Logger.getLogger(UtilesVisual.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
