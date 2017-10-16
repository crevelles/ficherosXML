package dom;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class CrearXMLDOMempleadosDeptBIN {

	public static void main(String[] args) {

		ObjectInputStream oi = null;

		try {
			oi = new ObjectInputStream(new FileInputStream("empleadosDeptOBJ.dat"));
			EmpledoDepartamento emp = null;
			emp = (EmpledoDepartamento) oi.readObject();
			//creamos el XML
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			DOMImplementation implementacion = db.getDOMImplementation();
			
			Document doc = implementacion.createDocument(null, "empleados", null);
		
			
			Element elemento = null;
			Element elementoHijo = null;
		try {
			while (emp != null) {
				// Creamos un documento vacío de nombre doc con el nodo raíz de nombre empleados
				//y asignamos la versión del XML
				
				doc.setXmlVersion("1.0");
				
				System.out.println("ID: " + emp.getId() 
								+ " Nombre: " + emp.getNombre() 
								+ " Depto: " + emp.getDpto().getNombreDept() 
								+ " Salario: " + emp.getSalario());

				
				//creamos un nodo o elemento de nombre empelado
				elemento =   doc.createElement("empleado");
				doc.getDocumentElement().appendChild(elemento);
				//creamos otro nodo
				elementoHijo = doc.createElement("departamento");
				doc.getDocumentElement().appendChild(elementoHijo);
				elemento.appendChild(elementoHijo);
				
				//añadimos atributo
				elemento.setAttribute("Fecha_alta", "27/11/2016");
				//observa que primero accedemos al getDpto.....
				crearElementoHijo(doc, elementoHijo, "id_departamento", emp.getDpto().getId()+"");
				crearElementoHijo(doc, elementoHijo, "nombre", emp.getDpto().getNombreDept());
				crearElementoHijo(doc, elementoHijo, "localizacion", emp.getDpto().getLocalizacion());
				
				//creamos otro elemento hijo
				crearElementoHijo( doc, elemento, "id", (emp.getId()+""));
				crearElementoHijo( doc, elemento, "nombre", (emp.getNombre()));
				
				
				
				
				
				crearElementoHijo( doc, elemento, "salario", (emp.getSalario()+""));
				
				
				
				
				emp = (EmpledoDepartamento) oi.readObject();
			}
			
			
			
			
		}catch (EOFException e) {
			System.out.println("Final lectura");
		} 
			
			
			// Creamos la fuente XML a partir del documento
			Source source = new DOMSource(doc);
			// Creamos el resultado en el fichero empleados.xml
			StreamResult result = new StreamResult(new File("empleadosDepto.xml"));

			// Obtenemos un TransformerFactory
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			
			// Le damos formato y realizamos la transformación del documento a fichero
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.METHOD, "xml"); 
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
			transformer.transform(source,  result); 
			
			// Mostramos el documento por consola especificando como canal de salida el System.out
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
