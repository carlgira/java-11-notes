# JDBC


## SQL


```oracle-sql

-- CREATE TABLE
CREATE TABLE DEVELOPERS
(
        DEVELOPER_ID NUMBER(10),
        FIRST_NAME VARCHAR2(40),
        AGE NUMBER(3),
        BIRTHDAY DATE
);

-- INSERT DATA
INSERT INTO DEVELOPERS VALUES (1, 'Laura', 21, TO_DATE('2000-05-10', 'YYYY-MM-DD'));
INSERT INTO DEVELOPERS VALUES (2, 'Izabela', 22, TO_DATE('2000-05-11', 'YYYY-MM-DD'));
INSERT INTO DEVELOPERS VALUES (3, 'Natalie', 23, TO_DATE('2000-05-12', 'YYYY-MM-DD'));
INSERT INTO DEVELOPERS VALUES (4, 'Ivan', 84, TO_DATE('1937-05-13', 'YYYY-MM-DD'));

-- SELECT DATA
SELECT * 
FROM DEVELOPERS;


SELECT FIRST_NAME, AGE 
FROM DEVELOPERS;


SELECT FIRST_NAME, AGE 
FROM DEVELOPERS 
WHERE AGE > 30;

SELECT FIRST_NAME, BIRTHDAY 
FROM DEVELOPERS 
WHERE AGE < 30
ORDER BY BIRTHDAY;


SELECT * FROM DEVELOPERS WHERE AGE = 1; DELETE FROM DEVELOPERS; --

-- DELETE DATA
DELETE FROM DEVELOPERS WHERE ID = 4;

DELETE FROM DEVELOPERS WHERE NAME='CARLOS';

-- UPDATE DATA
UPDATE DEVELOPERS SET AGE=22 WHERE NAME='Laura';


-- DROP TABLE
DROP TABLE DEVELOPERS;


-- PROCEDURE
CREATE OR REPLACE PROCEDURE PRINT_DEVELOPER(
    IN_DEVELOPER_ID NUMBER 
)
IS
  R_DEVELOPER DEVELOPERS%ROWTYPE;
BEGIN
  SELECT *
  INTO R_DEVELOPER
  FROM DEVELOPERS
  WHERE DEVELOPER_ID = IN_DEVELOPER_ID;
  
  dbms_output.put_line( R_DEVELOPER.FIRST_NAME || ' => ' || R_DEVELOPER.AGE);

EXCEPTION
   WHEN OTHERS THEN
      dbms_output.put_line( SQLERRM );
END;

set serveroutput on size 30000;
BEGIN
    PRINT_DEVELOPER(1);
END;



-- DROP 
DROP TABLE DEVELOPERS;
DROP PROCEDURE PRINT_DEVELOPER;

```

## Things to remember of JDBC

- Interfaces
    - Driver: 
    - Connection
    - Statement/PreparementStatement/CallableStatement
    - ResultSet
    
- Classes 
  - DriverManager
    
- JDBC URL 
    - jdbc:<database>:<connection details>
    - jdbc:derby:localhost:1527/BD1
    
- Querys 
    - executeQuery => SELECT
    - executeUpdate => INSERT, UPDATE, DELETE
    - execute => SELECT, INSERT, UPDATE, DELETE
    
- Statement
    - Statement => Query como string. SQL Injection
    - PreparedStatement => Valida query, remplzar variables.
    - CallableStatement => Procedures/funciones remplzar variables.
    
- Problems
    - SQL injection => Occurs when using Statement, a SQL can be injected on a query.
    - DoS => Can occurs if we dont close Connection, Statemenet or ResultSet.
  
