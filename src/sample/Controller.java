package sample;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Controller {

    private static String expression;
    private static String stringResult;

    private static double doubleResult;
    private static long longResult;


    public static String startWork(String str) {
        setExpression(str);
        calculation();
        return getStringResult();
    }

    // определяет, дробное или целое число введено пользователем
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
