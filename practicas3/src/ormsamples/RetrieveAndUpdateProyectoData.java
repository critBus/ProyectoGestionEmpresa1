/**
 * Licensee: 
 * License Type: Purchased
 */
package ormsamples;

import org.orm.*;
public class RetrieveAndUpdateProyectoData {
	public void retrieveAndUpdateTestData() throws PersistentException {
		PersistentTransaction t = entidades.ProyectoPersistentManager.instance().getSession().beginTransaction();
		try {
			entidades.Encargado lentidadesEncargado = entidades.Encargado.loadEncargadoByQuery(null, null);
			// Update the properties of the persistent object
			lentidadesEncargado.save();
			entidades.Local lentidadesLocal = entidades.Local.loadLocalByQuery(null, null);
			// Update the properties of the persistent object
			lentidadesLocal.save();
			entidades.Medios_basicos lentidadesMedios_basicos = entidades.Medios_basicos.loadMedios_basicosByQuery(null, null);
			// Update the properties of the persistent object
			lentidadesMedios_basicos.save();
			entidades.Rol lentidadesRol = entidades.Rol.loadRolByQuery(null, null);
			// Update the properties of the persistent object
			lentidadesRol.save();
			entidades.Usuario lentidadesUsuario = entidades.Usuario.loadUsuarioByQuery(null, null);
			// Update the properties of the persistent object
			lentidadesUsuario.save();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public void retrieveByCriteria() throws PersistentException {
		System.out.println("Retrieving Encargado by EncargadoCriteria");
		entidades.EncargadoCriteria lentidadesEncargadoCriteria = new entidades.EncargadoCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lentidadesEncargadoCriteria.id.eq();
		System.out.println(lentidadesEncargadoCriteria.uniqueEncargado());
		
		System.out.println("Retrieving Local by LocalCriteria");
		entidades.LocalCriteria lentidadesLocalCriteria = new entidades.LocalCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lentidadesLocalCriteria.id.eq();
		System.out.println(lentidadesLocalCriteria.uniqueLocal());
		
		System.out.println("Retrieving Medios_basicos by Medios_basicosCriteria");
		entidades.Medios_basicosCriteria lentidadesMedios_basicosCriteria = new entidades.Medios_basicosCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lentidadesMedios_basicosCriteria.id.eq();
		System.out.println(lentidadesMedios_basicosCriteria.uniqueMedios_basicos());
		
		System.out.println("Retrieving Rol by RolCriteria");
		entidades.RolCriteria lentidadesRolCriteria = new entidades.RolCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lentidadesRolCriteria.id.eq();
		System.out.println(lentidadesRolCriteria.uniqueRol());
		
		System.out.println("Retrieving Usuario by UsuarioCriteria");
		entidades.UsuarioCriteria lentidadesUsuarioCriteria = new entidades.UsuarioCriteria();
		// Please uncomment the follow line and fill in parameter(s)
		//lentidadesUsuarioCriteria.id.eq();
		System.out.println(lentidadesUsuarioCriteria.uniqueUsuario());
		
	}
	
	
	public static void main(String[] args) {
		try {
			RetrieveAndUpdateProyectoData retrieveAndUpdateProyectoData = new RetrieveAndUpdateProyectoData();
			try {
				retrieveAndUpdateProyectoData.retrieveAndUpdateTestData();
				//retrieveAndUpdateProyectoData.retrieveByCriteria();
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
