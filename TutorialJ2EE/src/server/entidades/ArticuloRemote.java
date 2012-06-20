package server.entidades;


import java.util.Vector;

import javax.ejb.Remote;

@Remote
public interface ArticuloRemote {
	public void addArticulo(Articulo artikel) throws ArticuloException;
	public void addTest(Test test) throws TestException;
	public Vector<Articulo> getArticulo();

	public Articulo getArticulo(int artnr) throws ArticuloException;

	public void changeArticulo(Articulo neu) throws ArticuloException;

	public void deleteArticulo(int artnr) throws ArticuloException;

	public void remove();
}
