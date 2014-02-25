package fr.xebia.functional.test;

import android.test.ActivityInstrumentationTestCase2;
import com.google.android.apps.common.testing.ui.espresso.contrib.DrawerActions;
import com.robotium.solo.By;
import com.robotium.solo.Solo;
import com.squareup.spoon.Spoon;
import fr.xebia.functional.MainActivity;
import fr.xebia.functional.R;
import org.fest.assertions.api.Assertions;

import static com.google.android.apps.common.testing.ui.espresso.Espresso.onData;
import static com.google.android.apps.common.testing.ui.espresso.action.ViewActions.click;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());
        getActivity();
    }

    public void testShowNavigationInsideApplication() {
        DrawerActions.openDrawer(R.id.drawer_layout);

        solo.sleep(3000);

        onData(allOf(instanceOf(String.class), equalTo("LE MONDE"))).perform(click());

        solo.sleep(3000);

        Spoon.screenshot(getActivity(), "le_monde");

        DrawerActions.openDrawer(R.id.drawer_layout);

        solo.sleep(3000);

        onData(allOf(instanceOf(String.class), equalTo("L'EQUIPE"))).perform(click());

        solo.sleep(3000);

        Spoon.screenshot(getActivity(), "l_equipe");

        boolean chronoTxtFound = solo.waitForWebElement(By.textContent("Chrono"));
        Assertions.assertThat(chronoTxtFound).isTrue();

        solo.clickOnWebElement(By.textContent("Chrono"));

        solo.sleep(3000);

        solo.clickOnWebElement(By.className("chrono-heure"), 25);

        Spoon.screenshot(getActivity(), "chrono");

        solo.sleep(3000);

        solo.clickOnWebElement(By.className("reagissez btn-full"));

        solo.typeTextInWebElement(By.id("login-box-input-login"),"my-mail@mail.com");

        solo.typeTextInWebElement(By.id("login-box-input-pwd"), "alongpassword");

        solo.sleep(3000);

        solo.clickOnWebElement(By.id("login-box-login-button"));

        solo.sleep(3000);

        Spoon.screenshot(getActivity(), "comment");
    }
}
