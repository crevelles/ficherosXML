package dom;

import java.io.Serializable;

public class Empleado implements Serializable{

	private static final long serialVersionUID = 4214973097459329946L;
	
	private int id, dept;
	private String nombre;
	private double salario;

	
	
	public Empleado(int id, int dept, String nombre, double salario) {
		this.id = id;
		this.dept = dept;
		this.nombre = nombre;
		this.salario = salario;
	}


	
	public int getId() {
		return id;
	}

	public int getDept() {
		return dept;
	}

	public String getNombre() {
		return nombre;
	}

	public double getSalario() {
		return salario;
	}

	
	
	
	
	

}
