CREATE TABLE `person` (
	`PersonID` INT(4) NOT NULL AUTO_INCREMENT,
	`FirstName` VARCHAR(50) NOT NULL,
	`LastName` VARCHAR(50) NOT NULL,
	`EmailAddress` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`PersonID`)
);

CREATE TABLE `subscription` (
	`SubscriptionID` INT(4) NOT NULL AUTO_INCREMENT,
	`PersonID` INT(4) NOT NULL,
	#`EmailAddress` VARCHAR(50) NOT NULL,
	`Topic` VARCHAR(12) NOT NULL,
	`ARN` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`SubscriptionID`),
	CONSTRAINT `FK_Person_Person_ID` FOREIGN KEY (`PersonID`) REFERENCES `person` (`PersonID`)
);	
	
CREATE TABLE `subscription` (
	`SubscriptionID` INT(4) NOT NULL AUTO_INCREMENT,
	`PersonID` INT(4) NOT NULL,
	`IsComputerSub` BOOL NOT NULL,
	`IsConsoleSub` BOOL NOT NULL,
	`IsHeaterSub` BOOL NOT NULL,
	`IsLawnSub` BOOL NOT NULL,
	`IsToolSub` BOOL NOT NULL,
	`IsTelevisionSub` BOOL NOT NULL,
	PRIMARY KEY (`SubscriptionID`),
	CONSTRAINT `FK_Person_Person_ID` FOREIGN KEY (`PersonID`) REFERENCES `person` (`PersonID`)
);

INSERT INTO person (FirstName, LastName, EmailAddress)
	VALUES ('Andrew', 'White', 'white_andrewj@yahoo.com');

SELECT @@identity -- returns the key of the most recently changed record for the current connection
	
INSERT INTO subscription (PersonID, IsComputerSub, IsConsoleSub, IsHeaterSub, IsLawnSub, IsToolSub, IsTelevisionSub)
	VALUES (19, true, false, true, true, false, false); 

DROP TABLE computer_subscription;