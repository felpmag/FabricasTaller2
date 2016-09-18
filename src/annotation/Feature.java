package annotation;

/**
 * Clase anotacion.
 * @author raulgomez
 * @author felipemartinez
 */
public @interface Feature {
	String padre = null;
	boolean or = false;
	boolean xor = false;
	boolean requerido = true;
}
