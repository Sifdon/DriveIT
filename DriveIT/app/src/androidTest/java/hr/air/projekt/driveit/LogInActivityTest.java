package hr.air.projekt.driveit;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LogInActivityTest {

    @Rule
    public ActivityTestRule<LogInActivity> mActivityTestRule = new ActivityTestRule<>(LogInActivity.class);

    @Test
    public void logInActivityTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.add_user), withText("Add user"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.add_user_firstName), isDisplayed()));
        appCompatEditText.perform(replaceText("Mickey"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.add_user_lastName), isDisplayed()));
        appCompatEditText2.perform(replaceText("Mouse"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.add_user_email), isDisplayed()));
        appCompatEditText3.perform(replaceText("mmouse@lummax.de "), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.add_user_password), isDisplayed()));
        appCompatEditText4.perform(replaceText("123456"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.add_user_repeatPassword), isDisplayed()));
        appCompatEditText5.perform(replaceText("123456"), closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.add_user_button_addUser), withText("Add user"), isDisplayed()));
        appCompatButton2.perform(click());

    }

}
