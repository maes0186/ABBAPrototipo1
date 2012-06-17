package server.entidades;

import java.util.List;
import java.util.Vector;

import javax.ejb.*;
import javax.persistence.*;
//gd
@Stateful
public class ArticuloBean implements ArticuloRemote {
	@PersistenceContext(unitName = "JavaEE")
	private EntityManager manager;

	public void addArticulo(Articulo articulo) throws ArticuloException {
		if (manager.find(Articulo.class, articulo.getArtnr()) != null) {
			throw new ArticuloException("Artikel bereits vorhanden");
		}
		manager.persist(articulo);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Vector<Articulo> getArticulo() {
		List liste = manager.createNamedQuery("Articulo.findAll")
				.getResultList();
		Vector<Articulo> erg = new Vector<Articulo>();
		for (Object o : liste) {
			erg.add((Articulo) o);
		}
		return erg;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Articulo getArticulo(int artnr) throws ArticuloException {
		Articulo erg = manager.find(Articulo.class, artnr);
		if (erg == null)
			throw new ArticuloException("Articulo nicht gefunden");
		else
			return erg;
	}

	public void changeArticulo(Articulo neu) throws ArticuloException {
		manager.merge(neu);
	}

	public void deleteArticulo(int artnr) throws ArticuloException {
		Articulo alt = manager.find(Articulo.class, artnr);
		if (alt == null)
			throw new ArticuloException("Artikel nicht gefunden");
		manager.remove(alt);
	}

	@Remove
	public void remove() {
	}
}
