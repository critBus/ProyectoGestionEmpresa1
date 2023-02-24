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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class RolDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression nombre;
	public final CollectionExpression usuario;
	
	public RolDetachedCriteria() {
		super(entidades.Rol.class, entidades.RolCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		nombre = new StringExpression("nombre", this.getDetachedCriteria());
		usuario = new CollectionExpression("ORM_Usuario", this.getDetachedCriteria());
	}
	
	public RolDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, entidades.RolCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		nombre = new StringExpression("nombre", this.getDetachedCriteria());
		usuario = new CollectionExpression("ORM_Usuario", this.getDetachedCriteria());
	}
	
	public UsuarioDetachedCriteria createUsuarioCriteria() {
		return new UsuarioDetachedCriteria(createCriteria("ORM_Usuario"));
	}
	
	public Rol uniqueRol(PersistentSession session) {
		return (Rol) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Rol[] listRol(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Rol[]) list.toArray(new Rol[list.size()]);
	}
}

