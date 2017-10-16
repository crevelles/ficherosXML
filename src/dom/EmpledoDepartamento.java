package dom;

import java.io.Serializable;

public class EmpledoDepartamento implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4058162440006151182L;
	private int id;
	//Ojo es de tipo Departamento (Clase creada previamente)
	private Departamento dpto;
	private String nombre;
	private double salario;
	
	
	
	
	public EmpledoDepartamento(int id, Departamento dpto, String nombre, double salario) {
		super();
		this.id = id;
		this.dpto = dpto;
		this.nombre = nombre;
		this.salario = salario;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public int getId() {
		return id;
	}



	public Departamento getDpto() {
		return dpto;
	}



	public String getNombre() {
		return nombre;
	}



	public double getSalario() {
		return salario;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setDpto(Departamento dpto) {
		this.dpto = dpto;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setSalario(double salario) {
		this.salario = salario;
	}

	
	



	


	
	
	
	
	




	
	
	
	
}
