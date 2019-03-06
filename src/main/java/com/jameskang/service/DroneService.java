package com.jameskang.service;

import com.jameskang.domain.Coordinate;
import com.jameskang.domain.Drone;
import com.jameskang.domain.Order;

import java.time.Duration;
import java.time.LocalTime;

public class DroneService {
	private Drone drone;

	public DroneService(Drone drone) {
		this.drone = drone;
	}

	public boolean canFulfillOrder(LocalTime currentTime, LocalTime deadLine, Order order) {
		return currentTime.isAfter(order.getTimeOrderMade()) && currentTime.plus(durationToFulfillOrder(order)).isBefore(deadLine);
	}

	/**
	 * From factory to order location
	 */
	public Duration durationToReachOrder(Order order) {
		Coordinate coordinate = order.getLocation();
		return Duration.ofMinutes(coordinate.getX() + coordinate.getY());
	}

	/**
	 * Need to go from factory (pick up order), deliver at order location, then come back to factory
	 * Assuming time to reach order is same as going back to factory
	 */
	public Duration durationToFulfillOrder(Order order) {
		Duration durationToReachOrder = durationToReachOrder(order);
		return durationToReachOrder.plus(durationToReachOrder);
	}
}
