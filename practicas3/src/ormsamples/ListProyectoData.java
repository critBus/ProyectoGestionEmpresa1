/**
 * Licensee: 
 * License Type: Purchased
 */
package ormsamples;

import org.orm.*;
public class ListProyectoData {
	private static final int ROW_COUNT = 100;
	
	public void listTestData() throws PersistentException {
		System.out.println("Listing Encargado...");
		entidades.Encargado[] entidadesEncargados = entidades.Encargado.listEncargadoByQuery(null, null);
		int length = Math.min(entidadesEncargados.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(entidadesEncargados[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Local...");
		entidades.Local[] entidadesLocals = entidades.Local.listLocalByQuery(null, null);
		length = Math.min(entidadesLocals.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(entidadesLocals[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Medios_basicos...");
		entidades.Medios_basicos[] entidadesMedios_basicoses = entidades.Medios_basicos.listMedios_basicosByQuery(null, null);
		length = Math.min(entidadesMedios_basicoses.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(entidadesMedios_basicoses[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Rol...");
		entidades.Rol[] entidadesRols = entidades.Rol.listRolByQuery(null, null);
		length = Math.min(entidadesRols.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(entidadesRols[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
		System.out.println("Listing Usuario...");
		entidades.Usuario[] entidadesUsuarios = entidades.Usuario.listUsuarioByQuery(null, null);
		length = Math.min(entidadesUsuarios.length, ROW_COUNT);
		for (int i = 0; i < length; i++) {
			System.out.println(entidadesUsuarios[i]);
		}
		System.out.println(length + " record(s) retrieved.");
		
	}
	
	public void listByCriteria() throws PersistentException {
		System.out.println("Listing Encargado by Criteria...");
		entidades.EncargadoCriteria lentidadesEncargadoCriteria = new entidades.EncargadoCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lentidadesEncargadoCriteria.id.eq();
		lentidadesEncargadoCriteria.setMaxResults(ROW_COUNT);
		entidades.Encargado[] entidadesEncargados = lentidadesEncargadoCriteria.listEncargado();
		int length =entidadesEncargados== null ? 0 : Math.min(entidadesEncargados.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(entidadesEncargados[i]);
		}
		System.out.println(length + " Encargado record(s) retrieved."); 
		
		System.out.println("Listing Local by Criteria...");
		entidades.LocalCriteria lentidadesLocalCriteria = new entidades.LocalCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lentidadesLocalCriteria.id.eq();
		lentidadesLocalCriteria.setMaxResults(ROW_COUNT);
		entidades.Local[] entidadesLocals = lentidadesLocalCriteria.listLocal();
		length =entidadesLocals== null ? 0 : Math.min(entidadesLocals.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(entidadesLocals[i]);
		}
		System.out.println(length + " Local record(s) retrieved."); 
		
		System.out.println("Listing Medios_basicos by Criteria...");
		entidades.Medios_basicosCriteria lentidadesMedios_basicosCriteria = new entidades.Medios_basicosCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lentidadesMedios_basicosCriteria.id.eq();
		lentidadesMedios_basicosCriteria.setMaxResults(ROW_COUNT);
		entidades.Medios_basicos[] entidadesMedios_basicoses = lentidadesMedios_basicosCriteria.listMedios_basicos();
		length =entidadesMedios_basicoses== null ? 0 : Math.min(entidadesMedios_basicoses.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(entidadesMedios_basicoses[i]);
		}
		System.out.println(length + " Medios_basicos record(s) retrieved."); 
		
		System.out.println("Listing Rol by Criteria...");
		entidades.RolCriteria lentidadesRolCriteria = new entidades.RolCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lentidadesRolCriteria.id.eq();
		lentidadesRolCriteria.setMaxResults(ROW_COUNT);
		entidades.Rol[] entidadesRols = lentidadesRolCriteria.listRol();
		length =entidadesRols== null ? 0 : Math.min(entidadesRols.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(entidadesRols[i]);
		}
		System.out.println(length + " Rol record(s) retrieved."); 
		
		System.out.println("Listing Usuario by Criteria...");
		entidades.UsuarioCriteria lentidadesUsuarioCriteria = new entidades.UsuarioCriteria();
		// Please uncomment the follow line and fill in parameter(s) 
		//lentidadesUsuarioCriteria.id.eq();
		lentidadesUsuarioCriteria.setMaxResults(ROW_COUNT);
		entidades.Usuario[] entidadesUsuarios = lentidadesUsuarioCriteria.listUsuario();
		length =entidadesUsuarios== null ? 0 : Math.min(entidadesUsuarios.length, ROW_COUNT); 
		for (int i = 0; i < length; i++) {
			 System.out.println(entidadesUsuarios[i]);
		}
		System.out.println(length + " Usuario record(s) retrieved."); 
		
	}
	
	public static void main(String[] args) {
		try {
			ListProyectoData listProyectoData = new ListProyectoData();
			try {
				listProyectoData.listTestData();
				//listProyectoData.listByCriteria();
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
