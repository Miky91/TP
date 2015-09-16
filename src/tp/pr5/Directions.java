/**
 * Clase Enumerada donde guardamos las direcciones posibles
 * Autores: Iván Martín Herrero
 * 			Miguel de Andrés Herrero
 */
package tp.pr5;

public enum Directions {

	EAST,
	NORTH,
	SOUTH,
	UNKNOWN,
	WEST;
	/**
	 * Método que devuelve la dirección contraria desde la que es llamada.
	 * @return Direccion contraria.
	 */
	public Directions getOposite()
	{
		switch(this)
		{
		case NORTH:
			return SOUTH;

		case EAST:
			return WEST;

		case SOUTH:
			return NORTH;

		case WEST:
			return EAST;
		}
		return UNKNOWN;

	}

}

