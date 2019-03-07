package com.jameskang.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.jameskang.domain.Coordinate;
import com.jameskang.domain.Direction;
import com.jameskang.domain.Order;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for {@link OrderService}
 */
public class OrderServiceTest {
	/**
	 * Tests that NPS is calculated as expected
	 */
	@Test
	public void testCalculateNPS() {
		Order detractor = new Order("WM001", new Coordinate(1, Direction.W, 1, Direction.S), LocalTime.of(6, 0, 0));
		detractor.setTimeOrderFulfilled(detractor.getTimeOrderMade().plus(Duration.ofHours(4)));
		Order neutral = new Order("WM002", new Coordinate(1, Direction.W, 1, Direction.S), LocalTime.of(6, 0, 0));
		neutral.setTimeOrderFulfilled(detractor.getTimeOrderMade().plus(Duration.ofHours(3)));
		Order promoter = new Order("WM002", new Coordinate(1, Direction.W, 1, Direction.S), LocalTime.of(6, 0, 0));
		promoter.setTimeOrderFulfilled(detractor.getTimeOrderMade().plus(Duration.ofHours(1)));
		Map<List<Order>, Double> map = ImmutableMap.<List<Order>, Double>builder()
				.put(ImmutableList.of(detractor, detractor, neutral, neutral), -50.)
				.put(ImmutableList.of(promoter, promoter, neutral, neutral), 50.)
				.put(ImmutableList.of(detractor, detractor, promoter, promoter), 0.)
				.put(ImmutableList.of(detractor, detractor, neutral, promoter, promoter, promoter), 16.7)
				.build();

		map.keySet().forEach(key -> {
			OrderService orderService = new OrderService(key);
			assertEquals(map.get(key), orderService.calculateNPS(), .1);
		});
	}
}
