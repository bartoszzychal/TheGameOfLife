package com.capgemini.thegameoflife;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TheGameOfLifeTest2 {

	TheGameOfLife game;
	static Logger logger;
	private List<Cell> params, expected;

	public TheGameOfLifeTest2(List<Cell> params, List<Cell> expected) {
		this.params = params;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{
					Arrays.asList(new Cell(1, 0), new Cell(2, 0), new Cell(1, 1), new Cell(2, 1), new Cell(2, 2)),
					Arrays.asList(new Cell(2, 0), new Cell(3, 1), new Cell(2, 2)) 
				}
				
		});

	}

	@Before
	public void before() {
		game = new TheGameOfLife(3,3);
	}

	@After
	public void after() {
		game = null;
	}

	@Test
	public void shouldBeListWithAllTheSameObjects() {
		// when
		game.setInitialState(params);
		game.evolve();
		game.evolve();
		List<Cell> cells = game.getAliveCell();
		// then
		assertEquals(cells.containsAll(expected),expected.containsAll(cells));
	}

}
