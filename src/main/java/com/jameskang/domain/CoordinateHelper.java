package com.jameskang.domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper methods for handling {@link Coordinate}
 */
public class CoordinateHelper {
	/**
	 * @return {@link Coordinate} object based on string representation
	 */
	public static Coordinate coordinateFromString(String coordinateAsString) {
		Pattern pattern = Pattern.compile("(S|N)(\\d+)(W|E)(\\d+)");
		Matcher matcher = pattern.matcher(coordinateAsString);

		if (!matcher.matches()) {
			throw new IllegalArgumentException("Invalid format given for coordinate");
		}

		return new Coordinate(Integer.valueOf(matcher.group(4)), Direction.valueOf(matcher.group(3)), Integer.valueOf(matcher.group(2)), Direction.valueOf(matcher.group(1)));
	}

	/**
	 * @return integer distance (in minutes away) from factory to this coordinate
	 */
	public static int distanceFromFactory(Coordinate coordinate) {
		return coordinate.getX() + coordinate.getY();
	}
}
