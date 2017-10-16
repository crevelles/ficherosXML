package serializaci�n;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import dom.Empleado;

public class LeerXMLXStreamEmpleados {

	public static void main(String[] args) {

		FileInputStream fis = null;
		try {
			XStream xs = new XStream();
			xs.alias("Empleados", ListaEmpleados.class);
			xs.alias("DatosEmpleado", Empleado.class);
			xs.addImplicitCollection(ListaEmpleados.class, "lista");

			fis = new FileInputStream("empleadosXStream.xml");

			ListaEmpleados listaTotal = (ListaEmpleados) xs.fromXML(fis);

			ArrayList<Empleado> listaEmp = new ArrayList<Empleado>();
			listaEmp = listaTotal.getLista();
			
			for (Empleado emp : listaEmp) {
				System.out.println(  "ID: " + emp.getId() + " � " + " Nombre: " + emp.getNombre() + 
									" Departamento:  " + emp.getDept()  + " salario: " + emp.getSalario());
			}

			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
