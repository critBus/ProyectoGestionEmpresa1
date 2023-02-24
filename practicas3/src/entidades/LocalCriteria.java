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

public class LocalCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression nombre;
	public final CollectionExpression encargado;
	public final CollectionExpression medios_basicos;
	
	public LocalCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		nombre = new StringExpression("nombre", this);
		encargado = new CollectionExpression("ORM_Encargado", this);
		medios_basicos = new CollectionExpression("ORM_Medios_basicos", this);
	}
	
	public LocalCriteria(PersistentSession session) {
		this(session.createCriteria(Local.class));
	}
	
	public LocalCriteria() throws PersistentException {
		this(ProyectoPersistentManager.instance().getSession());
	}
	
	public EncargadoCriteria createEncargadoCriteria() {
		return new EncargadoCriteria(createCriteria("ORM_Encargado"));
	}
	
	public Medios_basicosCriteria createMedios_basicosCriteria() {
		return new Medios_basicosCriteria(createCriteria("ORM_Medios_basicos"));
	}
	
	public Local uniqueLocal() {
		return (Local) super.uniqueResult();
	}
	
	public Local[] listLocal() {
		java.util.List list = super.list();
		return (Local[]) list.toArray(new Local[list.size()]);
	}
}

