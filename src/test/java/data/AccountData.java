package data;

import constants.FrameworkConstants;
import org.testng.annotations.DataProvider;
import helper.JsonHelper;

public class AccountData {

    @DataProvider(name = "AccountData")
    public static Object[][] getAccount() {
        return JsonHelper.ReadJsonFromFile(FrameworkConstants.PROJECT_PATH + "\\source\\accounts.json");
    }

}
