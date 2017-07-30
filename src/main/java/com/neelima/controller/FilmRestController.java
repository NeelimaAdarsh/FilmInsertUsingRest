package com.neelima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.neelima.model.FilmModel;
import com.neelima.services.CFilmService;

@RestController
public class FilmRestController {

	@Autowired
	CFilmService filmService;

	@RequestMapping(value = "/filmDetails", method = RequestMethod.POST, headers = "Accept=application/json")
	public ResponseEntity insertFilmDetails(@RequestBody FilmModel filmModel) {

		FilmModel filmDetails = null;
		
		String filmTitle = filmModel.getTitle();
		if(null != filmTitle && !"".equalsIgnoreCase(filmTitle)) {
			// filmModel.setFilm_id();
			filmDetails = filmService.insertFilmDetails(filmModel);
		}

		if(filmDetails == null) {
			// Return error object;
			return new ResponseEntity(filmDetails, HttpStatus.BAD_REQUEST);			
		} else {
			return new ResponseEntity(filmDetails, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getfilmDetails/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity getFilmDetails(@PathVariable String title) {
		FilmModel filmDetails = filmService.getFilmDetails(title);
		return new ResponseEntity(filmDetails, HttpStatus.OK);	
	}

}
