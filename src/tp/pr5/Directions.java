/**
 * Clase Enumerada donde guardamos las direcciones posibles
 * Autores: Iv�n Mart�n Herrero
 * 			Miguel de Andr�s Herrero
 */
package tp.pr5;

public enum Directions {

	EAST,
	NORTH,
	SOUTH,
	UNKNOWN,
	WEST;
	/**
	 * M�todo que devuelve la direcci�n contraria desde la que es llamada.
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

