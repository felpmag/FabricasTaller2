package annotation;

/**
 * Clase anotacion.
 * @author raulgomez
 * @author felipemartinez
 */
public @interface Feature {
    public enum RestriccionHijos{
        AND,OR,ALT;
    }
    
	String nombre();
	String padre() default "";
	RestriccionHijos relacion() default RestriccionHijos.AND;
	boolean requerido() default false;
	String requiero() default "";
}
