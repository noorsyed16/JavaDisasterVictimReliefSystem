-- Each time this file is executed, it will reset the database to the original state defined below.  You can import this directly in your database by (a) manually entering the first three lines of commands form this file, (b) removing the first three lines of commands from this file, and (c) \i 'path_to_file\project.sql' (with appropriate use of \ or / based on OS).

-- During grading, TAs will assume that these tables exist, but will enter different values.  Thus you cannot assume that any of the values provided here exist, but you can assume the tables exist. You may assume that we will use only valid data in the SQL file.


DROP DATABASE IF EXISTS ensf380project;
CREATE DATABASE ensf380project;
\c ensf380project

-- Create tables
CREATE TABLE Person (
    person_id SERIAL PRIMARY KEY,
    first_name VARCHAR,
    last_name VARCHAR,
    date_of_birth DATE,
    gender VARCHAR,
    comments VARCHAR,
    phone_number VARCHAR,
    family_group INT
);

CREATE TABLE Location (
    location_id SERIAL PRIMARY KEY,
    name VARCHAR,
    address VARCHAR
);

CREATE TABLE Inquiry (
    inquiry_id SERIAL PRIMARY KEY,
    inquirer_id INT REFERENCES Person(person_id),
    seeking_id INT REFERENCES Person(person_id),
    location_id INT REFERENCES Location(location_id),
    date_of_inquiry TIMESTAMP WITHOUT TIME ZONE,
    comments VARCHAR
);

CREATE TABLE Supply (
    supply_id SERIAL PRIMARY KEY,
    type VARCHAR,
    comments VARCHAR
);

CREATE TABLE MedicalRecord (
    medical_record_id SERIAL PRIMARY KEY,
    location_id INT REFERENCES Location(location_id),
    person_id INT REFERENCES Person(person_id),
    date_of_treatment TIMESTAMP WITHOUT TIME ZONE,
    treatment_details VARCHAR
);

-- Association tables
CREATE TABLE PersonLocation (
    person_id INT REFERENCES Person(person_id),
    location_id INT REFERENCES Location(location_id),
    PRIMARY KEY (person_id, location_id)
);

CREATE TABLE SupplyAllocation (
    supply_id INT REFERENCES Supply(supply_id),
    person_id INT REFERENCES Person(person_id),
    location_id INT REFERENCES Location(location_id),
    allocation_date TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (supply_id, allocation_date)
);

-- Insert example data
INSERT INTO Person (first_name, last_name, date_of_birth, gender, comments, phone_number, family_group)
VALUES
('Aurélie', 'Dupont', '1985-03-15', 'Non-binary person', 'Speaks only French', NULL, 1),
('Raman', 'Narayan', '1980-06-22', 'Man', NULL, NULL, 2),
('Chinoso', 'Nwosu', NULL, NULL, NULL, '555-9876', NULL),
('Chanida', 'Chaiyapong', NULL, NULL, NULL, '555-6543', NULL),
('Nathalie', 'Dupont-Nwosu', '2024-12-01', 'Woman', NULL, NULL, 1);

INSERT INTO Location (name, address)
VALUES
('TELUS', '136 8 Ave SE'),
('University of Calgary', '2500 University Dr NW');

INSERT INTO Inquiry (inquirer_id, seeking_id, location_id, date_of_inquiry, comments)
VALUES
(3, 1, 1, '2025-01-01', 'Seeking spouse'),
(4, 1, 1, '2025-01-02', 'Checking on neighbor'),
(1, 5, 1, '2025-01-02', 'Daughter missing');

INSERT INTO Supply (type, comments)
VALUES
('water', NULL),
('blanket', NULL),
('cot', '410 G16'),
('personal item', 'green leather suitcase'),
('water', NULL),
('water', NULL),
('water', NULL);

INSERT INTO MedicalRecord (location_id, person_id, date_of_treatment, treatment_details)
VALUES
(1, 1, '2025-01-05', 'Broken arm'),
(2, 5, '2025-01-06', 'Dehydration');

INSERT INTO PersonLocation (person_id, location_id)
VALUES
(1, 1),
(2, 2),
(5, 2);

INSERT INTO SupplyAllocation (supply_id, person_id, location_id, allocation_date)
VALUES
(1, 1, NULL, CURRENT_TIMESTAMP),
(2, 1, NULL, CURRENT_TIMESTAMP),
(3, 1, NULL, CURRENT_TIMESTAMP),
(4, 1, NULL, CURRENT_TIMESTAMP),
(5, NULL, 1, CURRENT_TIMESTAMP),
(6, NULL, 1, CURRENT_TIMESTAMP),
(7, NULL, 2, CURRENT_TIMESTAMP);

