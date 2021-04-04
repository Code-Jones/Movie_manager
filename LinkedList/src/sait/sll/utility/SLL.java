package sait.sll.utility;

import java.io.Serializable;

/**
 * Singly Linked list class
 * @author Matt Jones
 * @author Fernando Arenas
 * @version 03-31-2020
 */
public class SLL implements LinkedListADT, Serializable {
   
	private static final long serialVersionUID = 1L; // we don't need it for this assignment but i thought i'd add it anyways
	private Node tail;
    private Node head;
    
    /**
     * Default Constructor
     */
    public SLL() {
        head = tail = null;
    }

    /**
     * Checks if list is empty
     */
    @Override 
    public boolean isEmpty() {
        return (head == null);
    }

    /**
     * Empties list
     */
    @Override 
    public void clear() {
        head = null;
    }

    /**
     * Append Object to the end of the list
     * @param data element to be saved
     */
    @Override 
    public void append(Object data) {
        if (isEmpty()) {
            tail = new Node(data, null);
            head = new Node(null, tail);
        } else {
            tail.setNext(new Node(data));
            tail = tail.getNext();
        }
    }

    /**
     * Appends to the start of the list
     * @param data element to be saved
     */
    @Override
    public void prepend(Object data) {
        if (isEmpty()) {
            tail = new Node(data);
            head = new Node(null, tail);
        } else {
            Node newNode = new Node(data, head.getNext());
            head.setNext(newNode);
        }
    }

    /**
     * Inserts Object to selected
     * @param data element to be saved
     * @param index position to be saved at
     */
    @Override
    public void insert(Object data, int index) throws IndexOutOfBoundsException {
        if (index >= size() || isEmpty()) {
            throw new IndexOutOfBoundsException(index + " Is bigger then the zero-based list size of: " + size());
        } else if (index == -1) {
            throw new IndexOutOfBoundsException(index + "Is not an acceptable number for this list");
        }
        Node current = head;
        int counter = 0;
        while (current.getNext() != null && counter != index) {
            current = current.getNext();
            counter++;
        }
        Node newNode = new Node(data, current.getNext());
        current.setNext(newNode);

    }

    /**
     * Replaces the element at the index given
     * @param data Data to replace.
     * @param index Index of element to replace.
     * @throws IndexOutOfBoundsException Out of bounds for the list
     */
    @Override
    public void replace(Object data, int index) throws IndexOutOfBoundsException {
        if (index >= size() || isEmpty()) {
            throw new IndexOutOfBoundsException(index + " Is bigger then the zero-based list size of: " + size());
        } else if (index == -1) {
            throw new IndexOutOfBoundsException(index + "Is not an acceptable number for this list");
        }
        Node current = head;
        for (int i = -1; i < index; i++) { //todo: make -1 not accessible
            current = current.getNext();
        }
        current.setElement(data);
    }

    /**
     * Checks the size of the list and returns the int of the size
     * @return int size of list
     */
    @Override
    public int size() {
        if (head == null || isEmpty()) {
            return 0;
        }
        Node current = head;
        int index = 0;
        while (current.getNext() != null) {
            current = current.getNext();
            index++;
        }
        return index;
    }

    /**
     * Deletes the element at the index given
     * @param index Index of element to remove.
     * @throws IndexOutOfBoundsException Out of bounds for the list
     */
    @Override
    public void delete(int index) throws IndexOutOfBoundsException {
        if (index >= size() || isEmpty()) {
            throw new IndexOutOfBoundsException(index + " Is bigger then the zero-based list size of: " + size());
        } else if (index == -1) {
            throw new IndexOutOfBoundsException(index + "Is not an acceptable number for this list");
        }
        Node current = head;
        for (int i = -1; i < index; i++) { //todo: make -1 not accessible
            current = current.getNext();
        }
        current.setElement(current.getNext().getElement());
        current.setNext(current.getNext().getNext());
    }

    /**
     * Retrieves the element from the node at the index given
     * @param index Index of element to get.
     * @return Object element that is stored
     * @throws IndexOutOfBoundsException Out of bounds for the list
     */
    @Override
    public Object retrieve(int index) throws IndexOutOfBoundsException {
        if (index >= size()) {
            throw new IndexOutOfBoundsException(index + " Is bigger then the zero-based list size of: " + size());
        } else if (index == -1) {
            throw new IndexOutOfBoundsException(index + "Is not an acceptable number for this list");
        }
        Node current = head;
        for (int i = -1; i < index; i++) {
            current = current.getNext();
        }
        return current.getElement();
    }

    /**
     * Checks for the index of the given element
     * @param data Data object to find the first index of.
     * @return int of where the element given is stored
     */
    @Override
    public int indexOf(Object data) {
        Node current = head;
        int index = 0;
        while (current.getNext() != null && current.getElement() != data) {
            current.setElement(current.getNext().getElement());
            current = current.getNext();
            index++;
        }
        if (current.getElement() == data) {
            return index;
        } else {
            return -1;
        }
    }

    /**
     * Checks to see if the element given is in the list
     * @param data Data object to search for.
     * @return true if element is in list, false if it isn't
     */
    @Override
    public boolean contains(Object data) {
        Node current = head;
        while (current.getNext() != null && current.getElement() != data) {
            current.setElement(current.getNext().getElement());
            current = current.getNext();
        }
        return current.getElement() == data;
    }

}
