import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	public static String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	public static String getNewDate() {
		return formatDate(new Date());
	}

	public static boolean isNotNull(String value) {
		if (value != null && value.trim().length() > 0) {
			return true;
		}
		return false;
	}
}
