package com.capgemini.thegameoflife;

import static org.junit.Assert.*;

import org.junit.Test;

public class CellTest {
	
	@Test
	public void shouldBeOneForXAndOneForY() {
		//given
		Integer one = 1;
		//when
		Cell cell = new Cell(one, one);
		//then
		assertEquals(one, cell.getX());
		assertEquals(one, cell.getY());
	}
	
	@Test
	public void shouldBeTheSame() {
		//given
		Integer one = 1;
		//when
		Cell cell1 = new Cell(one, one);
		Cell cell2 = new Cell(one, one);
		//then
		assertTrue(cell1.equals(cell2));
	}
	


}
