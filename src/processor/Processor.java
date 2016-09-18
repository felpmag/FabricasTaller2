package processor;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtAnnotation;
import annotation.MyAnnotation;

public class Processor extends AbstractProcessor<CtAnnotation<MyAnnotation>>{
	
	@Override
	public void init() {
		System.out.println("Empieza procesamiento");
		super.init();
	}
	@Override
	public void process(CtAnnotation<MyAnnotation> annotation) {
		String attributeValue = annotation.getActualAnnotation().myAttribute();
		System.out.println("Anotacion encontrada, atributo: "+attributeValue);
		System.out.println("\t usada en: "+annotation.getParent().getSignature());
	}
	
	@Override
	public void processingDone() {
		System.out.println("Termina procesamiento");
		super.processingDone();
	}

}
