package edu.gt.tmb.gui;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import edu.gt.tmb.dao.AdminAddLineDao;
import edu.gt.tmb.dao.AdminAddStationDao;
import edu.gt.tmb.dao.CardDao;
import edu.gt.tmb.dao.LineDao;
import edu.gt.tmb.dao.ReviewDao;
import edu.gt.tmb.dao.StationDao;
import edu.gt.tmb.dao.StationOnLineDao;
import edu.gt.tmb.dao.TripDao;
import edu.gt.tmb.dao.UserDao;
import edu.gt.tmb.entity.Line;
import edu.gt.tmb.entity.Review;
import edu.gt.tmb.entity.Station;
import edu.gt.tmb.entity.User;

//MAIN CLASS GUI
public class GUI {
    //MAIN FRAME
    private JFrame frame;
    //private String userid;
    private UserDao userDao;
    private StationDao stationDao;
    private LineDao lineDao;
    private User currentUser;
    private ReviewDao reviewDao;
    private AdminAddLineDao adminAddLineDao;
    private AdminAddStationDao adminAddStationDao;
    private CardDao cardDao;
    private StationOnLineDao stationOnLineDao;
    private TripDao tripDao;
    

    //LAUNCH APPLICATION
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI window = new GUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //CREATE APPLICATION
    private GUI() {
        initialize();
    }

    //INITIALIZE FRAME CONTENTS
    private void initialize() {
    	userDao = new UserDao();
    	lineDao = new LineDao();
    	stationDao = new StationDao();
    	currentUser = new User();
    	
        reviewDao = new ReviewDao();
        adminAddLineDao = new AdminAddLineDao();
        adminAddStationDao = new AdminAddStationDao();
        cardDao = new CardDao();
        stationOnLineDao = new StationOnLineDao();
        tripDao = new TripDao();
    	
    	
    	
    	
    	
        //INITIALIZE FRAME***********************************************************************************************************************************************************************************
        frame = new JFrame();
        frame.setBounds(100, 100, 673, 555);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new CardLayout(0, 0));

        //PANELS*********************************************************************************************************************************************************************************************
        JPanel login = new JPanel();
        frame.getContentPane().add(login, "name_887701963216773");
        login.setLayout(null);
        login.setVisible(true);

        JPanel register = new JPanel();
        frame.getContentPane().add(register, "name_887705093727702");
        register.setLayout(null);
        register.setVisible(false);

        JPanel passenger_landing = new JPanel();
        frame.getContentPane().add(passenger_landing, "name_887708335338834");
        passenger_landing.setLayout(null);
        passenger_landing.setVisible(false);

        JPanel leave_review = new JPanel();
        frame.getContentPane().add(leave_review, "name_887711312617590");
        leave_review.setLayout(null);
        leave_review.setVisible(false);

        JPanel view_reviews = new JPanel(new BorderLayout());
        frame.getContentPane().add(view_reviews, "name_887714190402998");
        view_reviews.setLayout(null);
        view_reviews.setVisible(false);
//        JTable view_review_table = new JTable();
        //view_reviews.add(new JScrollPane(scrTbl););

        JPanel edit_review = new JPanel();
        frame.getContentPane().add(edit_review, "name_890815384007534");
        edit_review.setLayout(null);
        edit_review.setVisible(false);

        JPanel station_info_pass_LIST = new JPanel();
        frame.getContentPane().add(station_info_pass_LIST, "name_890895064647080");
        station_info_pass_LIST.setLayout(null);
        station_info_pass_LIST.setVisible(false);

        JPanel line_summary_LIST = new JPanel();
        frame.getContentPane().add(line_summary_LIST, "name_890912688645957");
        line_summary_LIST.setLayout(null);
        line_summary_LIST.setVisible(false);

        JPanel edit_profile_pass = new JPanel();
        frame.getContentPane().add(edit_profile_pass, "name_890924008034684");
        edit_profile_pass.setLayout(null);
        edit_profile_pass.setVisible(false);

        JPanel buy_card = new JPanel();
        frame.getContentPane().add(buy_card, "name_890939111999284");
        buy_card.setLayout(null);
        buy_card.setVisible(false);

        JPanel go_trip = new JPanel();
        frame.getContentPane().add(go_trip, "name_890954674937645");
        go_trip.setLayout(null);
        go_trip.setVisible(false);

        JPanel view_trips_LIST = new JPanel();
        frame.getContentPane().add(view_trips_LIST, "name_890968145577376");
        view_trips_LIST.setLayout(null);
        view_trips_LIST.setVisible(false);

        JPanel update_trip = new JPanel();
        frame.getContentPane().add(update_trip, "name_891035313193846");
        update_trip.setLayout(null);
        update_trip.setVisible(false);

        JPanel admin_landing = new JPanel();
        frame.getContentPane().add(admin_landing, "name_891058739491681");
        admin_landing.setLayout(null);
        admin_landing.setVisible(false);

        JPanel review_admin_LIST = new JPanel();
        frame.getContentPane().add(review_admin_LIST, "name_891069119890547");
        review_admin_LIST.setLayout(null);
        review_admin_LIST.setVisible(false);

        JPanel edit_profile_admin = new JPanel();
        frame.getContentPane().add(edit_profile_admin, "name_891103950965807");
        edit_profile_admin.setLayout(null);
        edit_profile_admin.setVisible(false);

        JPanel add_station_LIST = new JPanel();
        frame.getContentPane().add(add_station_LIST, "name_891125642668851");
        add_station_LIST.setLayout(null);
        add_station_LIST.setVisible(false);

        JPanel add_line_LIST = new JPanel();
        frame.getContentPane().add(add_line_LIST, "name_891134607849316");
        add_line_LIST.setLayout(null);
        add_line_LIST.setVisible(false);

        JPanel station_info_admin_LIST = new JPanel();
        frame.getContentPane().add(station_info_admin_LIST, "name_891174545343789");
        station_info_admin_LIST.setLayout(null);
        station_info_admin_LIST.setVisible(false);

        JPanel line_summary_admin_LIST = new JPanel();
        frame.getContentPane().add(line_summary_admin_LIST, "name_891160577625434");
        line_summary_admin_LIST.setLayout(null);
        line_summary_admin_LIST.setVisible(false);

        //ERROR SCREENS**************************************************************************************************************************************************************************************
        JInternalFrame logincred_error = new JInternalFrame("Login Credentials Error");
        frame.getContentPane().add(logincred_error, "name_1040828176775906");
        logincred_error.setLayout(null);

        JInternalFrame uniqueness_error = new JInternalFrame("Unique ID Error");
        frame.getContentPane().add(uniqueness_error, "name_1038285767295904");
        uniqueness_error.setLayout(null);

        JInternalFrame requiredinfo_error = new JInternalFrame("Required Info Error");
        frame.getContentPane().add(requiredinfo_error, "name_1040262648094191");
        requiredinfo_error.setLayout(null);

        JInternalFrame passlength_error = new JInternalFrame("Password Length Error");
        frame.getContentPane().add(passlength_error, "name_1041764932792924");
        passlength_error.setLayout(null);

        JInternalFrame passmatch_error = new JInternalFrame("Password Match Error");
        frame.getContentPane().add(passmatch_error, "name_1041924475565923");
        passmatch_error.setLayout(null);

        JInternalFrame invalidinfo_error = new JInternalFrame("Invalid Info Error");
        frame.getContentPane().add(invalidinfo_error, "name_1042093470286061");
        invalidinfo_error.setLayout(null);

        //SCREEN CAPTIONS
        JLabel logincred_lbl = new JLabel("Invalid login credentials");
        logincred_lbl.setBounds(166, 169, 282, 33);
        logincred_error.getContentPane().add(logincred_lbl);

        JLabel uniqueness_lbl = new JLabel("User ID must be unique");
        uniqueness_lbl.setBounds(166, 169, 282, 33);
        uniqueness_error.getContentPane().add(uniqueness_lbl);

        JLabel reqinfo_lbl = new JLabel("Missing required info");
        reqinfo_lbl.setBounds(166, 169, 282, 33);
        requiredinfo_error.getContentPane().add(reqinfo_lbl);

        JLabel passlength_lbl = new JLabel("Password must be at least 8 characters");
        passlength_lbl.setBounds(130, 169, 282, 33);
        passlength_error.getContentPane().add(passlength_lbl);

        JLabel passmatch_lbl = new JLabel("Passwords must match");
        passmatch_lbl.setBounds(166, 169, 282, 33);
        passmatch_error.getContentPane().add(passmatch_lbl);

        JLabel invalidinfo_lbl = new JLabel("Invalid info used");
        invalidinfo_lbl.setBounds(166, 169, 282, 33);
        invalidinfo_error.getContentPane().add(invalidinfo_lbl);

        //EXIT BUTTONS
        JButton exit_1=new JButton("X");
        exit_1.setBounds(400,400,60,30);
        logincred_error.add(exit_1);

        JButton exit_2=new JButton("X");
        exit_2.setBounds(400,400,60,30);
        uniqueness_error.add(exit_2);

        JButton exit_3=new JButton("X");
        exit_3.setBounds(400,400,60,30);
        requiredinfo_error.add(exit_3);

        JButton exit_4=new JButton("X");
        exit_4.setBounds(400,400,60,30);
        passlength_error.add(exit_4);

        JButton exit_5=new JButton("X");
        exit_5.setBounds(400,400,60,30);
        passmatch_error.add(exit_5);

        JButton exit_6=new JButton("X");
        exit_6.setBounds(400,400,60,30);
        invalidinfo_error.add(exit_6);



        List<Station> stations = stationDao.getAllStations();
        List<Line> lines = lineDao.orderLine();
        //String[] stations=new String[100];
        //stations[0]="Station Name";
        /*StationDao stationDao = new StationDao();
        List<tmb.entity.StationDao> stationList=stationDao.getAllStations();
        int length=stationList.size();
        for(int i=1;i<length+1;i++) {
            //stations[i]=stationList
        }*/

//        String[] lines=new String[50];
//        lines[0]="Line Name";






        //LOGIN**********************************************************************************************************************************************************************************************
        JTextField login_userid = new JTextField();
        login_userid.setText("User ID");
        login_userid.setBounds(186, 177, 236, 39);
        login.add(login_userid);
        login_userid.setColumns(10);

        JTextField login_pass = new JTextField();
        login_pass.setText("Password");
        login_pass.setBounds(186, 255, 236, 39);
        login.add(login_pass);
        login_pass.setColumns(20);
        
        JLabel passland_lbl_welcome = new JLabel("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
        
        JButton login_btn_login = new JButton("Login");
        login_btn_login.addActionListener(e -> {
        	
        	if (userDao.checkPassUser(login_pass.getText(),login_userid.getText())) {
        		//userid=login_userid.getText();
        		currentUser = new User();
        		currentUser.setId(login_userid.getText());
        		//current.getUser(userid);
        		currentUser = userDao.getUser(currentUser.getId());
        		passland_lbl_welcome.setText("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
        		if (userDao.isUserAdmin(currentUser)) {
        			 admin_landing.setVisible(true);
                     login.setVisible(false);
        		} else {
        			 
        			 passenger_landing.setVisible(true);
        			 
                     login.setVisible(false);
        		}
        		
        		
                //current=getUser(userid);
//                String type="P";                                      //DETERMINE TYPE OF USER SIGNING IN
//                if(type.equals("P")) {
//                    passenger_landing.setVisible(true);
//                    login.setVisible(false);
//                }
//                else    {
//                    admin_landing.setVisible(true);
//                    login.setVisible(false);
//                }
//        		
        	} else {
        		logincred_error.setVisible(true);
                login.setVisible(false);
                exit_1.addActionListener(f -> {
             
                    login.setVisible(true);
                    logincred_error.setVisible(false);
                });
        	}
        	
//            if(login_userid.getText().equals("no")) {                   //USER ID NOT IN DATABASE
//                logincred_error.setVisible(true);
//                login.setVisible(false);
//                exit_1.addActionListener(f -> {
//             
//                    login.setVisible(true);
//                    logincred_error.setVisible(false);
//                });
//            }
//            else if(login_pass.getText().equals("no")) {                //PASSWORD NOT ALIGNED WITH USER ID
//                logincred_error.setVisible(true);
//                login.setVisible(false);
//                exit_1.addActionListener(f -> {
//                    login.setVisible(true);
//                    logincred_error.setVisible(false);
//                });
//            }
//            else {
//                userid=login_userid.getText();
//                //current=getUser(userid);
//                String type="P";                                      //DETERMINE TYPE OF USER SIGNING IN
//                if(type.equals("P")) {
//                    passenger_landing.setVisible(true);
//                    login.setVisible(false);
//                }
//                else    {
//                    admin_landing.setVisible(true);
//                    login.setVisible(false);
//                }
//            }
        });
        login_btn_login.setBounds(120, 361, 171, 41);
        login.add(login_btn_login);

        JButton login_btn_regis = new JButton("Register");
        login_btn_regis.addActionListener(e -> {
            register.setVisible(true);
            login.setVisible(false);
        });
        login_btn_regis.setBounds(331, 361, 171, 41);
        login.add(login_btn_regis);

        JLabel login_lbl_login = new JLabel("LOGIN");
        login_lbl_login.setBounds(231, 81, 115, 33);
        login.add(login_lbl_login);

        //REGISTER*******************************************************************************************************************************************************************************************
        JLabel regis_lbl_regis = new JLabel("REGISTER");
        regis_lbl_regis.setBounds(247, 28, 151, 33);
        register.add(regis_lbl_regis);

        JTextField regis_first = new JTextField();
        regis_first.setText("First Name"); 
        regis_first.setBounds(36, 89, 440, 39);
        register.add(regis_first);
        regis_first.setColumns(10);
        
        JTextField regis_m = new JTextField();
        regis_m.setText("");
        regis_m.setBounds(521, 89, 78, 39);
        register.add(regis_m);
        regis_m.setColumns(10);

        JTextField regis_last = new JTextField();
        regis_last.setText("Last Name");
        
        regis_last.setBounds(36, 146, 440, 39);
        register.add(regis_last);
        regis_last.setColumns(10);

        JTextField regis_userid = new JTextField();
        regis_userid.setText("User ID");
        regis_userid.setBounds(36, 237, 563, 39);
        register.add(regis_userid);
        regis_userid.setColumns(10);

        JTextField regis_email = new JTextField();
        regis_email.setText("Email");
        regis_email.setBounds(36, 290, 563, 39);
        register.add(regis_email);
        regis_email.setColumns(10);

        JTextField regis_pass = new JTextField();
        regis_pass.setText("Password");
        regis_pass.setBounds(36, 357, 258, 39);
        register.add(regis_pass);
        regis_pass.setColumns(10);

        JTextField regis_passagain = new JTextField();
        regis_passagain.setText("Password (again)");
        regis_passagain.setBounds(341, 357, 258, 39);
        register.add(regis_passagain);
        regis_passagain.setColumns(10);
        
        //JLabel passland_lbl_welcome = new JLabel("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
        JButton regis_btn_regis = new JButton("Register");
        regis_btn_regis.addActionListener(e -> {
            if(regis_first.getText().equals("First Name")) {
                requiredinfo_error.setVisible(true);
                register.setVisible(false);
                exit_3.addActionListener(f -> {
                    register.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            else if(regis_last.getText().equals("Last Name")) {
                requiredinfo_error.setVisible(true);
                register.setVisible(false);
                exit_3.addActionListener(f -> {
                    register.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            else if(regis_email.getText().equals("Email")) {        //CHECK FOR VALID EMAIL?????
                requiredinfo_error.setVisible(true);
                register.setVisible(false);
                exit_3.addActionListener(f -> {
                    register.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            else if(!regis_pass.getText().equals(regis_passagain.getText())) {
                passmatch_error.setVisible(true);
                register.setVisible(false);
                exit_5.addActionListener(f -> {
                    register.setVisible(true);
                    passmatch_error.setVisible(false);
                });
            }
            else if(regis_pass.getText().length() < 8) {
                passlength_error.setVisible(true);
                register.setVisible(false);
                exit_4.addActionListener(f -> {
                    register.setVisible(true);
                    passlength_error.setVisible(false);
                });
            }
            else if(regis_userid.getText().equals("User ID")) {
                requiredinfo_error.setVisible(true);
                register.setVisible(false);
                exit_3.addActionListener(f -> {
                    register.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            else if(userDao.getUser(regis_userid.getText()) != null) {                                        //CHECK FOR USER ID UNIQUENESS
            	
                uniqueness_error.setVisible(true);
                register.setVisible(false);
                exit_2.addActionListener(f -> {
                    register.setVisible(true);
                    uniqueness_error.setVisible(false);
                });
            }
            else {	//SET VALUES
            	//User user = new User();
            	currentUser.setId(regis_userid.getText());
            	currentUser.setFirstName(regis_first.getText());
            	currentUser.setLastName(regis_last.getText());
            	currentUser.setMinit(regis_m.getText());
            	currentUser.setPassengerEmail(regis_email.getText());
            	currentUser.setPassword(regis_pass.getText());

            	userDao.addUser(currentUser);
            	passland_lbl_welcome.setText("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
                passenger_landing.setVisible(true);
                
                register.setVisible(false);
            }
        });
        regis_btn_regis.setBounds(235, 408, 171, 41);
        register.add(regis_btn_regis);

        //PASSENGER LANDING*********************************************************************************************************************************************************************************
        
        //JLabel passland_lbl_welcome = new JLabel("Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
        
        passland_lbl_welcome.setBounds(36, 73, 477, 33);
        passenger_landing.add(passland_lbl_welcome);

        JButton passland_btn_leaverev = new JButton("Leave Review");
        passland_btn_leaverev.addActionListener(e -> {
            leave_review.setVisible(true);
            passenger_landing.setVisible(false);
        });
        passland_btn_leaverev.setBounds(36, 165, 235, 41);
        passenger_landing.add(passland_btn_leaverev);

        JButton passland_btn_writerev = new JButton("View Review");
        passland_btn_writerev.addActionListener(e-> {
            view_reviews.setVisible(true);
            passenger_landing.setVisible(false);
        });
        passland_btn_writerev.setBounds(36, 257, 235, 41);
        passenger_landing.add(passland_btn_writerev);

        JButton passland_btn_buycard = new JButton("Buy Card");
        passland_btn_buycard.addActionListener(e -> {
            buy_card.setVisible(true);
            passenger_landing.setVisible(false);
        });
        passland_btn_buycard.setBounds(36, 355, 235, 41);
        passenger_landing.add(passland_btn_buycard);

        JButton passland_btn_gotrip = new JButton("Go On Trip");
        passland_btn_gotrip.addActionListener(e -> {
            go_trip.setVisible(true);
            passenger_landing.setVisible(false);
        });
        passland_btn_gotrip.setBounds(323, 165, 235, 41);
        passenger_landing.add(passland_btn_gotrip);

        JButton passland_btn_viewtrip = new JButton("View Trips");
        passland_btn_viewtrip.addActionListener(e -> {
            view_trips_LIST.setVisible(true);
            passenger_landing.setVisible(false);
        });
        passland_btn_viewtrip.setBounds(323, 257, 235, 41);
        passenger_landing.add(passland_btn_viewtrip);

        JButton passland_btn_editprof = new JButton("Edit Profile");
//        passland_btn_editprof.addActionListener(e -> {
//        	
//            editprof_first.setText(currentUser.getFirstName());                                           //INPUT USERS FIRST NAME
//            
//            editprof_last.setText(currentUser.getLastName());                                              //INPUT USERS LAST NAME
//            editprof_email.setText(currentUser.getPassengerEmail());                                                //INPUT USERS EMAIL
//            editprof_userid.setText(currentUser.getId());                                             //INPUT USERS ID
//            
//            editprof_pass.setText(currentUser.getPassword());                                              //INPUT USERS PASSWORD
//            
//
//            edit_profile_pass.setVisible(true);
//            passenger_landing.setVisible(false);
//        });
        passland_btn_editprof.setBounds(323, 355, 235, 41);
        passenger_landing.add(passland_btn_editprof);

        //LEAVE REVIEW***************************************************************************************************************************************************************************************
        JButton home_pass_1 = new JButton("Home");
        home_pass_1.setBounds(0,0,80,20);
        home_pass_1.addActionListener(e -> {
            passenger_landing.setVisible(true);
            leave_review.setVisible(false);
        });
        leave_review.add(home_pass_1);
        
        JComboBox leaverev_drop_station = new JComboBox();
		leaverev_drop_station.setModel(new DefaultComboBoxModel(stations.toArray()));
		leaverev_drop_station.setMaximumRowCount(100);
		leaverev_drop_station.setBounds(36, 89, 294, 39);
		leave_review.add(leaverev_drop_station);

        JLabel leaverev_lbl_leaverev = new JLabel("Leave A Review");
        leaverev_lbl_leaverev.setBounds(26, 28, 209, 33);
        leave_review.add(leaverev_lbl_leaverev);

        JLabel leaverev_lbl_shop = new JLabel("Shopping");
        leaverev_lbl_shop.setBounds(36, 162, 115, 33);
        leave_review.add(leaverev_lbl_shop);

        JLabel leaverev_lbl_connect = new JLabel("Connection Speed");
        leaverev_lbl_connect.setBounds(36, 208, 253, 33);
        leave_review.add(leaverev_lbl_connect);

        JTextField leaverev_comment = new JTextField();
        leaverev_comment.setText("Comments");
        leaverev_comment.setBounds(36, 280, 579, 104);
        leave_review.add(leaverev_comment);
        leaverev_comment.setColumns(10);

        JRadioButton leavrev_star1shop = new JRadioButton("");
        leavrev_star1shop.setBounds(221, 158, 41, 41);
        leave_review.add(leavrev_star1shop);

        JRadioButton leavrev_star2shop = new JRadioButton("");
        leavrev_star2shop.setBounds(280, 158, 41, 41);
        leave_review.add(leavrev_star2shop);

        JRadioButton leavrev_star3shop = new JRadioButton("");
        leavrev_star3shop.setBounds(339, 158, 41, 41);
        leave_review.add(leavrev_star3shop);

        JRadioButton leavrev_star4shop = new JRadioButton("");
        leavrev_star4shop.setBounds(398, 158, 41, 41);
        leave_review.add(leavrev_star4shop);

        JRadioButton leavrev_star5shop = new JRadioButton("");
        leavrev_star5shop.setBounds(457, 158, 41, 41);
        leave_review.add(leavrev_star5shop);

        JRadioButton leavrev_star1connect = new JRadioButton("");
        leavrev_star1connect.setBounds(290, 204, 41, 41);
        leave_review.add(leavrev_star1connect);

        JRadioButton leavrev_star2connect = new JRadioButton("");
        leavrev_star2connect.setBounds(349, 204, 41, 41);
        leave_review.add(leavrev_star2connect);

        JRadioButton leavrev_star3connect = new JRadioButton("");
        leavrev_star3connect.setBounds(408, 204, 41, 41);
        leave_review.add(leavrev_star3connect);

        JRadioButton leavrev_star4connect = new JRadioButton("");
        leavrev_star4connect.setBounds(467, 204, 41, 41);
        leave_review.add(leavrev_star4connect);

        JRadioButton leavrev_star5connect = new JRadioButton("");
        leavrev_star5connect.setBounds(520, 204, 41, 41);
        leave_review.add(leavrev_star5connect);

        JButton leaverev_btn_submit = new JButton("Submit Review");
        leaverev_btn_submit.addActionListener(g -> {
            String reviewed_station = String.valueOf(leaverev_drop_station.getSelectedItem());
            if(reviewed_station.equals("Station Name")) {
                requiredinfo_error.setVisible(true);
                leave_review.setVisible(false);
                exit_3.addActionListener(d -> {
                    leave_review.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            int shop_rate = 0;
            if (leavrev_star5shop.isSelected()) {
                shop_rate = 5;
            } else if (leavrev_star4shop.isSelected()) {
                shop_rate = 4;
            } else if (leavrev_star3shop.isSelected()) {
                shop_rate = 3;
            } else if (leavrev_star2shop.isSelected()) {
                shop_rate = 2;
            } else if (leavrev_star1shop.isSelected()) {
                shop_rate = 1;
            } else {
                requiredinfo_error.setVisible(true);
                leave_review.setVisible(false);
                exit_3.addActionListener(d -> {
                    leave_review.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            int connect_rate = 0;
            if (leavrev_star5connect.isSelected()) {
                connect_rate = 5;
            } else if (leavrev_star4connect.isSelected()) {
                connect_rate = 4;
            } else if (leavrev_star3connect.isSelected()) {
                connect_rate = 3;
            } else if (leavrev_star2connect.isSelected()) {
                connect_rate = 2;
            } else if (leavrev_star1connect.isSelected()) {
                connect_rate = 1;
            } else {
                requiredinfo_error.setVisible(true);
                leave_review.setVisible(false);
                exit_3.addActionListener(d -> {
                    leave_review.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            //SAVE THE INFOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO
            
            String comment = leaverev_comment.getText();
            
            Review review = new Review();
            Station s = (Station)leaverev_drop_station.getSelectedItem();
            review.setStationName(s.getName());
            if(!comment.equals("Comment")) {
                //SAVE COMMENTTTTTTTTTTTTTTTTTTTTT
            	review.setComment(comment);
            }
            
            review.setShopping(shop_rate);
            review.setConnectionSpeed(connect_rate);
            review.setPassengerId(currentUser.getId());
            reviewDao.addReview(review);
            passenger_landing.setVisible(true);
            leave_review.setVisible(false);
        });

        leaverev_btn_submit.setBounds(221, 398, 238, 41);
        leave_review.add(leaverev_btn_submit);

        //VIEW REVIEWS***************************************************************************************************************************************************************************************
        JButton home_pass_2 = new JButton("Home");
        home_pass_2.setBounds(0,0,80,20);
        home_pass_2.addActionListener(e -> {
            passenger_landing.setVisible(true);
            view_reviews.setVisible(false);
        });
        view_reviews.add(home_pass_2);

        JLabel viewrev_lbl_name = new JLabel(currentUser.getFirstName()+" "+currentUser.getFirstName()+"'s Reviews");         //ADD USERS NAME
        viewrev_lbl_name.setBounds(26, 28, 532, 33);
        view_reviews.add(viewrev_lbl_name);
        
        JTable view_revTable = new JTable();
        
        JButton idTblHeader = new JButton("ID [v]");
        idTblHeader.setBounds(0,50,100,33);
        idTblHeader.setBorderPainted(false);
        idTblHeader.addActionListener(e -> {
            //passenger_landing.setVisible(true);
            //view_reviews.setVisible(false);
        	List<Review> orderedReviews = reviewDao.orderReview("rid", currentUser.getId());
        	ReviewTableModel model = new ReviewTableModel(orderedReviews,new String[] {"ID", "Station","Shopping","Connection Speed","Comment","Approval Status"}); 
        	view_revTable.setModel(model);
        	view_revTable.revalidate();
        	view_revTable.repaint();
        });
        view_reviews.add(idTblHeader);
        
        JButton stationTblHeader = new JButton("Station [v]");
        stationTblHeader.setBounds(100, 50, 100, 33);
        stationTblHeader.setBorderPainted(false);
        stationTblHeader.addActionListener(e -> {
            //passenger_landing.setVisible(true);
            //view_reviews.setVisible(false);
        	List<Review> orderedReviews = reviewDao.orderReview("station_name", currentUser.getId());
        	ReviewTableModel model = new ReviewTableModel(orderedReviews,new String[] {"ID", "Station","Shopping","Connection Speed","Comment","Approval Status"}); 
        	view_revTable.setModel(model);
        	view_revTable.revalidate();
        	view_revTable.repaint();
        });
        view_reviews.add(stationTblHeader);
        JLabel shopTblHeader = new JLabel("Shopping [v]");
        shopTblHeader.setBounds(200, 50, 100, 33);
        view_reviews.add(shopTblHeader);
        JLabel connSpeedTblHeader = new JLabel("Connection Speed [v]");
        connSpeedTblHeader.setBounds(300, 50, 100, 33);
        view_reviews.add(connSpeedTblHeader);
        JLabel commentTblHeader = new JLabel("Comment");
        commentTblHeader.setBounds(400, 50, 100, 33);
        view_reviews.add(commentTblHeader);
        JLabel statusTblHeader = new JLabel("Approval Status [v]");
        statusTblHeader.setBounds(500, 50, 100, 33);
        view_reviews.add(statusTblHeader);
        
        //uniqueness_error.getContentPane().add(uniqueness_lbl);
        
        //view_reviews.add(table, BorderLayout.CENTER);
		//tablePanel.add(table.getTableHeader(), BorderLayout.NORTH);
		
		view_revTable.setShowGrid(true);
		//view_revTable.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setPreferredSize(new Dimension(0, 0));
		view_revTable.getTableHeader().setDefaultRenderer(renderer);
		JScrollPane scrollPane = new JScrollPane(view_revTable);
		view_revTable.setFillsViewportHeight(true);
		//view_reviews.add(new JScrollPane(view_revTable));
		
		view_revTable.setModel(new ReviewTableModel(reviewDao.getReviews("chall68"),new String[]{"ID", "Station","Shopping","Connection Speed","Comment","Approval Status"}) );
		
		view_revTable.setBounds(100, 100, 450, 300);
		view_reviews.add(view_revTable);//, BorderLayout.CENTER);
		//view_reviews.add(viewrev_list.getTableHeader(), BorderLayout.NORTH);

        //EDIT REVIEW****************************************************************************************************************************************************************************************
        JButton home_pass_3 = new JButton("Home");
        home_pass_3.setBounds(0,0,80,20);
        home_pass_3.addActionListener(e -> {
            passenger_landing.setVisible(true);
            edit_review.setVisible(false);
        });
        edit_review.add(home_pass_3);

        JLabel editrev_lbl_name = new JLabel("Edit Review: //insert Station");
        editrev_lbl_name.setBounds(26, 28, 356, 33);
        edit_review.add(editrev_lbl_name);

        JLabel editrev_lbl_status = new JLabel("//status");
        editrev_lbl_status.setBounds(500, 28, 115, 33);
        edit_review.add(editrev_lbl_status);

        JLabel editrev_lbl_idstatic = new JLabel("ID");
        editrev_lbl_idstatic.setBounds(36, 89, 115, 33);
        edit_review.add(editrev_lbl_idstatic);

        JLabel editrev_lbl_stationid = new JLabel("//insert station id");
        editrev_lbl_stationid.setBounds(306, 89, 220, 33);
        edit_review.add(editrev_lbl_stationid);

        JLabel editrev_lbl_shop = new JLabel("Shopping");
        editrev_lbl_shop.setBounds(26, 178, 115, 33);
        edit_review.add(editrev_lbl_shop);

        JLabel editrev_lbl_connect = new JLabel("Connection Speed");
        editrev_lbl_connect.setBounds(26, 241, 213, 33);
        edit_review.add(editrev_lbl_connect);

        JTextField editrev_comments = new JTextField();
        editrev_comments.setText("Comments");
        editrev_comments.setBounds(26, 302, 572, 85);
        edit_review.add(editrev_comments);
        editrev_comments.setColumns(10);

        JButton editrev_btn_deleterev = new JButton("Delete Review");
        editrev_btn_deleterev.setBounds(95, 398, 236, 41);
        edit_review.add(editrev_btn_deleterev);

        JButton editrev_btn_saverev = new JButton("Save Review");
        editrev_btn_saverev.setBounds(357, 398, 227, 41);
        edit_review.add(editrev_btn_saverev);

        JRadioButton editrev_star1shop = new JRadioButton("");
        editrev_star1shop.setBounds(207, 178, 41, 41);
        edit_review.add(editrev_star1shop);

        JRadioButton editrev_star2shop = new JRadioButton("");
        editrev_star2shop.setBounds(266, 178, 41, 41);
        edit_review.add(editrev_star2shop);

        JRadioButton editrev_star3shop = new JRadioButton("");
        editrev_star3shop.setBounds(325, 178, 41, 41);
        edit_review.add(editrev_star3shop);

        JRadioButton editrev_star4shop = new JRadioButton("");
        editrev_star4shop.setBounds(384, 178, 41, 41);
        edit_review.add(editrev_star4shop);

        JRadioButton editrev_star5shop = new JRadioButton("");
        editrev_star5shop.setBounds(443, 178, 41, 41);
        edit_review.add(editrev_star5shop);

        JRadioButton editrev_star1connect = new JRadioButton("");
        editrev_star1connect.setBounds(276, 241, 41, 41);
        edit_review.add(editrev_star1connect);

        JRadioButton editrev_star2connect = new JRadioButton("");
        editrev_star2connect.setBounds(335, 241, 41, 41);
        edit_review.add(editrev_star2connect);

        JRadioButton editrev_star3connect = new JRadioButton("");
        editrev_star3connect.setBounds(394, 241, 41, 41);
        edit_review.add(editrev_star3connect);

        JRadioButton editrev_star4connect = new JRadioButton("");
        editrev_star4connect.setBounds(453, 241, 41, 41);
        edit_review.add(editrev_star4connect);

        JRadioButton editrev_star5connect = new JRadioButton("");
        editrev_star5connect.setBounds(512, 241, 41, 41);
        edit_review.add(editrev_star5connect);

        //STATION INFO PASSENGER*****************************************************************************************************************************************************************************
        JButton home_pass_4 = new JButton("Home");
        home_pass_4.setBounds(0,0,80,20);
        home_pass_4.addActionListener(e -> {
            passenger_landing.setVisible(true);
            station_info_pass_LIST.setVisible(false);
        });
        station_info_pass_LIST.add(home_pass_4);


        /*List<StationDao> data=;
        JTable stationinfo_table = new JTable(data,columns);
        String[] columns = {"User","Shopping","Connection Speed","Comments"};*/



        //ADD METHODS TO FIND DATA and CONCATENATE WITH LABEL
        JLabel statinfo_lbl_status = new JLabel("Status: //status");
        statinfo_lbl_status.setBounds(421, 28, 194, 33);
        station_info_pass_LIST.add(statinfo_lbl_status);

        JLabel statinfo_lbl_address = new JLabel("Address:");
        statinfo_lbl_address.setBounds(36, 89, 115, 33);
        station_info_pass_LIST.add(statinfo_lbl_address);

        JLabel statinfo_lbl_lines = new JLabel("Lines: ");
        statinfo_lbl_lines.setBounds(36, 135, 115, 33);
        station_info_pass_LIST.add(statinfo_lbl_lines);

        JLabel statinfo_lbl_avgshop = new JLabel("Avg. Shopping: ");
        statinfo_lbl_avgshop.setBounds(36, 181, 200, 33);
        station_info_pass_LIST.add(statinfo_lbl_avgshop);

        JLabel statinfo_lbl_avgconn = new JLabel("Avg. Connection Speed: ");
        statinfo_lbl_avgconn.setBounds(262, 181, 353, 33);
        station_info_pass_LIST.add(statinfo_lbl_avgconn);

		JComboBox statinfo_drop_station = new JComboBox();
		statinfo_drop_station.setModel(new DefaultComboBoxModel(stations.toArray()));
		statinfo_drop_station.setBounds(36, 25, 359, 39);
		station_info_pass_LIST.add(statinfo_drop_station);

        JLabel statinfo_lbl_reviews = new JLabel("Reviews");
        statinfo_lbl_reviews.setBounds(36, 231, 115, 33);
        station_info_pass_LIST.add(statinfo_lbl_reviews);

        //LINE SUMMARY PASSENGER*****************************************************************************************************************************************************************************
        JButton home_pass_5 = new JButton("Home");
        home_pass_5.setBounds(0,0,80,20);
        home_pass_5.addActionListener(e -> {
            passenger_landing.setVisible(true);
            line_summary_LIST.setVisible(false);
        });
        line_summary_LIST.add(home_pass_5);

        JLabel linesum_lbl_stopcnt = new JLabel("//no stops");
        linesum_lbl_stopcnt.setBounds(433, 28, 171, 33);
        line_summary_LIST.add(linesum_lbl_stopcnt);

		JComboBox linesum_drop_station = new JComboBox();
		linesum_drop_station.setModel(new DefaultComboBoxModel(stations.toArray()));
		linesum_drop_station.setBounds(26, 28, 332, 39);
		line_summary_LIST.add(linesum_drop_station);

        //EDIT PROFILE PASSENGER*****************************************************************************************************************************************************************************
        JButton home_pass_6 = new JButton("Home");
        home_pass_6.setBounds(0,0,80,20);
        home_pass_6.addActionListener(e -> {
            passenger_landing.setVisible(true);
            edit_profile_pass.setVisible(false);
        });
        edit_profile_pass.add(home_pass_6);

        JLabel editprof_lbl_editprof = new JLabel("Edit Profile");
        editprof_lbl_editprof.setBounds(26, 28, 179, 33);
        edit_profile_pass.add(editprof_lbl_editprof);

        JTextField editprof_first = new JTextField();
        //editprof_first.setText(currentUser.getFirstName());                                           //INPUT USERS FIRST NAME
        editprof_first.setBounds(36, 89, 447, 39);
        edit_profile_pass.add(editprof_first);
        editprof_first.setColumns(10);

        JTextField editprof_m = new JTextField();
        editprof_m.setText("");                                                         //INPUT USERS MIDDLE INITIAL
        editprof_m.setBounds(528, 89, 58, 39);
        edit_profile_pass.add(editprof_m);
        editprof_m.setColumns(10);

        JTextField editprof_last = new JTextField();
        //editprof_last.setText(currentUser.getLastName());                                              //INPUT USERS LAST NAME
        editprof_last.setBounds(36, 145, 447, 39);
        edit_profile_pass.add(editprof_last);
        editprof_last.setColumns(10);

        JTextField editprof_email = new JTextField();
        //editprof_email.setText(currentUser.getPassengerEmail());                                                //INPUT USERS EMAIL
        editprof_email.setBounds(36, 212, 550, 39);
        edit_profile_pass.add(editprof_email);
        editprof_email.setColumns(10);

        JTextField editprof_userid = new JTextField();
        //editprof_userid.setText(currentUser.getId());                                             //INPUT USERS ID
        editprof_userid.setBounds(36, 279, 550, 39);
        edit_profile_pass.add(editprof_userid);
        editprof_userid.setColumns(10);

        JTextField editprof_pass = new JTextField();
        //editprof_pass.setText(currentUser.getPassword());                                              //INPUT USERS PASSWORD
        editprof_pass.setBounds(36, 346, 236, 39);
        edit_profile_pass.add(editprof_pass);
        editprof_pass.setColumns(10);

        JTextField editprof_passagain = new JTextField();
        editprof_passagain.setText("");                                 //INPUT USERS PASSWORD AGAIN
        editprof_passagain.setColumns(10);
        editprof_passagain.setBounds(350, 346, 236, 39);
        edit_profile_pass.add(editprof_passagain);

        
        passland_btn_editprof.addActionListener(e -> {
        	
            editprof_first.setText(currentUser.getFirstName());                                           //INPUT USERS FIRST NAME
            
            editprof_last.setText(currentUser.getLastName());                                              //INPUT USERS LAST NAME
            editprof_email.setText(currentUser.getPassengerEmail());                                                //INPUT USERS EMAIL
            editprof_userid.setText(currentUser.getId());                                             //INPUT USERS ID
            
            editprof_pass.setText(currentUser.getPassword());                                              //INPUT USERS PASSWORD
            

            edit_profile_pass.setVisible(true);
            passenger_landing.setVisible(false);
        });
        
        
        JButton editprof_btn_delete = new JButton("Delete");
        editprof_btn_delete.addActionListener(j -> {
           //delete user from database
        	userDao.deleteUser(currentUser.getId());
           login.setVisible(true);
           edit_profile_pass.setVisible(false);
        });
        editprof_btn_delete.setBounds(84, 413, 171, 41);
        edit_profile_pass.add(editprof_btn_delete);

        JButton editprof_btn_update = new JButton("Update");
        editprof_btn_update.addActionListener(h -> {
            if(editprof_first.getText().equals("First Name")) {
                requiredinfo_error.setVisible(true);
                edit_profile_pass.setVisible(false);
                exit_3.addActionListener(f -> {
                    edit_profile_pass.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            else if(editprof_first.getText().equals("Last Name")) {
                requiredinfo_error.setVisible(true);
                edit_profile_pass.setVisible(false);
                exit_3.addActionListener(f -> {
                    edit_profile_pass.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            else if(editprof_email.getText().equals("Email")) {        //CHECK FOR VALID EMAIL?????
                requiredinfo_error.setVisible(true);
                edit_profile_pass.setVisible(false);
                exit_3.addActionListener(f -> {
                    edit_profile_pass.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            else if(!editprof_pass.getText().equals(editprof_passagain.getText())) {
                passmatch_error.setVisible(true);
                edit_profile_pass.setVisible(false);
                exit_5.addActionListener(f -> {
                    edit_profile_pass.setVisible(true);
                    passmatch_error.setVisible(false);
                });
            }
            else if(editprof_pass.getText().length() < 8) {
                passlength_error.setVisible(true);
                edit_profile_pass.setVisible(false);
                exit_6.addActionListener(f -> {
                    edit_profile_pass.setVisible(true);
                    passlength_error.setVisible(false);
                });
            }
            else if(editprof_userid.getText().equals("User ID")) {
                requiredinfo_error.setVisible(true);
                edit_profile_pass.setVisible(false);
                exit_3.addActionListener(f -> {
                    edit_profile_pass.setVisible(true);
                    requiredinfo_error.setVisible(false);
                });
            }
            else if(userDao.getUser(editprof_userid.getText()) != null) {                                        //CHECK FOR USER ID UNIQUENESS
                uniqueness_error.setVisible(true);
                edit_profile_pass.setVisible(false);
                exit_2.addActionListener(f -> {
                    edit_profile_pass.setVisible(true);
                    uniqueness_error.setVisible(false);
                });
            }
            else {                                                  //CHECK IF VALUES CHANGED AND SET ACCORDINGLY
            	
            	currentUser.setId(editprof_userid.getText());
            	currentUser.setFirstName(editprof_first.getText());
            	currentUser.setLastName(editprof_last.getText());
            	currentUser.setMinit(editprof_m.getText());
            	currentUser.setPassengerEmail(editprof_email.getText());
            	currentUser.setPassword(editprof_pass.getText());

            	userDao.updateUser(currentUser);




                passenger_landing.setVisible(true);
                edit_profile_pass.setVisible(false);
            }
        });
        editprof_btn_update.setBounds(393, 413, 171, 41);
        edit_profile_pass.add(editprof_btn_update);

        //BUY CARD*******************************************************************************************************************************************************************************************
        JButton home_pass_7 = new JButton("Home");
        home_pass_7.setBounds(0,0,80,20);
        home_pass_7.addActionListener(e -> {
            passenger_landing.setVisible(true);
            buy_card.setVisible(false);
        });
        buy_card.add(home_pass_7);

        JLabel buy_lbl_buycard = new JLabel("Buy Card");
        buy_lbl_buycard.setBounds(26, 28, 115, 33);
        buy_card.add(buy_lbl_buycard);

        JButton buy_btn_tmes = new JButton("T-Mes");
        buy_btn_tmes.addActionListener(l -> {
            //ADD TICKET TO USER
            passenger_landing.setVisible(true);
            buy_card.setVisible(false);
        });
        buy_btn_tmes.setBounds(92, 129, 171, 41);
        buy_card.add(buy_btn_tmes);

        JButton buy_btn_fifty = new JButton("T-50/30");
        buy_btn_fifty.addActionListener(l -> {
            //ADD TICKET TO USER
            passenger_landing.setVisible(true);
            buy_card.setVisible(false);
        });
        buy_btn_fifty.setBounds(92, 275, 171, 41);
        buy_card.add(buy_btn_fifty);

        JButton buy_btn_ten = new JButton("T-10");
        buy_btn_ten.addActionListener(l -> {
            //ADD TICKET TO USER
            passenger_landing.setVisible(true);
            buy_card.setVisible(false);
        });
        buy_btn_ten.setBounds(350, 129, 171, 41);
        buy_card.add(buy_btn_ten);

        JButton buy_btn_jove = new JButton("T-jove");
        buy_btn_jove.addActionListener(l -> {
            //ADD TICKET TO USER
            passenger_landing.setVisible(true);
            buy_card.setVisible(false);
        });
        buy_btn_jove.setBounds(350, 275, 171, 41);
        buy_card.add(buy_btn_jove);

        //GO ON A TRIP***************************************************************************************************************************************************************************************
        JButton home_pass_8 = new JButton("Home");
        home_pass_8.setBounds(0,0,80,20);
        home_pass_8.addActionListener(e -> {
            passenger_landing.setVisible(true);
            go_trip.setVisible(false);
        });
        go_trip.add(home_pass_8);

        JLabel go_lbl_gotrip = new JLabel("Go On A Trip");
        go_lbl_gotrip.setBounds(26, 28, 225, 33);
        go_trip.add(go_lbl_gotrip);

        JLabel go_lbl_startstat = new JLabel("Start Station");
        go_lbl_startstat.setBounds(72, 116, 204, 33);
        go_trip.add(go_lbl_startstat);

		JComboBox go_drop_station = new JComboBox();
		go_drop_station.setModel(new DefaultComboBoxModel(stations.toArray()));
		go_drop_station.setBounds(287, 113, 235, 39);
		go_trip.add(go_drop_station);

        JLabel go_lbl_cardused = new JLabel("Card Used");
        go_lbl_cardused.setBounds(72, 243, 159, 33);
        go_trip.add(go_lbl_cardused);

        String[] cards= new String[100];
        cards[0]="Cards";
		JComboBox go_drop_cards = new JComboBox();
		go_drop_cards.setModel(new DefaultComboBoxModel(cards));
		go_drop_cards.setBounds(287, 240, 235, 39);
		go_trip.add(go_drop_cards);

        JButton go_btn_embark = new JButton("Embark");
        go_btn_embark.addActionListener(g -> {
            //CHECK FOR VALIDITIY
            //ADD TRIP TO USER
            passenger_landing.setVisible(true);
            go_trip.setVisible(false);
        });
        go_btn_embark.setBounds(203, 374, 171, 41);
        go_trip.add(go_btn_embark);

        //VIEW TRIPS*****************************************************************************************************************************************************************************************
        JButton home_pass_9 = new JButton("Home");
        home_pass_9.setBounds(0,0,80,20);
        home_pass_9.addActionListener(e -> {
            passenger_landing.setVisible(true);
            view_trips_LIST.setVisible(false);
        });
        view_trips_LIST.add(home_pass_9);

        JLabel trips_lbl_mytrips = new JLabel("My Trips");
        trips_lbl_mytrips.setBounds(26, 28, 115, 33);
        view_trips_LIST.add(trips_lbl_mytrips);

        //ADD LISTTTTT

        //UPDATE TRIP****************************************************************************************************************************************************************************************
        JButton home_pass_10 = new JButton("Home");
        home_pass_10.setBounds(0,0,80,20);
        home_pass_10.addActionListener(e -> {
            passenger_landing.setVisible(true);
            update_trip.setVisible(false);
        });
        update_trip.add(home_pass_10);

        JLabel updatetrip_lbl_updatetrip = new JLabel("Update Trip");
        updatetrip_lbl_updatetrip.setBounds(26, 28, 198, 33);
        update_trip.add(updatetrip_lbl_updatetrip);

        String start_station="Start";
        JLabel updatetrip_lbl_startstat = new JLabel("Start Station: "+start_station);
        updatetrip_lbl_startstat.setBounds(74, 118, 481, 33);
        update_trip.add(updatetrip_lbl_startstat);

        JLabel updatetrip_lbl_endstat = new JLabel("End Station");
        updatetrip_lbl_endstat.setBounds(300,206,70,39);
        update_trip.add(updatetrip_lbl_endstat);

		JComboBox updatetrip_drop_endstat = new JComboBox();
		updatetrip_drop_endstat.setModel(new DefaultComboBoxModel(stations.toArray()));
		updatetrip_drop_endstat.setBounds(74, 206, 248, 39);
		update_trip.add(updatetrip_drop_endstat);

		String card_used="GET CARD";
        JLabel updatetrip_lbl_cardused = new JLabel("Card Used: "+card_used);
        updatetrip_lbl_cardused.setBounds(74, 311, 505, 33);
        update_trip.add(updatetrip_lbl_cardused);

        JButton updatetrip_btn_update = new JButton("Update");
        updatetrip_btn_update.addActionListener(f -> {
            //ALTER VALUES IN DATABASE
            passenger_landing.setVisible(true);
            update_trip.setVisible(false);
        });
        updatetrip_btn_update.setBounds(223, 398, 171, 41);
        update_trip.add(updatetrip_btn_update);

        //ADMIN LANDING**************************************************************************************************************************************************************************************
        JLabel adland_lbl_welcome = new JLabel("Welcome "+currentUser.getFirstName() + " "+currentUser.getLastName());
        adland_lbl_welcome.setBounds(26, 28, 287, 33);
        admin_landing.add(adland_lbl_welcome);

        JLabel adland_lbl_admin = new JLabel("Admin");
        adland_lbl_admin.setBounds(500, 28, 115, 33);
        admin_landing.add(adland_lbl_admin);

        JButton adland_btn_viewtrips = new JButton("View Trips");
        adland_btn_viewtrips.addActionListener(i -> {
            view_trips_LIST.setVisible(true);
            admin_landing.setVisible(false);
        });
        adland_btn_viewtrips.setBounds(51, 109, 171, 41);
        admin_landing.add(adland_btn_viewtrips);

        JButton adland_btn_buycard = new JButton("Buy Card");
        adland_btn_buycard.addActionListener(f -> {
            buy_card.setVisible(true);
            admin_landing.setVisible(false);
        });
        adland_btn_buycard.setBounds(51, 207, 171, 41);
        admin_landing.add(adland_btn_buycard);

        JButton adland_btn_gotrip = new JButton("Go On Trip");
        adland_btn_gotrip.addActionListener(d -> {
            go_trip.setVisible(true);
            admin_landing.setVisible(false);
        });
        adland_btn_gotrip.setBounds(51, 304, 171, 41);
        admin_landing.add(adland_btn_gotrip);

        JButton adland_btn_revrev = new JButton("Review Passenger Reviews");
        adland_btn_revrev.addActionListener(d -> {
            review_admin_LIST.setVisible(true);
            admin_landing.setVisible(false);
        });
        adland_btn_revrev.setBounds(51, 398, 401, 41);
        admin_landing.add(adland_btn_revrev);

        JButton adland_btn_editprof = new JButton("Edit Profile");
        adland_btn_editprof.addActionListener(d -> {
            edit_profile_admin.setVisible(true);
            admin_landing.setVisible(false);
        });
        adland_btn_editprof.setBounds(366, 109, 171, 41);
        admin_landing.add(adland_btn_editprof);

        JButton adland_btn_addstat = new JButton("Add Station");
        adland_btn_addstat.addActionListener(a -> {
            add_station_LIST.setVisible(true);
            admin_landing.setVisible(false);
        });
        adland_btn_addstat.setBounds(366, 207, 171, 41);
        admin_landing.add(adland_btn_addstat);

        JButton adland_btn_addline = new JButton("Add Line");
        adland_btn_addline.addActionListener(g -> {
            add_line_LIST.setVisible(true);
            admin_landing.setVisible(false);
        });
        adland_btn_addline.setBounds(366, 304, 171, 41);
        admin_landing.add(adland_btn_addline);

        //REVIEW PASSENGER REVIEWS***************************************************************************************************************************************************************************
        JButton home_ad_1 = new JButton("Home");
        home_ad_1.setBounds(0,0,80,20);
        home_ad_1.addActionListener(e -> {
            passenger_landing.setVisible(true);
            review_admin_LIST.setVisible(false);
        });
        review_admin_LIST.add(home_ad_1);

        JLabel revadmin_lbl_pendingrevs = new JLabel("Pending Reviews");
        revadmin_lbl_pendingrevs.setBounds(26, 28, 374, 33);
        review_admin_LIST.add(revadmin_lbl_pendingrevs);

        //ADD LISTTTTTTTTTTTTTTTTTTT

        //EDIT PROFILE ADMIN*********************************************************************************************************************************************************************************
        JButton home_ad_2 = new JButton("Home");
        home_ad_2.setBounds(0,0,80,20);
        home_ad_2.addActionListener(e -> {
            passenger_landing.setVisible(true);
            edit_profile_admin.setVisible(false);
        });
        edit_profile_admin.add(home_ad_2);

        JLabel editprofad_lbl_editprof = new JLabel("Edit Profile");
        editprofad_lbl_editprof.setBounds(26, 28, 149, 33);
        edit_profile_admin.add(editprofad_lbl_editprof);

        JLabel editprofad_lbl_admin = new JLabel("Admin");
        editprofad_lbl_admin.setBounds(500, 28, 115, 33);
        edit_profile_admin.add(editprofad_lbl_admin);

        JTextField editprofad_first = new JTextField();
        editprofad_first.setText("First Name");
        editprofad_first.setBounds(26, 114, 402, 39);
        edit_profile_admin.add(editprofad_first);
        editprofad_first.setColumns(10);

        JTextField editprofad_m = new JTextField();
        editprofad_m.setText("M");
        editprofad_m.setBounds(479, 114, 82, 39);
        edit_profile_admin.add(editprofad_m);
        editprofad_m.setColumns(10);

        JTextField editprofad_last = new JTextField();
        editprofad_last.setText("Last Name");
        editprofad_last.setBounds(26, 181, 402, 39);
        edit_profile_admin.add(editprofad_last);
        editprofad_last.setColumns(10);

        JTextField editprofad_userid = new JTextField();
        editprofad_userid.setText("User ID");
        editprofad_userid.setBounds(26, 272, 535, 39);
        edit_profile_admin.add(editprofad_userid);
        editprofad_userid.setColumns(10);

        JButton editprofad_btn_delete = new JButton("Delete");
        editprofad_btn_delete.setBounds(63, 409, 171, 41);
        edit_profile_admin.add(editprofad_btn_delete);

        JButton editprofad_btn_update = new JButton("Update");
        editprofad_btn_update.setBounds(345, 409, 171, 41);
        edit_profile_admin.add(editprofad_btn_update);

        JTextField editprofad_pass = new JTextField();
        editprofad_pass.setText("Password");
        editprofad_pass.setBounds(26, 339, 236, 39);
        edit_profile_admin.add(editprofad_pass);
        editprofad_pass.setColumns(10);

        JTextField editprofad_passagain = new JTextField();
        editprofad_passagain.setText("Password (again)");
        editprofad_passagain.setBounds(309, 342, 236, 39);
        edit_profile_admin.add(editprofad_passagain);
        editprofad_passagain.setColumns(10);

        adland_btn_editprof.addActionListener(e -> {
            editprofad_first.setText(currentUser.getFirstName());
            editprofad_last.setText(currentUser.getLastName());
            editprofad_userid.setText(currentUser.getId());
            editprofad_pass.setText(currentUser.getPassword());
            edit_profile_admin.setVisible(true);
            admin_landing.setVisible(false);
        });

        //ADD STATION****************************************************************************************************************************************************************************************
        JButton home_ad_3 = new JButton("Home");
        home_ad_3.setBounds(0,0,80,20);
        home_ad_3.addActionListener(e -> {
            passenger_landing.setVisible(true);
            add_station_LIST.setVisible(false);
        });
        add_station_LIST.add(home_ad_3);

        JLabel addstat_lbl_addstat = new JLabel("Add Station");
        addstat_lbl_addstat.setBounds(26, 28, 219, 33);
        add_station_LIST.add(addstat_lbl_addstat);

        JTextField addstat_statname = new JTextField();
        addstat_statname.setText("Station Name");
        addstat_statname.setBounds(337, 25, 253, 39);
        add_station_LIST.add(addstat_statname);
        addstat_statname.setColumns(10);

        JTextField addstat_address = new JTextField();
        addstat_address.setText("Street Address");
        addstat_address.setBounds(26, 89, 563, 39);
        add_station_LIST.add(addstat_address);
        addstat_address.setColumns(10);

        JTextField addstat_city = new JTextField();
        addstat_city.setText("City");
        addstat_city.setBounds(26, 141, 123, 39);
        add_station_LIST.add(addstat_city);
        addstat_city.setColumns(10);

        JTextField addstat_state = new JTextField();
        addstat_state.setText("State/Province");
        addstat_state.setBounds(162, 141, 192, 39);
        add_station_LIST.add(addstat_state);
        addstat_state.setColumns(10);

        JTextField addstat_zip = new JTextField();
        addstat_zip.setText("Postal/Zip Code");
        addstat_zip.setBounds(379, 141, 210, 39);
        add_station_LIST.add(addstat_zip);
        addstat_zip.setColumns(10);

		JComboBox addstat_drop_line = new JComboBox();
		addstat_drop_line.setModel(new DefaultComboBoxModel(lines.toArray()));
		addstat_drop_line.setBounds(26, 208, 236, 39);
		add_station_LIST.add(addstat_drop_line);

        JTextField addstat_order = new JTextField();
        addstat_order.setText("Order");
        addstat_order.setBounds(288, 208, 90, 39);
        add_station_LIST.add(addstat_order);
        addstat_order.setColumns(10);

        JButton addstat_btn_addline = new JButton("Add Line");
        addstat_btn_addline.setBounds(419, 208, 171, 41);
        add_station_LIST.add(addstat_btn_addline);

        JButton addstat_btn_addstat = new JButton("Add Station");
        addstat_btn_addstat.setBounds(238, 414, 171, 41);
        add_station_LIST.add(addstat_btn_addstat);

        //ADD LINE*******************************************************************************************************************************************************************************************
        JButton home_ad_4 = new JButton("Home");
        home_ad_4.setBounds(0,0,80,20);
        home_ad_4.addActionListener(e -> {
            admin_landing.setVisible(true);
            add_line_LIST.setVisible(false);
        });
        add_line_LIST.add(home_ad_4);

        JLabel addline_lbl_addline = new JLabel("Add Line");
        addline_lbl_addline.setBounds(26, 28, 115, 33);
        add_line_LIST.add(addline_lbl_addline);

        JTextField addline_linename = new JTextField();
        addline_linename.setText("Line Name");
        addline_linename.setBounds(365, 25, 236, 39);
        add_line_LIST.add(addline_linename);
        addline_linename.setColumns(10);

		JComboBox addline_drop_station = new JComboBox();
		addline_drop_station.setModel(new DefaultComboBoxModel(stations.toArray()));
		addline_drop_station.setBounds(26, 103, 193, 39);
		add_line_LIST.add(addline_drop_station);

        JTextField addline_order = new JTextField();
        addline_order.setText("Order");
        addline_order.setBounds(249, 103, 89, 39);
        add_line_LIST.add(addline_order);
        addline_order.setColumns(10);

        JButton addline_btn_addstat = new JButton("Add Station");
        addline_btn_addstat.setBounds(430, 102, 171, 41);
        add_line_LIST.add(addline_btn_addstat);

        JButton addline_btn_addline = new JButton("Add Line");
        addline_btn_addline.setBounds(430, 398, 171, 41);
        add_line_LIST.add(addline_btn_addline);

        //String[] columns={"Station","Order"};
        //Object[][] data = {{"yeet"},{"yeet"}};

        //LINE SUMMARY ADMIN*********************************************************************************************************************************************************************************
        JButton home_ad_5 = new JButton("Home");
        home_ad_5.setBounds(0,0,80,20);
        home_ad_5.addActionListener(e -> {
            admin_landing.setVisible(true);
            line_summary_admin_LIST.setVisible(false);
        });
        line_summary_admin_LIST.add(home_ad_5);

        JLabel linesumad_lbl_stops = new JLabel("No. stops//");//ADD NUMBER OF STOPS
        linesumad_lbl_stops.setBounds(443, 28, 172, 33);
        line_summary_admin_LIST.add(linesumad_lbl_stops);

        JButton linesumad_btn_update = new JButton("Update");
        linesumad_btn_update.addActionListener(f -> {
            admin_landing.setVisible(true);
            line_summary_admin_LIST.setVisible(false);
        });
        linesumad_btn_update.setBounds(430, 398, 171, 41);
        line_summary_admin_LIST.add(linesumad_btn_update);

		JComboBox linesumad_drop_linename = new JComboBox();
		linesumad_drop_linename.setModel(new DefaultComboBoxModel(lines.toArray()));
		linesumad_drop_linename.setBounds(26, 25, 370, 39);
		line_summary_admin_LIST.add(linesumad_drop_linename);

        //STATION INFO ADMIN*********************************************************************************************************************************************************************************
        JButton home_ad_6 = new JButton("Home");
        home_ad_6.setBounds(0,0,80,20);
        home_ad_6.addActionListener(e -> {
            admin_landing.setVisible(true);
            station_info_admin_LIST.setVisible(false);
        });
        station_info_admin_LIST.add(home_ad_6);

        JComboBox statinfoad_drop_statname = new JComboBox();
		statinfoad_drop_statname.setModel(new DefaultComboBoxModel(stations.toArray()));
		statinfoad_drop_statname.setBounds(36, 28, 266, 39);
		station_info_admin_LIST.add(statinfoad_drop_statname);

		JComboBox statinfoad_drop_status = new JComboBox();
		statinfoad_drop_status.setModel(new DefaultComboBoxModel(new String[] {"Status"}));//ADD STATUS, WHERE SECOND ARRAY ENTRY IS CURRENT STATUS OF STATION
		statinfoad_drop_status.setBounds(371, 28, 225, 39);
		station_info_admin_LIST.add(statinfoad_drop_status);

        JLabel statinfoad_lbl_address = new JLabel("Address: ");//ADD ADDRESS
        statinfoad_lbl_address.setBounds(36, 89, 560, 33);
        station_info_admin_LIST.add(statinfoad_lbl_address);

        JLabel statinfoad_lbl_lines = new JLabel("Lines: ");//ADD LINES
        statinfoad_lbl_lines.setBounds(36, 131, 560, 33);
        station_info_admin_LIST.add(statinfoad_lbl_lines);

        JLabel statinfoad_lbl_avgshop = new JLabel("Avg Shopping: ");//ADD SHOPPING RATING
        statinfoad_lbl_avgshop.setBounds(36, 175, 225, 33);
        station_info_admin_LIST.add(statinfoad_lbl_avgshop);

        JLabel statinfoad_lbl_avgconn = new JLabel("Avg. Connection Speed: ");//ADD CONNECTION SPEED
        statinfoad_lbl_avgconn.setBounds(258, 175, 332, 33);
        station_info_admin_LIST.add(statinfoad_lbl_avgconn);

        JLabel statinfoad_lvl_reviews = new JLabel("Reviews");
        statinfoad_lvl_reviews.setBounds(36, 224, 115, 33);
        station_info_admin_LIST.add(statinfoad_lvl_reviews);

        //ADD LIST
    }
    
    //JTable viewrev_list = new JTable();
    public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {
    	 
        public SimpleHeaderRenderer() {
            // code to initilize the GUI...
        }
     
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
     
            // code to customize the GUI based on the parameters
     
            return this;
        }
     
    }

	class ReviewTableModel extends AbstractTableModel {
		private List<Review> reviews;
		private String[] columnHeaders;
		public ReviewTableModel(List<Review> reviews, String[] columnHeaders) {
			this.reviews= reviews;
			this.columnHeaders = columnHeaders;
		}
		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return reviews.size();
		}

		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnHeaders.length;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			// TODO Auto-generated method stub
			Review r = reviews.get(rowIndex);
			
			//columnIndex=8;
			switch (columnIndex) {
				case 0: return r.getRid();
				case 1: return r.getStationName();
				case 2: return r.getShopping();
				case 3: return r.getConnectionSpeed();
				case 4: return r.getComment();
				case 5: return r.getApprovalStatus();
//				case 6: return r.getRid();
//				case 7: return r.getShopping();
//				case 8: return r.getStationName();
				//case 9: return r.getApprovalStatus();
			}
			
			//columnHeaders.get
			return null;
		}
		
		public Class getColumnClass(int c) {
			if (getValueAt(0,c)==null) {
				return String.class;
			}
	        return getValueAt(0, c).getClass();
	    }
		
	}
}
