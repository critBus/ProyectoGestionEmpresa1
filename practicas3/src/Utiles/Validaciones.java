/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import entidades.*;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Rene2
 */
public class Validaciones {

    private final static String MENSAJE_NO_PUEDE_ESTAR_VACIO = "No puede estar vacio ";
     private final static String MENSAJE_NO_PUEDE_TENER_NUMEROS="No puede tener números ";
     private final static String TIENEN_QUE_SER_SOLO_LETRAS="Tienen que ser solo letras ";
    private final static String MENSAJE_CANTIDAD_MAXIMA_DE_CARACTERES = "Supera la cantidad máxima de caracteres ";
    private final static String MENSAJE_NO_CARACTERES_ESTRANNOS = "No puede contener caracteres extraños ";
    private final static String MENSAJE_DEBE_CONTENER_LETRAS = "Debe de contener letras ";
    private final static String MENSAJE_YA_EXISTE_LOCAL = "Ya existe un local con este nombre ";
    private final static String MENSAJE_NOMBRES_CON_MAYUSCULA = "Las palabras deben de comenzar en mayúscula";
    private final static String MENSAJE_NOMBRES_SOLO_PRIMERA_LETRA_MAYUSCULA = "Solo la primera letra de un nombre debe ser en mayúscula ";
    private final static String MENSAJE_CANTIDAD_MINIMA_DE_CARACTERES = "No supera la cantidad mínima de caracteres ";
    private final static String MENSAJE_CANTIDAD_CARACTERES_TELEFONO = "Un teléfono debe de contener 8 caracteres ";
    private final static String MENSAJE_TODOS_DEBEN_DE_SER_NUMEROS = "Todos los caracteres deben de ser números ";
    private final static String MENSAJE_YA_EXISTE_ENCARGADO_EN_LOCAL = "Ya existe un encargado en este local con este nombre ";
    private final static String MENSAJE_CANTIDAD_MAXIMA_DE_ENCARGADOS_EN_EL_LOCAL = "Ya se alcanzó la cantidad máxima de encargados en este local ";
    private final static String MENSAJE_DEBEN_TENER_NUMEROS = "Debe de contener números";
    private final static String MENSAJE_YA_EXISTE_USUARIO = "Ya existe este usuario ";
    private final static String MENSAJE_COINCIDIR_CONTRASENNAS="Tienen que coincidir las contraseñas ";

    public static String obtener_validacion_seguridad_cantrasenna(String s,String confirmar){
        s = s.trim();
        if (s.isEmpty()) {
            return MENSAJE_NO_PUEDE_ESTAR_VACIO;
        }
        if (s.length() > 50) {
            return MENSAJE_CANTIDAD_MAXIMA_DE_CARACTERES;
        }
        if (s.length() < 8) {
            return MENSAJE_CANTIDAD_MINIMA_DE_CARACTERES;
        }
        confirmar=confirmar.trim();
        if(!confirmar.equals(s)){
            return MENSAJE_COINCIDIR_CONTRASENNAS;
        }
        
        boolean contieneLetras = false;
        boolean tieneNumeros=false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isLetter(c)) {
                contieneLetras = true;
            }
            if(Character.isDigit(c)){
                tieneNumeros=true;
            }
            if(tieneNumeros&&contieneLetras){
                break;
            }
        }
        if (!contieneLetras) {
            return MENSAJE_DEBE_CONTENER_LETRAS;
        }
        if (!tieneNumeros) {
            return MENSAJE_DEBEN_TENER_NUMEROS;
        }
        return null;
    }
    
    public static String obtener_validacion_telefono_cubano(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return MENSAJE_NO_PUEDE_ESTAR_VACIO;
        }
        if (s.length() != 8) {
            return MENSAJE_CANTIDAD_CARACTERES_TELEFONO;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isDigit(c)) {
                return MENSAJE_TODOS_DEBEN_DE_SER_NUMEROS;
            }
        }
        return null;
    }

    public static String obtener_validacion_con_letras(String s, int cantidad_minima_caracteres, int cantidad_maxima_caracteres) {
        s = s.trim();
        if (s.isEmpty()) {
            return MENSAJE_NO_PUEDE_ESTAR_VACIO;
        }
        if (s.length() > cantidad_maxima_caracteres) {
            return MENSAJE_CANTIDAD_MAXIMA_DE_CARACTERES;
        }
        if (s.length() < cantidad_minima_caracteres) {
            return MENSAJE_CANTIDAD_MINIMA_DE_CARACTERES;
        }
        boolean contieneLetras = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isLetter(c)) {
                contieneLetras = true;
            }
        }
        if (!contieneLetras) {
            return MENSAJE_DEBE_CONTENER_LETRAS;
        }
        return null;
    }

    public static String obtener_validacion_nombre(String s, int cantidad_minima_caracteres, int cantidad_maxima_caracteres) {
        s = s.trim();
        if (s.isEmpty()) {
            return MENSAJE_NO_PUEDE_ESTAR_VACIO;
        }
        if (s.length() > cantidad_maxima_caracteres) {
            return MENSAJE_CANTIDAD_MAXIMA_DE_CARACTERES;
        }
        if (s.length() < cantidad_minima_caracteres) {
            return MENSAJE_CANTIDAD_MINIMA_DE_CARACTERES;
        }
        boolean contieneLetras = false;
        boolean elAnteriorFueEspacio = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
//            if (!(Character.isLetter(c)  || Character.isWhitespace(c))) {
//                return MENSAJE_NO_CARACTERES_ESTRANNOS;
//            }
            if (Character.isLetter(c)) {
                contieneLetras = true;
                elAnteriorFueEspacio = false;
                if (i == 0 || elAnteriorFueEspacio) {
                    if (!Character.isUpperCase(c)) {
                        return MENSAJE_NOMBRES_CON_MAYUSCULA;
                    }
                } else {
                    if (Character.isUpperCase(c)) {
                        return MENSAJE_NOMBRES_SOLO_PRIMERA_LETRA_MAYUSCULA;
                    }
                }
                continue;
            }
            if (Character.isWhitespace(c)) {
                elAnteriorFueEspacio = true;

                continue;
            }
            return TIENEN_QUE_SER_SOLO_LETRAS;
        }
        if (!contieneLetras) {
            return MENSAJE_DEBE_CONTENER_LETRAS;
        }
        return null;
    }

    public static String obtener_validacion_correo(String email) {

        int posicionarroba, posicionpunto, longitud;
        longitud = email.length();
        posicionarroba = email.indexOf("@");
        posicionpunto = email.indexOf(".");
        if (posicionarroba == -1) {
            System.out.println("No hay arroba en el mail");
            return "No hay arroba en el mail";
        } else if (posicionarroba == 0 || posicionarroba == longitud - 1) {
            System.out.println("La arroba no puede estar ni al principio ni al final");
            return "La arroba no puede estar ni al principio ni al final";
        } else if (posicionarroba != email.lastIndexOf("@")) {
            System.out.println("Existe mas de una @ dentro del mail");
            return "Existe mas de una @ dentro del mail";
        } else if (posicionpunto == -1) {
            System.out.println("No hay punto en el mail");
            return "No hay punto en el mail";
        } else if (posicionpunto == 0 || posicionpunto == longitud - 1) {
            System.out.println("El punto no puede estar ni al principio ni al final");
            return "El punto no puede estar ni al principio ni al final";
        } else if (email.indexOf("..") != -1) {
            System.out.println("No pueden existir dos puntos seguidos");
            return "No pueden existir dos puntos seguidos";
        } else if (email.indexOf("@.") != -1 || email.indexOf(".@") != -1) {
            System.out.println("El punto no puede ir seguido de la @");
            return "El punto no puede ir seguido de la @";
        } else if (email.indexOf(" ") != -1) {
            System.out.println("Un email no puede contenter espacios");
            return "Un email no puede contenter espacios";
        } else {
            String dominio;
            int ultimopunto;
            ultimopunto = email.lastIndexOf(".");
            dominio = email.substring(ultimopunto + 1);
            if (dominio.length() >= 2 && dominio.length() <= 4) {
                System.out.println("EMAIL CORRECTO");
                return null;
            } else {
                System.out.println("El dominio solo puede ser de 2 a 4 caracteres");
                return "El dominio solo puede ser de 2 a 4 caracteres";
            }
        }
    }

    
    
    /**
     * <ul>
     * <li>tiene que tener letras como minimo</li>
     * <li>no puede contener caracteres extraños </li>
     * </ul>
     *
     * @param s
     * @return  <ul>
     * <li><b>null</b> si es valido </li>
     * <li><b>String</b> con el mensaje correspondiente si no es valido  </li>
     * </ul>
     */
    public static String obtener_validacion_string_normal(String s, int cantidad_maxima_caracteres) {
        s = s.trim();
        if (s.isEmpty()) {
            return MENSAJE_NO_PUEDE_ESTAR_VACIO;
        }
        if (s.length() > cantidad_maxima_caracteres) {
            return MENSAJE_CANTIDAD_MAXIMA_DE_CARACTERES;
        }
        boolean contieneLetras = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(Character.isAlphabetic(c) || Character.isDigit(c) || Character.isWhitespace(c))) {
                return MENSAJE_NO_CARACTERES_ESTRANNOS;
            }
            if (Character.isLetter(c)) {
                contieneLetras = true;
            }
        }
        if (!contieneLetras) {
            return MENSAJE_DEBE_CONTENER_LETRAS;
        }
        return null;
    }

    private static boolean validar_es_string_normal(Component parentComponent, String s, int cantidad_maxima_caracteres) {
        return validar_es_string_normal(parentComponent, s, cantidad_maxima_caracteres, null);
    }

    private static boolean validar_es_string_normal(Component parentComponent, String s, int cantidad_maxima_caracteres, String principioDeMensaje) {
        String respuesta = obtener_validacion_string_normal(s, cantidad_maxima_caracteres);
        if (respuesta != null) {
            if (principioDeMensaje != null) {
                respuesta = principioDeMensaje + respuesta;
            }
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, respuesta);
        }
        return respuesta == null;
    }

    public static boolean validar_es_nuevo_Local_correcto(Component parentComponent, String nombre) throws Exception {
        if (Referencias.BD.existeLocal(nombre)) {
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, MENSAJE_YA_EXISTE_LOCAL);
            return false;
        }
        return validar_es_string_normal(parentComponent, nombre, 50);
    }

    public static boolean validar_se_Puede_Agregar_encargado(Component parentComponent, Local l) throws Exception {
        if (Referencias.BD.obtenerTodos_Encargados(l).length >= 2) {
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, MENSAJE_CANTIDAD_MAXIMA_DE_ENCARGADOS_EN_EL_LOCAL);
            return false;
        }
        return true;
    }

    public static boolean validar_datos_encargados_correctos(Component parentComponent,String nombre, String apellidos, String cargo, String correo, String direccion, String telefono) {
        return validar_es_nombre(parentComponent, nombre, 4, 50, "En el Nombre: ")
                && validar_es_nombre(parentComponent, apellidos, 4, 50, "En los Apellidos: ")
                && validar_es_string_normal(parentComponent, cargo, 50, "En el Cargo: ")
                && validar_es_correo(parentComponent, correo, "En el Correo: ")
                && validar_es_con_letras(parentComponent, direccion, 4, 256, "En la Direccion: ")
                && validar_es_telefono_cubano(parentComponent, telefono, "En el Telefono: ");
    }

    public static boolean validar_es_editar_Encargado_correcto(Component parentComponent,
             Encargado e, String nombre, String apellidos, String cargo, String correo, String direccion, String telefono
    ) throws Exception {
        if (((!e.getNombre().equals(nombre))
                || (!e.getApellidos().equals(apellidos))) && Referencias.BD.existeEncargado(e.getLocal(), nombre, apellidos)) {
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, MENSAJE_YA_EXISTE_ENCARGADO_EN_LOCAL);
            return false;
        }

       return validar_datos_encargados_correctos(parentComponent,nombre, apellidos, cargo, correo, direccion, telefono);
    }

    public static boolean validar_es_nuevo_Encargado_correcto(Component parentComponent,
             Local l, String nombre, String apellidos, String cargo, String correo, String direccion, String telefono
    ) throws Exception {
        if (Referencias.BD.existeEncargado(l, nombre, apellidos)) {
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, MENSAJE_YA_EXISTE_ENCARGADO_EN_LOCAL);
            return false;
        }
        if (!validar_se_Puede_Agregar_encargado(parentComponent, l)) {
            return false;
        }
        return validar_datos_encargados_correctos(parentComponent,nombre, apellidos, cargo, correo, direccion, telefono);

    }

    public static boolean validar_es_modificar_Local_correcto(Component parentComponent, Local l, String nombre) throws Exception {
        if ((!l.getNombre().equals(nombre)) && Referencias.BD.existeLocal(nombre)) {
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, MENSAJE_YA_EXISTE_LOCAL);
            return false;
        }
        return validar_es_string_normal(parentComponent, nombre, 50);
    }

    public static boolean validar_es_nuevo_MedioBasico_correcto(Component parentComponent, String nombre) throws Exception {

        return validar_es_string_normal(parentComponent, nombre, 256);
    }

    public static boolean validar_es_modificar_MedioBasico_correcto(Component parentComponent, String nombre) throws Exception {

        return validar_es_string_normal(parentComponent, nombre, 256);
    }

    private static boolean validar_es_telefono_cubano(Component parentComponent, String s, String principioDeMensaje) {
        String respuesta = obtener_validacion_telefono_cubano(s);
        if (respuesta != null) {
            if (principioDeMensaje != null) {
                respuesta = principioDeMensaje + respuesta;
            }
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, respuesta);
        }
        return respuesta == null;
    }

    private static boolean validar_es_con_letras(Component parentComponent, String s, int cantidad_minima_caracteres, int cantidad_maxima_caracteres, String principioDeMensaje) {
        String respuesta = obtener_validacion_con_letras(s, cantidad_minima_caracteres, cantidad_maxima_caracteres);
        if (respuesta != null) {
            if (principioDeMensaje != null) {
                respuesta = principioDeMensaje + respuesta;
            }
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, respuesta);
        }
        return respuesta == null;
    }

    private static boolean validar_es_nombre(Component parentComponent, String s, int cantidad_minima_caracteres, int cantidad_maxima_caracteres, String principioDeMensaje) {
        String respuesta = obtener_validacion_nombre(s, cantidad_minima_caracteres, cantidad_maxima_caracteres);
        if (respuesta != null) {
            if (principioDeMensaje != null) {
                respuesta = principioDeMensaje + respuesta;
            }
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, respuesta);
        }
        return respuesta == null;
    }

    private static boolean validar_es_correo(Component parentComponent, String s, String principioDeMensaje) {
        String respuesta = obtener_validacion_correo(s);
        if (respuesta != null) {
            if (principioDeMensaje != null) {
                respuesta = principioDeMensaje + respuesta;
            }
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, respuesta);
        }
        return respuesta == null;
    }
    
    
    public static boolean validar_seguridad_cantrasenna(Component parentComponent,String contrasenna,String confirmar, String principioDeMensaje) {
        String respuesta = obtener_validacion_seguridad_cantrasenna(contrasenna,confirmar);
        if (respuesta != null) {
            if (principioDeMensaje != null) {
                respuesta = principioDeMensaje + respuesta;
            }
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, respuesta);
        }
        return respuesta == null;
    }
    
    
     public static boolean validar_es_nuevo_usuario_correcto(Component parentComponent,
             String username, String contrasenna,String confirmar
    ) throws Exception {
        if (Referencias.BD.existeUsuario( username, contrasenna)) {
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, MENSAJE_YA_EXISTE_USUARIO);
            return false;
        }
        return validar_es_con_letras(parentComponent, username, 4, 50, "El username:")
                &&validar_seguridad_cantrasenna(parentComponent,contrasenna,confirmar,"En las contraseñas:");
    }
     
     public static boolean validar_es_modificar_usuario_correcto(Component parentComponent,
             Usuario u,String username
    ) throws Exception {
        if ( (!u.getUsername().equals(username))&&Referencias.BD.existeUsuario( username)) {
            DialogosDeRespuesta.mostrarDlgInvalido(parentComponent, MENSAJE_YA_EXISTE_USUARIO);
            return false;
        }
        return validar_es_con_letras(parentComponent, username, 4, 50, "El username:");
    }
}
