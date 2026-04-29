package utils;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {

            //CUSTOM PATH
            String path = "C:\\Users\\Bhavya Sree\\git\\repository4\\AutoQA\\reports\\ExtentReport.html";

            ExtentSparkReporter spark = new ExtentSparkReporter(path);

            spark.config().setReportName("Automation Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Bhavya");
            extent.setSystemInfo("Environment", "QA");

            System.out.println("Extent Report Generated At: " + path);
        }

        return extent;
    }
}