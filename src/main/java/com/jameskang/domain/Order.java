package com.jameskang.domain;

import java.time.LocalTime;

/**
 * Representation of an order
 */
public class Order {
	private String id;
	private Coordinate coordinate;
	private LocalTime timeOrderMade;
	private LocalTime timeOrderFulfilled;
	private LocalTime timeDroneDeparted;

	public Order(String id, Coordinate coordinate, LocalTime timeOrderMade) {
		this.id = id;
		this.coordinate = coordinate;
		this.timeOrderMade = timeOrderMade;
		this.timeOrderFulfilled = null;
		this.timeDroneDeparted = null;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public LocalTime getTimeOrderMade() {
		return timeOrderMade;
	}

	public void setTimeOrderMade(LocalTime timeOrderMade) {
		this.timeOrderMade = timeOrderMade;
	}

	public LocalTime getTimeOrderFulfilled() {
		return timeOrderFulfilled;
	}

	public void setTimeOrderFulfilled(LocalTime timeOrderFulfilled) {
		this.timeOrderFulfilled = timeOrderFulfilled;
	}

	public LocalTime getTimeDroneDeparted() {
		return timeDroneDeparted;
	}

	public void setTimeDroneDeparted(LocalTime timeDroneDeparted) {
		this.timeDroneDeparted = timeDroneDeparted;
	}
}
