/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import entidades.*;
import org.orm.PersistentException;

/**
 *
 * @author Rene2
 */
public class Conexion {
//    public boolean existeMedioBasico(Local l,String nombre){
//        
//    }
    public Usuario modificarUsuario(Usuario u,TipoDeRol rol) throws Exception {
        u.save();
        boolean esAdmin=esAdministrador(u);
        if(rol==TipoDeRol.ADMINISTRADOR&&!esAdmin){
            Rol r = obtenerEsteRol(TipoDeRol.ADMINISTRADOR);
            u.rol.add(r);
            u.save();
        }
        if(rol==TipoDeRol.USUARIO&&esAdmin){
            Rol r = obtenerEsteRol(TipoDeRol.ADMINISTRADOR);
            u.rol.remove(r);
            u.save();
        }
        return u;
    }
    public void eliminarUsuario(Usuario u) throws Exception{
        u.deleteAndDissociate();
    }

    public Encargado editarEncargado(Encargado e) throws Exception {
        e.save();
        return e;
    }

    public Encargado agregarEncargado(Local l, String nombre, String apellidos, String cargo, String correo, String direccion, String telefono) throws Exception {
        Encargado e = new Encargado();
        e.setApellidos(apellidos);
        e.setCargo(cargo);
        e.setCorreo(correo);
        e.setDireccion(direccion);
        e.setLocal(l);
        e.setNombre(nombre);
        e.setPhone(Integer.parseInt(telefono));
        e.save();
        return e;
    }

    public boolean existeEncargado(Local l, String nombre, String apellidos) throws Exception {
        Encargado[] le = obtenerTodos_Encargados(l);
        for (Encargado encargado : le) {
            if (encargado.getNombre().equals(nombre)
                    && encargado.getApellidos().equals(apellidos)) {
                return true;
            }
        }
        return false;
    }

    public Medios_basicos modificarMedioBasico(Medios_basicos l) throws Exception {
        l.save();
        return l;
    }

    public Medios_basicos agregarMedioBasico(Local l, String nombre) throws Exception {
        Medios_basicos m = new Medios_basicos();
        m.setLocal(l);
        m.setNombre(nombre);
        m.save();
        return m;

    }

    public void eliminarMedioBasico(Medios_basicos e) throws Exception {
        e.deleteAndDissociate();
    }

    public Medios_basicos[] obtenerTodos_MediosBasicos(Local l) throws Exception {
        return l.medios_basicos.toArray();
    }

    public void eliminarEncargado(Encargado e) throws Exception {
        e.deleteAndDissociate();
    }

    public Encargado[] obtenerTodos_Encargados(Local l) throws Exception {
        return l.encargado.toArray();
    }

    public void eliminarLocal(Local l) throws Exception {
        Encargado[] le = obtenerTodos_Encargados(l);
        for (Encargado encargado : le) {
            eliminarEncargado(encargado);
        }
        Medios_basicos[] lm = obtenerTodos_MediosBasicos(l);
        for (Medios_basicos medios_basicos : lm) {
            eliminarMedioBasico(medios_basicos);
        }
        l.deleteAndDissociate();
    }
    

    public Local modificarLocal(Local l) throws Exception {
        l.save();
        return l;
    }

    public Local[] obtenerTodos_Local() throws Exception {
        return Local.listLocalByQuery(null, null);
    }

    public boolean existeLocal(String nombre) throws Exception {
        Local[] L = obtenerTodos_Local();
        for (Local local : L) {
            if (local.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }

    public Local agregarLocal(String nombre) throws Exception {
        Local l = new Local();
        l.setNombre(nombre);
        l.save();
        return l;
    }

    public Usuario agregarUsuario(String username, String password, TipoDeRol tipoDeRol) throws Exception {
        Usuario u = new Usuario();
        u.setPassword(Encriptar.encriptar(password));
        u.setUsername(username);
        u.save();
        agregarRolesDeSerNecesario();

        if (tipoDeRol == TipoDeRol.ADMINISTRADOR) {
            agregarRolAUsuario(u, TipoDeRol.ADMINISTRADOR);
        }
        agregarRolAUsuario(u, TipoDeRol.USUARIO);

        return u;
    }

    public boolean existeUsuario(String username, String password) throws Exception {
//        System.out.println("password0="+password);
        Usuario[] U = obtenerTodos_Usuarios();
        password = Encriptar.encriptar(password);
        for (int i = 0; i < U.length; i++) {
//            System.out.println("U[i].getUsername()="+U[i].getUsername());
//            System.out.println("U[i].getPassword()="+U[i].getPassword());
//            System.out.println("username="+username);
//            System.out.println("password="+password);
            if (U[i].getUsername().equals(username) && U[i].getPassword().equals(password)) {
                return true;
            }
        }
//        System.out.println("no existe el usuario");
        return false;
    }
    public boolean existeUsuario(String username) throws Exception {
        Usuario[] U = obtenerTodos_Usuarios();
        
        for (int i = 0; i < U.length; i++) {
            if (U[i].getUsername().equals(username) ) {
                return true;
            }
        }
        return false;
    }

    public Usuario[] obtenerTodos_Usuarios() throws Exception {
        Usuario[] us=Usuario.listUsuarioByQuery(null, null);
        //System.out.println("us leng="+us.length);
        return us;
    }

    public Usuario obtenerUsuario(String username) throws Exception {
        Usuario[] U = obtenerTodos_Usuarios();
        for (int i = 0; i < U.length; i++) {
            if (U[i].getUsername().equals(username)) {
                return U[i];
            }
        }
        return null;
    }

    private void agregarRolesDeSerNecesario() throws Exception {
        Rol R[] = Rol.listRolByQuery(null, null);
        boolean existeRol_ADMINISTRADOR = false;
        boolean existeRol_USUARIO = false;
        for (int i = 0; i < R.length; i++) {
            String rolStr=R[i].getNombre();
            if (rolStr.equals(TipoDeRol.ADMINISTRADOR.toString())) {
                existeRol_ADMINISTRADOR = true;
            }
            if (rolStr.equals(TipoDeRol.USUARIO.toString())) {
                existeRol_USUARIO = true;
            }
        }
        if (!existeRol_ADMINISTRADOR) {
            Rol r = new Rol();
            r.setNombre(TipoDeRol.ADMINISTRADOR.toString());
            r.save();
        }
        if (!existeRol_USUARIO) {
            Rol r = new Rol();
            r.setNombre(TipoDeRol.USUARIO.toString());
            r.save();

        }
    }

    private boolean tieneRol(Usuario u, TipoDeRol rol) throws Exception {
        Rol[] R = obtenerTodos_Rol_De_Usuario(u);
        for (int i = 0; i < R.length; i++) {
            Rol r = R[i];
            if (r.getNombre().equals(rol.toString())) {
                return true;
            }
        }
        return false;
    }

    public boolean esAdministrador(Usuario u) throws Exception {
        return tieneRol(u, TipoDeRol.ADMINISTRADOR);
    }

    private Rol[] obtenerTodos_Rol() throws Exception {
        return Rol.listRolByQuery(null, null);
    }

    private Rol[] obtenerTodos_Rol_De_Usuario(Usuario u) throws Exception {
        return u.rol.toArray();
    }

    public boolean esUsuario(Usuario u) throws Exception {
        return tieneRol(u, TipoDeRol.USUARIO);
    }

    private Rol obtenerEsteRol(TipoDeRol rol) throws Exception {
        Rol[] R = obtenerTodos_Rol();
        for (int i = 0; i < R.length; i++) {
            Rol r = R[i];
            if (r.getNombre().equals(rol.toString())) {
                return r;
            }
        }
        return null;
    }

    private void agregarRolAUsuario(Usuario u, TipoDeRol rol) throws Exception {
        if (!tieneRol(u, rol)) {
            Rol r = obtenerEsteRol(rol);
            u.rol.add(r);
            u.save();
        }
    }
}
