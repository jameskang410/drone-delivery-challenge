package com.jameskang.service;

import com.google.common.collect.ImmutableList;
import com.jameskang.domain.Coordinate;
import com.jameskang.domain.Direction;
import com.jameskang.domain.Order;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Unit tests for {@link WeightedOrderComparator}
 */
public class WeightedOrderComparatorTest {
	/**
	 * Tests that an order with far location will be ranked accordingly even though order time is close
	 */
	@Test
	public void testComparatorWhenLocationIsFar() {
		Order order1 = new Order("WM001", new Coordinate(10, Direction.W, 10, Direction.N), LocalTime.of(6, 0, 0));
		Order order2 = new Order("WM002", new Coordinate(1, Direction.W, 1, Direction.N), LocalTime.of(10, 0, 0));
		Order order3 = new Order("WM003", new Coordinate(5, Direction.W, 5, Direction.N), LocalTime.of(7, 0, 0));

		List<Order> orders = ImmutableList.of(order1, order2, order3);

		Queue<Order> queue = new PriorityQueue<>(new WeightedOrderComparator(orders));
		queue.addAll(orders);

		assertThat(ImmutableList.copyOf(queue), contains(order3, order1, order2));
	}

	/**
	 * Tests that an order with close location and early order time will be ranked first
	 */
	@Test
	public void testComparatorWhenLocationIsCloseAndOrderTimeIsEarly() {
		Order order1 = new Order("WM001", new Coordinate(10, Direction.W, 10, Direction.N), LocalTime.of(6, 0, 0));
		Order order2 = new Order("WM002", new Coordinate(2, Direction.W, 2, Direction.N), LocalTime.of(6, 0, 0));
		Order order3 = new Order("WM003", new Coordinate(10, Direction.W, 10, Direction.N), LocalTime.of(6, 30, 0));

		List<Order> orders = ImmutableList.of(order1, order2, order3);

		Queue<Order> queue = new PriorityQueue<>(new WeightedOrderComparator(orders));
		queue.addAll(orders);

		assertThat(ImmutableList.copyOf(queue), contains(order2, order1, order3));
	}
}
