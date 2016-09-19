package annotation;

/**
 * Clase anotacion.
 * @author raulgomez
 * @author felipemartinez
 */
public @interface Feature {
	Class<?> padre() default DEFAULT.class;      
	static final class DEFAULT {};
	boolean or() default false;
	boolean xor() default false;
	boolean requerido() default false;
}
