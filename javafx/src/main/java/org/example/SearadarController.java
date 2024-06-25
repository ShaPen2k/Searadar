package org.example;

import db.ConnectionUtil;
import db.Messages;
import db.MessagesFromDB;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.searadar.mr231.convert.Mr231Converter;
import org.example.searadar.mr231.station.Mr231StationType;
import ru.oogis.searadar.api.message.SearadarStationMessage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringJoiner;

public class SearadarController implements Initializable {

    ConnectionUtil connectionUtil = new ConnectionUtil();
    MessagesFromDB messagesFromDB = new MessagesFromDB();

    Messages messages = new Messages();
    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TextField textField;

    @FXML
    private Button sendButton;

    @FXML
    private Button getLastButton;

    @FXML
    private Label exampleLabel;

    @FXML
    private Label typeLabel;

    @FXML
    private Label inputLabel;

    @FXML
    private Label outputLabel;

    @FXML
    private Label inTypeLabel;

    @FXML
    private Label inInputLabel;

    @FXML
    private Label inOutputLabel;


    int choicePosition;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        connectionUtil.createTables();
        choiceBox.setItems(FXCollections.observableArrayList("TrackedTargetMessage", "WaterSpeedHeadingMessage", "RadarSystemData"));
        choiceBox.setOnAction(this::getData);
        SendButtonClick();
    }

    @FXML
    private void SendButtonClick(){
        sendButton.setOnAction(event -> {
            String outputText = ConvertString(textField.getText());
            messagesFromDB.addMessage(choicePosition, textField.getText(), outputText);
        });
    }

    @FXML
    private void LastButtonClick(){
        getLastButton.setOnAction(event -> {
            try {
                messages = messagesFromDB.getLastMessage();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            typeLabel.setText(messages.getMessage_type());
            inputLabel.setText(messages.getMessage_input());
            outputLabel.setText(messages.getMessage_output());
            typeLabel.setVisible(true);
            inputLabel.setVisible(true);
            outputLabel.setVisible(true);
            inTypeLabel.setVisible(true);
            inInputLabel.setVisible(true);
            inOutputLabel.setVisible(true);
        });
    }

    private void getData(javafx.event.ActionEvent actionEvent) {
        String selected = choiceBox.getValue();
        switch (selected){
            case ("TrackedTargetMessage"):
                textField.setText("$RATTM,");
                exampleLabel.setText("Пример сообщения: $RATTM,66,28.71,341.1,T,57.6,024.5,T,0.4,4.1,N,b,L,,457362,А*42");
                exampleLabel.setVisible(true);
                choicePosition = 1;
                break;
            case ("WaterSpeedHeadingMessage"):
                textField.setText("$RAVHW,");
                exampleLabel.setText("Пример сообщения: $RAVHW,115.6,T,,,46.0,N,,*71");
                exampleLabel.setVisible(true);
                choicePosition = 2;
                break;
            case ("RadarSystemData"):
                textField.setText("$RARSD,");
                exampleLabel.setText("Пример сообщения: $RARSD,36.5,331.4,8.4,320.6,,,,,11.6,185.3,96.0,N,N,S*33");
                exampleLabel.setVisible(true);
                choicePosition = 3;
                break;
        }
    }

    private String ConvertString(String inputString){
        Mr231StationType mr231 = new Mr231StationType();
        Mr231Converter converter = mr231.createConverter();
        List<SearadarStationMessage> searadarMessages = converter.convert(inputString);
        StringJoiner joiner = new StringJoiner(", ");
        for (SearadarStationMessage message : searadarMessages) {
            joiner.add(message.toString());
        }
        System.out.println(joiner.toString());
        return joiner.toString();
    }

}