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

public class Medios_basicosDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression nombre;
	public final IntegerExpression localId;
	public final AssociationExpression local;
	
	public Medios_basicosDetachedCriteria() {
		super(entidades.Medios_basicos.class, entidades.Medios_basicosCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		nombre = new StringExpression("nombre", this.getDetachedCriteria());
		localId = new IntegerExpression("local.id", this.getDetachedCriteria());
		local = new AssociationExpression("local", this.getDetachedCriteria());
	}
	
	public Medios_basicosDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, entidades.Medios_basicosCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		nombre = new StringExpression("nombre", this.getDetachedCriteria());
		localId = new IntegerExpression("local.id", this.getDetachedCriteria());
		local = new AssociationExpression("local", this.getDetachedCriteria());
	}
	
	public LocalDetachedCriteria createLocalCriteria() {
		return new LocalDetachedCriteria(createCriteria("local"));
	}
	
	public Medios_basicos uniqueMedios_basicos(PersistentSession session) {
		return (Medios_basicos) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Medios_basicos[] listMedios_basicos(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Medios_basicos[]) list.toArray(new Medios_basicos[list.size()]);
	}
}

