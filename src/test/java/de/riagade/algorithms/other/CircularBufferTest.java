package de.riagade.algorithms.other;

import org.junit.jupiter.api.Test;

import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class CircularBufferTest {

	@Test
	void put_one() {
		// Given
		var buffer = new CircularBuffer<>(Integer.class, 3);

		// When
		buffer.put(1);

		// Then
		assertEquals(1, buffer.head);
	}

	@Test
	void put_two() {
		// Given
		var buffer = new CircularBuffer<>(Integer.class,3);

		// When
		buffer.put(1);
		buffer.put(2);

		// Then
		assertEquals(2, buffer.head);
	}

	@Test
	void put_too_much() {
		// Given
		var buffer = new CircularBuffer<>(Integer.class, 2);

		// When
		buffer.put(1);
		buffer.put(2);
		assertThrows(BufferOverflowException.class, () -> buffer.put(3));

		// Then
		assertEquals(0, buffer.head);
	}

	@Test
	void readNext_one() {
		// Given
		var buffer = new CircularBuffer<>(Integer.class, 3);
		buffer.put(1);

		// When
		var result = buffer.readNext();

		// Then
		assertEquals(1, result);
		assertEquals(1, buffer.tail);
	}

	@Test
	void readNext_two() {
		// Given
		var buffer = new CircularBuffer<>(Integer.class, 3);
		buffer.put(1);
		buffer.put(2);

		// When
		var result1 = buffer.readNext();
		var result2 = buffer.readNext();

		// Then
		assertEquals(1, result1);
		assertEquals(2, result2);
		assertEquals(2, buffer.tail);
	}

	@Test
	void readNext_too_much() {
		// Given
		var buffer = new CircularBuffer<>(Integer.class, 2);
		buffer.put(1);
		buffer.put(2);

		// When
		var result1 = buffer.readNext();
		var result2 = buffer.readNext();
		assertThrows(NoSuchElementException.class, buffer::readNext);

		// Then
		assertEquals(1, result1);
		assertEquals(2, result2);
		assertEquals(0, buffer.tail);
	}

}