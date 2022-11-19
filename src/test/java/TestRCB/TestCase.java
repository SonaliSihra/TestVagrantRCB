package TestRCB;

import HelperClasses.BaseTest;
import HelperClasses.CommonMethods;
import com.aventstack.extentreports.Status;
import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.util.Iterator;

public class TestCase extends BaseTest {

    @Test(description = "Verify that the team has only 4 foreign player")
    public void verifyForeignPlayer() {

        test = extent.createTest("Verify that the team has only 4 foreign player");
        int countPlayerCountry = 0;

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/test/resources/TeamRCB.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray playerCount = (JSONArray) jsonObject.get("player");
            Iterator i = playerCount.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                if (!innerObj.get("country").equals("India")) {
                    countPlayerCountry++;
                }
            }

            //Assert.assertEquals(countPlayerCountry, 4, "Checking if the player country count is 4");
            CommonMethods.verifyEquals(countPlayerCountry, 4, "Checking if the player country count is 4");
            test.log(Status.INFO, "Checking if the player country count is 4");//To send logs in html report.

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test(description = "Verify that there is at least one wicket keeper")
    public void verifyWicketKeeper() {

        test = extent.createTest("Verify that there is at least one wicket keeper");


        int wicketKeeperCount = 0;

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/test/resources/TeamRCB.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray playerCount = (JSONArray) jsonObject.get("player");

            Iterator i = playerCount.iterator();

            while (i.hasNext()) {
                JSONObject innerObj = (JSONObject) i.next();
                if (innerObj.get("role").equals("Wicket-keeper")) {
                    wicketKeeperCount++;
                }
            }

            //Assert.assertTrue(wicketKeeperCount >= 1, "Checking if the Wicket-keeper count is greater than equal to 1");
            CommonMethods.verifyTrue(wicketKeeperCount >= 1, "Checking if the Wicket-keeper count is greater than equal to 1");
            test.log(Status.INFO, "Checking if the Wicket-keeper count is greater than equal to 1");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
