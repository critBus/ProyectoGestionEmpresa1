/**
 * Licensee: 
 * License Type: Purchased
 */
package ormsamples;

import org.orm.*;
public class DeleteProyectoData {
	public void deleteTestData() throws PersistentException {
		PersistentTransaction t = entidades.ProyectoPersistentManager.instance().getSession().beginTransaction();
		try {
			entidades.Encargado lentidadesEncargado = entidades.Encargado.loadEncargadoByQuery(null, null);
			lentidadesEncargado.delete();
			entidades.Local lentidadesLocal = entidades.Local.loadLocalByQuery(null, null);
			lentidadesLocal.delete();
			entidades.Medios_basicos lentidadesMedios_basicos = entidades.Medios_basicos.loadMedios_basicosByQuery(null, null);
			lentidadesMedios_basicos.delete();
			entidades.Rol lentidadesRol = entidades.Rol.loadRolByQuery(null, null);
			lentidadesRol.delete();
			entidades.Usuario lentidadesUsuario = entidades.Usuario.loadUsuarioByQuery(null, null);
			lentidadesUsuario.delete();
			t.commit();
		}
		catch (Exception e) {
			t.rollback();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			DeleteProyectoData deleteProyectoData = new DeleteProyectoData();
			try {
				deleteProyectoData.deleteTestData();
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
