package annotation;

/**
 * Clase anotacion.
 * @author raulgomez
 * @author felipemartinez
 */
public @interface Feature {
	String padre() default "";
	boolean or() default false;
	boolean xor() default false;
	boolean requerido() default false;
}
