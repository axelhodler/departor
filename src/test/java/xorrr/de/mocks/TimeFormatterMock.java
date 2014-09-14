package xorrr.de.mocks;

import xorrr.de.util.TimeFormatter;

public class TimeFormatterMock implements TimeFormatter {

	public boolean minuteFormatted;

	@Override
	public String formatMinute(String minute) {
		minuteFormatted = true;

		// the file that is parsed by XPathDepartureFinderTest expects 29
		return "29";
	}

}
