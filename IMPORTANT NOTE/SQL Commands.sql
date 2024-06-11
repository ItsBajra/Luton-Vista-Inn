-- DROP DATABASE assignment2;

CREATE DATABASE assignment2;

USE assignment2;

CREATE TABLE ClientProfile (
ProfileID INT PRIMARY KEY auto_increment,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50) NOT NULL,
Email VARCHAR(50) NOT NULL,
ContactNumber VARCHAR(50) NOT NULL,
Address VARCHAR(50) NOT NULL,
City VARCHAR(50) NOT NULL,
ZIPCode VARCHAR(10) NOT NULL,
Country VARCHAR(50) NOT NULL,
Password VARCHAR(50) NOT NULL,
CCNumber VARCHAR(16),
NameOnCard VARCHAR(50),
ExpiryDate VARCHAR(50),
CVVCode VARCHAR(4)
);

CREATE TABLE CorporateClientProfile (
ProfileID INT PRIMARY KEY auto_increment,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50) NOT NULL,
Email VARCHAR(50) NOT NULL,
CompanyName VARCHAR(50) NOT NULL,
ContactNumber VARCHAR(50) NOT NULL,
Address VARCHAR(50) NOT NULL,
City VARCHAR(50) NOT NULL,
ZIPCode VARCHAR(10) NOT NULL,
Country VARCHAR(50) NOT NULL,
Password VARCHAR(50) NOT NULL,
CCNumber VARCHAR(16) NOT NULL,
NameOnCard VARCHAR(50) NOT NULL,
ExpiryDate VARCHAR(50) NOT NULL,
CVVCode VARCHAR(4) NOT NULL
);

CREATE TABLE ReceptionistProfile (
ReceptionistID INT PRIMARY KEY AUTO_INCREMENT,
Email VARCHAR(50) NOT NULL,
Password VARCHAR(50) NOT NULL
);

CREATE TABLE Reservation (
BookingID INT PRIMARY KEY AUTO_INCREMENT,
Email VARCHAR(50) NOT NULL,
CheckInDate DATE NOT NULL,
CheckOutDate DATE NOT NULL,
RoomType VARCHAR(50) NOT NULL,
BookingStatus VARCHAR(50) NOT NULL,
RoomID INT, 
ReceptionistID INT
);

/** ALTER TABLE Reservation
ADD CONSTRAINT fk_ReceptionistID
FOREIGN KEY (ReceptionistID) 
REFERENCES ReceptionistProfile(ReceptionistID);

ALTER TABLE Reservation
ADD CONSTRAINT fk_RoomID
FOREIGN KEY (RoomID) 
REFERENCES Rooms(RoomID); **/

INSERT INTO ReceptionistProfile 
VALUES (0, "admin@admin.com", "admin");

SELECT * FROM ClientProfile;

SELECT * FROM CorporateClientProfile;

SELECT * FROM ReceptionistProfile;

SELECT * FROM Reservation;




