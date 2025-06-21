package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentReport.CustomHtmlLoggerExtension;

public class HtmlLogManager {

    private static ExtentTest getCurrentTest() {
        ExtentTest test = CustomHtmlLoggerExtension.getCurrentTest();
        if (test == null) {
            throw new IllegalStateException("No current test instance found. Ensure that your test class is annotated with @ExtendWith(CustomHtmlLoggerExtension.class)");
        }
        return test;
    }

    public static void onStart(String message) {
        getCurrentTest().info("onStart: " + message);
        System.out.println("onStart: " + message);
    }

    public static void step(String message) {
        getCurrentTest().log(Status.INFO, message);
        System.out.println("STEP: " + message);
    }

    public static void pass(String message) {
        getCurrentTest().log(Status.PASS, message);
        System.out.println("PASS: " + message);
    }

    public static void fail(String message) {
        getCurrentTest().log(Status.FAIL, message);
        System.out.println("FAIL: " + message);
    }

    public static void info(String message) {
        getCurrentTest().log(Status.INFO, message);
        System.out.println("INFO: " + message);
    }

    public static void clear() {
        CustomHtmlLoggerExtension.clearCurrentTest();
        System.out.println("Current test instance cleared.");
    }

}