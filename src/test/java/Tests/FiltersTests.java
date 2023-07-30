package Tests;

import com.testframework.baseBrowser;
import org.SwagLabsPages.FiltersPage;
import org.SwagLabsPages.LogInPage;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.testframework.baseBrowser.Baseurl;
import static com.testframework.baseBrowser.driver;

public class FiltersTests extends baseBrowser {

    @Test
    void accessingFilters() {

        driver.get(Baseurl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        LogInPage logInPage = new LogInPage(driver);
        logInPage.userLogInSuccessful();

        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage.filterAccess();

    }
}

