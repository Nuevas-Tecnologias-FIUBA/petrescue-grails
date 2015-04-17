package petrescue

class PublicarAvisoCommand {
	String nombre
	String raza
	String direccion
	
	static constraints = {
		nombre blank: false, nullable: false, inList: ["secreto"]
		raza blank: false, nullable: false
		direccion blank: false, nullable: false
	}
}

class PublicacionController {
	def index() {
		[command: new PublicarAvisoCommand()]
	}

	def publicarAviso(PublicarAvisoCommand command) {
		if (command.hasErrors()) {
			render view: "index", model: [command: command]
			return
		}

		def nombre = command.nombre
		def raza = command.raza
		def direccion = command.direccion

		try {
			Usuario logeado = Usuario.get(1)

			throw new IllegalStateException("no hay base")

			Mascota mascota = new Mascota(nombre: nombre, raza: raza)
			Ubicacion ubicacion = new Ubicacion(direccion: direccion, provincia: 'CABA')
			Aviso aviso = logeado.publicarAviso(mascota, ubicacion)
			aviso.save(failOnError: true)

			// render "creando aviso ${aviso}"
			redirect action: 'verAviso', id: aviso.id
		} catch (UsuarioSinHojaDeContactoException e) {
			// render "cargá la hoja de contacto"
			redirect action: 'cargarHojaDeContacto'
		}
	}

	def cargarHojaDeContacto() {
		render "aca vas a cargar tus datos"
	}

	def noExiste() {
		render "no existe el aviso amigo"
	}

	def verAviso(Long id) {
		def aviso = Aviso.get(id)
		if (!aviso) {
			// render "el aviso no existe"
			redirect action: "noExiste"
			return
		}

		Map modelo = ['aviso': aviso]
		return modelo
	}

	def verPerdidos() {
		List avisos = Aviso.list() // todos

		List filtrados = avisos.findAll { Aviso aviso ->
			aviso.tipoAviso == TipoAviso.PERDIDO
		}
		
		List perdidos = Aviso.findAllByTipoAviso(TipoAviso.PERDIDO)
		List perdidos2 = Aviso.findAllByTipoAvisoAndPublicador(TipoAviso.PERDIDO, Usuario.get(1))
		// List noanda = Aviso.findAllByManzana(Usuario.get(1))
		// googlear dynamic finders
		
		List copado = Aviso.withCriteria {
			eq('publicador', Usuario.get(1))
			lt('fecha', new Date())
		}
		
		Usuario actual = Usuario.get(1)
		

		Map modelo = new HashMap()
		modelo.put('perdidos', actual.avisos)
		modelo.put('manzana', 2000)
		return modelo
		
		// [perdidos: Aviso.list()] equivalente
	}
}
