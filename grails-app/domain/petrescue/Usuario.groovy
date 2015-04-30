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

	Set avisos = []

	HojaDeContacto hojaDeContacto

	static embedded = ['hojaDeContacto']

	static hasMany = [avisos: Aviso]

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
	
	Aviso publicarAviso(def mascota, def ubicacion) {
		if (!this.hojaDeContacto) {
			//return null 
			// throw new UsuarioSinHojaDeContactoException("el usuario no cargo la hoja de contacto, no puede publicar")
		}
		
		Aviso aviso = new Aviso(
			this,
			mascota, new Date(), ubicacion, TipoAviso.PERDIDO)

		this.avisos << aviso
		aviso
	}
}
