package kneu.ishi501.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import kneu.ishi501.service.DbService;
import kneu.ishi501.service.FXMLService;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

import static kneu.ishi501.service.FXMLService.fxmlService;

public class ClientWindowController implements Initializable {
    @FXML
    private Text fullName;

    @FXML
    private Text gender;

    @FXML
    private Text maritalStatus;

    @FXML
    private Text age;

    @FXML
    private Text amountOfCredit;

    @FXML
    private Text paymentOfPreviousCredits;

    @FXML
    private Text durationOfCredit;

    @FXML
    private Button back;

    @FXML
    private Button calculate;

    private double scoring;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeFields();

        back.setOnAction(actionEvent -> {
            back.getScene().getWindow().hide();
            FXMLService.fxmlService.goToAnotherWindow("/kneu/ishi501/fxml/main_window.fxml", "Клієнська база");
        });

        calculate.setOnAction(actionEvent -> {
            back.getScene().getWindow().centerOnScreen();
            calculateScoring();

            if (scoring > 12)
                FXMLService.fxmlService.goToAnotherWindow("/kneu/ishi501/fxml/good_result_window.fxml", "Кредитна історія");
            else
                FXMLService.fxmlService.goToAnotherWindow("/kneu/ishi501/fxml/bad_result_window.fxml", "Кредитна історія");
        });
    }

    private void initializeFields() {
        Document doc = DbService.document;
        //init full name
        StringBuilder sb = new StringBuilder();
        sb.append(doc.getString("lastName"));
        sb.append(" ");
        sb.append(doc.getString("firstName"));
        sb.append(" ");
        sb.append(doc.getString("patronymic"));
        this.fullName.setText(sb.toString());

        //init gender
        if(doc.getString("gender").equals("male"))
            this.gender.setText("чоловіча");
        else
            this.gender.setText("жіноча");

        //init marital status
        if(doc.getString("maritalStatus").equals("single"))
            this.maritalStatus.setText("не зумужній(ня)");
        else  if(doc.getString("maritalStatus").equals("married"))
            this.maritalStatus.setText("у шлюбі");
        else
            this.maritalStatus.setText("розлучений(на)");

        //init age
        this.age.setText(DbService.document.getInteger("age").toString());

        //init amount of credit
        this.amountOfCredit.setText(DbService.document.getInteger("amountOfCredit").toString());

        //init payment of previous credits
        if(doc.getString("paymentOfPreviousCredits").equals("no problems with current credits"))
            this.paymentOfPreviousCredits.setText("проблем з погашеням немає");
        else if(doc.getString("paymentOfPreviousCredits").equals("hesistant"))
            this.paymentOfPreviousCredits.setText("ненадійний");
        else if(doc.getString("paymentOfPreviousCredits").equals("no previous credits"))
            this.paymentOfPreviousCredits.setText("немає кредитної історії");
        else
            this.paymentOfPreviousCredits.setText("була заборгованість");

        //init duration of credit
        this.durationOfCredit.setText(DbService.document.getInteger("durationOfCredit").toString());
    }

    private void calculateScoring() {
        scoring = 0;
        Document doc = DbService.document;
        //gender
        if (doc.getString("gender").equals("female"))
            scoring += 1;
        else
            scoring += 0.5;

        //DurationOfCredit
        if (doc.getInteger("durationOfCredit") > 28)
            scoring += 2;
        else if (doc.getInteger("durationOfCredit") <= 12)
            scoring += 1;
        else
            scoring += 1.5;

        //PaymentOfPreviousCredits
        if (doc.getString("paymentOfPreviousCredits").equals("no problems with current credits"))
            scoring += 4;
        else if (doc.getString("paymentOfPreviousCredits").equals("no previous credits"))
            scoring += 3;
        else if (doc.getString("paymentOfPreviousCredits").equals("paid back"))
            scoring += 2;
        else
            scoring += 0.5;

        //AmountOfCredit
        if (doc.getInteger("amountOfCredit") < 5000)
            scoring += 4;
        else if (doc.getInteger("amountOfCredit") > 15000)
            scoring += 1;
        else
            scoring += 2.5;

        //MaritalStatus
        if (doc.getString("maritalStatus").equals("married"))
            scoring += 3;
        else if (doc.getString("maritalStatus").equals("single"))
            scoring += 2;
        else
            scoring += 1;

        //Age
        if (doc.getInteger("age") > 35)
            scoring += 2;
        else if (doc.getInteger("age") < 25)
            scoring += 0.5;
        else
            scoring += 1.5;

        System.out.println("scoring = " + scoring);
    }
}