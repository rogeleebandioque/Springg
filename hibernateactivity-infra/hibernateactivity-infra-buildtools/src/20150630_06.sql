ALTER TABLE Contacts RENAME COLUMN id TO contact_id;

CREATE SEQUENCE personid_generator START WITH 1 INCREMENT BY 1 ;

CREATE SEQUENCE contact_generator START WITH 1 INCREMENT BY 1 ;
