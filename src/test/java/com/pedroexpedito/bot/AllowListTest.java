package com.pedroexpedito.bot;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AllowListTest {
	// contains 
	@Test
	public void Contains() {
		assertTrue(AllowList.contains("pedro"));
	}

}
