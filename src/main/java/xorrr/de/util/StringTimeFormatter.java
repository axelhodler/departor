package xorrr.de.util;

public class StringTimeFormatter implements TimeFormatter {

	@Override
	public String formatMinute(String minute) {
		String formatted = "";
		if (singleDigit(minute))
			formatted = appendZeroToFront(minute);
		else
			formatted = minute;
		return formatted;
	}

	private boolean singleDigit(String minute) {
		return minute.length() < 2;
	}

	private String appendZeroToFront(String minute) {
		String formatted;
		formatted = "0" + minute;
		return formatted;
	}

}
