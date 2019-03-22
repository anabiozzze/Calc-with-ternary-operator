package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

    private Scene scene; // наше окно, на котором будут располагаться все прочие элементы
    private Group group; // основной контейнер с координатами 0.0 по размеру окна
    protected volatile static BorderPane pane; // второй контейнер - в нем будут лежать все графические элементы

    private TextField textField; // текстовое поле для ввода ссылок
    private VBox vBox;
    private FlowPane fPane;

    private String result;


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

        btn_AC.setStyle("-fx-background-color: floralwhite");
        btn_multiply.setStyle("-fx-background-color: whitesmoke");
        btn_divide.setStyle("-fx-background-color: whitesmoke");
        btn_equals.setStyle("-fx-background-color: whitesmoke");
        btn_minus.setStyle("-fx-background-color: whitesmoke");
        btn_plus.setStyle("-fx-background-color: whitesmoke");

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

        // задаем кнопкам действия
        btn_one.setOnAction(event -> textField.appendText("1"));
        btn_two.setOnAction(event -> textField.appendText("2"));
        btn_three.setOnAction(event -> textField.appendText("3"));
        btn_four.setOnAction(event -> textField.appendText("4"));
        btn_five.setOnAction(event -> textField.appendText("5"));
        btn_six.setOnAction(event -> textField.appendText("6"));
        btn_seven.setOnAction(event -> textField.appendText("7"));
        btn_eight.setOnAction(event -> textField.appendText("8"));
        btn_nine.setOnAction(event -> textField.appendText("9"));
        btn_zero.setOnAction(event -> textField.appendText("0"));
        btn_plus.setOnAction(event -> textField.appendText("+"));
        btn_minus.setOnAction(event -> textField.appendText("-"));
        btn_divide.setOnAction(event -> textField.appendText("/"));
        btn_multiply.setOnAction(event -> textField.appendText("*"));
        btn_comma.setOnAction(event -> textField.appendText("."));
        btn_colon.setOnAction(event -> textField.appendText(":"));
        btn_question.setOnAction(event -> textField.appendText("?"));
        btn_more.setOnAction(event -> textField.appendText(">"));
        btn_less.setOnAction(event -> textField.appendText("<"));
        btn_leftBrack.setOnAction(event -> textField.appendText("("));
        btn_rightBrack.setOnAction(event -> textField.appendText(")"));
        btn_AC.setOnAction(event -> textField.clear());

        btn_square.setOnAction(event -> {
            // "квадрат" берет число из textField и вычисляет его корень, затем
            // выводит результат обратно в textField в формате String
            result = Controller.squareCalc(textField.getText());
            checkAndShow();
        });

        btn_equals.setOnAction(event -> {
            // "равно" берет все данные из textField и производит вычисления, затем
            // выводит результат обратно в textField в формате String
            result = Controller.startWork(textField.getText());
            checkAndShow();
        });

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

    // метод следит за реакцией контроллера на введенную строку; если она некорректна -
    // метод сообщает об ошибке в окке программы. Если корректна - выводит результат в окно программы.
    private void checkAndShow() {
        if (result.equals("Введено некорректное значение")) {

            textField.clear();
            textField.setPromptText("Введено некорректное значение");

        } else {
            textField.setPromptText("Введите выражение:");
            textField.clear();
            textField.insertText(0, result);
        }
    }
}

