package sait.sll.utility;

import java.io.Serializable;

/**
 * SLL node class. That's about it. Pretty self explanatory. 
 * @version 2020-3-30
 * @author Matt Jones
 * @author Fernando Arenas
 *
 */
public class Node implements Serializable {

	private static final long serialVersionUID = 1L;
	private Object element;
	private Node nextNode;
	// default
	public Node (Object element, Node nextNode) {
		this.element = element;
		this.nextNode = nextNode;
	}
	// tail
	public Node (Object element) {
		this.element = element;
		this.nextNode = null;
	}
	
	public void setNext(Node nextNode) { this.nextNode = nextNode; }
	
	public Node getNext() { return this.nextNode; }
	
	public void setElement(Object element) { this.element = element; }
	
	public Object getElement() { return this.element; }
	
	@Override
	public String toString() {
		System.out.println("{" + element + "/ " + nextNode + "}");
		return super.toString();
	}

}
