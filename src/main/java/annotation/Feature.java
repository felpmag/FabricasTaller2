package annotation;

/**
 * Clase anotacion.
 * @author raulgomez
 * @author felipemartinez
 */
public @interface Feature {
	String nombre();
	String padre() default "";
	boolean or() default false;
	boolean xor() default false;
	boolean requerido() default false;
	String requiero() default "";
}
