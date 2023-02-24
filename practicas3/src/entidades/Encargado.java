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
public class Encargado implements Serializable {
	public Encargado() {
	}
	
	public static Encargado loadEncargadoByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadEncargadoByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado getEncargadoByORMID(int id) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return getEncargadoByORMID(session, id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado loadEncargadoByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadEncargadoByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado getEncargadoByORMID(int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return getEncargadoByORMID(session, id, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado loadEncargadoByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Encargado) session.load(entidades.Encargado.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado getEncargadoByORMID(PersistentSession session, int id) throws PersistentException {
		try {
			return (Encargado) session.get(entidades.Encargado.class, new Integer(id));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado loadEncargadoByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Encargado) session.load(entidades.Encargado.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado getEncargadoByORMID(PersistentSession session, int id, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Encargado) session.get(entidades.Encargado.class, new Integer(id), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryEncargado(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return queryEncargado(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryEncargado(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return queryEncargado(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado[] listEncargadoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return listEncargadoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado[] listEncargadoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return listEncargadoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryEncargado(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Encargado as Encargado");
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
	
	public static List queryEncargado(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Encargado as Encargado");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Encargado", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado[] listEncargadoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryEncargado(session, condition, orderBy);
			return (Encargado[]) list.toArray(new Encargado[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado[] listEncargadoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryEncargado(session, condition, orderBy, lockMode);
			return (Encargado[]) list.toArray(new Encargado[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado loadEncargadoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadEncargadoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado loadEncargadoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return loadEncargadoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado loadEncargadoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Encargado[] encargados = listEncargadoByQuery(session, condition, orderBy);
		if (encargados != null && encargados.length > 0)
			return encargados[0];
		else
			return null;
	}
	
	public static Encargado loadEncargadoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Encargado[] encargados = listEncargadoByQuery(session, condition, orderBy, lockMode);
		if (encargados != null && encargados.length > 0)
			return encargados[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateEncargadoByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return iterateEncargadoByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateEncargadoByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = ProyectoPersistentManager.instance().getSession();
			return iterateEncargadoByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateEncargadoByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Encargado as Encargado");
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
	
	public static java.util.Iterator iterateEncargadoByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From entidades.Encargado as Encargado");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Encargado", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Encargado loadEncargadoByCriteria(EncargadoCriteria encargadoCriteria) {
		Encargado[] encargados = listEncargadoByCriteria(encargadoCriteria);
		if(encargados == null || encargados.length == 0) {
			return null;
		}
		return encargados[0];
	}
	
	public static Encargado[] listEncargadoByCriteria(EncargadoCriteria encargadoCriteria) {
		return encargadoCriteria.listEncargado();
	}
	
	public static Encargado createEncargado() {
		return new entidades.Encargado();
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
				getLocal().encargado.remove(this);
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
				getLocal().encargado.remove(this);
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
		if (key == ORMConstants.KEY_ENCARGADO_LOCAL) {
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
	
	private String apellidos;
	
	private String correo;
	
	private String direccion;
	
	private int phone;
	
	private String cargo;
	
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
	
	public void setApellidos(String value) {
		this.apellidos = value;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setCorreo(String value) {
		this.correo = value;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setDireccion(String value) {
		this.direccion = value;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setPhone(int value) {
		this.phone = value;
	}
	
	public int getPhone() {
		return phone;
	}
	
	public void setCargo(String value) {
		this.cargo = value;
	}
	
	public String getCargo() {
		return cargo;
	}
	
	public void setLocal(entidades.Local value) {
		if (local != null) {
			local.encargado.remove(this);
		}
		if (value != null) {
			value.encargado.add(this);
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
