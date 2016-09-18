package annotation;

public @interface MyAnnotation {
	String myAttribute() default "defaultValue";

}
