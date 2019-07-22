package edu.gt.tmb.gui;

import edu.gt.tmb.dao.*;
import edu.gt.tmb.entity.User;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

public class GUI2 extends Application {

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

    private Scene scene;

    public void start(Stage stage) {
        initializeDao();
        stage.setScene(login());
        stage.setTitle("Login");
        stage.show();
    }

    private void initializeDao() {
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
    }

    private Scene login() {
        Label id_label = new Label();
        id_label.setText("ID");
        TextField id_text = new TextField();

        Label password_label = new Label();
        password_label.setText("Password");
        TextField password_text = new TextField();

        Label error_label = new Label();

        Button register_button = new Button();
        register_button.setText("Register");
        register_button.setOnAction(e -> {
            Stage stage = (Stage) register_button.getScene().getWindow();
            stage.setScene(register());
            stage.setTitle("Register");
        });

        Button login_button = new Button();
        login_button.setText("Login");
        login_button.setOnAction(e -> {
            if (userDao.checkPassUser(password_text.getText(), id_text.getText())) {
                currentUser = userDao.getUser(id_text.getText());
                Stage stage = (Stage) login_button.getScene().getWindow();
                if (userDao.isUserAdmin(currentUser)) {
                    // go to admin landing
                } else {
                    stage.setScene(passengerLanding());
                    stage.setTitle("Welcome " + currentUser.getFirstName()
                            + " " + currentUser.getLastName());
                }
            } else {
                error_label.setText("Login credentials invalid.");
            }
        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(register_button, login_button);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(id_label, id_text,
                password_label, password_text,
                error_label, buttons);

        scene = new Scene(vbox);

        return scene;
    }

    private Scene register() {
        Label first_label = new Label();
        first_label.setText("First Name:");
        TextField first_text = new TextField();

        Label mi_label = new Label();
        mi_label.setText("Middle Initial:");
        TextField mi_text = new TextField();

        Label last_label = new Label();
        last_label.setText("Last Name:");
        TextField last_text = new TextField();

        Label email_label = new Label();
        email_label.setText("Email:");
        TextField email_text = new TextField();

        Label userid_label = new Label();
        userid_label.setText("User ID (must be unique):");
        TextField userid_text = new TextField();

        Label password_label = new Label();
        password_label.setText("Password:");
        TextField password_text = new TextField();

        Label conf_password_label = new Label();
        conf_password_label.setText("Confirm password:");
        TextField conf_password_text = new TextField();

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                first_label, first_text,
                mi_label, mi_text,
                last_label, last_text,
                email_label, email_text,
                userid_label, userid_text,
                password_label, password_text,
                conf_password_label, conf_password_text);
        return new Scene(vbox);
    }

    private Scene passengerLanding() {
        Button leave_review = new Button();
        leave_review.setText("Leave Review");

        Button view_reviews = new Button();
        view_reviews.setText("View Reviews");

        Button view_trips = new Button();
        view_trips.setText("View Trips");

        Button buy_card = new Button();
        buy_card.setText("Buy Card");

        Button go_on_trip = new Button();
        go_on_trip.setText("Go On Trip");

        Button edit_profile = new Button();
        edit_profile.setText("Edit Profile");

        VBox left = new VBox();
        left.getChildren().addAll(leave_review, view_reviews, view_trips);

        VBox right = new VBox();
        right.getChildren().addAll(buy_card, go_on_trip, edit_profile);

        HBox hbox = new HBox();
        hbox.getChildren().addAll(left, right);

        return new Scene(hbox);
    }

    private void adminLanding() {
    }
}
