package xorrr.de.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import xorrr.de.util.StringTimeFormatter;

public class StringTimeFormatterTest {

	@Test
	public void test() {
		StringTimeFormatter tf = new StringTimeFormatter();

		assertEquals("01", tf.formatMinute("1"));
		assertEquals("02", tf.formatMinute("2"));
		assertEquals("22", tf.formatMinute("22"));
	}

}
