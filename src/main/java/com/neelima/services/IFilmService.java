package com.neelima.services;

import com.neelima.model.FilmModel;

public interface IFilmService {

	public FilmModel getFilmDetails(String title);

	public FilmModel insertFilmDetails(FilmModel filmModel);

}
