package annotation;

/**
 * Clase anotacion.
 * @author raulgomez
 * @author felipemartinez
 */
public @interface Feature {
	Class<?> padre();
	boolean or() default true;
	boolean xor() default false;
	boolean requerido() default true;
}
