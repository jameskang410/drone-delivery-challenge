package com.jameskang.service;

import com.jameskang.domain.Coordinate;
import com.jameskang.domain.Direction;
import com.jameskang.domain.Order;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;

import static com.jameskang.service.DroneService.canDroneFulfillOrder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for {@link DroneService}
 */
public class DroneServiceTest {
	@Test
	public void testCanDroneFulfillOrderWhenCurrentTimeIsBeforeTimeOrderIsMade() {
		LocalTime currentTime = LocalTime.of(21, 55, 0);
		LocalTime deadline = LocalTime.of(22, 0, 0);
		Order order = new Order("WM001", new Coordinate(1, Direction.E, 1, Direction.S), currentTime.plus(Duration.ofMinutes(1)));

		assertFalse(canDroneFulfillOrder(currentTime, deadline, order));
	}

	/**
	 * Test that false is returned when the order will take longer than the deadline
	 */
	@Test
	public void testCanDroneFulfillOrderWhenCurrentTimeIsTooCloseToDeadline() {
		LocalTime currentTime = LocalTime.of(21, 55, 0);
		LocalTime deadline = LocalTime.of(22, 0, 0);
		Order order = new Order("WM001", new Coordinate(5, Direction.E, 10, Direction.S), LocalTime.of(21, 0, 0));

		assertFalse(canDroneFulfillOrder(currentTime, deadline, order));
	}

	/**
	 * Test that true is returned when fulfilling the order can take place before deadline
	 */
	@Test
	public void testCanDroneFulfillOrderWhenCurrentTimeIsFarEnoughFromDeadline() {
		LocalTime currentTime = LocalTime.of(21, 55, 0);
		LocalTime deadline = LocalTime.of(22, 0, 0);
		Order order = new Order("WM001", new Coordinate(1, Direction.E, 1, Direction.S), LocalTime.of(21, 0, 0));

		assertTrue(canDroneFulfillOrder(currentTime, deadline, order));
	}
}
