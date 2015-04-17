class UrlMappings {

	static mappings = {
		"/url-re-copada"(controller: 'publicacion', action: 'index')
		
		"/${controller}/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
