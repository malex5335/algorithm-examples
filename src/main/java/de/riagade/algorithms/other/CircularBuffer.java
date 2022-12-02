package de.riagade.algorithms.other;

import java.lang.reflect.Array;
import java.nio.BufferOverflowException;
import java.util.*;

public class CircularBuffer<T> {
	private final T[] buffer;
	public int head;
	public int tail;
	private boolean isFull;

	public CircularBuffer(Class<T> clazz, int size) {
		this.buffer = (T[]) Array.newInstance(clazz, size);
		this.head = 0;
		this.tail = 0;
		this.isFull = size <= 0;
	}

	public void put(T element) {
		if(isFull)
			throw new BufferOverflowException();
		buffer[head] = element;
		head = (head + 1) % buffer.length;
		isFull = head == 0;
	}

	public T readNext() {
		if(head == tail && !isFull)
			throw new NoSuchElementException();
		var element = buffer[tail];
		tail = (tail + 1) % buffer.length;
		isFull = false;
		return element;
	}

}
