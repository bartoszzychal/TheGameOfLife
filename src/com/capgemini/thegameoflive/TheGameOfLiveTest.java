package com.capgemini.thegameoflive;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TheGameOfLiveTest {

	TheGameOfLive game;
	static Logger logger;
	private List<Cell> params, expected;

	public TheGameOfLiveTest(List<Cell> params, List<Cell> expected) {
		this.params = params;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{ 
				Arrays.asList(new Cell(0, 0)), Arrays.asList() 
				}, 
			{ //cell()
				Arrays.asList(new Cell(0, 2), new Cell(1, 1), new Cell(1, 2), new Cell(2, 0)),
				Arrays.asList(new Cell(0, 2), new Cell(0, 1), new Cell(1, 1), new Cell(1, 2), new Cell(2, 1)) 
				},
			{
					Arrays.asList(new Cell(1, 0), new Cell(2, 0), new Cell(1, 1), new Cell(2, 1), new Cell(2, 2)),
					Arrays.asList(new Cell(1, 0), new Cell(2, 0), new Cell(3, 1), new Cell(1, 2), new Cell(2, 2)) 
				}
				
		});

	}

	@BeforeClass
	public static void beforeClass() {
		logger = Logger.getLogger("Logger");
	}

	@Before
	public void before() {
		game = new TheGameOfLive();
	}

	@After
	public void after() {
		game = null;
	}

	@Test
	public void testTheLive() {
		// when
		game.setInitialState(params);
		game.nextStates();
		List<Cell> cells = game.getPositionsOfLiveCell();
		// then
		assertEquals(cells.containsAll(expected),expected.containsAll(cells) );
	}

}
