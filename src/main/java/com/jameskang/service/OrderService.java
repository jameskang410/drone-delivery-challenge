package com.jameskang.service;

import com.jameskang.domain.Order;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static com.jameskang.DroneDeliveryChallengeApplication.START_TIME;

/**
 * Handles operations pertaining to an order
 */
public class OrderService {
	private List<Order> orders;

	/**
	 *
	 * @param orders Assuming all orders from text file
	 */
	public OrderService(List<Order> orders) {
		this.orders = orders;
	}


	public double calculateNPS() {
		return 100 *  ((double) getPromotersCount() / orders.size()) - ((double) getDetractorsCount() / orders.size());
	}

	private long getPromotersCount() {
		return orders.stream()
				.filter(order -> hoursToFulfillOrder(order) < 2)
				.count();
	}

	private long getDetractorsCount() {
		return orders.stream()
				.filter(order -> hoursToFulfillOrder(order) > 3)
				.count();
	}

	private long hoursToFulfillOrder(Order order) {
		LocalTime startingTime = order.getTimeOrderMade();

		if (startingTime.isBefore(START_TIME)) {
			startingTime = START_TIME;
		}

		return Math.abs(Duration.between(startingTime, order.getTimeOrderFulfilled()).toHours());
	}

}
