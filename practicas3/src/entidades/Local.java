/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: 
 * License Type: Purchased
 */
package entidades;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

import java.io.Serializable;
public class Local implements Serializable {
	public Local() {
	}
	
	public static Local loadLocalByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadLocalByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local getLocalByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return getLocalByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local loadLocalByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadLocalByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local getLocalByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return getLocalByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local loadLocalByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Local) session.load(entidades.Local.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local getLocalByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Local) session.get(entidades.Local.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local loadLocalByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Local) session.load(entidades.Local.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local getLocalByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Local) session.get(entidades.Local.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryLocal(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return queryLocal(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryLocal(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return queryLocal(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local[] listLocalByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return listLocalByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local[] listLocalByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return listLocalByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryLocal(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Local as Local");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryLocal(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Local as Local");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Local", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local[] listLocalByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryLocal(session, condition, orderBy);
			return (Local[]) list.toArray(new Local[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local[] listLocalByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryLocal(session, condition, orderBy, lockMode);
			return (Local[]) list.toArray(new Local[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local loadLocalByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadLocalByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local loadLocalByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadLocalByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local loadLocalByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Local[] locals = listLocalByQuery(session, condition, orderBy);
		if (locals != null && locals.length > 0)
			return locals[0];
		else
			return null;
	}
	
	public static Local loadLocalByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Local[] locals = listLocalByQuery(session, condition, orderBy, lockMode);
		if (locals != null && locals.length > 0)
			return locals[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateLocalByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return iterateLocalByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateLocalByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return iterateLocalByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateLocalByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Local as Local");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateLocalByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Local as Local");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Local", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Local loadLocalByCriteria(LocalCriteria localCriteria) {
		Local[] locals = listLocalByCriteria(localCriteria);
		if(locals == null || locals.length == 0) {
			return null;
		}
		return locals[0];
	}
	
	public static Local[] listLocalByCriteria(LocalCriteria localCriteria) {
		return localCriteria.listLocal();
	}
	
	public static Local createLocal() {
		return new entidades.Local();
	}
	
	public boolean save() throws PersistentException {
		try {
			ProyectoPersistentManager.instance().saveObject(this);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean delete() throws PersistentException {
		try {
			ProyectoPersistentManager.instance().deleteObject(this);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean refresh() throws PersistentException {
		try {
			ProyectoPersistentManager.instance().getSession().refresh(this);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean evict() throws PersistentException {
		try {
			ProyectoPersistentManager.instance().getSession().evict(this);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate()throws PersistentException {
		try {
			entidades.Encargado[] lEncargados = encargado.toArray();
			for(int i = 0; i < lEncargados.length; i++) {
				lEncargados[i].setLocal(null);
			}
			entidades.Medios_basicos[] lMedios_basicoss = medios_basicos.toArray();
			for(int i = 0; i < lMedios_basicoss.length; i++) {
				lMedios_basicoss[i].setLocal(null);
			}
			return delete();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(org.orm.PersistentSession session)throws PersistentException {
		try {
			entidades.Encargado[] lEncargados = encargado.toArray();
			for(int i = 0; i < lEncargados.length; i++) {
				lEncargados[i].setLocal(null);
			}
			entidades.Medios_basicos[] lMedios_basicoss = medios_basicos.toArray();
			for(int i = 0; i < lMedios_basicoss.length; i++) {
				lMedios_basicoss[i].setLocal(null);
			}
			try {
				session.delete(this);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == ORMConstants.KEY_LOCAL_ENCARGADO) {
			return ORM_encargado;
		}
		else if (key == ORMConstants.KEY_LOCAL_MEDIOS_BASICOS) {
			return ORM_medios_basicos;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int id;
	
	private String nombre;
	
	private java.util.Set ORM_encargado = new java.util.HashSet();
	
	private java.util.Set ORM_medios_basicos = new java.util.HashSet();
	
	private void setId(int value) {
		this.id = value;
	}
	
	public int getId() {
		return id;
	}
	
	public int getORMID() {
		return getId();
	}
	
	public void setNombre(String value) {
		this.nombre = value;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	private void setORM_Encargado(java.util.Set value) {
		this.ORM_encargado = value;
	}
	
	private java.util.Set getORM_Encargado() {
		return ORM_encargado;
	}
	
	public final entidades.EncargadoSetCollection encargado = new entidades.EncargadoSetCollection(this, _ormAdapter, ORMConstants.KEY_LOCAL_ENCARGADO, ORMConstants.KEY_ENCARGADO_LOCAL, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Medios_basicos(java.util.Set value) {
		this.ORM_medios_basicos = value;
	}
	
	private java.util.Set getORM_Medios_basicos() {
		return ORM_medios_basicos;
	}
	
	public final entidades.Medios_basicosSetCollection medios_basicos = new entidades.Medios_basicosSetCollection(this, _ormAdapter, ORMConstants.KEY_LOCAL_MEDIOS_BASICOS, ORMConstants.KEY_MEDIOS_BASICOS_LOCAL, ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
