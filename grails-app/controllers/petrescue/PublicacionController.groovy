package petrescue

class PublicacionController {
	def index() {
	}

	def publicarAviso() {
		def nombre = params.nombre
		def raza = params.raza
		def direccion = params.direccion
		
		if (!nombre || !raza || !direccion) {
			render "cargá todo"
			return
		}

		Mascota mascota = new Mascota(nombre: nombre, raza: raza)
		Ubicacion ubicacion = new Ubicacion(direccion: direccion, provincia: 'CABA')
		Usuario logeado = Usuario.get(1)
		Aviso aviso = new Aviso(logeado, mascota, new Date(), ubicacion, TipoAviso.PERDIDO)
		aviso.save(failOnError: true)

		// render "creando aviso ${aviso}"
		redirect action: 'verAviso', id: aviso.id
	}

	def verAviso(Long id) {
		def aviso = Aviso.get(id)

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

		Map modelo = new HashMap()
		modelo.put('perdidos', copado)
		modelo.put('manzana', 2000)
		return modelo
		
		// [perdidos: Aviso.list()] equivalente
	}
}
