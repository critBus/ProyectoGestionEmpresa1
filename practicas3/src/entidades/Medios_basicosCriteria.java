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

public class Medios_basicosCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression nombre;
	public final IntegerExpression localId;
	public final AssociationExpression local;
	
	public Medios_basicosCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		nombre = new StringExpression("nombre", this);
		localId = new IntegerExpression("local.id", this);
		local = new AssociationExpression("local", this);
	}
	
	public Medios_basicosCriteria(PersistentSession session) {
		this(session.createCriteria(Medios_basicos.class));
	}
	
	public Medios_basicosCriteria() throws PersistentException {
		this(ProyectoPersistentManager.instance().getSession());
	}
	
	public LocalCriteria createLocalCriteria() {
		return new LocalCriteria(createCriteria("local"));
	}
	
	public Medios_basicos uniqueMedios_basicos() {
		return (Medios_basicos) super.uniqueResult();
	}
	
	public Medios_basicos[] listMedios_basicos() {
		java.util.List list = super.list();
		return (Medios_basicos[]) list.toArray(new Medios_basicos[list.size()]);
	}
}

