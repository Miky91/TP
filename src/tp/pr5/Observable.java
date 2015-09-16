package tp.pr5;

import java.util.ArrayList;

/**
 * Clase donde guardamos los métodos para tratar con los observables y
 * observadores
 * 
 * @author Ivan Martín Herrero & Miguel de Andrés Herrero
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
	 * Métodos públicos
	 */
	/**
	 * Añade un observador al array
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
