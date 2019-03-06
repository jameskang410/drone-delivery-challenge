package com.jameskang.domain;

/**
 * Representation of location as a coordinate
 */
public class Coordinate {
	private int x;
	private Direction xLabel;
	private int y;
	private Direction yLabel;

	Coordinate(int x, Direction xLabel, int y, Direction yLabel) {
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
}
