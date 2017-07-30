package com.neelima.dao;

import java.util.List;

import com.neelima.model.FilmModel;

public interface IFilmDao {

	public FilmModel getFilmDetails(String title);

	public FilmModel insertFilmDetails(FilmModel filModel);

}
