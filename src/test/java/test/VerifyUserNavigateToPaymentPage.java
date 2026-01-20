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
        Thread.sleep(2000);
        takeScreenShot(method.getName());
        dashboardPage.selectDepature("Valencia (VLC)");
        Thread.sleep(2000);
        takeScreenShot(method.getName());
        dashboardPage.selectDestination("Barcelona (BCN)");
        Thread.sleep(2000);
        takeScreenShot(method.getName());
        dashboardPage.setTextDeparture(DateTimeUtils.getFutureDate(1));
        Thread.sleep(1000);
        takeScreenShot(method.getName());
        dashboardPage.clickSearch();
        takeScreenShot(method.getName());

        Thread.sleep(1000 * 30);
    }

}
