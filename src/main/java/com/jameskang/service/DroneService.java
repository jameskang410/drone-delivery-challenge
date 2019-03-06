package com.jameskang.service;

import com.jameskang.domain.Order;

import java.time.Duration;
import java.time.LocalTime;

import static com.jameskang.domain.CoordinateHelper.distanceFromFactory;

/**
 * Handles operations pertaining to a drone
 */
public class DroneService {
	/**
	 * @return boolean based on if drone can fulfill order based on: order's distance from factory, the current time, and deadline
	 */
	public static boolean canDroneFulfillOrder(LocalTime currentTime, LocalTime deadLine, Order order) {
		return currentTime.isAfter(order.getTimeOrderMade()) && currentTime.plus(durationToFulfillOrder(order)).isBefore(deadLine);
	}

	/**
	 * @return minutes a drone will take to go from factory to order location
	 */
	public static Duration durationToReachOrder(Order order) {
		return Duration.ofMinutes(distanceFromFactory(order.getCoordinate()));
	}

	/**
	 * @return minutes to fulfill an order, which is defined as minutes required to: go from factory (pick up order), deliver at order location, come back to factory
	 */
	public static Duration durationToFulfillOrder(Order order) {
		Duration durationToReachOrder = durationToReachOrder(order);
		return durationToReachOrder.plus(durationToReachOrder);
	}
}
