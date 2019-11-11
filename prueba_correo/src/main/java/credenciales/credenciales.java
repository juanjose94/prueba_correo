package credenciales;

public enum credenciales {

	// Correo
	cuenta_correo("juan.garcia@confiar.com.co"), 
	contraseña_correo("@Pollozilla942019"),
	
	// HOST
	HOST("10.5.4.17")
	;
	

	

	private String descripcion;

	private credenciales(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
