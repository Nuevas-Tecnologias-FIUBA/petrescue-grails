package petrescue

class HojaDeContacto {
	String nombre
	String apellido
	String telefono
	String email

	static constraints = {
		nombre nullable: false, blank: false
		apellido nullable: false, blank: false
		telefono nullable: false, blank: false
		email nullable: false, blank: false, email: true
	}
}

class Usuario {

	String apodo
	String contrasena

	Set<Aviso> avisos = []

	HojaDeContacto hojaDeContacto

	static embedded = ['hojaDeContacto']

	static constraints = {
		apodo nullable: false, blank: false, unique: true
		contrasena nullable: false, blank: false
		hojaDeContacto nullable: true
	}

	Usuario(String apodo, String contrasena) {
		if (apodo == null || apodo.trim() == '') {
			throw new IllegalArgumentException("dale che")
		}

		this.apodo = apodo
		this.contrasena = contrasena
	}
}
