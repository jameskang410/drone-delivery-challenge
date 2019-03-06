package com.jameskang.service;

import com.jameskang.domain.Order;
import javafx.util.Pair;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static com.jameskang.domain.CoordinateHelper.distanceFromFactory;

/**
 * Custom comparator that compares orders based on the combined rank of the time the order was made and how far it is from the factory
 */
public class WeightedOrderComparator implements Comparator<Order> {
	private List<Order> orders;
	private Pair<TreeSet<LocalTime>, TreeSet<Integer>> timeAndLocationRankTreeSets;

	public WeightedOrderComparator(List<Order> orders) {
		this.orders = orders;
		this.timeAndLocationRankTreeSets = timeAndLocationRankTreeSets();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(Order o1, Order o2) {
		TreeSet<LocalTime> timeTreeSet = timeAndLocationRankTreeSets.getKey();
		TreeSet<Integer> locationTreeSet = timeAndLocationRankTreeSets.getValue();

		int timeRank1 = timeTreeSet.tailSet(o1.getTimeOrderMade()).size();
		int locationRank1 = locationTreeSet.tailSet(distanceFromFactory(o1.getCoordinate())).size();

		int timeRank2 = timeTreeSet.tailSet(o2.getTimeOrderMade()).size();
		int locationRank2 = locationTreeSet.tailSet(distanceFromFactory(o2.getCoordinate())).size();

		int overallRank1 = (int) ((timeRank1 + locationRank1) / 2.);
		int overallRank2 = (int) ((timeRank2 + locationRank2) / 2.);

		if (overallRank1 < overallRank2) {
			return 1;
		} else {
			return -1;
		}
	}

	private Pair<TreeSet<LocalTime>, TreeSet<Integer>> timeAndLocationRankTreeSets() {
		TreeSet<LocalTime> timeRank = new TreeSet<>();
		TreeSet<Integer> locationRank = new TreeSet<>();

		for (Order order : orders) {
			timeRank.add(order.getTimeOrderMade());
			locationRank.add(distanceFromFactory(order.getCoordinate()));
		}

		return new Pair<>(timeRank, locationRank);
	}
}
