package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import extentReport.CustomHtmlLoggerExtension;

public class LogManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void onStart(String testName) {
        ExtentTest test = CustomHtmlLoggerExtension.getCurrentTest();
        if(test == null) {
            throw new IllegalStateException("No current test instance found. Make sure your test is annotated with @ExtendWith(CustomHtmlLoggerExtension.class)");
        }
        extentTest.set(test);
        test.log(Status.INFO, testName + " Test başladı");
    }

    public static void step(String message) {
        ExtentTest test = extentTest.get();
        if (test != null) {
            test.log(Status.INFO, message);
        }
    }

    public static void pass(String message) {
        ExtentTest test = extentTest.get();
        if(test != null) {
            test.log(Status.PASS, message);
        }
    }

    public static void fail(String message) {
        ExtentTest test = extentTest.get();
        if(test != null) {
            test.log(Status.FAIL, message);
        }
    }

    public static void info(String message) {
        ExtentTest test = extentTest.get();
        if(test != null) {
            test.log(Status.INFO, message);
        }
    }

    public static void clear() {
        extentTest.remove();
    }
}