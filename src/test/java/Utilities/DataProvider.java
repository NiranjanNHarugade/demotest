package Utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.Parameters;

public class DataProvider extends BaseTest {
	
	@org.testng.annotations.DataProvider(name = "readData")
	
	public Object[][] withoutEregEsignGetData(Method m) throws IOException {
		File inputFile = new File(System.getProperty("user.dir") + "//Test_data");
		//File inputFile = new File("C:\\opt\\caf\\json\\CONA\\ICEP\\Regression Suite");
		
		ArrayList<String> Files = new ArrayList<String>();
		for (String value : ((Parameters) m.getAnnotation(Parameters.class)).value()) {
			Files.add(value);
		}
		String jsonFile = Files.get(0);
		List<HashMap<String, String>> values = null;
		values = getJsonData(inputFile + "//" + jsonFile);
		return new Object[][] { { values.get(0) } };
	}
	
}
