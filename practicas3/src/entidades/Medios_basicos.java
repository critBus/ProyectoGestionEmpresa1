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
public class Medios_basicos implements Serializable {
	public Medios_basicos() {
	}
	
	public static Medios_basicos loadMedios_basicosByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadMedios_basicosByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos getMedios_basicosByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return getMedios_basicosByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos loadMedios_basicosByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadMedios_basicosByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos getMedios_basicosByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return getMedios_basicosByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos loadMedios_basicosByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Medios_basicos) session.load(entidades.Medios_basicos.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos getMedios_basicosByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Medios_basicos) session.get(entidades.Medios_basicos.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos loadMedios_basicosByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Medios_basicos) session.load(entidades.Medios_basicos.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos getMedios_basicosByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Medios_basicos) session.get(entidades.Medios_basicos.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMedios_basicos(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return queryMedios_basicos(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMedios_basicos(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return queryMedios_basicos(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos[] listMedios_basicosByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return listMedios_basicosByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos[] listMedios_basicosByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return listMedios_basicosByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryMedios_basicos(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Medios_basicos as Medios_basicos");
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
	
	public static List queryMedios_basicos(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Medios_basicos as Medios_basicos");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Medios_basicos", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos[] listMedios_basicosByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryMedios_basicos(session, condition, orderBy);
			return (Medios_basicos[]) list.toArray(new Medios_basicos[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos[] listMedios_basicosByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryMedios_basicos(session, condition, orderBy, lockMode);
			return (Medios_basicos[]) list.toArray(new Medios_basicos[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos loadMedios_basicosByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadMedios_basicosByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos loadMedios_basicosByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadMedios_basicosByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos loadMedios_basicosByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Medios_basicos[] medios_basicoses = listMedios_basicosByQuery(session, condition, orderBy);
		if (medios_basicoses != null && medios_basicoses.length > 0)
			return medios_basicoses[0];
		else
			return null;
	}
	
	public static Medios_basicos loadMedios_basicosByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Medios_basicos[] medios_basicoses = listMedios_basicosByQuery(session, condition, orderBy, lockMode);
		if (medios_basicoses != null && medios_basicoses.length > 0)
			return medios_basicoses[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateMedios_basicosByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return iterateMedios_basicosByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMedios_basicosByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return iterateMedios_basicosByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateMedios_basicosByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Medios_basicos as Medios_basicos");
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
	
	public static java.util.Iterator iterateMedios_basicosByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Medios_basicos as Medios_basicos");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Medios_basicos", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Medios_basicos loadMedios_basicosByCriteria(Medios_basicosCriteria medios_basicosCriteria) {
		Medios_basicos[] medios_basicoses = listMedios_basicosByCriteria(medios_basicosCriteria);
		if(medios_basicoses == null || medios_basicoses.length == 0) {
			return null;
		}
		return medios_basicoses[0];
	}
	
	public static Medios_basicos[] listMedios_basicosByCriteria(Medios_basicosCriteria medios_basicosCriteria) {
		return medios_basicosCriteria.listMedios_basicos();
	}
	
	public static Medios_basicos createMedios_basicos() {
		return new entidades.Medios_basicos();
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
			if(getLocal() != null) {
				getLocal().medios_basicos.remove(this);
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
			if(getLocal() != null) {
				getLocal().medios_basicos.remove(this);
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
	
	private void this_setOwner(Object owner, int key) {
		if (key == ORMConstants.KEY_MEDIOS_BASICOS_LOCAL) {
			this.local = (entidades.Local) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private int id;
	
	private String nombre;
	
	private entidades.Local local;
	
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
	
	public void setLocal(entidades.Local value) {
		if (local != null) {
			local.medios_basicos.remove(this);
		}
		if (value != null) {
			value.medios_basicos.add(this);
		}
	}
	
	public entidades.Local getLocal() {
		return local;
	}
	
	/**
	 * This method is for internal use only.
	 */
	private void setORM_Local(entidades.Local value) {
		this.local = value;
	}
	
	private entidades.Local getORM_Local() {
		return local;
	}
	
	public String toString() {
		return String.valueOf(getId());
	}
	
}
