package edu.gt.tmb.gui;

import com.mysql.cj.xdevapi.Table;
import edu.gt.tmb.dao.*;
import edu.gt.tmb.entity.*;
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
import java.sql.Timestamp;
import java.util.Date;
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
    private Line selectedLine;
    private Trip currentTrip;

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
        buy_card.setOnAction(e -> {
            Stage stage = (Stage) buy_card.getScene().getWindow();
            stage.setScene(buyCard());
            stage.setTitle("Buy Card");
        });

        Button go_on_trip = new Button();
        go_on_trip.setText("Go On Trip");

        Button edit_profile = new Button();
        edit_profile.setText("Edit Profile");
        edit_profile.setOnAction(e -> {
            Stage stage = (Stage) edit_profile.getScene().getWindow();
            stage.setScene(editProfile());
            stage.setTitle("Edit Profile");
        });

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
        List<Review> reviewList =
                reviewDao.getReviews(currentUser.getId());
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
        stationCol.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                        String station = (String) cell.getItem();
                        selectedStation = stationDao.getStation(station);
                        Stage stage = (Stage) cell.getScene().getWindow();
                        stage.setScene(stationInfo());
                        stage.setTitle(station + "(Status: " + selectedStation.getStatus() + ")");
                    }
                });
                return cell;
            }
        });

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

        Label lines_label = new Label();
        lines_label.setText("Lines:");

        ListView lines_list = new ListView();
        lines_list.setPrefHeight(80);

        List<StationOnLine> stationList = stationOnLineDao.getStationLineName(selectedStation.getName());
        ObservableList<StationOnLine> stations = FXCollections.observableList(stationList);

        lines_list.setItems(stations);

        lines_list.setCellFactory(new Callback<ListView, ListCell>() {
            @Override
            public ListCell call(ListView param) {
                ListCell cell = new ListCell() {
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item != null) {
                            setText(((StationOnLine) item).getLineName());
                        }
                    }
                };
                cell.setOnMouseClicked(e -> {
                    if (!cell.isEmpty()) {
                        String name = ((StationOnLine) cell.getItem()).getLineName();
                        selectedLine = lineDao.getLine(name).get(0);
                        Stage stage = (Stage) cell.getScene().getWindow();
                        stage.setScene(lineSummary());
                        // set stage name here???
                    }
                });
                return cell;
            }
        });

        Label shopping = new Label();
        shopping.setText("Average Shopping: " +
                reviewDao.reviewAverage(
                        "approved", selectedStation.getName(),
                        "shopping"
                ));

        Label connection = new Label();
        connection.setText("Average Connection Speed: " +
                reviewDao.reviewAverage(
                        "approved", selectedStation.getName(),
                        "connection_speed"
                ));

        Label reviews_label = new Label();
        reviews_label.setText("\nREVIEWS");

        List<Review> reviewList =
                reviewDao.getStationReviews(selectedStation.getName());
        ObservableList<Review> reviews =
                FXCollections.observableList(reviewList);

        TableView reviews_table = new TableView();
        TableColumn userCol = new TableColumn("User");
        userCol.setCellValueFactory(
                new PropertyValueFactory<Review, String>("passengerId"));
        userCol.setSortable(false);

        TableColumn shoppingCol = new TableColumn("Shopping");
        shoppingCol.setCellValueFactory(
                new PropertyValueFactory<Review, Integer>("shopping"));
        shoppingCol.setSortable(false);

        TableColumn connectionCol = new TableColumn("Connection\nSpeed");
        connectionCol.setCellValueFactory(
                new PropertyValueFactory<Review, Integer>("connectionSpeed"));
        connectionCol.setSortable(false);

        TableColumn commentCol = new TableColumn("Comment");
        commentCol.setCellValueFactory(
                new PropertyValueFactory<Review, String>("comment"));
        commentCol.setSortable(false);

        reviews_table.setItems(reviews);
        reviews_table.getColumns().addAll(userCol, shoppingCol,
                connectionCol, commentCol);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(address, lines_label, lines_list,
                shopping, connection,
                reviews_label, reviews_table);

        return new Scene(vBox);
    }

    private Scene lineSummary() {
        TableView table = new TableView();
        List<StationOnLine> stationList =
                stationOnLineDao.getLineInfo(selectedLine.getName());
        ObservableList<StationOnLine> stations =
                FXCollections.observableList(stationList);
        table.setItems(stations);

        TableColumn stationCol = new TableColumn("Station");
        stationCol.setCellValueFactory(
                new PropertyValueFactory<StationOnLine, String>("stationName")
        );
        stationCol.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                    if (!cell.isEmpty()) {
                        String name = (String) cell.getItem();
                        selectedStation = stationDao.getStation(name);
                        Stage stage = (Stage) cell.getScene().getWindow();
                        stage.setScene(stationInfo());
                        stage.setTitle(name +
                                "(Status: " + selectedStation.getStatus() + ")");
                    }
                });
                return cell;
            }
        });

        TableColumn orderCol = new TableColumn("Order");
        orderCol.setCellValueFactory(
                new PropertyValueFactory<StationOnLine, Integer>("orderNumber")
        );

        table.getColumns().addAll(stationCol, orderCol);

        return new Scene(table);
    }

    private Scene editProfile() {
        Label first_label = new Label();
        first_label.setText("First Name:");
        TextField first_text = new TextField();
        first_text.setText(currentUser.getFirstName());

        Label mi_label = new Label();
        mi_label.setText("Middle Initial:");
        TextField mi_text = new TextField();
        mi_text.setText(currentUser.getMinit());

        Label last_label = new Label();
        last_label.setText("Last Name:");
        TextField last_text = new TextField();
        last_text.setText(currentUser.getLastName());

        Label email_label = new Label();
        email_label.setText("Email:");
        TextField email_text = new TextField();
        email_text.setText(currentUser.getPassengerEmail());

        Label userid_label = new Label();
        userid_label.setText("User ID");
        TextField userid_text = new TextField();
        userid_text.setText(currentUser.getId());

        Label password_label = new Label();
        password_label.setText("Password:");
        TextField password_text = new TextField();
        password_text.setText(currentUser.getPassword());

        Label conf_password_label = new Label();
        conf_password_label.setText("Confirm password:");
        TextField conf_password_text = new TextField();
        conf_password_text.setText(currentUser.getPassword());

        Label error = new Label();

        Button delete = new Button();
        delete.setText("Delete");
        delete.setOnAction(e -> {
            if (userDao.deleteUser(currentUser.getId())) {
                Stage stage = (Stage) delete.getScene().getWindow();
                stage.setScene(login());
                stage.setTitle("Login");
            } else {
                error.setText("Failed to delete user.");
            }
        });

        Button update = new Button();
        update.setText("Update");
        update.setOnAction(e -> {
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
                currentUser = new User(userid_text.getText(),
                        first_text.getText(), mi_text.getText(),
                        last_text.getText(),
                        password_text.getText());
                currentUser.setPassengerEmail(email_text.getText());
                if (userDao.updateUser(currentUser)) {
                    Stage stage = (Stage) update.getScene().getWindow();
                    stage.setScene(passengerLanding());
                    stage.setTitle("Welcome " + currentUser.getFirstName()
                            + " " + currentUser.getLastName());
                } else {
                    error.setText("User ID must be unique.");
                }
            }
        });
        HBox buttons = new HBox();
        buttons.getChildren().addAll(delete, update);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                first_label, first_text,
                mi_label, mi_text,
                last_label, last_text,
                email_label, email_text,
                userid_label, userid_text,
                password_label, password_text,
                conf_password_label, conf_password_text,
                error, buttons);

        return new Scene(vBox);
    }

    private Scene buyCard() {
        Card card = new Card();
        card.setUserId(currentUser.getId());

        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());

        Button tmes = new Button();
        tmes.setText("T-mes");
        tmes.setOnAction(e -> {
            //card.setExpirationDate();
        });

        Button t10 = new Button();
        t10.setText("T-10");

        Button t5030 = new Button();
        t5030.setText("T-50/30");

        Button tjove = new Button();
        tjove.setText("T-jove");

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(tmes, t10, t5030, tjove);

        return new Scene(vBox);
    }

    private Scene goOnTrip() {
        Label start_label = new Label();
        start_label.setText("Start Station");

        ObservableList<Station> stationObservableList =
                FXCollections.observableList(stationDao.orderStation());
        ComboBox<Station> stations = new ComboBox<>(stationObservableList);

        Label card_label = new Label();
        card_label.setText("Card Used");

        //ObservableList<Card> cards = FXCollections.observableList(cardDao.getValidCard())
        ComboBox<Card> cards = new ComboBox<>();

        Button embark = new Button();
        embark.setText("Embark");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(start_label, stations,
                card_label, cards, embark);

        return new Scene(vBox);
    }

    private Scene viewTrips() {
        List<Trip> tripList = tripDao.getAllFromID(currentUser.getId());
        ObservableList<Trip> trips = FXCollections.observableList(tripList);
        TableView tableView = new TableView();

        TableColumn startCol = new TableColumn("Start DateTime");
        startCol.setCellValueFactory(
                new PropertyValueFactory<Trip, Timestamp>("startDateTime")
        );
        startCol.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                    //update trip
                });
                return cell;
            }
        });

        TableColumn endCol = new TableColumn("End DateTime");
        endCol.setCellValueFactory(
                new PropertyValueFactory<Trip, Timestamp>("endDateTime")
        );

        TableColumn cardCol = new TableColumn("Card Used");
        cardCol.setCellValueFactory(
                new PropertyValueFactory<Trip, String>("cardType")
        );

        TableColumn fromCol = new TableColumn("From");
        fromCol.setCellValueFactory(
                new PropertyValueFactory<Trip, String>("fromStationName")
        );
        fromCol.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                        String station = (String) cell.getItem();
                        selectedStation = stationDao.getStation(station);
                        Stage stage = (Stage) cell.getScene().getWindow();
                        stage.setScene(stationInfo());
                        stage.setTitle(station + " (Status: " + selectedStation.getStatus() + ")");
                    }
                });
                return cell;
            }
        });

        TableColumn toCol = new TableColumn("To");
        toCol.setCellValueFactory(
                new PropertyValueFactory<Trip, String>("toStationName")
        );
        toCol.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                        String station = (String) cell.getItem();
                        selectedStation = stationDao.getStation(station);
                        Stage stage = (Stage) cell.getScene().getWindow();
                        stage.setScene(stationInfo());
                        stage.setTitle(station + " (Status: " + selectedStation.getStatus() + ")");
                    }
                });
                return cell;
            }
        });

        tableView.getColumns().addAll(startCol, endCol, cardCol, fromCol, toCol);

        return new Scene(tableView);
    }

    private Scene updateTrip() {
        Label start = new Label();
        start.setText("Start Station:\n" + currentTrip.getFromStationName());

        Label end_label = new Label();
        end_label.setText("End Station:");

        List<Station> stationList = stationDao.orderStation();
        ObservableList<Station> stations;
        if (stationList != null) {
            stations = FXCollections.observableList(stationList);
        } else stations = null;
        ComboBox<Station> comboBox = new ComboBox<>(stations);

        Label card = new Label();
        card.setText("Card Used:\n" + currentTrip.getCardType() + " (" + currentTrip.getEndDateTime() + ")");

        Button update = new Button();
        update.setText("Update");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(start, end_label, comboBox, card, update);

        return new Scene(vBox);
    }

    private Scene adminLanding() {

        Button view_trips = new Button();
        view_trips.setText("View Trips");

        Button buy_card = new Button();
        buy_card.setText("Buy Card");
        buy_card.setOnAction(e -> {
            Stage stage = (Stage) buy_card.getScene().getWindow();
            stage.setScene(buyCard());
            stage.setTitle("Buy Card");
        });

        Button go_on_trip = new Button();
        go_on_trip.setText("Go On Trip");

        Button edit_profile = new Button();
        edit_profile.setText("Edit Profile");
        edit_profile.setOnAction(e -> {
            Stage stage = (Stage) edit_profile.getScene().getWindow();
            stage.setScene(editProfile());
            stage.setTitle("Edit Profile");
        });

        Button add_station = new Button();
        add_station.setText("Add Station");

        Button add_line = new Button();
        add_line.setText("Add Line");

        Button review_reviews = new Button();
        review_reviews.setText("Review Passenger Reviews");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(view_trips,
                buy_card, go_on_trip, edit_profile,
                add_station, add_line, review_reviews);
        vBox.setAlignment(Pos.CENTER);

        return new Scene(vBox);
    }

    private Scene reviewReviews() {
        List<Review> reviewList = reviewDao.getPendingReview();
        ObservableList<Review> reviews;
        if (reviewList != null) {
            reviews = FXCollections.observableList(reviewList);
        } else reviews = null;

        TableView table = new TableView();
        TableColumn idCol = new TableColumn("User");
        idCol.setCellValueFactory(
                new PropertyValueFactory<Review, Integer>("passengerID"));
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
                        // approve/reject review page
                    }
                });
                return cell;
            }
        });

        TableColumn stationCol = new TableColumn("Station");
        stationCol.setCellValueFactory(
                new PropertyValueFactory<Review, String>("stationName"));
        stationCol.setCellFactory(new Callback<TableColumn, TableCell>() {
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
                        String station = (String) cell.getItem();
                        selectedStation = stationDao.getStation(station);
                        Stage stage = (Stage) cell.getScene().getWindow();
                        stage.setScene(stationInfo());
                        stage.setTitle(station + "(Status: " + selectedStation.getStatus() + ")");
                    }
                });
                return cell;
            }
        });

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

        table.setItems(reviews);
        table.getColumns().addAll(idCol, stationCol, shoppingCol,
                connectionCol, commentCol);

        return new Scene(table);
    }

    private Scene editAdminProfile() {
        Label first_label = new Label();
        first_label.setText("First Name:");
        TextField first_text = new TextField();
        first_text.setText(currentUser.getFirstName());

        Label mi_label = new Label();
        mi_label.setText("Middle Initial:");
        TextField mi_text = new TextField();
        mi_text.setText(currentUser.getMinit());

        Label last_label = new Label();
        last_label.setText("Last Name:");
        TextField last_text = new TextField();
        last_text.setText(currentUser.getLastName());

        Label userid_label = new Label();
        userid_label.setText("User ID");
        TextField userid_text = new TextField();
        userid_text.setText(currentUser.getId());

        Label password_label = new Label();
        password_label.setText("Password:");
        TextField password_text = new TextField();
        password_text.setText(currentUser.getPassword());

        Label conf_password_label = new Label();
        conf_password_label.setText("Confirm password:");
        TextField conf_password_text = new TextField();
        conf_password_text.setText(currentUser.getPassword());

        Label error = new Label();

        Button delete = new Button();
        delete.setText("Delete");
        delete.setOnAction(e -> {
            if (userDao.deleteUser(currentUser.getId())) {
                Stage stage = (Stage) delete.getScene().getWindow();
                stage.setScene(login());
                stage.setTitle("Login");
            } else {
                error.setText("Failed to delete user.");
            }
        });

        Button update = new Button();
        update.setText("Update");
        update.setOnAction(e -> {
            if (first_text.getText().equals("") ||
                    last_text.getText().equals("") ||
                    userid_text.getText().equals("") ||
                    password_text.getText().equals("")) {
                error.setText("All fields are required except the middle initial.");
            } else if (!password_text.getText().equals(conf_password_text.getText())) {
                error.setText("Passwords do not match.");
            } else if (password_text.getText().length() < 8) {
                error.setText("Passwords must be at least 8 characters.");
            } else {
                currentUser = new User(userid_text.getText(),
                        first_text.getText(), mi_text.getText(),
                        last_text.getText(),
                        password_text.getText());
                if (userDao.updateUser(currentUser)) {
                    Stage stage = (Stage) update.getScene().getWindow();
                    stage.setScene(adminLanding());
                    stage.setTitle("Welcome " + currentUser.getFirstName()
                            + " " + currentUser.getLastName());
                } else {
                    error.setText("User ID must be unique.");
                }
            }
        });
        HBox buttons = new HBox();
        buttons.getChildren().addAll(delete, update);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                first_label, first_text,
                mi_label, mi_text,
                last_label, last_text,
                userid_label, userid_text,
                password_label, password_text,
                conf_password_label, conf_password_text,
                error, buttons);

        return new Scene(vBox);
    }

    private Scene addStation() {
        Label name_label = new Label();
        name_label.setText("Station Name");
        TextField name_text = new TextField();

        Label address_label = new Label();
        address_label.setText("Street Address");
        TextField address_text = new TextField();

        Label city_label = new Label();
        city_label.setText("City");
        TextField city_text = new TextField();

        Label state_label = new Label();
        state_label.setText("State/Province");
        TextField state_text = new TextField();

        Label zip_label = new Label();
        zip_label.setText("Postal/Zip Code");
        TextField zip_text = new TextField();

        Label line_combo_label = new Label();
        line_combo_label.setText("Line");
        ComboBox<Line> lineComboBox = new ComboBox<>();

        Label order_label = new Label();
        order_label.setText("Order");
        TextField order_text = new TextField();

        Button add_line = new Button();
        add_line.setText("Add Line");

        TableView table = new TableView();

        TableColumn lineCol = new TableColumn("Line");
        TableColumn orderCol = new TableColumn("Order");

        table.getColumns().addAll(lineCol, orderCol);

        Button add_station = new Button();
        add_line.setText("Add Station");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                name_label, name_text,
                address_label, address_text,
                city_label, city_text,
                state_label, state_text,
                zip_label, zip_text,
                line_combo_label, lineComboBox,
                order_label, order_text,
                add_line, table, add_station);

        return new Scene(vBox);
    }

    private Scene addLine() {
        Label line_label = new Label();
        line_label.setText("Line Name");
        TextField line_text = new TextField();

        Label combo_label = new Label();
        combo_label.setText("Station");
        ComboBox<Station> comboBox = new ComboBox<>();

        Label order_label = new Label();
        order_label.setText("Order");
        TextField order_text = new TextField();

        Button add_station = new Button();
        add_station.setText("Add Station");

        TableView table = new TableView();

        TableColumn stationCol = new TableColumn("Station");
        TableColumn orderCol = new TableColumn("Order");

        table.getColumns().addAll(stationCol, orderCol);

        Button add_line = new Button();
        add_line.setText("Add Line");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                line_label, line_text,
                combo_label, comboBox,
                order_label, order_text,
                add_station, table, add_line);

        return new Scene(vBox);
    }
}
