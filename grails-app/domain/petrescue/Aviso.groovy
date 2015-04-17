package petrescue

class Mascota {
	String nombre
	String raza
}

class Ubicacion {
	String direccion
	String provincia
}

enum TipoAviso {
	PERDIDO("se perdió"), ENCONTRADO("se encontró");

	String mensaje

	TipoAviso(String mensaje) {
		this.mensaje = mensaje
	}
	
	String toString() {
		this.mensaje
	}
}

class Aviso {

	TipoAviso tipoAviso

	byte[] fotitos // no usar nunca

	Mascota mascota
	Usuario publicador

	Date fecha // TODO usar joda time
	Ubicacion ubicacion

	static embedded = ['mascota', 'ubicacion']

	static constraints = {
		tipoAviso nullable: false
		mascota nullable: false
		publicador nullable: false
		fecha nullable: false
		ubicacion nullable: false
	}

	Aviso(Usuario publicador, Mascota mascota, Date fecha, Ubicacion ubicacion, TipoAviso tipoAviso) {
		this.publicador = publicador
		this.mascota = mascota
		this.fecha = fecha
		this.ubicacion = ubicacion
		this.tipoAviso = tipoAviso
	}
}
