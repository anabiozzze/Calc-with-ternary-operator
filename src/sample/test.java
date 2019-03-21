package sample;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class test {
    static double doubleResult;
    static long longResult;

    static String stringResult;

    static String foo = "(400+2)/5";

    public static void main(String[] args) {
        work(foo);
    }

    private static void calcDouble(Object a) {
        doubleResult = (double)a;
        stringResult = Double.toString(doubleResult);
    }

    private static void calcLong(Object a) {
        longResult = (int)a;
        stringResult = Long.toString(longResult);
    }

    public static void work(String str) {
//        ScriptEngineManager mgr = new ScriptEngineManager();
//        ScriptEngine engine = mgr.getEngineByName("JavaScript");
//
//        Object a;
//        try {
//            a = engine.eval(str);
//
//            if (a.getClass().equals(Double.class)) {
//                calcDouble(a);
//            } else if (a.getClass().equals(Integer.class)) {
//                calcLong(a);
//            }
//
//        } catch (ScriptException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(stringResult);
    }
}
