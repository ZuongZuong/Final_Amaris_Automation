package test;

import io.qameta.allure.*;
import listeners.ListenerEx;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;
import page.DashboardPage;
import utils.DateTimeUtils;

import java.lang.reflect.Method;

@Epic("Regression Tests")
@Feature("Navigation")
@Listeners(ListenerEx.class)
public class VerifyUserNavigateToPaymentPage extends BaseTest {

    @Story("User navigates to payment page")
    @Description("This test case is created to verify that user can see error message after clicking on backwards")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("ZuongZuong")
    @Issue("ISSUE-123")
    @TmsLink("TMS-456")
    @Test(description = "Verify User Able To Backwards")
    public void verifyUserAbleToBackwards(Method method) throws InterruptedException {
        DashboardPage dashboardPage = new DashboardPage(getDriver());
        dashboardPage.selectTripOption("Solo ida");
        dashboardPage.selectDepature("Valencia (VLC)");
        dashboardPage.selectDestination("Barcelona (BCN)");
        dashboardPage.setTextDeparture(DateTimeUtils.getFutureDate(1));
        dashboardPage.clickSearch();
    }

}
