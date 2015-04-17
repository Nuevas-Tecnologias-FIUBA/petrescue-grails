package petrescue

class Libro {

	String ISBN
	
	static hasMany = [autores: Autor]
}
