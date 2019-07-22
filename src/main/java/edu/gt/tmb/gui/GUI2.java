package edu.gt.tmb.gui;

import edu.gt.tmb.dao.*;
import edu.gt.tmb.entity.Line;
import edu.gt.tmb.entity.Review;
import edu.gt.tmb.entity.Station;
import edu.gt.tmb.entity.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sun.management.snmp.util.SnmpTableCache;

import javax.xml.soap.Text;
import java.util.List;

public class GUI2 extends Application {

    private UserDao userDao;
    private StationDao stationDao;
    private LineDao lineDao;
    private ReviewDao reviewDao;
    private AdminAddLineDao adminAddLineDao;
    private AdminAddStationDao adminAddStationDao;
    private CardDao cardDao;
    private StationOnLineDao stationOnLineDao;
    private TripDao tripDao;

    private User currentUser;
    private Review selectedReview;
    private Station selectedStation;

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

        return new Scene(vbox);
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

        Label error = new Label();

        Button register = new Button();
        register.setText("Register");
        register.setOnAction(e -> {
            if (first_text.getText().equals("") ||
                    last_text.getText().equals("") ||
                    email_text.getText().equals("") ||
                    userid_text.getText().equals("") ||
                    password_text.getText().equals("")) {
                error.setText("All fields are required except the middle initial.");
            } else if (!password_text.getText().equals(conf_password_text.getText())) {
                error.setText("Passwords do not match.");
            } else if (password_text.getText().length() < 8) {
                error.setText("Passwords must be at least 8 characters.");
            } else {
                User user = new User(userid_text.getText(),
                        first_text.getText(), mi_text.getText(), last_text.getText(),
                        password_text.getText());
                user.setPassengerEmail(email_text.getText());
                if (userDao.addUser(user)) {
                    Stage stage = (Stage) register.getScene().getWindow();
                    stage.setScene(passengerLanding());
                    stage.setTitle("Welcome " + currentUser.getFirstName()
                            + " " + currentUser.getLastName());
                } else {
                    error.setText("User ID must be unique.");
                }
            }
        });

        VBox vbox = new VBox();
        vbox.getChildren().addAll(
                first_label, first_text,
                mi_label, mi_text,
                last_label, last_text,
                email_label, email_text,
                userid_label, userid_text,
                password_label, password_text,
                conf_password_label, conf_password_text,
                error, register);
        return new Scene(vbox);
    }

    private Scene passengerLanding() {
        Button leave_review = new Button();
        leave_review.setText("Leave Review");
        leave_review.setOnAction(e -> {
            Stage stage = (Stage) leave_review.getScene().getWindow();
            stage.setScene(leaveReview());
            stage.setTitle("Leave a Review");
        });

        Button view_reviews = new Button();
        view_reviews.setText("View Reviews");
        view_reviews.setOnAction(e -> {
            Stage stage = (Stage) view_reviews.getScene().getWindow();
            stage.setScene(viewReviews());
            stage.setTitle(currentUser.getFirstName() + " " +
                    currentUser.getLastName() + "'s Reviews");
        });

        Button view_trips = new Button();
        view_trips.setText("View Trips");

        Button buy_card = new Button();
        buy_card.setText("Buy Card");

        Button go_on_trip = new Button();
        go_on_trip.setText("Go On Trip");

        Button edit_profile = new Button();
        edit_profile.setText("Edit Profile");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(leave_review, view_reviews, view_trips,
                buy_card, go_on_trip, edit_profile);
        vBox.setAlignment(Pos.CENTER);

        return new Scene(vBox);
    }

    private Scene leaveReview() {
        List<Station> stationList = stationDao.orderStation();
        ObservableList<Station> stations;
        if (stationList != null) {
            stations = FXCollections.observableList(stationList);
            System.out.println("station list isnt null!!");
        } else stations = null;
        ComboBox<Station> comboBox = new ComboBox<>(stations);

        Label shopping_label = new Label();
        shopping_label.setText("Shopping (1 - 5):");
        TextField shopping_text = new TextField();

        Label connection_label = new Label();
        connection_label.setText("Connection Speed (1 - 5):");
        TextField connection_text = new TextField();

        Label comment_label = new Label();
        comment_label.setText("Comment:");
        TextField comment_text = new TextField();

        Label error = new Label();

        Button submit = new Button();
        submit.setText("Submit Review");
        submit.setOnAction(e -> {
            try {
                int shopping = Integer.parseInt(shopping_text.getText());
                int connection = Integer.parseInt(connection_text.getText());
                Station station = comboBox.getValue();
                if (station == null) {
                    error.setText("Please select a station.");
                } else {
                    Review review = new Review(station.getName(),
                            shopping, connection,
                            comment_text.getText(), currentUser.getId());
                    reviewDao.addReview(review);
                    Stage stage = (Stage) submit.getScene().getWindow();
                    stage.setScene(passengerLanding());
                    stage.setTitle("Welcome " + currentUser.getFirstName()
                            + " " + currentUser.getLastName());
                }
            } catch (NumberFormatException n) {
                error.setText("Please ensure that valid ratings have been entered");
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(comboBox,
                shopping_label, shopping_text,
                connection_label, connection_text,
                comment_label, comment_text,
                error, submit);

        return new Scene(vBox);
    }

    private Scene viewReviews() {
        List<Review> reviewList = reviewDao.getReviews(currentUser.getId());
        ObservableList<Review> reviews;
        if (reviewList != null) {
            reviews = FXCollections.observableList(reviewList);
        } else reviews = null;

        TableView table = new TableView();
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(
                new PropertyValueFactory<Review, Integer>("rid"));
        idCol.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                TableCell cell = new TableCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            setText(item.toString());
                        }
                    }
                };
                cell.setOnMouseClicked(e -> {
                    if(!cell.isEmpty()) {
                        int id = (Integer) cell.getItem();
                        selectedReview = reviewDao.getReviewName(id, currentUser.getId()).get(0);
                        Stage stage = (Stage) cell.getScene().getWindow();
                        stage.setScene(editReview());
                        stage.setTitle("Edit Review: " + selectedReview.getStationName() +
                                "(Status: " + selectedReview.getApprovalStatus() + ")");
                    }
                });
                return cell;
            }
        });

        TableColumn stationCol = new TableColumn("Station");
        stationCol.setCellValueFactory(
                new PropertyValueFactory<Review, String>("stationName"));

        TableColumn shoppingCol = new TableColumn("Shopping");
        shoppingCol.setCellValueFactory(
                new PropertyValueFactory<Review, Integer>("shopping"));

        TableColumn connectionCol = new TableColumn("Connection\nSpeed");
        connectionCol.setCellValueFactory(
                new PropertyValueFactory<Review, Integer>("connectionSpeed"));

        TableColumn commentCol = new TableColumn("Comment");
        commentCol.setCellValueFactory(
                new PropertyValueFactory<Review, String>("comment"));
        commentCol.setSortable(false);

        TableColumn statusCol = new TableColumn("Approval\nStatus");
        statusCol.setCellValueFactory(
                new PropertyValueFactory<Review, String>("approvalStatus"));

        table.setItems(reviews);
        table.getColumns().addAll(idCol, stationCol, shoppingCol,
                connectionCol, commentCol, statusCol);

        return new Scene(table);
    }

    private Scene editReview() {
        Label id_label = new Label();
        id_label.setText("ID: " + selectedReview.getRid());

        Label shopping_label = new Label();
        shopping_label.setText("Shopping (1 - 5):");
        TextField shopping_text = new TextField();

        Label connection_label = new Label();
        connection_label.setText("Connection Speed (1 - 5):");
        TextField connection_text = new TextField();

        Label comment_label = new Label();
        comment_label.setText("Comment:");
        TextField comment_text = new TextField();

        Label error = new Label();

        Button delete = new Button();
        delete.setText("Delete Review");

        Button save = new Button();
        save.setText("Save Review");
        save.setOnAction(e -> {
            try {
                int shopping = Integer.parseInt(shopping_text.getText());
                int connection = Integer.parseInt(connection_text.getText());
                // update review here; set status back to pending
            } catch (NumberFormatException n) {
                error.setText("Please ensure that valid ratings have been entered");
            }
        });

        HBox buttons = new HBox();
        buttons.getChildren().addAll(delete, save);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(id_label,
                shopping_label, shopping_text,
                connection_label, connection_text,
                comment_label, comment_text,
                error, buttons);

        return new Scene(vBox);
    }

    private Scene stationInfo() {
        Label address = new Label();
        address.setText("Address: " + selectedStation.getAddress());

        Label lines = new Label();
        lines.setText("Lines:");

        Label shopping = new Label();
        shopping.setText("Average Shopping: ");

        Label connection = new Label();
        connection.setText("Average Connection Speed: ");

        Label reviews_label = new Label();
        reviews_label.setText("REVIEWS");

        TableView table = new TableView();
        TableColumn userCol = new TableColumn("User");
        userCol.setCellValueFactory(
                new PropertyValueFactory<Review, String>("user"));
        userCol.setSortable(false);
        TableColumn shoppingCol = new TableColumn("Shopping");
        shoppingCol.setCellValueFactory(
                new PropertyValueFactory<Review, Integer>("shopping"));
        shoppingCol.setSortable(false);
        TableColumn connectionCol = new TableColumn("Connection\nSpeed");
        connectionCol.setCellValueFactory(
                new PropertyValueFactory<Review, Integer>("connection"));
        connectionCol.setSortable(false);
        TableColumn commentCol = new TableColumn("Comment");
        commentCol.setCellValueFactory(
                new PropertyValueFactory<Review, String>("comment"));
        commentCol.setSortable(false);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(address, lines,
                shopping, connection,
                reviews_label, table);

        return new Scene(vBox);
    }

    private void adminLanding() {
    }
}
