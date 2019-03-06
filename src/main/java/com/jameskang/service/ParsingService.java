package com.jameskang.service;

import com.google.common.collect.ImmutableList;
import com.jameskang.domain.CoordinateHelper;
import com.jameskang.domain.Order;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Handles I/O
 */
public class ParsingService {
	/**
	 * @return orders interpreted from the given input file
	 */
	public static List<Order> parseFileToOrders(String absoluteFilePath) {
		BufferedReader reader;
		ImmutableList.Builder<Order> orderBuilder = ImmutableList.builder();

		try {
			reader = new BufferedReader(new FileReader(absoluteFilePath));
			String line = reader.readLine();

			while (line != null) {
				orderBuilder.add(parseStringToOrder(line));
				line = reader.readLine();
			}
			reader.close();

			return orderBuilder.build();

		} catch (IOException e) {
			throw new RuntimeException("Unable to read text file", e);
		}
	}

	/**
	 * Writes the expected output file at ./output.txt
	 */
	public static void writeOutputFile(List<Order> orders, double nps) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("./output.txt"));

		for (Order order : orders) {
			writer.write(order.getId() + " " + order.getTimeDroneDeparted());
			writer.newLine();
		}

		writer.write("NPS " + nps);

		writer.close();
	}

	private static Order parseStringToOrder(String line) {
		String[] parsedLine = line.split(" ");

		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime timeOrderMade = LocalTime.parse(parsedLine[2], timeFormatter);

		return new Order(parsedLine[0], CoordinateHelper.coordinateFromString(parsedLine[1]), timeOrderMade);
	}
}
