package com.example.avjindersinghsekhon.minimaltodo.AddToDo;


import android.app.TimePickerDialog;
import android.app.VoiceInteractor;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.avjindersinghsekhon.minimaltodo.Main.MainActivity;
import com.example.avjindersinghsekhon.minimaltodo.R;
import com.example.avjindersinghsekhon.minimaltodo.Settings.SettingsActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;



@RunWith(AndroidJUnit4.class)
public class AddToDoActivityTest {

    @Rule
    public ActivityTestRule<AddToDoActivity> mActivityRule = new ActivityTestRule<>(
            AddToDoActivity.class);

    @Test
    public void checkUserToDoEditText() {
        onView(ViewMatchers.withId(R.id.userToDoEditText))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkUserToDoDescription() {
        onView(ViewMatchers.withId(R.id.userToDoDescription))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkMakeToDoFloatingActionButton() {
        onView(ViewMatchers.withId(R.id.makeToDoFloatingActionButton))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkToDoHasDateSwitchCompat() {
        onView(ViewMatchers.withId(R.id.toDoHasDateSwitchCompat))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkCopyclipboard() {
        onView(ViewMatchers.withId(R.id.copyclipboard))
                .check(matches(isDisplayed()));
    }


    @Test
    public void typeUserToDoEditText() {
        onView(ViewMatchers.withId(R.id.userToDoEditText))
                .perform(typeText("Buy one apple"));

        onView(ViewMatchers.withId(R.id.userToDoEditText))
                .check(matches(withText("Buy one apple")));
    }

    @Test
    public void typeUserToDoDescription() {
        onView(ViewMatchers.withId(R.id.userToDoDescription))
                .perform(typeText("My description"));

        onView(ViewMatchers.withId(R.id.userToDoDescription))
                .check(matches(withText("My description")));
    }

    @Test
    public void enableNotification() {
        onView(ViewMatchers.withId(R.id.toDoHasDateSwitchCompat))
                .perform(click());
        onView(ViewMatchers.withId(R.id.toDoHasDateSwitchCompat))
                .check(matches(isChecked()));


    }

    @Test
    public void checkDatePicker() {
        onView(ViewMatchers.withId(R.id.toDoHasDateSwitchCompat))
                .perform(click());
        onView(ViewMatchers.withId(R.id.newTodoDateEditText))
                .check(matches(isDisplayed()));
    }

    // TODO
    @Test
    public void settingDate() {

        onView(ViewMatchers.withId(R.id.toDoHasDateSwitchCompat))
                .perform(click());


        onView(withId(R.id.newTodoDateEditText))
                .perform(click());

        onView(
        //onData(
                isAssignableFrom(DatePicker.class))
                //withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(
                PickerActions.setDate(
                        2022,
                        10,
                        10
                )
        );
    }


}