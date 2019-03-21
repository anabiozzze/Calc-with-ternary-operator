package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {

    private Scene scene; // наше окно, на котором будут располагаться все прочие элементы
    private Group group; // основной контейнер с координатами 0.0 по размеру окна
    protected volatile static BorderPane pane; // второй контейнер - в нем будут лежать все графические элементы

    private TextField textField; // текстовое поле для ввода ссылок
    private VBox vBox;
    private FlowPane fPane;
    private static HBox hBox;


    @Override
    public void start(Stage primaryStage) throws Exception {

        // задаем размер и цвет окна и основной области, на которой будем располагать элементы
        group = new Group();
        scene = new Scene(group, 370, 620);
        pane = new BorderPane();
        pane.setMinSize(370, 620);
        pane.setStyle("-fx-background-color: lightgrey");

        // привяжем размеры основной области к размерам окна программы, чтобы все масштабировалось вместе
        pane.prefHeightProperty().bind(scene.heightProperty());
        pane.prefWidthProperty().bind(scene.widthProperty());

        // пока отключим масшитабирование окна программы
        primaryStage.setResizable(false);

        // присваиваем окну заголовок и запускаем
        primaryStage.setTitle("Calculator Supreme Turbo");
        primaryStage.setScene(scene);

        addTextFields();
        addButtons();
        placeContainers();

        group.getChildren().add(pane);

        primaryStage.show();
    }

    // метод создает текстовое поле для ввода данных пользователем
    private void addTextFields() {
        textField = new TextField();
        textField.setMinSize(350, 100);
        textField.setStyle("-fx-background-color: snow");

        textField.setPromptText("Введите выражение:");

        vBox = new VBox();
        vBox.getChildren().addAll(textField);
    }


    // метод добавляет на панель все необходимые кнопки и действия на них
    private void addButtons() {
        Button btn_one = new Button("1");
        Button btn_two = new Button("2");
        Button btn_three = new Button("3");
        Button btn_four = new Button("4");
        Button btn_five = new Button("5");
        Button btn_six = new Button("6");
        Button btn_seven = new Button("7");
        Button btn_eight = new Button("8");
        Button btn_nine = new Button("9");
        Button btn_zero = new Button("0");
        Button btn_plus = new Button("+");
        Button btn_minus = new Button("-");
        Button btn_equals = new Button("=");
        Button btn_divide = new Button("/");
        Button btn_multiply = new Button("*");
        Button btn_square = new Button("√");
        Button btn_comma = new Button(",");
        Button btn_more = new Button(">");
        Button btn_less = new Button("<");
        Button btn_question = new Button("?");
        Button btn_colon = new Button(":");
        Button btn_rightBrack = new Button(")");
        Button btn_leftBrack = new Button("(");
        Button btn_AC = new Button("AC");

        btn_zero.setMinSize(80, 65);
        btn_one.setMinSize(80, 65);
        btn_two.setMinSize(80, 65);
        btn_three.setMinSize(80, 65);
        btn_four.setMinSize(80, 65);
        btn_five.setMinSize(80, 65);
        btn_six.setMinSize(80, 65);
        btn_seven.setMinSize(80, 65);
        btn_eight.setMinSize(80, 65);
        btn_nine.setMinSize(80, 65);
        btn_equals.setMinSize(80, 65);
        btn_plus.setMinSize(80, 65);
        btn_minus.setMinSize(80, 65);
        btn_multiply.setMinSize(80, 65);
        btn_divide.setMinSize(80, 65);
        btn_square.setMinSize(80, 65);
        btn_comma.setMinSize(80, 65);
        btn_colon.setMinSize(80, 65);
        btn_question.setMinSize(80, 65);
        btn_more.setMinSize(80, 65);
        btn_less.setMinSize(80, 65);
        btn_leftBrack.setMinSize(80, 65);
        btn_rightBrack.setMinSize(80, 65);
        btn_AC.setMinSize(80, 65);

        fPane = new FlowPane();
        fPane.maxWidth(360);
        fPane.getChildren().addAll(btn_one, btn_two, btn_three, btn_plus, btn_four, btn_five, btn_six, btn_minus, btn_seven,
                btn_eight, btn_nine, btn_multiply, btn_comma, btn_zero, btn_square, btn_divide,
                btn_colon, btn_less, btn_more, btn_equals, btn_question, btn_leftBrack, btn_rightBrack, btn_AC);
    }


    // метод размещает контейнеры с кнопками и полями на основном поле
    private void placeContainers() {
        vBox.setSpacing(10);
        BorderPane.setMargin(vBox, new Insets(10, 10,10, 10));
        pane.setTop(vBox);

        fPane.setHgap(10);
        fPane.setVgap(10);
        BorderPane.setMargin(fPane, new Insets(0, 10,10, 10));
        pane.setCenter(fPane);
    }

    // этот метод забирает из текстового поля введенный текст и проверяет его на корректность
    private String getText() {
        String UserText = textField.getText();

        String result = "";

        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(UserText);
        final StringBuffer text = new StringBuffer(UserText.length());

        while (matcher.find()) {
            matcher.appendReplacement(
                    text,
                    " ");
        }

        matcher.appendTail(text);

        result = text.toString().trim();

        return result;
    }
}

