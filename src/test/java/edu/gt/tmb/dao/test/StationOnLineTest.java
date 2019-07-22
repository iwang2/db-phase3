package edu.gt.tmb.dao.test;

import org.junit.Test;

import edu.gt.tmb.dao.StationOnLineDao;

public class StationOnLineTest {
	StationOnLineDao sold = new StationOnLineDao();
	@Test
	public void testAddLinetoStation() {
		System.out.println(sold.getAllStationOnLine());
	}
}
