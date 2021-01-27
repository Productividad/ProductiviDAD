package dad.productiviDAD.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class CSSUtils {

	public static String generateCss(String resource, Map<String, String> params) {
		String css = ResourceUtils.getResourceAsString(resource);
		for (String paramName : params.keySet()) { 
			css = css.replaceAll("\\$" + paramName, params.get(paramName));
		}
		try {
			File temp = File.createTempFile("ProductiviDAD_", ".css");
			FileUtils.writeStringToFile(temp, css, StandardCharsets.UTF_8);
			return temp.toURI().toString();
		} catch (IOException e) {
			return null;
		}
	}

}
