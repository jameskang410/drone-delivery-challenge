package com.jameskang.domain;

import java.time.LocalTime;

public class Order {
	private String id;
	private Coordinate location;
	private LocalTime timeOrderMade;
	private LocalTime timeOrderFulfilled;
	private LocalTime timeDroneDeparted;

	public Order(String id, Coordinate location, LocalTime timeOrderMade) {
		this.id = id;
		this.location = location;
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

	public Coordinate getLocation() {
		return location;
	}

	public void setLocation(Coordinate location) {
		this.location = location;
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
