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

public class EncargadoCriteria extends AbstractORMCriteria {
	public final IntegerExpression id;
	public final StringExpression nombre;
	public final StringExpression apellidos;
	public final StringExpression correo;
	public final StringExpression direccion;
	public final IntegerExpression phone;
	public final StringExpression cargo;
	public final IntegerExpression localId;
	public final AssociationExpression local;
	
	public EncargadoCriteria(Criteria criteria) {
		super(criteria);
		id = new IntegerExpression("id", this);
		nombre = new StringExpression("nombre", this);
		apellidos = new StringExpression("apellidos", this);
		correo = new StringExpression("correo", this);
		direccion = new StringExpression("direccion", this);
		phone = new IntegerExpression("phone", this);
		cargo = new StringExpression("cargo", this);
		localId = new IntegerExpression("local.id", this);
		local = new AssociationExpression("local", this);
	}
	
	public EncargadoCriteria(PersistentSession session) {
		this(session.createCriteria(Encargado.class));
	}
	
	public EncargadoCriteria() throws PersistentException {
		this(ProyectoPersistentManager.instance().getSession());
	}
	
	public LocalCriteria createLocalCriteria() {
		return new LocalCriteria(createCriteria("local"));
	}
	
	public Encargado uniqueEncargado() {
		return (Encargado) super.uniqueResult();
	}
	
	public Encargado[] listEncargado() {
		java.util.List list = super.list();
		return (Encargado[]) list.toArray(new Encargado[list.size()]);
	}
}

