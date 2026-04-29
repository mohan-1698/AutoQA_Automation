package utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String capture(WebDriver driver, String name) {

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

            // SAME BASE PATH
            String path = "C:\\Users\\Bhavya Sree\\git\\repository4\\AutoQA\\reports\\screenshots\\"
                    + name + "_" + timestamp + ".png";

            File dest = new File(path);

            // Create folder if not exists
            dest.getParentFile().mkdirs();

            FileUtils.copyFile(src, dest);

            return path;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}