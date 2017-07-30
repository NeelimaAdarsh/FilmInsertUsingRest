package com.neelima.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.neelima.model.FilmModel;

@Component
public class CFilmDao implements IFilmDao {

	String fetch_details = " Select * from film where title  = ? ";

	String insert_details = "INSERT INTO film(film_id,title,description,release_year,language_id,original_language_id,rental_duration,rental_rate,length,replacement_cost,rating,special_features,last_update)"
			+ " VALUES (:film_id,:title,:description,:releaseyear,:languageid,:originallanguageid,:rentalduration,:rentalrate,:length,:replacementcost,:rating,:spclfeatures,:lastUpdate)";

	@Autowired
	private JdbcTemplate movieJdbcTemplate;

	public FilmModel insertFilmDetails(FilmModel filmModel) {

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("film_id", filmModel.getFilm_id());
		map.put("title", filmModel.getTitle());
		map.put("description", filmModel.getDescription());
		map.put("releaseyear", filmModel.getRelease_year());
		map.put("languageid", filmModel.getLanguage_id());
		map.put("originallanguageid", filmModel.getOriginal_language_id());
		map.put("rentalduration", filmModel.getRental_duration());
		map.put("rentalrate", filmModel.getRental_rate());
		map.put("length", filmModel.getLength());
		map.put("replacementcost", filmModel.getReplacement_cost());
		map.put("rating", filmModel.getRating());
		map.put("spclfeatures", filmModel.getSpecial_features());
		map.put("lastUpdate", filmModel.getLast_update());

		int noOfRowsInserted = movieJdbcTemplate.update(insert_details, map);
		FilmModel filmDetails = null;
		if (noOfRowsInserted > 0) {
			// Fetch inserted film details from database and return to service.
			filmDetails = getFilmDetails(filmModel.getTitle());
		}
		return filmDetails;
	}

	public FilmModel getFilmDetails(String title) {
		FilmModel databaseResult = null;
		try {
			databaseResult = movieJdbcTemplate.queryForObject(fetch_details, new Object[] { title },
					new RowMapper<FilmModel>() {

				public FilmModel mapRow(ResultSet rs, int rownum) throws SQLException {
					FilmModel filmModel = new FilmModel();
					filmModel.setFilm_id(rs.getInt(1));
					filmModel.setTitle(rs.getString("title"));
					filmModel.setDescription(rs.getString("description"));
					filmModel.setRelease_year(rs.getString("release_year"));
					filmModel.setLanguage_id(rs.getString("language_id"));
					filmModel.setOriginal_language_id(rs.getString("original_language_id"));
					filmModel.setRental_duration(rs.getString("rental_duration"));
					filmModel.setRental_rate(rs.getString("rental_rate"));
					filmModel.setLength(rs.getString("length"));
					filmModel.setReplacement_cost(rs.getString("replacement_cost"));
					filmModel.setRating(rs.getString("rating"));
					filmModel.setSpecial_features(rs.getString("special_features"));
					filmModel.setLast_update(rs.getString("last_update"));
					return filmModel;
				}

			});

		} catch (EmptyResultDataAccessException ex) {
			System.out.println("Result set is empty");
			ex.printStackTrace();
		}
		return databaseResult;
	}

}
