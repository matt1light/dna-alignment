package alignment;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

public class TracebackNodeTest {
	
	
	@Test
	public void defaultConstructorTest(){
		TracebackNode <Integer> testNode = new TracebackNode<Integer>();
		assertEquals("parent was not set properly", null, testNode.getParent());
		assertEquals("children were not set properly", new ArrayList<TracebackNode<Integer>>(), testNode.getChildren());
		assertEquals("data was not set properly", null, testNode.getElement());
	}
	
	@Test
	public void overloadedConstructorTest(){
		TracebackNode <Integer> parentNode = new TracebackNode<Integer>();
		ArrayList<TracebackNode<Integer>> childrenArray = new ArrayList<TracebackNode<Integer>>();
		Integer num = 1;
		
		TracebackNode <Integer> testNode = new TracebackNode<Integer>(parentNode, childrenArray, num);
		
		assertEquals("parent was not set properly", parentNode, testNode.getParent());
		assertEquals("children were not set properly", childrenArray, testNode.getChildren());
		assertEquals("data was not set properly", num, testNode.getElement());
	}
	
	@Test
	public void isRootTest(){
		TracebackNode <Integer> parentNode = new TracebackNode<Integer>();
		TracebackNode <Integer> childNode = new TracebackNode<Integer>();
		
		childNode.setParent(parentNode);
		
		assertEquals("root node was not a root", true, parentNode.isRoot());
		assertEquals("child node was a root", false, childNode.isRoot());
	}
	
	@Test
	public void isLeafTest(){
		TracebackNode <Integer> parentNode = new TracebackNode<Integer>();
		TracebackNode <Integer> childNode = new TracebackNode<Integer>();
		
		parentNode.getChildren().add(childNode);
		
		assertEquals("leaf node was not a leaf", true, childNode.isLeaf());
		assertEquals("non-leaf node was a leaf", false, parentNode.isLeaf());
	}
	
	@Test
	public void addNodeTest(){
		TracebackNode<Integer> testNode = new TracebackNode<Integer>();
		Integer num = 5;
		TracebackNode<Integer> addedNode = new TracebackNode<Integer>();
		addedNode.setElement(num);
		addedNode.setParent(testNode);
		
		testNode.addChildNode(5);
		
		assertEquals("child node was not added as a child properly", addedNode, testNode.getChildren().get(0));
		assertEquals("child node was not a leaf", true, addedNode.isLeaf());
		assertEquals("parent node was not a root", true, testNode.isRoot());
		assertEquals("parent node was a leaf", false, testNode.isLeaf());
		assertEquals("child node was a root", false, addedNode.isRoot());
	}
}
