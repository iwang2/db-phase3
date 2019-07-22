package edu.gt.tmb.dao.test;

import org.junit.Assert;
import org.junit.Test;

import edu.gt.tmb.dao.ReviewDao;
import edu.gt.tmb.entity.Review;

public class ReviewTest {
	private ReviewDao reviewDao = new ReviewDao();
	@Test
	public void createReviewTest() {
		Review review = new Review();
		review.setPassengerId("chall68");
        //review.setApprovalStatus( "pending" );
        //review.setApproverId("somethingelse");
        review.setComment( "i hate this" ); 
        review.setConnectionSpeed( 1 );
        review.setShopping( 4 );
        //review.setEditTimestamp(null);
        //review.setRid(15);
        review.setStationName("Arc de Triomf");
		//reviewDao.addReview(review);
		System.out.println(reviewDao.getReviews("chall68"));
		System.out.println(reviewDao.getAllReviews());
		
		//System.out.println(reviewDao.orderReview());
		
		System.out.println(reviewDao.getAllReviews());
		
		
	}
	
	@Test
	public void testOrderReview() {
		//System.out.println("Ordered:"+reviewDao.orderReview("rid"));
		//System.out.println("Ordered:"+reviewDao.orderReview("station_name"));
		//System.out.println(reviewDao.getAllReviews());
	}
	
	@Test
	public void testDeleteReview() {
//		System.out.println(reviewDao.deleteReview(3));
//		System.out.println(reviewDao.getAllReviews());
	}
	
	@Test
	public void testApprovedReview() {
		System.out.println("approved reviews: "+reviewDao.getApprovedReview("Catalunya", "approved") );
		
	}
	
	
	
	@Test
	public void testUpdateReviewStatus() {
		Review review = new Review();
		review.setPassengerId("chall68");
        review.setApprovalStatus("reject");

        review.setRid(1);
        Assert.assertTrue("must be true", reviewDao.updateReviewStatus(review));
        
        System.out.println("CheckUpdate"+reviewDao.getReviewName(review.getRid(), review.getPassengerId()));
        Assert.assertEquals("must be rejected", "reject",review.getApprovalStatus());

		
	}
	
	@Test
	public void testPendingReview() {
		System.out.println("pending reviews: "+reviewDao.getPendingReview("pending") );
		
	}
}
