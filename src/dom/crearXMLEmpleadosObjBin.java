package dom;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
public class crearXMLEmpleadosObjBin {

	public static void main(String[] args) {

		ObjectInputStream oi = null;

		try {
			oi = new ObjectInputStream(new FileInputStream("empleadosOBJ.dat"));
			Empleado emp = null;
			emp = (Empleado) oi.readObject();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			DOMImplementation implementacion = db.getDOMImplementation();
			
			Document doc = implementacion.createDocument(null, "empleados", null);
		
			
			Element elemento = null;

		try {
			while (emp != null) {
				// Creamos un documento vacío de nombre doc con el nodo raíz de nombre empleados
				//y asignamos la versión del XML
				
				doc.setXmlVersion("1.0");
				
				System.out.println("ID: " + emp.getId() 
								+ " Nombre: " + emp.getNombre() 
								+ " Depto: " + emp.getDept() 
								+ " Salario: " + emp.getSalario());

				
				//creamos un nodo o elemento de nombre empelado
				elemento =   doc.createElement("empleado");
				doc.getDocumentElement().appendChild(elemento);
				
				//creamos otro elemento hijo
				crearElementoHijo( doc, elemento, "id", (emp.getId()+""));
				crearElementoHijo( doc, elemento, "nombre", (emp.getNombre()));
				crearElementoHijo( doc, elemento, "departamento", (emp.getDept()+""));
				crearElementoHijo( doc, elemento, "salario", (emp.getSalario()+""));

				
				
				emp = (Empleado) oi.readObject();
			}
			
		}catch (EOFException e) {
			System.out.println("Final lectura");
		} 
			
			
			// Creamos la fuente XML a partir del documento
			Source source = new DOMSource(doc);
			// Creamos el resultado en el fichero empleados.xml
			StreamResult result = new StreamResult(new File("empleados.xml"));

			// Obtenemos un TransformerFactory
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			// Le damos formato y realizamos la transformación del documento a fichero
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml"); 
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source,  result); 
			
			// Mostramos el documento por pantalla especificando como canal de salida el System.out
			Result console = new StreamResult(System.out);
			transformer.transform(source, console);


			
			

		} catch (FileNotFoundException e) {
			System.out.println("Error en E/S del fichero");
		} catch (IOException e) {
			System.out.println("Error en E/S del fichero");
		} catch (ClassNotFoundException e) {

		} catch (ParserConfigurationException e) {
			System.out.println("Error en la creación del XML");
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oi != null) {
				try {
					oi.close();
				} catch (IOException e) {
					System.out.println("Error al cerrar el stream");
				}
			}
		}

	}

	//El metodo recibe el doc y el elemneto al que va a añadir el elemnto hijo, 
	//el nombre de la etiqueta correspodientoe al elemento hijo y el contenido del elemnto hijo
	//ESTE METODO SIRVE PARA ELEMENTOS FINALES
	private static void crearElementoHijo(Document doc, Element elemento, String nombreElemento, String valor) {
		//CREA el elemtno hijo
		Element elemHijo = doc.createElement(nombreElemento);
		//crea el texto correspondiente al contenido
		Text text = doc.createTextNode(valor);
		//asigna el elemento hijo al padre
		elemento.appendChild(elemHijo);
		//asigna el contenido texto al elemento hijo
		elemHijo.appendChild(text);
	}

}
