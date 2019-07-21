package edu.gt.tmb;

import edu.gt.tmb.dao.ReviewDao;
import edu.gt.tmb.dao.StationDao;
import edu.gt.tmb.dao.StationOnLineDao;
import edu.gt.tmb.dao.UserDao;
import edu.gt.tmb.entity.Review;
import edu.gt.tmb.entity.Station;
import edu.gt.tmb.entity.User;



public class TmbApp {
	public static void main(String[] args) {
		UserDao userDao = new UserDao();
		User user = userDao.getUser("chall68");
		
		System.out.println(user);
		System.out.println(userDao.getAllUsers());
		
		User userNeethu = new User();
		userNeethu.setId("007");
		userNeethu.setFirstName("Nikita");
		userNeethu.setMinit(null);
		userNeethu.setLastName("Bipin");
		userNeethu.setPassengerEmail("neethu.bipin@yahoo.com");
		userNeethu.setPassword("test");
		
		userDao.updateUser(userNeethu);
		System.out.println(userDao.getAllUsers());
		//userDao.addUser(userNeethu);
		System.out.println(userDao.getUser("005"));
		
		
		//User newuser = new User();
		//userDao.checkPassUser("some", "notrightt");
		System.out.println(userDao.checkPassUser("pwd", "chall68"));
		System.out.println(userDao.getUserName("Charles", "Hall"));
		
		System.out.println(userDao.deleteUser("005"));
		System.out.println(userDao.getAllUsers());
		
		//System.out.println(userDao.updateUser(userNeethu));
		//System.out.println(userDao.getAllUsers());
		StationDao stationDao = new StationDao();
		System.out.println(stationDao.orderStation());
		
		System.out.println(stationDao.getAllStations());
		Station station = stationDao.getStation("Catalunya");
		System.out.println(station);
		System.out.println(station.getName());
		
		Station newStation = new Station();
		newStation.setName("Arc de Triomf");
		newStation.setStatus("approved");
		newStation.setAddress("Somewhere");
		newStation.setCity("Barca");
		newStation.setZipcode(1234);
		newStation.setStateProvince("Barcelona");
		
		
		//stationDao.addStation(newStation);
		System.out.println(stationDao.getAllStations());
		
		ReviewDao reviewDao = new ReviewDao();
		//Review newrev = new Review();
		System.out.println(reviewDao.getApprovedReview("Catalunya","approved"));
		//Review some = reviewDao.getReviews("chall68");
		//System.out.println(some);
		System.out.println(reviewDao.getAllReviews());
		//System.out.println(reviewDao.shoppingAvg("approved", "Catalunya"));
		Review newreview = new Review();
		newreview.setPassengerId("something");
		newreview.setShopping(2);
		newreview.setConnectionSpeed(9);
		newreview.setComment("im sad");
		//reviewDao.addReview(newreview);
		System.out.println(reviewDao.getAllReviews());
		StationOnLineDao sold = new StationOnLineDao();
		System.out.println(sold.getAllStationOnLine());
		System.out.println(sold.getStationLineName("Catalunya"));
		System.out.println(sold.getLineInfo("L1"));
//		TmbApp pro = new TmbApp();
//        pro.createConnection();
    }

//    void createConnection() {
//        try {
//            //Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TMB?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","password"); 
//            		//"jdbc:mysql://localhost:3306/TMB","root","password");
//            System.out.println("Database connection success");
//
////        } catch (ClassNotFoundException ex) {
////            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (java.sql.SQLException ex) {
//            Logger.getLogger(TmbApp.class.getName()).log(Level.SEVERE, null,ex);
//        }
//    }
}
