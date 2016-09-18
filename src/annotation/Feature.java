package annotation;

/**
 * Clase anotacion.
 * @author raulgomez
 * @author felipemartinez
 */
public @interface Feature {
	Class padre = null;
	boolean or = false;
	boolean xor = false;
	boolean requerido = true;
}
