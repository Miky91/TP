package tp.pr5;

import java.util.ArrayList;

/**
 * Clase donde guardamos los m�todos para tratar con los observables y
 * observadores
 * 
 * @author Ivan Mart�n Herrero & Miguel de Andr�s Herrero
 * 
 * @param <E>
 */
public class Observable<E> {

	protected ArrayList<E> observarGame;

	/**
	 * Constructor por defecto
	 */
	public Observable() {
		this.observarGame = new ArrayList<E>();

	}

	/*
	 * M�todos p�blicos
	 */
	/**
	 * A�ade un observador al array
	 * @param entrada
	 */
	public void addObserver(E entrada) {
		if(!this.observarGame.contains(entrada))
		this.observarGame.add(entrada);
	}

	/**
	 * Elimina un observador del array
	 * @param entrada
	 */
	public void removeObserverG(E entrada) {

		int i = 0;
		while (entrada != this.observarGame.get(i))
			i++;
		this.observarGame.remove(i);
	}

}
