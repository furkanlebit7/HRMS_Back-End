package kodlamaio.hrms.core.utilities;

import java.util.Random;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

@Service
public class CodeGenerator {
	public String create() {
		return this.givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect(); 
	}
	
	@Test
	public String givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
	    int leftLimit = 48; // numeral '0'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
	
}
