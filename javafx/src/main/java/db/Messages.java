package db;

public class Messages {
    private int id;
    private String message_type;
    private String message_input;
    private String message_output;

    public Messages(int id, String message_type, String message_input, String message_output) {
        this.id = id;
        this.message_type = message_type;
        this.message_input = message_input;
        this.message_output = message_output;
    }

    public Messages(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getMessage_input() {
        return message_input;
    }

    public void setMessage_input(String message_input) {
        this.message_input = message_input;
    }

    public String getMessage_output() {
        return message_output;
    }

    public void setMessage_output(String message_output) {
        this.message_output = message_output;
    }
}
