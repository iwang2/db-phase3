##REGIRSTRATION SCREEN##
#inserting from registration screen
INSERT INTO USER(first_name, minit, last_name, password, passenger_email)
VALUES ("?","?","?","?","?");
#ID unique?



##LOGIN SCREEN##
#checking that the login works so the id and password
SELECT ID, password
FROM USER
WHERE USER.ID = "inputted_userID" AND USER.password = "inputted_userpassword";



##PASSENGER LANDING SCREEN##
#grab name for passenger landing
SELECT first_name, last_name
FROM USER
WHERE first_name = "?" AND last_name = "?";



##LEAVE REVIEW SCREEN#
#pull down menu for review
SELECT name
FROM STATION ORDER BY name ASC;

#LEAVE A REVIEW ADDING info into review table
INSERT INTO REVIEW (passenger_ID, shopping, connection_speed, comment)
VALUES ("?","?","?","?");



##VIEW REIVEWS SCREEN##
#select reviews for the user in list of review page
SELECT (rid, station_name, shopping, connection_speed, comment, approval_status)
FROM REVIEW
WHERE USER.ID = "user id from gui";

#name of user for title of review page
SELECT first_name, last_name
FROM USER
WHERE first_name = "?" AND last_name = "?";

#ORDER reviews BY REVIEW ID
SELECT rid
FROM REVIEW ORDER BY rid ASC;

#order reviews by station name
SELECT station_name
FROM REVIEW ORDER BY station_name ASC;

#order reviews by shopping
SELECT shopping
FROM REVIEW ORDER BY shopping ASC;

#order reviews by connection_speed
SELECT connection_speed
FROM REVIEW ORDER BY connection_speed ASC;

#order reviews by approval status
SELECT approval_status
FROM REVIEW ORDER BY approval_status ASC;

#order reviews by comments
SELECT comment
FROM REVIEW ORDER BY comment ASC;



##EDIT REVIEW SCREEN##
#edit review title - station name
SELECT station_name
FROM REVIEW
WHERE passenger_id = "?" AND rid = "?";

#edit review status
SELECT approval_status
FROM STATION
WHERE passenger_id = "?" AND rid = "?";

#delete the review
DELETE FROM REVIEW
WHERE rid = "?";

#update/edit the review
UPDATE REVIEW
SET shopping = "?", connection_speed="?", comment = "?"
WHERE rid = "?";



##STATION INFO USER SCREEN##
#station info title
SELECT name
FROM STATION
WHERE name = "?";

#address of station in station info
SELECT address, zipcode, city
FROM STATION
WHERE name = "?";

#station info review status
SELECT approval_status
FROM STATION
WHERE passenger_id = "?" AND rid = "?";

#lines for the station in station info
SELECT line_name
FROM STATION_ON_LINE
WHERE station_name = "?";

#REVIEWS DISPLAYED IN STATION INFO
SELECT passenger_ID, shopping, connection_speed, comment
FROM REVIEW
WHERE station_name = "?" AND approval_status = "approved";

#how to calculate average???
SELECT AVG(shopping)
FROM REVIEW
WHERE approval_status = "approved" AND station_name = "?";


##LINE SUMMARY SCREEN##
#title of line summary - line name
SELECT line_name
FROM STATION_ON_LINE
WHERE line_name = "?";

#line info table
SELECT station_name, order_number
FROM STATION_ON_LINE;

#linetable ordered by station name??
SELECT station_name
FROM STATION_ON_LINE ORDER BY STATION_NAME ASC;

#linetable ordered by order number??
SELECT order_number
FROM STATION_ON_LINE ORDER BY ORDER_NUMBER ASC;

#calculates number of stops
SELECT COUNT(order_number)
FROM STATION_ON_LINE
WHERE line_name = "?";



##EDIT PROFILE SCREENS##
#edit profile info
SELECT * 
FROM USER
WHERE ID = "?";

#delete profile
DELETE FROM USER
WHERE ID = "?";

#update/edit profile
UPDATE USER
SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?"
WHERE ID = "?";

#UNIQUE ID??



##BUY A CARD SCREEN##
#buying a tmes card
INSERT INTO CARD (user_ID, purchase_date_time, uses_left, expiration_date)
VALUES ("?","someDate","infinite","someDate + 30");

#buying a t50/30 card
INSERT INTO CARD (user_ID, purchase_date_time, uses_left, expiration_date)
VALUES ("?","someDate","50","someDate + 30");

#buying a t10 card
INSERT INTO CARD (user_ID, purchase_date_time, uses_left, expiration_date)
VALUES ("?","someDate","10","never");

#buying a tjove card
INSERT INTO CARD (user_ID, purchase_date_time, uses_left, expiration_date)
VALUES ("?","someDate","infinite","someDate + 90");



##GO ON A TRIP SCREEN##
#drop down menu for go on trip screen
SELECT name
FROM STATION ORDER BY name ASC;

#selected start station for trip
SELECT name
FROM STATION 
WHERE NAME = "?";

#selected available card types and dates #no VALID CARD CHECK? if returns null then error should occur and trip shouldnt
SELECT type, purchase_date_time
FROM CARD
WHERE user_ID = "?" AND purchase_date_time = "?" AND uses_left > 0 AND expiration_date <> (SELECT getdate());

#removing a use after a journey is begun
UPDATE CARD
SET uses_left = "uses_left - 1"
WHERE user_ID = "?" AND purchase_date_time = "?";

#insert a trip into trip TABLE after beginning one
INSERT INTO TRIP (user_ID, card_type, card_purchase_date_time, start_date_time, end_date_time, from_station_name, to_station_name)
VALUES ("?","FROMPREVIOUS SQL","FROM PREV", (SELECT getdate()), NULL, "GIVEN",NULL);



##VIEW TRIPS SCREEN##
#view trips screen
SELECT (start_date_time, end_date_time, card_type, from_station_name, to_station_name)
FROM TRIP
WHERE user_ID  = "?";

#order trips by start_date_time??
SELECT start_date_time
FROM TRIP ORDER BY start_date_time ASC;

#order trips by to_station_name??
SELECT to_station_name
FROM TRIP ORDER BY to_station_name ASC;

#order trips by to_station_name??
SELECT from_station_name
FROM TRIP ORDER BY from_station_name ASC;



##UPDATE TRIP SCREEN##
#start station displayed in update trip
SELECT from_station_name
FROM TRIP
WHERE user_ID = "?" AND card_type = "?" AND card_purchase_date_time = "?" AND start_date_time = "?";

#card_type, purchase_date_time displayed in update trip
SELECT card_type, purchase_date_time
FROM TRIP
WHERE user_ID = "?" AND card_type = "?" AND card_purchase_date_time = "?" AND start_date_time = "?";

#drop down menu for end station names
SELECT name
FROM STATION ORDER BY name ASC;

#update trip and add to table
UPDATE TRIP
SET to_station_name = "?"
WHERE user_ID = "?" AND card_type = "?" AND card_purchase_date_time = "?" AND start_date_time = "?";



##ADMIN LANDING SCREEN##
#ADMIN user name title for admin landing
SELECT first_name, last_name
FROM USER
WHERE first_name = "?" AND last_name = "?"; 
#HOW TO CHECK IF IT IS AN ADMIN?



##REVIEW PASSENGER REVIEWS SCREEN##
#admin review passengers review screen pending reviews
CREATE VIEW pending_reviews
AS SELECT passenger_id, station_name, shopping, connection_speed, comment
FROM REVIEW
WHERE approval_status = "pending";

#if the admin approves the review
UPDATE REVIEW
SET approval_status = "approve"
WHERE passenger_id = "?" AND rid = "?";

#if the admin rejects the review
UPDATE REVIEW
SET approval_status = "reject"
WHERE passenger_id = "?" AND rid = "?";

#removing rejected/approved reviews from pending???? -> SHOULD HAPPEND WHEN VIEW UPDATES AUTOMATICALLY



##EDIT ADMIN PROFILE SCREEN##
#edit ADMIN profile info
SELECT * 
FROM USER
WHERE ID = "?";

#delete ADMIN profile
DELETE FROM USER
WHERE ID = "?";

#update/edit ADMIN profile
UPDATE USER
SET first_name = "?", minit = "?", last_name = "?", password = "?", passenger_email = "?"
WHERE ID = "?";

#UNIQUE ID???



##ADD STATION SCREEN##
#??HOW TO CHECK UNIQUENESS OF STATION NAME AND ALL ASPECTS OF ADDRESS

#ADDED STATION INFO INTO STATION DATABASE (ADD STATION BUTTON)
INSERT INTO STATION (name, address,state_province, zipcode, city)
VALUES ("NAME","STREET ADDRESS","CITY","STATE", "ZIP");
 
#line drop down menu for add station
SELECT name
FROM LINE ORDER BY line ASC;

#SELECT LINE AND ORDER FOR THE STATION
SELECT line_name, order_number
FROM Station_On_Line
WHERE station_name = "from gui enter";

#line and order add to station_on_line table with addline button
INSERT INTO STATION_ON_LINE (line_name, order_number)
VALUES ("from gui","from gui");

#how to check if that line already has a line with that order number??? (should not return something, else error)
SELECT *
FROM STATION_ON_LINE
WHERE order_number = "?";

#adding the station into the admin_add_station table
INSERT INTO ADMIN_ADD_STATION (line_name, admin_ID, date_time)
VALUES ("linename","ADMINID","date");

#the station is on at least one line (should return something else error)
SELECT line_name
FROM STATION_ON_LINE
WHERE station_name = "?";



##ADD LINE SCREEN##
#how to ensure the line name is unique

#how to prevent having two stations with the same order number (should not return something, else error)
SELECT *
FROM STATION_ON_LINE
WHERE order_number = "?";

#pull down menu for stations in add line
SELECT name
FROM STATION ORDER BY name ASC;

#SELECT stations AND ORDER FOR THE line
SELECT station_name, order_number
FROM Station_On_Line
WHERE line_name = "from gui enter";

#line and order add to station on line table with addline button
INSERT INTO STATION_ON_LINE (station_name, order_number)
VALUES ("from gui","from gui");

#adding the station into the admin_add_station table
INSERT INTO ADMIN_ADD_LINE (station_name, admin_ID, date_time)
VALUES ("STATIONNAME","ADMINID","date");



##LINE SUMMARY SCREEN##
#LINE TITLE FOR THE LINE SUMMARY
SELECT name
FROM LINE
WHERE name = "?";

#table for the lines IN LINE SUMMARY
SELECT station_name, order_number
FROM STATION_ON_LINE
WHERE line_name = "?";

#displaying number of stops on a line
SELECT COUNT(order_number)
FROM STATION_ON_LINE
WHERE line_name = "?";

#ordering of stations on lines
SELECT station_name
FROM STATION_ON_LINE 
WHERE line_name = "?" ORDER BY station_name ASC;

#ordering of ORDERNUMBER on lines
SELECT order_number
FROM STATION_ON_LINE 
WHERE line_name = "?" ORDER BY order_number ASC;

#how to change order number?? GUI??

#DELETING THE STATION OFF A LINE
DELETE FROM STATION_ON_LINE
WHERE line_name = "?" AND station_name = "?";

#UPDATING STATION ON LINE TABLE
UPDATE STATION_ON_LINE
SET order_number = "?"
WHERE line_name = "?" AND station_name = "?";



##STATION INFO ADMIN SCREEN##

#title of line summary - line name
SELECT line_name
FROM STATION_ON_LINE
WHERE line_name = "?";

#INSERTING STATUS OPTION FROM ADMIN STATION INFO
INSERT INTO STATION (approval_status)
VALUES("what admin selects");

#how to calculate average
SELECT AVG(shopping)
FROM REVIEW
WHERE approval_status = "approved" AND station_name = "?";

#status types selectable from gui

#REVIEWS DISPLAYED IN admin STATION INFO
SELECT passenger_ID, shopping, connection_speed, comment
FROM REVIEW
WHERE station_name = "?" AND approval_status = "approved";

#lines for the station in station info for the admin
SELECT line_name
FROM STATION_ON_LINE
WHERE station_name = "?" ;

#station info for admin title
SELECT name
FROM STATION
WHERE name = "?";

#address of station in admin in station info
SELECT address, zipcode, city
FROM STATION
WHERE name = "?";































