package com.example.avjindersinghsekhon.minimaltodo.Settings;

import android.preference.Preference;
import android.support.test.espresso.matcher.PreferenceMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.avjindersinghsekhon.minimaltodo.Main.MainActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.PreferenceMatchers.withTitle;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.hamcrest.core.IsNot.not;


@RunWith(AndroidJUnit4.class)
public class SettingsActivityTest {
    @Rule
    public ActivityTestRule<SettingsActivity> mActivityRule = new ActivityTestRule<>(
            SettingsActivity.class);

    @Test
    public void checkPreferences() {
        onData(allOf(is(
                instanceOf(Preference.class)),
                withTitle(R.string.night_mode)))
                .check(matches(isDisplayed()));
    }


    // TODO
    @Test
    public void isCheckboxChecked() {
        onData(allOf(is(
                instanceOf(Preference.class)),
                withTitle(R.string.night_mode)))
                .perform(click())
                .check(matches(isEnabled()));
        /*onData(allOf(is(
                instanceOf(Preference.class)),
                withTitle(R.string.night_mode)))
                .check(matches(not(isChecked())));*/
    }

}