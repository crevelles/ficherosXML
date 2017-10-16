package dom;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class LecturaXML {

	public static void main(String[] args) {
		
		
		//creamos instancia de DocumentBuiderFatory 
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// creamos el doccumentbuilder para usarlo como parseador
			DocumentBuilder db = dbf.newDocumentBuilder();
			//creamos el documento que vamos a leer
			Document document= db.parse(new File("empleados.xml"));
			
			
			//accedemos al nodo raiz
			Node raiz = document.getFirstChild();
			//mostramos por consola el nodo raiz
			System.out.println(raiz.getNodeName());
			//accedems a los nodos hijos del nodo raiz y obtenemos la lista
			NodeList elementos = raiz.getChildNodes();
			
			Node nodo, nodo2 = null;
			NodeList hijos, hijos2 = null;

			//recorremos la lista de nodos hijos del nodo raiz
			for (int i = 0; i < elementos.getLength(); i++) {
				//accedemos a los elementos de la lista --> empleado
				nodo = elementos.item(i); // obtener un nodo
				//comprobamos que cada nodo sea de tipo elemento
				if(nodo.getNodeType() == Node.ELEMENT_NODE) {
					//sacamos por consola el nombre de la etiqueta con una tabulación 
					System.out.println("\t" + nodo.getNodeName());
					//como sigue conteniendo hijos, accedemos a la lista de elementos hijos del nodo que estamos manejado
					hijos = nodo.getChildNodes();
					//recorremos la lista de nodos hijos del nodo qye estamos manejando 
					for (int j = 0; j < hijos.getLength(); j++) {
						//accedemos a los elementos de la lista --> id, nombre, departamento, salario
						nodo2 = hijos.item(j);
						//para cada uno de los nodos comprobamos que sea de tipo elemento
						if(nodo2.getNodeType() == Node.ELEMENT_NODE) {
							//sacamos por consola
							System.out.println("\t\t" + nodo2.getNodeName());
							hijos2 = nodo2.getChildNodes();
							System.out.println("Número de hijos de " + nodo2.getNodeName());
							//como sabemos que el nodo2 ya contiene texto y por tanto solo tiene un nodo hijo accedeos directamente al valor
							System.out.println("\t\t\t" + hijos2.item(0).getNodeValue());
							
						}
					}

				}

			}

			
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
