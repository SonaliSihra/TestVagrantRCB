package HelperClasses;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    public static  ExtentSparkReporter spark;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static ExtentTest node;
    @BeforeSuite

    public void setup()
    {

        spark = new ExtentSparkReporter("src/");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        //ExtentTest test= extent.createTest("MyFirstTest");
        // .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");

    }

    @AfterMethod
    public void getResult(ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE)
        {
            test.fail(MarkupHelper.createLabel(result.getName() + "Test case is failed", ExtentColor.RED));
            test.fail(result.getThrowable());

        }
        else if (result.getStatus() == ITestResult.SUCCESS)
        {
            test.pass(MarkupHelper.createLabel(result.getName() + "Test case is passed", ExtentColor.GREEN));
        }
        else
        {
            test.skip(MarkupHelper.createLabel(result.getName() + "Test case is skipped", ExtentColor.YELLOW));
            test.skip(result.getThrowable());
        }

    }
    @AfterSuite
    public void tearDown()
    {
        extent.flush();

    }


}

