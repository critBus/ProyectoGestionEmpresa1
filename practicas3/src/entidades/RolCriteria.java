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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class RolCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression nombre;
	public final CollectionExpression usuario;
	
	public RolCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		nombre = new StringExpression("nombre", this);
		usuario = new CollectionExpression("ORM_Usuario", this);
	}
	
	public RolCriteria(PersistentSession session) {
		this(session.createCriteria(Rol.class));
	}
	
	public RolCriteria() throws PersistentException {
		this(ProyectoPersistentManager.instance().getSession());
	}
	
	public UsuarioCriteria createUsuarioCriteria() {
		return new UsuarioCriteria(createCriteria("ORM_Usuario"));
	}
	
	public Rol uniqueRol() {
		return (Rol) super.uniqueResult();
	}
	
	public Rol[] listRol() {
		java.util.List list = super.list();
		return (Rol[]) list.toArray(new Rol[list.size()]);
	}
}

