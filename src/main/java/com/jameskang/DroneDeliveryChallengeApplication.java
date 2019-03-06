package com.jameskang;

import com.google.common.collect.ImmutableList;
import com.jameskang.domain.Drone;
import com.jameskang.domain.Order;
import com.jameskang.service.OrderService;
import com.jameskang.service.WeightedOrderComparator;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

import static com.jameskang.service.DroneService.canDroneFulfillOrder;
import static com.jameskang.service.DroneService.durationToFulfillOrder;
import static com.jameskang.service.DroneService.durationToReachOrder;
import static com.jameskang.service.ParsingService.parseFileToOrders;
import static com.jameskang.service.ParsingService.writeOutputFile;

public class DroneDeliveryChallengeApplication {

	public static LocalTime START_TIME = LocalTime.of(6, 0, 0);
	private static LocalTime END_TIME = LocalTime.of(22, 0, 0);

	/**
	 * Main application
	 */
	private static void runApplication(List<Order> orders) throws IOException {
		LocalTime currentTime = START_TIME;

		Queue<Order> queue = new PriorityQueue<>(new WeightedOrderComparator(orders));
		queue.addAll(orders);

		Drone drone = new Drone(ImmutableList.of());

		while (!queue.isEmpty() && currentTime.isBefore(END_TIME)) {
			Order order = queue.peek();

			// check if order can be fulfilled (time to reach location and back to factory is before end time)
			if (canDroneFulfillOrder(currentTime, END_TIME, order)) {
				order = queue.remove();

				order.setTimeDroneDeparted(currentTime);
				order.setTimeOrderFulfilled(currentTime.plus(durationToReachOrder(order)));

				drone.addFulfilledOrder(order);

				currentTime = currentTime.plus(durationToFulfillOrder(order));
			} else {
				// can't do anything - increment time
				currentTime = currentTime.plusSeconds(1);
			}
		}

		// sort for output file (time drone departed)
		List<Order> ordersFulfilledSorted = drone.getOrdersFulfilled().stream()
				.sorted((o1, o2) -> {
					if (o1.getTimeDroneDeparted().isBefore(o2.getTimeDroneDeparted())) {
							return -1;
					} else if (o1.getTimeDroneDeparted().isAfter(o2.getTimeDroneDeparted())) {
						return 1;
					}
					return 0;
					})
				.collect(Collectors.toList());


		try {
			writeOutputFile(ordersFulfilledSorted, new OrderService(ordersFulfilledSorted).calculateNPS());
		} catch (IOException e) {
			throw e;
		}
	}

	public static void main(String[] args) throws IOException {
		List<Order> orders = parseFileToOrders(args[0]);
		runApplication(orders);
	}
}
