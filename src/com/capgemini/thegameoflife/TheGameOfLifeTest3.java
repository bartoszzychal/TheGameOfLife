package com.capgemini.thegameoflife;

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
public class TheGameOfLifeTest3 {

	TheGameOfLife game;
	static Logger logger;
	private List<Cell> params, expected;

	public TheGameOfLifeTest3(List<Cell> params, List<Cell> expected) {
		this.params = params;
		this.expected = expected;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			{ 
				Arrays.asList(new Cell(2, 2)), Arrays.asList() 
				}, 			
		});
	}

	@Before
	public void before() {
		game = new TheGameOfLife(2,2);
	}

	@After
	public void after() {
		game = null;
	}

	@Test(expected = IllegalArgumentException.class)
	public void	shouldBeIllegalArgumentException() {
		// when
		game.setInitialState(params);
		game.evolve();	
		//then exception
	}

}
