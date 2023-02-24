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

public class EncargadoDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression id;
	public final StringExpression nombre;
	public final StringExpression apellidos;
	public final StringExpression correo;
	public final StringExpression direccion;
	public final IntegerExpression phone;
	public final StringExpression cargo;
	public final IntegerExpression localId;
	public final AssociationExpression local;
	
	public EncargadoDetachedCriteria() {
		super(entidades.Encargado.class, entidades.EncargadoCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		nombre = new StringExpression("nombre", this.getDetachedCriteria());
		apellidos = new StringExpression("apellidos", this.getDetachedCriteria());
		correo = new StringExpression("correo", this.getDetachedCriteria());
		direccion = new StringExpression("direccion", this.getDetachedCriteria());
		phone = new IntegerExpression("phone", this.getDetachedCriteria());
		cargo = new StringExpression("cargo", this.getDetachedCriteria());
		localId = new IntegerExpression("local.id", this.getDetachedCriteria());
		local = new AssociationExpression("local", this.getDetachedCriteria());
	}
	
	public EncargadoDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, entidades.EncargadoCriteria.class);
		id = new IntegerExpression("id", this.getDetachedCriteria());
		nombre = new StringExpression("nombre", this.getDetachedCriteria());
		apellidos = new StringExpression("apellidos", this.getDetachedCriteria());
		correo = new StringExpression("correo", this.getDetachedCriteria());
		direccion = new StringExpression("direccion", this.getDetachedCriteria());
		phone = new IntegerExpression("phone", this.getDetachedCriteria());
		cargo = new StringExpression("cargo", this.getDetachedCriteria());
		localId = new IntegerExpression("local.id", this.getDetachedCriteria());
		local = new AssociationExpression("local", this.getDetachedCriteria());
	}
	
	public LocalDetachedCriteria createLocalCriteria() {
		return new LocalDetachedCriteria(createCriteria("local"));
	}
	
	public Encargado uniqueEncargado(PersistentSession session) {
		return (Encargado) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Encargado[] listEncargado(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Encargado[]) list.toArray(new Encargado[list.size()]);
	}
}

