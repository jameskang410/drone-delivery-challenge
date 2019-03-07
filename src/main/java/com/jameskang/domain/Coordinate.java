package com.jameskang.domain;

import java.util.Objects;

/**
 * Representation of location as a coordinate
 */
public class Coordinate {
	private int x;
	private Direction xLabel;
	private int y;
	private Direction yLabel;

	public Coordinate(int x, Direction xLabel, int y, Direction yLabel) {
		this.x = x;
		this.xLabel = xLabel;
		this.y = y;
		this.yLabel = yLabel;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public Direction getxLabel() {
		return xLabel;
	}

	public void setxLabel(Direction xLabel) {
		this.xLabel = xLabel;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Direction getyLabel() {
		return yLabel;
	}

	public void setyLabel(Direction yLabel) {
		this.yLabel = yLabel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinate that = (Coordinate) o;
		return x == that.x &&
				y == that.y &&
				xLabel == that.xLabel &&
				yLabel == that.yLabel;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, xLabel, y, yLabel);
	}

	@Override
	public String toString() {
		return "Coordinate{" +
				"x=" + x +
				", xLabel=" + xLabel +
				", y=" + y +
				", yLabel=" + yLabel +
				'}';
	}
}
