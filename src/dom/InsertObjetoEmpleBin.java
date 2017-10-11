package dom;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class InsertObjetoEmpleBin {

	public static void main(String[] args) {

		
		String[] nombres = { "Alberto", "Paco", "Alejandro", "Ana", "Patricia" };
		int[] departamentos = { 10, 20, 30, 20, 10 };
		double[] salarios = { 2000.00, 1500.50, 3000.40, 2300.60, 1900.10 };
		
		
		
		ObjectOutputStream ou = null;
		
		try {
			ou = new ObjectOutputStream(new FileOutputStream("empleadosOBJ.dat"));
			Empleado emp = null;
			
			
			for (int i = 0; i < nombres.length; i++) {
				emp = new Empleado(i+1, departamentos[i], nombres[i], salarios[i]);
				ou.writeObject(emp);
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Error en E/S fichero");
		} catch (IOException e) {
			System.out.println("Error en E/S fichero");
		} finally {
			if (ou != null) {
				try {
					ou.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		
		
		
		
		
		

	}

}
