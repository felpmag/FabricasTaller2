package processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;
/**
 * Utility class to marshall and unmarshall xml files using JAXB
 * @author carlos
 *
 */
public class JaxbWriterReader {

	/**
	 * Reads the contents of an xml with JAXB
	 * 
	 * @param classType
	 *            the class type of the root of the document
	 * @param path
	 *            path to the file to load
	 * @return an object representing the root of the file loaded
	 */
	public static Object jaxbReader(Class<?> classType, String path) {
		Object root = null;
		try {
			JAXBContext context = JAXBContext.newInstance(classType);
			root = context.createUnmarshaller().unmarshal(new FileReader(path));

		} catch (JAXBException e) {
			System.err
					.println("Error while reading the JAXB model in: " + path);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("Error while trying to load the file: " + path);
			e.printStackTrace();
		}
		return root;
	}

	/**
	 * Reads the contents of an xml with JAXB and validates it with an schema
	 * 
	 * @param classType
	 *            the class type of the root of the document
	 * @param pathToXML
	 *            path to the xml file to load
	 * @param pathToXSD
	 *            path to the xsd used for the validation
	 * @return an object representing the root of the file loaded
	 */
	public static Object jaxbReaderSchemaValidation(Class<?> classType,
			String pathToXML, String pathToXSD) {
		Object root = null;
		try {

			JAXBContext context = JAXBContext.newInstance(classType);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			Schema schema = SchemaFactory.newInstance(
					XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(
					new File(pathToXSD));
			unmarshaller.setSchema(schema);
			root = unmarshaller.unmarshal(new FileReader(pathToXML));

		} catch (JAXBException e) {
			System.err.println("Error while reading the JAXB model in: "
					+ pathToXML + " with the schema: " + pathToXSD);
			e.printStackTrace();
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.err.println("Error while trying to load the file: "
					+ pathToXML);
			e.printStackTrace();
		} catch (SAXException e) {
			System.err.println("Error while processing the file " + pathToXML
					+ " with the schema: " + pathToXSD);
			e.printStackTrace();
			System.exit(1);
		}
		return root;
	}
	
	/**
	 * Writes the contents of a JAXB model in an xml file with identation and
	 * blank spaces without schema verification
	 * 
	 * @param root
	 *            the root of the object to write
	 * @param path
	 *            destination of the file to create
	 */
	public static void jaxbWriterNoSchema(Object root, String path) {
		try {
			JAXBContext context = JAXBContext.newInstance(root.getClass());
			Marshaller marshaller = context.createMarshaller();
			// marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
			// MAVEN_SCHEMA);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(root, new FileWriter(path));
		} catch (JAXBException e) {
			System.err
					.println("Error while preparing to write the JAXB model in: "
							+ path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error while writting the JAXB model in: "
					+ path);
			e.printStackTrace();
		}
	}

	/**
	 * Writes the contents of a JAXB model in an xml file with identation and
	 * blank spaces and schema verification
	 * 
	 * @param root
	 *            the root of the object to write
	 * @param path
	 *            destination of the file to create
	 */
	public static void jaxbWriter(Object root, String path, String schema) {
		try {
			JAXBContext context = JAXBContext.newInstance(root.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, schema);
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.marshal(root, new FileWriter(path));
		} catch (JAXBException e) {
			System.err
					.println("Error while preparing to write the JAXB model in: "
							+ path);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Error while writting the JAXB model in: "
					+ path);
			e.printStackTrace();
		}
	}
}
