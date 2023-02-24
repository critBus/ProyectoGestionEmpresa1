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

public class LocalDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression nombre;
	public final CollectionExpression encargado;
	public final CollectionExpression medios_basicos;
	
	public LocalDetachedCriteria() {
		super(entidades.Local.class, entidades.LocalCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		nombre = new StringExpression("nombre", this.getDetachedCriteria());
		encargado = new CollectionExpression("ORM_Encargado", this.getDetachedCriteria());
		medios_basicos = new CollectionExpression("ORM_Medios_basicos", this.getDetachedCriteria());
	}
	
	public LocalDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, entidades.LocalCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		nombre = new StringExpression("nombre", this.getDetachedCriteria());
		encargado = new CollectionExpression("ORM_Encargado", this.getDetachedCriteria());
		medios_basicos = new CollectionExpression("ORM_Medios_basicos", this.getDetachedCriteria());
	}
	
	public EncargadoDetachedCriteria createEncargadoCriteria() {
		return new EncargadoDetachedCriteria(createCriteria("ORM_Encargado"));
	}
	
	public Medios_basicosDetachedCriteria createMedios_basicosCriteria() {
		return new Medios_basicosDetachedCriteria(createCriteria("ORM_Medios_basicos"));
	}
	
	public Local uniqueLocal(PersistentSession session) {
		return (Local) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Local[] listLocal(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Local[]) list.toArray(new Local[list.size()]);
	}
}

