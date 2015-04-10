import petrescue.*

class BootStrap {

    def init = { servletContext ->
		new Usuario("pablito", "1234").save(failOnError: true)    
		new Usuario("juancito", "capo").save(failOnError: true)
    }
    def destroy = {
    }
}
