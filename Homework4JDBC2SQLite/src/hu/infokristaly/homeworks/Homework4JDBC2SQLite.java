/**
 * 
 */
package hu.infokristaly.homeworks;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author pzoli
 * 
 * http://www.tutorialspoint.com/sqlite/sqlite_java.htm
 * http://service.psoft.hu/eovwgs100.php?eovy=648507&eovx=239772&jsCallback=EOVWGS.callback
 *
 */
public class Homework4JDBC2SQLite {

    public static void main(String[] args) {
        Connection c = null;
        Statement stmt = null;
        try {
          Class.forName("org.sqlite.JDBC");
          c = DriverManager.getConnection("jdbc:sqlite:megye-sqlite.db");
          c.setAutoCommit(false);
          System.out.println("Opened database successfully");

          stmt = c.createStatement();
          ResultSet rs = stmt.executeQuery( "SELECT * FROM megye;" );
          while ( rs.next() ) {
             int mid = rs.getInt("mid");
             String  name = rs.getString("name");
             String  capital = rs.getString("capital");
             String  area = rs.getString("area");
             System.out.println( "TPID = " + mid );
             System.out.println( "NAME = " + name );
             System.out.println( "CITY = " + capital );
             System.out.println( "STREET = " + area );
          }
          rs.close();
          System.out.println();

          rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table'");
          while ( rs.next() ) {
              String  name = rs.getString("name");
              System.out.println( "TABLE NAME = " + name );
          }
          rs.close();
          System.out.println();

          rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type = 'view'");
          while ( rs.next() ) {
              String  name = rs.getString("name");
              System.out.println( "VIEW NAME = " + name );
          }
          rs.close();
          System.out.println();
          
          rs = stmt.executeQuery("PRAGMA table_info(megye)");
          while ( rs.next() ) {
              String  name = rs.getString("name");
              String  type = rs.getString("type");
              System.out.println( "TABLE FIELD NAME = " + name );
              System.out.println( "TABLE FIELD TYPE = " + type );
          }
          rs.close();

          stmt.close();
          c.close();
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
        System.out.println("Operation done successfully");
    }

}
