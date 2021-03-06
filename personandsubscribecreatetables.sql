CREATE TABLE `person` (
	`PersonID` INT(4) NOT NULL AUTO_INCREMENT,
	`FirstName` VARCHAR(50) NOT NULL,
	`LastName` VARCHAR(50) NOT NULL,
	`EmailAddress` VARCHAR(60) NOT NULL,
	PRIMARY KEY (`PersonID`)
);

CREATE TABLE `subscription` (
	`SubscriptionID` INT(4) NOT NULL AUTO_INCREMENT,
	`PersonID` INT(4) NOT NULL,
	#`EmailAddress` VARCHAR(50) NOT NULL,
	`Topic` VARCHAR(12) NOT NULL,
	`ARN` VARCHAR(250) NOT NULL,
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
	
INSERT INTO subscription (PersonID, Topic, ARN)
	VALUES (20, "Heater", "pending confirmation"); 



DROP TABLE person;

SELECT PersonID, FirstName, LastName, EmailAddress 
FROM person
WHERE EmailAddress = "*"

SELECT person.EmailAddress, subscription.Topic, subscription.ARN
FROM subscription
INNER JOIN person ON subscription.PersonID = person.PersonID;

#WHERE clause comes after join statement
SELECT p.PersonID, p.FirstName, p.LastName, p.EmailAddress, s.Topic, s.ARN
FROM person p
LEFT OUTER JOIN subscription s ON s.PersonID = p.PersonID
#WHERE s.Topic = "Computer"

SELECT Topic, ARN
FROM subscription
WHERE PersonID = 44;

UPDATE subscription
SET ARN=""
WHERE PersonID=3 AND Topic="Heater"

DELETE FROM subscription
WHERE PersonID=3 AND Topic="Heater"
