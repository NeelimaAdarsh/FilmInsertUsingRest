import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neelima.model.FilmModel;

public class JSONGenerator {

	public static void main(String[] args) {

		ObjectMapper obj = new ObjectMapper();
		
		FilmModel filmModel = new FilmModel();
		try {
			String jsonValue = obj.writeValueAsString(filmModel);
			System.out.println(""+jsonValue);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
