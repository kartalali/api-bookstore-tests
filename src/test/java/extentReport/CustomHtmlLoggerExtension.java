package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomHtmlLoggerExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    private static final ThreadLocal<ExtentReports> REPORTS = new ThreadLocal<>();
    private static final ThreadLocal<ExtentTest> CURRENT_TEST = new ThreadLocal<>();
    private static final ThreadLocal<String> REPORT_FILE_PATH = new ThreadLocal<>();

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        String testName = context.getDisplayName().replaceAll("[^a-zA-Z0-9]", "_");
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

        Path logDir = Paths.get("src/test/html_logs");
        if (!Files.exists(logDir)) {
            Files.createDirectories(logDir);
        }

        String fileName = testName + "_" + timestamp + ".html";
        Path reportPath = logDir.resolve(fileName);
        REPORT_FILE_PATH.set(reportPath.toString());

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath.toString());
        reporter.config().setTheme(Theme.STANDARD);
        reporter.config().setDocumentTitle("Test Log for " + testName);
        reporter.config().setReportName(testName + " Log");

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Test Name", testName);
        extent.setSystemInfo("Timestamp", timestamp);
        REPORTS.set(extent);

        ExtentTest test = extent.createTest(testName);
        CURRENT_TEST.set(test);

        System.out.println("TEST STARTED: " + testName + " at " + timestamp);
        test.info("Test started at " + timestamp);
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        ExtentReports extent = REPORTS.get();
        if (extent != null) {
            extent.flush();
        }

        boolean testFailed = context.getExecutionException().isPresent();
        String resultFolder = testFailed ? "FAIL" : "PASS";

        String originalPath = REPORT_FILE_PATH.get();
        Path originalFile = Paths.get(originalPath);

        Path targetFolder = originalFile.getParent().resolve(resultFolder);
        if (!Files.exists(targetFolder)) {
            Files.createDirectories(targetFolder);
        }
        String newFileName = originalFile.getFileName().toString();
        Path newFilePath = targetFolder.resolve(newFileName);

        Files.move(originalFile, newFilePath);
        System.out.println("Rapor dosyası " + (testFailed ? "FAIL" : "PASS") + " klasörüne taşındı: "
                + newFilePath);

        String filePath = REPORT_FILE_PATH.get();
        System.out.println("TEST FINISHED: " + context.getDisplayName() + ". HTML log created at " + filePath);


        REPORTS.remove();
        CURRENT_TEST.remove();
        REPORT_FILE_PATH.remove();
    }

    public static ExtentTest getCurrentTest() {
        return CURRENT_TEST.get();
    }

    public static void clearCurrentTest() {
        CURRENT_TEST.remove();
    }
}