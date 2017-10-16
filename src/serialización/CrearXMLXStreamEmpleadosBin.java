package serialización;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import com.thoughtworks.xstream.XStream;
import dom.Empleado;



public class CrearXMLXStreamEmpleadosBin {

	public static void main(String[] args) {
	
		
		ObjectInputStream ois = null;

			try {
				ois = new ObjectInputStream(new FileInputStream("empleadosOBJ.dat"));
				Empleado emp = null;
				ListaEmpleados listaEmp = new ListaEmpleados();
				
				
				//recorremos el fichero
				try {
					while ((emp = (Empleado) ois.readObject()) != null) {
						// añadimos el empleado a la lista
						listaEmp.add(emp);
					}
				}catch (EOFException e) {
					System.out.println("Fin fichero");
				}
				
				// Creamos el fichero XML 
				XStream xs = new XStream();
				xs.alias("Empleados", ListaEmpleados.class);
				xs.alias("DatosEmpleado", Empleado.class);
				xs.addImplicitCollection(ListaEmpleados.class, "lista");
				xs.toXML(listaEmp, new FileOutputStream("empleadosXstream.xml"));

				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (ois != null) {
					try {
						ois.close();
					} catch (IOException e) {
						System.out.println("Error al cerrar el stream");
					}
				}
			}
		

	}

}
