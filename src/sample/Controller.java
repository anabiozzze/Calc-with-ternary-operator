package sample;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    private static String expression;
    private static String stringResult;

    private static double doubleResult;
    private static long longResult;


    // главный метод класса - проверяет и задаёт строку для вычислений, запускает рассчёты,
    // отдаёт результат обратно пользователю в текстовое поле программы
    public static String startWork(String str) {
        if (checkText(str)) {
            setExpression(str);
            calculation();
            return getStringResult();
        } else {
            return "Введено некорректное значение";
        }
    }

    // этот метод использует введенный пользователем текст и проверяет его на корректность;
    // возвращает true, если текст корректен (не содержит буквы, строковые и пробельные символы)
    private static boolean checkText(String str) {
        String result = "";
        str = str.replaceAll("\\s", "");

        System.out.println(str);

        if ((str.contains("?")) && str.contains(":")) {
            ternarExpression(str);

            return true;
        }

        Pattern pattern = Pattern.compile("^[\\d-+/*><().]*$");
        Matcher matcher = pattern.matcher(str);
        final StringBuffer text = new StringBuffer(str.length());

        while (matcher.find()) {
            result = str.substring(matcher.start(), matcher.end());
        }

        return !result.equals("");
    }

    // основной метод для вычислений: принимает строку, вычисляет результат, в зависимости
    // от типа результата сохраняет данные в double, long и всегда в String (на выдачу пользователю)
    private static void calculation() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");

        Object a;
        try {
            a = engine.eval(expression);

            if (a.getClass().equals(Double.class)) {
                calcDouble(a);
            } else if (a.getClass().equals(Integer.class)) {
                calcLong(a);
            }

        } catch (ScriptException e) {
            e.printStackTrace();
        }

        System.out.println(stringResult);
    }

    private static void ternarExpression(String str) {
        int indexOperator = (str.indexOf('<') > 0) ? str.indexOf('<') : str.indexOf(">");
        int indexBracket = str.indexOf(')');
        int indexQuest = str.indexOf("?");
        int indexColon = str.indexOf(":");

        String firstNum = str.substring(1, indexOperator);
        System.out.println(firstNum);

        String secondNum = str.substring(indexOperator + 1, indexQuest - 1);
        System.out.println(secondNum);

        setExpression(firstNum);
        calculation();
        firstNum = getStringResult();
        System.out.println(firstNum);

        setExpression(secondNum);
        calculation();
        secondNum = getStringResult();
        System.out.println(secondNum);

        Boolean temp = null;

        if (str.contains("<")) {
            try {
                temp = Integer.parseInt(firstNum) < Integer.parseInt(secondNum);
            } catch (NumberFormatException e) {
                temp = Double.parseDouble(firstNum) < Double.parseDouble(secondNum);
            }

        } else {
            try {
                temp = Integer.parseInt(firstNum) > Integer.parseInt(secondNum);
            } catch (NumberFormatException e) {
                temp = Double.parseDouble(firstNum) > Double.parseDouble(secondNum);
            }
        }

        stringResult = (temp) ? (str.substring(indexQuest + 1, indexColon)) : str.substring(indexColon + 1);
        System.out.println(stringResult);
    }


    // метод определяет, дробное или целое число введено пользователем
    // высчитывает квадратный корень, возвращает как строку
    public static String squareCalc(String str) {
        if (str.contains(".")) {
            doubleResult = Math.sqrt(Double.parseDouble(str));
            stringResult = Double.toString(doubleResult);
        } else {
            longResult = (long)Math.sqrt(Long.parseLong(str));
            stringResult = Long.toString(longResult);
        }

        System.out.println(stringResult);
        return getStringResult();
    }


    private static void calcDouble(Object a) {
        doubleResult = (double)a;
        stringResult = Double.toString(doubleResult);
    }

    private static void calcLong(Object a) {
        longResult = (int)a;
        stringResult = Long.toString(longResult);
    }

    public static String getStringResult() {
        return stringResult;
    }

    public static double getDoubleResult() {
        return doubleResult;
    }

    public static long getLongResult() {
        return longResult;
    }

    public static void setExpression(String expression) {
        Controller.expression = expression;
    }
}
