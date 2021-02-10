package com.example.avjindersinghsekhon.minimaltodo;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.avjindersinghsekhon.minimaltodo.About.AboutActivity;
import com.example.avjindersinghsekhon.minimaltodo.AddToDo.AddToDoActivity;
import com.example.avjindersinghsekhon.minimaltodo.Main.MainActivity;
import com.example.avjindersinghsekhon.minimaltodo.Settings.SettingsActivity;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);

    /*@Before
    public void setUp() throws Exception {
        super.setUp();
    }*/

    @Test
    public void checkMyCoordinatorLayout() {
        onView(ViewMatchers.withId(R.id.myCoordinatorLayout))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkAddToDoItemFAB() {
        onView(ViewMatchers.withId(R.id.addToDoItemFAB))
                .check(matches(allOf(isDisplayed(),
                        isClickable())));
    }

    @Test
    public void checkToolbar() {
        onView(ViewMatchers.withId(R.id.toolbar))
                .check(matches(isDisplayed()));
    }

    /*@Test
    public void clickAddToDoItemFAB() {
        onView(ViewMatchers.withId(R.id.addToDoItemFAB))
                .perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.toDoCustomTextInput))
                .check(matches(isDisplayed()));
    }*/

    @Test
    public void checkActionsBar() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText(R.string.action_settings))
                .check(matches(isDisplayed()));
        onView(ViewMatchers.withText(R.string.about))
                .check(matches(isDisplayed()));
    }

    @Test
    public void startAddToDoFromAddToDoItemFAB(){
        onView(ViewMatchers.withId(R.id.addToDoItemFAB))
                .perform(ViewActions.click());
        intended(hasComponent(AddToDoActivity.class.getName()));
    }

    @Test
    public void startSettingsActivityFromOptionsBar(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText(R.string.action_settings))
                .perform(ViewActions.click());
        intended(hasComponent(SettingsActivity.class.getName()));
    }

    @Test
    public void startAboutActivityFromOptionsBar(){
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(ViewMatchers.withText(R.string.about))
                .perform(ViewActions.click());
        intended(hasComponent(AboutActivity.class.getName()));
    }


}