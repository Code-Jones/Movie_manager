package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import sait.sll.utility.*;

/**
 * Tests for SLL class
 * @author Matt Jones
 * @author Fernando Arenas
 * @version 2020-03-31
 *
 */
class LinkedListTests {
	/**
	 * Contains the linked list that is manipulated in each test.
	 */
	private LinkedListADT linkedList;

	/**
	 * Instantiates a SLL and assigns it to the global linkedList variable
	 */
	@BeforeEach
	void setUp() {
		// Create your concrete linked list class and assign to to linkedList.
		this.linkedList = new SLL();
	}

	/**
	 * Clears the list after
	 */
	@AfterEach
	void tearDown() {
		this.linkedList.clear();
	}

	/**
	 * Test the linked list is empty.
	 */
	@Test
	void testIsEmpty() {
		assertTrue(this.linkedList.isEmpty());
		assertEquals(0, this.linkedList.size());
	}
	
	/**
	 * Tests appending elements to the linked list.
	 */
	@Test
	void testAppendNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");

		/*
		  Linked list should now be:

		  a -> b -> c -> d
		 */

		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));
		
		// Test the third node value is c
		assertEquals("c", this.linkedList.retrieve(2));
		
		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(3));
	}

	/**
	 * Tests prepending nodes to linked list.
	 */
	@Test
	void testPrependNodes() {
		this.linkedList.prepend("a");
		this.linkedList.prepend("b");
		this.linkedList.prepend("c");
		this.linkedList.prepend("d");
		
		/*
		 * Linked list should now be:
		 * 
		 * d -> c -> b -> a
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("d", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("c", this.linkedList.retrieve(1));
		
		// Test the third node value is c
		assertEquals("b", this.linkedList.retrieve(2));
		
		// Test the fourth node value is d
		assertEquals("a", this.linkedList.retrieve(3));
	}
	
	/**
	 * Tests inserting node at valid index.
	 */
	@Test
	void testInsertNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		this.linkedList.insert("e", 2);
		
		/*
		 * Linked list should now be:
		 * 
		 * a -> b -> e -> c -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(5, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));
		
		// Test the third node value is e
		assertEquals("e", this.linkedList.retrieve(2));
		
		// Test the third node value is c
		assertEquals("c", this.linkedList.retrieve(3));
		
		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(4));
	}
	
	/**
	 * Tests replacing existing nodes data.
	 */
	@Test
	void testReplaceNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		this.linkedList.replace("e", 2);

		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(4, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));
		
		// Test the third node value is e
		assertEquals("e", this.linkedList.retrieve(2));
		
		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(3));
	}
	
	/**
	 * Tests deleting node from linked list.
	 */
	@Test
	void testDeleteNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		this.linkedList.delete(2);
		
		/*
		 * Linked list should now be:
		 * 
		 * a -> b -> d
		 */
		
		// Test the linked list is not empty.
		assertFalse(this.linkedList.isEmpty());
		
		// Test the size is 4
		assertEquals(3, this.linkedList.size());

		// Test the first node value is a
		assertEquals("a", this.linkedList.retrieve(0));

		// Test the second node value is b
		assertEquals("b", this.linkedList.retrieve(1));
		
		// Test the fourth node value is d
		assertEquals("d", this.linkedList.retrieve(2));
	}
	
	/**
	 * Tests finding and retrieving node in linked list.
	 */
	@Test
	void testFindNode() {
		this.linkedList.append("a");
		this.linkedList.append("b");
		this.linkedList.append("c");
		this.linkedList.append("d");
		
		/*
		 * Linked list should now be:
		 * 
		 * a -> b -> c -> d
		 */
		
		boolean contains = this.linkedList.contains("b");
		assertTrue(contains);
		
		int index = this.linkedList.indexOf("b");
		assertEquals(1, index);
		
		String value = (String) this.linkedList.retrieve(1);
		assertEquals("b", value);
	}

	/**
	 * Tests appending to index -1
	 */
	@Test
	void testInputIndexMinusOne() {
		try {
			this.linkedList.append(-1);
			assertFalse(false);
		} catch (IndexOutOfBoundsException ex) {
			assertTrue(true);
		}
	}

	/**
	 *  Tests deleting an empty list
	 */
	@Test
	void testDeleteEmptyList() {
		boolean boo = this.linkedList.isEmpty();
		assertTrue(boo);
		try {
			this.linkedList.delete(1);
			fail();
		} catch (IndexOutOfBoundsException er) {
			assertTrue(true);
		}
	}

	/**
	 * Tests the size of the list
	 */
	@Test
	void testSize() {
		this.linkedList.append(1);
		this.linkedList.append(2);
		this.linkedList.append(3);
		this.linkedList.append(4);
		this.linkedList.append(5);
		assertFalse(this.linkedList.isEmpty());
		assertEquals(5, linkedList.size());
		this.linkedList.delete(3);
		assertEquals(4, this.linkedList.size());
	}

	/**
	 * Tests for nodes that doesn't exist
	 */
	@Test
	void testNodeNotExist() {
		this.linkedList.append(12);
		this.linkedList.append(21);
		assertFalse(this.linkedList.isEmpty());
		assertEquals(2, this.linkedList.size());
		assertTrue(this.linkedList.contains(12));
		assertFalse(this.linkedList.contains(55));
	}

	/**
	 * Tests putting a node at the start of a list
	 */
	@Test
	void testNodeAtStart() {
		this.linkedList.append(1);
		this.linkedList.append(2);
		this.linkedList.append(3);
		this.linkedList.prepend(65);
		assertFalse(this.linkedList.isEmpty());
		assertEquals(4, this.linkedList.size());
		assertEquals(65, this.linkedList.retrieve(0));
	}

	/**
	 * Tests looking for the first element of the list before it is made
	 */
	@Test
	void testStartNodeBeforeAppending() {
		assertEquals(0, this.linkedList.size());
	}

	/**
	 *  Tests replacing a node that is out of the list
	 */
	@Test
	void testReplaceOutOfBounds() {
		this.linkedList.append(2);
		this.linkedList.append(5);
		assertFalse(this.linkedList.isEmpty());
		assertEquals(2, this.linkedList.size());
		try {
			this.linkedList.replace(77, 3);
			fail();
		} catch (IndexOutOfBoundsException er) {
			assertTrue(true);
		}
	}

	/**
	 *  Tests inserting a node with a index that is larger then the size of the list
	 */
	@Test
	void testInsertOutOfBounds() {
		this.linkedList.append(2);
		this.linkedList.append(5);
		assertFalse(this.linkedList.isEmpty());
		assertEquals(2, this.linkedList.size());
		try {
			this.linkedList.insert(77, -1);
			fail();
		} catch (IndexOutOfBoundsException er) {
			assertTrue(true);
		}
	}

	/**
	 *  Tests deleting the first node of the list
	 */
	@Test
	void testDeleteNodeFromStart() {
		this.linkedList.append(2);
		this.linkedList.append(5);
		assertFalse(this.linkedList.isEmpty());
		assertEquals(2, this.linkedList.size());
		try {
			this.linkedList.delete(0);
			assertEquals(1, this.linkedList.size());
		} catch (IndexOutOfBoundsException er) {
			fail();
		}
	}

	/**
	 *  Tests finding a node that doesn't exist
	 */
	@Test
	void testFindNodeThatDoesntExist() {
		this.linkedList.append(5);
		this.linkedList.append(1);
		assertFalse(this.linkedList.isEmpty());
		assertEquals(2, this.linkedList.size());
		assertTrue(this.linkedList.contains(5));
		assertFalse(this.linkedList.contains(7));
	}
}
