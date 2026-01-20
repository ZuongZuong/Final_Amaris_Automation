package test;

import org.testng.annotations.Test;
import page.DashboardPage;
import utils.DateTimeUtils;

import java.lang.reflect.Method;

public class VerifyUserNavigateToPaymentPage extends BaseTest {

    @Test
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
