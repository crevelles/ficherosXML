package dom;

import java.io.Serializable;

public class Departamento implements Serializable{
	

	private static final long serialVersionUID = -1382660096425764560L;
	int id;
	String nombreDept, localizacion;
	
	public Departamento(int id, String nombreDept, String localizacion) {
		super();
		this.id = id;
		this.nombreDept = nombreDept;
		this.localizacion = localizacion;
	}

	public int getId() {
		return id;
	}

	public String getNombreDept() {
		return nombreDept;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombreDept(String nombreDept) {
		this.nombreDept = nombreDept;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	
	
	
	
	
	
	
}
