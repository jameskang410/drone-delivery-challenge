package com.jameskang.service;

import com.jameskang.domain.Order;
import javafx.util.Pair;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

public class WeightedOrderComparator implements Comparator<Order> {
	private List<Order> orders;
	private Pair<TreeSet<LocalTime>, TreeSet<Integer>> timeAndLocationRankTreeSets;

	public WeightedOrderComparator(List<Order> orders) {
		this.orders = orders;
		this.timeAndLocationRankTreeSets = timeAndLocationRankTreeSets();
	}

	@Override
	public int compare(Order o1, Order o2) {
		TreeSet<LocalTime> timeTreeSet = timeAndLocationRankTreeSets.getKey();
		TreeSet<Integer> locationTreeSet = timeAndLocationRankTreeSets.getValue();

		int timeRank1 = timeTreeSet.tailSet(o1.getTimeOrderMade()).size();
		int locationRank1 = locationTreeSet.tailSet(o1.getLocation().getX() + o1.getLocation().getY()).size();

		int timeRank2 = timeTreeSet.tailSet(o2.getTimeOrderMade()).size();
		int locationRank2 = locationTreeSet.tailSet(o2.getLocation().getX() + o2.getLocation().getY()).size();

		int overallRank1 = (int) ((timeRank1 + locationRank1) / 2.);
		int overallRank2 = (int) ((timeRank2 + locationRank2) / 2.);

		return Integer.compare(overallRank1, overallRank2);
	}

	private Pair<TreeSet<LocalTime>, TreeSet<Integer>> timeAndLocationRankTreeSets() {
		TreeSet<LocalTime> timeRank = new TreeSet<>();
		TreeSet<Integer> locationRank = new TreeSet<>();

		for (Order order : orders) {
			timeRank.add(order.getTimeOrderMade());
			locationRank.add(order.getLocation().getX() + order.getLocation().getY());
		}

		return new Pair<>(timeRank, locationRank);
	}
}
