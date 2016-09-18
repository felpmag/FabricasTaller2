package code;

import annotation.MyAnnotation;

@MyAnnotation(myAttribute="Class")
public class TestClass {

	int i;
	
	public TestClass(){}
	
	public void foo(){
		bar();
	}
	
	@MyAnnotation(myAttribute="Method")
	private int bar(){
		this.i++;
		return this.i;
	}
	

}
