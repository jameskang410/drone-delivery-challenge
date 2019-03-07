package com.jameskang.domain;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import java.util.Map;

import static com.jameskang.domain.CoordinateHelper.coordinateFromString;
import static com.jameskang.domain.CoordinateHelper.distanceFromFactory;
import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link CoordinateHelper}
 */
public class CoordinateHelperTest {
	/**
	 * Tests that coordinates are properly mapped from string
	 */
	@Test
	public void testCoordinateFromString() {
		Map<String, Coordinate> map = ImmutableMap.<String, Coordinate>builder()
				.put("N11W5", new Coordinate(5, Direction.W, 11, Direction.N))
				.put("S5E2", new Coordinate(2, Direction.E, 5, Direction.S))
				.build();

		map.keySet().forEach(key -> {
			assertEquals(map.get(key), coordinateFromString(key));
		});
	}

	/**
	 * Tests that coordinates are properly mapped from string
	 */
	@Test
	public void testDistanceFromFactory() {
		Map<Coordinate, Integer> map = ImmutableMap.<Coordinate, Integer>builder()
				.put(new Coordinate(5, Direction.W, 11, Direction.N), 16)
				.put(new Coordinate(2, Direction.E, 5, Direction.S), 7)
				.build();

		map.keySet().forEach(key -> {
			assertEquals((int) map.get(key), distanceFromFactory(key));
		});
	}
}
