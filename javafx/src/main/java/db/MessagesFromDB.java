package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessagesFromDB {

    public void addMessage(int choicePosition, String inputText, String outputText) {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Statement statement;
        try {
            Connection connect = connectionUtil.connect_to_db();
            String query = "INSERT INTO messages (message_type_id, message_input, message_output) VALUES (" + choicePosition + ",'" + inputText + "','" + outputText + "');";
            statement = connect.createStatement();
            statement.executeUpdate(query);
            System.out.println("Данные успешно внесены в таблицу");

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public Messages getLastMessage() throws SQLException {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        Messages messages = new Messages();
        try {
            Connection connect = connectionUtil.connect_to_db();
            String query = "select message_id, message_type_name, message_input, message_output from messages\n" +
                    "inner join message_types on message_types.message_type_id = messages.message_type_id\n" +
                    "order by message_id desc limit 1;";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("message_id");
                String message_type = result.getString("message_type_name");
                String message_input = result.getString("message_input");
                String message_output = result.getString("message_output");
                messages = new Messages(id, message_type, message_input, message_output);
                System.out.println("Последние данные получены");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return messages;
    }
}
