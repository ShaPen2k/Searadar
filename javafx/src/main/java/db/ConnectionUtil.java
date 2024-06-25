package db;
import java.sql.*;

public class ConnectionUtil {



    public Connection connect_to_db() {

        Connection conn = null;
        String dbname = "searadar";
        String user = "postgres";
        String pass = "12345";
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void createTables() {

        Statement statement;
        try {
            Connection connect = connect_to_db();
            String query = "create table if not exists message_types\n" +
                    "(\n" +
                    "\tmessage_type_id serial primary key,\n" +
                    "\tmessage_type_name varchar(50) unique\n" +
                    ");";

            statement = connect.createStatement();
            statement.executeUpdate(query);

            String query2 = "INSERT INTO message_types (message_type_name) \n" +
                    "VALUES ('TrackedTargetMessage'), ('WaterSpeedHeadingMessage'), ('RadarSystemData')\n" +
                    "ON CONFLICT (message_type_name) DO NOTHING;";
            statement = connect.createStatement();
            statement.executeUpdate(query2);

            String query3 = "create table if not exists messages \n" +
                    "(\n" +
                    "\tmessage_id serial primary key, \n" +
                    " \tmessage_type_id int references message_types (message_type_id),\n" +
                    "\tmessage_input varchar(200) not null,\n" +
                    "\tmessage_output varchar(400) not  null\n" +
                    ");";
            statement = connect.createStatement();
            statement.executeUpdate(query3);
            System.out.println("Таблицы успешно созданы");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
