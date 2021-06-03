package com.carlgira.jdbc;

import java.sql.*;
import java.util.Scanner;

class Main {

    /**
     * Interfaces
         * Driver
         * Connection
         * Statement/PreparementStatement/CallableStatement
         * ResultSet
     * Class
         *  DriverManager
     *  JDBC URL
         *  jdbc:<database>:<connection details>
         *  jdbc:derby:localhost:1527/BD1
     *  QUERYS
         *  executeQuery => SELECT
         *  executeUpdate => INSERT, UPDATE, DELETE
         *  execute => SELECT, INSERT, UPDATE, DELETE
     *  Statement
         *  Statement => Query como string. SQL Injection
         *  PreparedStatement => Valida query, remplzar variables.
         *  CallableStatement => Procedures/funciones remplzar variables.
     *  Problems
         *  SQL injection => Utilizamos Statement, y el usuario ingresa un SQL malicioso. (DROP, DELETE)
         *  DoS => Ocurre no cerramos conexiones.
     */


    /**
     * JDBC Connection => executeQuery
     * Create a JDBC connection using a Statement and ResultSet to get results.
     * Use the while loop to print data.
     * Close the resources with .close() method
     */
    public static void executeQuery1(){
        try{
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM DEVELOPERS");
            // rs -1 next()

            //rs.getString("FIRST_NAME");
            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }

            rs.close();
            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * JDBC Connection with try-with-resources => executeQuery
     * Create a JDBC connection using a Statement and ResultSet to get results.
     * Use the while loop to print data.
     */
    public static void executeQuery2(){
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM DEVELOPERS");
                )
        {

            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }

        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * JDBC Connection with try-with-resources => executeUpdate
     * Create a JDBC connection using a Statement.
     * Update/Insert/Delete data with executeUpdate
     */
    public static void executeUpdate1(){
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");
                Statement stmt = con.createStatement();
        )
        {
            stmt.executeUpdate("UPDATE DEVELOPERS SET AGE=19 WHERE FIRST_NAME='Natalie'");
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * JDBC Connection with try-with-resources => execute
     * Create a JDBC connection using a Statement.
     * Select/Update/Insert/Delete data with execute
     */
    public static void execute1(){
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");
                Statement stmt = con.createStatement();
        )
        {
            boolean r1 = stmt.execute("UPDATE DEVELOPERS SET AGE=23 WHERE FIRST_NAME='Natalie'");
            boolean r2 = stmt.execute("SELECT * FROM DEVELOPERS");
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * DoS => Denial of service
     * When the code lacks of a .close() call the functions is going to keep opening connections and never close them.
     * The application is going to be without resources after a while
     */
    public static void problem1(){ // DoS
        try{
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");

            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM DEVELOPERS");

            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }

            // rs.close();
            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * SQL injection
     * It's possible that if you are not careful, that an input String can contain a SQL, and can disrupt the expected execution of the query.
     * In the example the age var is expecting to have the age, but it's passed SQL droping a table.
     */
    public static void problem2(){ // SQL Injection

        String age = "1; DROP TABLE DEVELOPERS; --";

        try
        {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");
            Statement stmt = con.createStatement();
            String query = "UPDATE DEVELOPERS SET AGE= " + age + " WHERE FIRST_NAME='Laura'";
            System.out.println(query);

            stmt.executeUpdate(query);

            stmt.close();
            con.close();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }


    /**
     * JDBC Connection => PreparedStatement
     * It's safer to use PreparedStatement than Statement. It's not possible to do an SQl injection with PreparedStament.
     * In a preparedStatement it's possible to mark where the inputs or outputs goes using a question mark
     * After that is possible to use any of the many setMethods to set the value of the var.
     */
    public static void preparedStatement(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Filter Age: ");
        int age = scanner.nextInt();

        try
        {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM DEVELOPERS WHERE AGE = ?");

            stmt.setInt(1, age);

            //stmt.setString();
            //stmt.setBoolean();
            //stmt.setDate();
            //stmt.setObject();

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * JDBC Connection => CallableStatement
     * Used for Store Procedures or functions
     */
    public static void callabledStatement(){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Filter Age: ");
        int age = scanner.nextInt();

        try
        {
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");
            CallableStatement stmt = con.prepareCall("{call PRINT_DEVELOPER( ? )}");

            stmt.setInt(1, age);

            stmt.executeQuery();

        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * JDBC Connection => Transaction
     * When querying a database all the querys execute in a transaction.
     * All the querys can be executed instanly because auto-commit is activated by default.
     * But is possible to set the commit behavior with the method setAutoCommit.
     * In this case only if the two querys are susefull the data is going to be updated.
     * It's possible to use commit() or rollback() methods as needed.
     */
    public static void transaction(){

        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");
                Statement stmt = con.createStatement();
        )
        {
            con.setAutoCommit(false);

            stmt.executeUpdate("UPDATE DEVELOPERS SET AGE=19 WHERE FIRST_NAME='Laura'");
            stmt.executeUpdate("UPDATE DEVELOPERS SET AGE=18 WHERE FIRST_NAME='Izabela'");

            con.rollback();
            con.commit();
            /**
            if(age > 10){
                con.rollback();
            }
            else{
                con.commit();
            }*/

            //throw new SQLException("SuperException");
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * JDBC Metadata
     * It's possible to get metadata of the ResultSet and the Database.
     */
    public static void metadata(){
        try (
                Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL","sodauser","sodauser");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM DEVELOPERS");
        )
        {

            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            resultSetMetaData.getColumnCount();
            resultSetMetaData.getColumnName(1);

            DatabaseMetaData m = con.getMetaData();
            m.getDatabaseProductName();
            m.getSQLKeywords();

            while(rs.next()){
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) throws ClassNotFoundException {
        preparedStatement();
    }
}
