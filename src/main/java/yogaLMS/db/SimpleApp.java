package yogaLMS.db;/*

   Derby - Class SimpleApp

   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

import java.sql.*;

import java.util.ArrayList;
import java.util.Properties;


/**
 * <p>
 * This sample program is a minimal Java application showing JDBC access to a
 * Derby database.</p>
 * <p>
 * Instructions for how to run this program are
 * given in <A HREF=example.html>example.html</A>, by default located in the
 * same directory as this source file ($DERBY_HOME/demo/programs/simple/).</p>
 * <p>
 * Derby applications can run against Derby running in an embedded
 * or a client/server framework.</p>
 * <p>
 * When Derby runs in an embedded framework, the JDBC application and Derby
 * run in the same Java Virtual Machine (JVM). The application
 * starts up the Derby engine.</p>
 * <p>
 * When Derby runs in a client/server framework, the application runs in a
 * different JVM from Derby. The connectivity framework (in this case the Derby
 * Network Server) provides network connections. The client driver is loaded
 * automatically.</p>
 */
public class SimpleApp
{
    /* the default framework is embedded */
    private String framework = "embedded";
    private String protocol = "jdbc:derby:";

    /**
     * <p>
     * Starts the demo by creating a new instance of this class and running
     * the <code>go()</code> method.</p>
     * <p>
     * When you run this application, you may give one of the following
     * arguments:
     *  <ul>
          <li><code>embedded</code> - default, if none specified. Will use
     *        Derby's embedded driver. This driver is included in the derby.jar
     *        file.</li>
     *    <li><code>derbyclient</code> - will use the Derby client driver to
     *        access the Derby Network Server. This driver is included in the
     *        derbyclient.jar file.</li>
     *  </ul>
     * <p>
     * When you are using a client/server framework, the network server must
     * already be running when trying to obtain client connections to Derby.
     * This demo program will will try to connect to a network server on this
     * host (the localhost), see the <code>protocol</code> instance variable.
     * </p>
     * <p>
     * When running this demo, you must include the correct driver in the
     * classpath of the JVM. See <a href="example.html">example.html</a> for
     * details.
     * </p>
     * @param args This program accepts one optional argument specifying which
     *        connection framework (JDBC driver) to use (see above). The default
     *        is to use the embedded JDBC driver.
     */
    public static void main(String[] args)
    {
        new SimpleApp().go(args);
        System.out.println("SimpleApp finished");
    }

    /**
     * <p>
     * Starts the actual demo activities. This includes creating a database by
     * making a connection to Derby (automatically loading the driver),
     * creating a table in the database, and inserting, updating and retrieving
     * some data. Some of the retrieved data is then verified (compared) against
     * the expected results. Finally, the table is deleted and, if the embedded
     * framework is used, the database is shut down.</p>
     * <p>
     * Generally, when using a client/server framework, other clients may be
     * (or want to be) connected to the database, so you should be careful about
     * doing shutdown unless you know that no one else needs to access the
     * database until it is rebooted. That is why this demo will not shut down
     * the database unless it is running Derby embedded.</p>
     *
     * @param args - Optional argument specifying which framework or JDBC driver
     *        to use to connect to Derby. Default is the embedded framework,
     *        see the <code>main()</code> method for details.
     * @see #main(String[])
     */
    void go(String[] args)
    {
        /* parse the arguments to determine which framework is desired*/
        parseArguments(args);

        System.out.println("YogaLMS starting in " + framework + " mode");

        /* We will be using Statement and PreparedStatement objects for
         * executing SQL. These objects, as well as Connections and ResultSets,
         * are resources that should be released explicitly after use, hence
         * the try-catch-finally pattern used below.
         * We are storing the Statement and Prepared statement object references
         * in an array list for convenience.
         */
        Connection conn = null;
        ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
        PreparedStatement psInsert;
        PreparedStatement psUpdate;
        Statement s;
        ResultSet rs = null;
        try
        {
            Properties props = new Properties(); // connection properties
            props.put("user", "user1");
            props.put("password", "user1");

            String dbName = "yogaDB"; // the name of the database

            conn = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);

            System.out.println("Connected to and created database " + dbName);

            // We want to control transactions manually. Autocommit is on by
            // default in JDBC.
            conn.setAutoCommit(false);

            /* Creating a statement object that we can use for running various
             * SQL statements commands against the database.*/
            s = conn.createStatement();
            statements.add(s);

            // We create a table...
            s.execute("create table yoga_class" +
                    "(id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
                    "class_date DATE NOT NULL," +
                    "class_name VARCHAR(50) NOT NULL," +
                    "teacher_name VARCHAR(50) NOT NULL," +
                    "studio_name VARCHAR(50) NOT NULL," +
                    "hours DOUBLE NOT NULL )");
            System.out.println("Created table yoga_class");

            // We create a table...
            s.execute("create table log_class" +
                    "(id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
                    "log_id int NOT NULL," +
                    "class_id int NOT NULL)");
            System.out.println("Created table log_class");

            // We create a table...
            s.execute("create table log" +
                    "(id int NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY," +
                    "program_id int NOT NULL," +
                    "start_date DATE NOT NULL," +
                    "end_date DATE)");
            System.out.println("Created table log");

            // and add a few rows...

            /* It is recommended to use PreparedStatements when you are
             * repeating execution of an SQL statement. PreparedStatements also
             * allows you to parameterize variables. By using PreparedStatements
             * you may increase performance (because the Derby engine does not
             * have to recompile the SQL statement each time it is executed) and
             * improve security (because of Java type checking).
             */

            psInsert = conn.prepareStatement(
                        "insert into yoga_class (class_date, class_name, teacher_name, studio_name, hours) " +
                                "values (?, ?, ?, ?, ?)");
            statements.add(psInsert);

            psInsert.setDate(1, Date.valueOf("2018-06-10"));
            psInsert.setString(2, "Yoga I");
            psInsert.setString(3, "Janis Sheldon");
            psInsert.setString(4, "YogaLife");
            psInsert.setDouble(5, 1.5);
            psInsert.executeUpdate();
            System.out.println("Inserted 1st yoga class");

            psInsert.setDate(1, Date.valueOf("2018-06-9"));
            psInsert.setString(2, "Yoga II");
            psInsert.setString(3, "Brian Serven");
            psInsert.setString(4, "YogaLife");
            psInsert.setDouble(5, 1);
            psInsert.executeUpdate();
            System.out.println("Inserted 2nd yoga class");

            psInsert = conn.prepareStatement(
                    "insert into log (program_id, start_date, end_date) " +
                            "values (?, ?, ?)");
            statements.add(psInsert);
            psInsert.setInt(1, 1);
            psInsert.setDate(2, Date.valueOf("2018-01-01"));
            psInsert.setDate(3, null);
            psInsert.executeUpdate();
            System.out.println("Inserted training log");

            psInsert = conn.prepareStatement(
                    "insert into log_class (log_id, class_id) " +
                            "values (?, ?)");
            statements.add(psInsert);
            psInsert.setInt(1, 1);
            psInsert.setInt(2, 1);
            psInsert.executeUpdate();
            System.out.println("linked 1st yoga class and training log");

            psInsert.setInt(1, 1);
            psInsert.setInt(2, 2);
            psInsert.executeUpdate();
            System.out.println("linked 2nd yoga class and training log");

            /*
               We select the rows and verify the results.
             */
            rs = s.executeQuery(
                    "SELECT class_date, class_name FROM yoga_class ORDER BY class_date ASC");

            System.out.println("SQL results for all yoga classes ordered by date: ");
            while(rs.next()) {
                System.out.println("date: " + rs.getDate(1) + "\nclass name: " + rs.getString(2));
            }

            rs = s.executeQuery("SELECT yc.class_name, yc.teacher_name FROM yoga_class yc " +
                    "INNER JOIN log_class lc on yc.id = lc.class_id " +
                    "INNER JOIN log lg on lc.log_id = lg.id " +
                    "WHERE lg.id = 1");
            System.out.println("SQL results for all yoga classes where log id = 1: ");
            while(rs.next()) {
                System.out.println("class name: " + rs.getString(1) + "\nteacher name: " + rs.getString(2));
            }

            /*
               We commit the transaction. Any changes will be persisted to
               the database now.
             */
            conn.commit();
            System.out.println("Committed the transaction");

            /*
             * In embedded mode, an application should shut down the database.
             * If the application fails to shut down the database,
             * Derby will not perform a checkpoint when the JVM shuts down.
             * This means that it will take longer to boot (connect to) the
             * database the next time, because Derby needs to perform a recovery
             * operation.
             *
             * It is also possible to shut down the Derby system/engine, which
             * automatically shuts down all booted databases.
             *
             * Explicitly shutting down the database or the Derby engine with
             * the connection URL is preferred. This style of shutdown will
             * always throw an SQLException.
             *
             * Not shutting down when in a client environment, see method
             * Javadoc.
             */

            if (framework.equals("embedded"))
            {
                try
                {
                    // the shutdown=true attribute shuts down Derby
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");

                    // To shut down a specific database only, but keep the
                    // engine running (for example for connecting to other
                    // databases), specify a database in the connection URL:
                    //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
                }
                catch (SQLException se)
                {
                    if (( (se.getErrorCode() == 50000)
                            && ("XJ015".equals(se.getSQLState()) ))) {
                        // we got the expected exception
                        System.out.println("Derby shut down normally");
                        // Note that for single database shutdown, the expected
                        // SQL state is "08006", and the error code is 45000.
                    } else {
                        // if the error code or SQLState is different, we have
                        // an unexpected exception (shutdown failed)
                        System.err.println("Derby did not shut down normally");
                        printSQLException(se);
                    }
                }
            }
        }
        catch (SQLException sqle)
        {
            printSQLException(sqle);
        } finally {
            // release all open resources to avoid unnecessary memory usage

            // ResultSet
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }

            // Statements and PreparedStatements
            int i = 0;
            while (!statements.isEmpty()) {
                // PreparedStatement extend Statement
                Statement st = (Statement)statements.remove(i);
                try {
                    if (st != null) {
                        st.close();
                        st = null;
                    }
                } catch (SQLException sqle) {
                    printSQLException(sqle);
                }
            }

            //Connection
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }
        }
    }

    /**
     * Reports a data verification failure to System.err with the given message.
     *
     * @param message A message describing what failed.
     */
    private void reportFailure(String message) {
        System.err.println("\nData verification failed:");
        System.err.println('\t' + message);
    }

    /**
     * Prints details of an SQLException chain to <code>System.err</code>.
     * Details included are SQL State, Error code, Exception message.
     *
     * @param e the SQLException from which to print details.
     */
    public static void printSQLException(SQLException e)
    {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null)
        {
            System.err.println("\n----- SQLException -----");
            System.err.println("  SQL State:  " + e.getSQLState());
            System.err.println("  Error Code: " + e.getErrorCode());
            System.err.println("  Message:    " + e.getMessage());
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }

    /**
     * Parses the arguments given and sets the values of this class's instance
     * variables accordingly - that is, which framework to use, the name of the
     * JDBC driver class, and which connection protocol to use. The
     * protocol should be used as part of the JDBC URL when connecting to Derby.
     * <p>
     * If the argument is "embedded" or invalid, this method will not change
     * anything, meaning that the default values will be used.</p>
     * <p>
     * @param args JDBC connection framework, either "embedded" or "derbyclient".
     * Only the first argument will be considered, the rest will be ignored.
     */
    private void parseArguments(String[] args)
    {
        if (args.length > 0) {
            if (args[0].equalsIgnoreCase("derbyclient"))
            {
                framework = "derbyclient";
                protocol = "jdbc:derby://localhost:1527/";
            }
        }
    }
}
