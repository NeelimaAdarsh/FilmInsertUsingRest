package com.neelima.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.neelima.dao.CFilmDao;
import com.neelima.model.FilmModel;

@Component
public class CFilmService implements IFilmService {

	@Autowired
	CFilmDao filmDao;

	public FilmModel insertFilmDetails(FilmModel filModel) {

		return filmDao.insertFilmDetails(filModel);
	}

	public FilmModel getFilmDetails(String title) {

		return filmDao.getFilmDetails(title);
	}

}
