package edu.uwm.cs351.util;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A class representing an immutable stack:
 * being immutable, instances share tails.
 * @param T element type
 */
public class ImmutableStack<T> extends AbstractCollection<T> {

	private final T top;
	private final ImmutableStack<T> shorter;
	private final int size;
	
	private ImmutableStack(T val, ImmutableStack<T> st) {
		top = val;
		shorter = st;
		size = st == null ? 0 : st.size + 1;
	}
	
	private static ImmutableStack<Object> empty = new ImmutableStack<Object>(null,null);
	
	/**
	 * Return an empty stack.
	 * @return empty stack, never null
	 */
	@SuppressWarnings("unchecked")
	public static <T> ImmutableStack<T> empty() {
		return (ImmutableStack<T>) empty;
	}
	
	/**
	 * Return the top of the stack.
	 * @throws NoSuchELementException if the stack is empty
	 * @return top of the stack
	 */
	public T top() {
		if (this == empty) throw new NoSuchElementException("stack is empty");
		return top;
	}
	
	/**
	 * Return the stack that results from popping off the top element.
	 * @throws NoSuchElementException if the stack is empty
	 * @return stack after popping element
	 */
	public ImmutableStack<T> pop() {
		if (this == empty) throw new NoSuchElementException("empty stack");
		return shorter;
	}
	
	/**
	 * Return the stack that results from pushing another element on top.
	 * @param val element to push
	 * @return new stack resulting from pushing element
	 */
	public ImmutableStack<T> push(T val) {
		return new ImmutableStack<>(val,this);
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public Iterator<T> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<T> {

		private ImmutableStack<T> rest = ImmutableStack.this;
		
		@Override
		public boolean hasNext() {
			return !rest.isEmpty();
		}

		@Override
		public T next() {
			T result = rest.top();
			rest = rest.pop();
			return result;
		}
		
	}
}
