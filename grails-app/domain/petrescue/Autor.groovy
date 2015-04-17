package petrescue

class Autor {

	String nombre
	
	static hasMany = [libros: Libro]
	static belongsTo = Libro
	
	String toString() {
		"${nombre}"
	}
}
