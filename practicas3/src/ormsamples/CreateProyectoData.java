/**
 * Licensee: 
 * License Type: Purchased
 */
package ormsamples;

import org.orm.*;
public class CreateProyectoData {
	public void createTestData() throws PersistentException {
		PersistentTransaction t = entidades.ProyectoPersistentManager.instance().getSession().beginTransaction();
		try {
			entidades.Encargado lentidadesEncargado = entidades.Encargado.createEncargado();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : local, cargo, phone, direccion, correo, apellidos, nombre
			lentidadesEncargado.save();
			entidades.Local lentidadesLocal = entidades.Local.createLocal();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : medios_basicos, encargado, nombre
			lentidadesLocal.save();
			entidades.Medios_basicos lentidadesMedios_basicos = entidades.Medios_basicos.createMedios_basicos();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : local, nombre
			lentidadesMedios_basicos.save();
			entidades.Rol lentidadesRol = entidades.Rol.createRol();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : usuario, nombre
			lentidadesRol.save();
			entidades.Usuario lentidadesUsuario = entidades.Usuario.createUsuario();
			// TODO Initialize the properties of the persistent object here, the following properties must be initialized before saving : rol, password, username
			lentidadesUsuario.save();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			CreateProyectoData createProyectoData = new CreateProyectoData();
			try {
				createProyectoData.createTestData();
			}
			finally {
				entidades.ProyectoPersistentManager.instance().disposePersistentManager();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
