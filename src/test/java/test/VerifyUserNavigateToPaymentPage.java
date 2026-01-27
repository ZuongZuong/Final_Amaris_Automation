package test;

import io.qameta.allure.*;
import listeners.AllureTestListener;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import page.DashboardPage;
import utils.DateTimeUtils;

import java.lang.reflect.Method;

@Listeners(AllureTestListener.class)
public class VerifyUserNavigateToPaymentPage extends BaseTest {

    @Description("This test case is created to verify that user can see error message after clicking on backwards")
    @Test(description = "Verify User Able To Backwards")
    public void verifyUserAbleToBackwards(Method method) throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.selectTripOption("One way");
        takeScreenShot(method.getName());
        dashboardPage.selectDepature("Valencia (VLC)");
        takeScreenShot(method.getName());
        dashboardPage.selectDestination("Barcelona (BCN)");
        takeScreenShot(method.getName());
        dashboardPage.setTextDeparture(DateTimeUtils.getFutureDate(1));
        takeScreenShot(method.getName());
        dashboardPage.clickSearch();
        takeScreenShot(method.getName());

    }

}
