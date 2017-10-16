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

public class LecturaXMLObjetoEmpleYdepto {

	public static void main(String[] args) {

		// creamos instancia de DocumentBuiderFatory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			// creamos el doccumentbuilder para usarlo como parseador
			DocumentBuilder db = dbf.newDocumentBuilder();
			// creamos el documento que vamos a leer
			Document document = db.parse(new File("empleadosDepto.xml"));

			// accedemos al nodo raiz
			Node raiz = document.getFirstChild();
			// mostramos por consola el nodo raiz
			System.out.println(raiz.getNodeName());
			// accedems a los nodos hijos del nodo raiz y obtenemos la lista
			NodeList elementos = raiz.getChildNodes();

			obtenerNodosHijos(elementos,"");

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

	private static void obtenerNodosHijos(NodeList elementos, String tab) {
		Node nodo;
		NodeList hijos;
		if (elementos.getLength() > 1) {
			for (int i = 0; i < elementos.getLength(); i++) {
				// accedemos a los elementos de la lista --> empleado
				nodo = elementos.item(i); // obtener un nodo
				// comprobamos que cada nodo sea de tipo elemento
				if (nodo.getNodeType() == Node.ELEMENT_NODE) {
					// sacamos por consola el nombre de la etiqueta con una tabulación
					System.out.println(tab + "\t" + nodo.getNodeName());
					// como sigue conteniendo hijos, accedemos a la lista de elementos hijos del
					// nodo que estamos manejado
					hijos = nodo.getChildNodes();
					obtenerNodosHijos(hijos,tab + "\t");
				}
			}
		} else {
			System.out.println(tab + "\t" + elementos.item(0).getNodeValue());
		}

	}

}
