package com.jameskang.domain;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Representation of a drone that fulfills orders
 */
public class Drone {
	private List<Order> ordersFulfilled;

	public Drone(List<Order> ordersFulfilled) {
		this.ordersFulfilled = ordersFulfilled;
	}

	public List<Order> getOrdersFulfilled() {
		return ordersFulfilled;
	}

	public void addFulfilledOrder(Order order) {
		this.ordersFulfilled = ImmutableList.<Order>builder()
				.addAll(ordersFulfilled)
				.add(order)
				.build();
	}
}
