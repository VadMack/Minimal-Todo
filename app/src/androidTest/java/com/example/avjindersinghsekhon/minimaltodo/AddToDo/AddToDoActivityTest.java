package com.example.avjindersinghsekhon.minimaltodo.AddToDo;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.example.avjindersinghsekhon.minimaltodo.R;
import com.wdullaer.materialdatetimepicker.date.DatePickerController;
import com.wdullaer.materialdatetimepicker.date.DayPickerView;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Field;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


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

    @Test
    public void settingDate() {

        onView(ViewMatchers.withId(R.id.toDoHasDateSwitchCompat))
                .perform(click())
                .check(matches(isChecked()));

        onView(withId(R.id.newTodoDateEditText))
                .check(matches(isClickable()))
                .perform(click());

        onView(isAssignableFrom(DayPickerView.class))
                .check(matches(isDisplayed()))
                .perform(setDate(2030, 3, 10));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.newTodoDateEditText))
                .check(matches(withText("10 Apr, 2030")));
    }

    @Test
    public void settingTime() {

        onView(ViewMatchers.withId(R.id.toDoHasDateSwitchCompat))
                .perform(click())
                .check(matches(isChecked()));

        onView(withId(R.id.newTodoTimeEditText))
                .check(matches(isClickable()))
                .perform(click());

        onView(isAssignableFrom(RadialPickerLayout.class))
                .check(matches(isDisplayed()))
                .perform(setTime(6, 30));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.newTodoTimeEditText))
                .check(matches(withText("6:30 AM")));
    }

    @Test
    public void settingDateAndTime() {
        onView(ViewMatchers.withId(R.id.toDoHasDateSwitchCompat))
                .perform(click())
                .check(matches(isChecked()));

        onView(withId(R.id.newTodoDateEditText))
                .check(matches(isClickable()))
                .perform(click());

        onView(isAssignableFrom(DayPickerView.class))
                .check(matches(isDisplayed()))
                .perform(setDate(2030, 3, 10));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.newTodoTimeEditText))
                .check(matches(isClickable()))
                .perform(click());

        onView(isAssignableFrom(RadialPickerLayout.class))
                .check(matches(isDisplayed()))
                .perform(setTime(6, 30));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.newToDoDateTimeReminderTextView))
                .check(matches(withText("Reminder set for 10 Apr, 2030, 6:30 AM")));
    }

    public static ViewAction setDate(final int year, final int monthOfYear, final int dayOfMonth) {

        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                final DayPickerView dayPickerView = (DayPickerView) view;

                try {
                    Field f = null; //NoSuchFieldException
                    f = DayPickerView.class.getDeclaredField("mController");
                    f.setAccessible(true);
                    DatePickerController controller = (DatePickerController) f.get(dayPickerView); //IllegalAccessException
                    controller.onDayOfMonthSelected(year, monthOfYear, dayOfMonth);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public String getDescription() {
                return "set date";
            }

            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(DayPickerView.class), isDisplayed());
            }
        };

    }

    public static ViewAction setTime(final int hours, final int minutes) {

        return new ViewAction() {

            @Override
            public void perform(UiController uiController, View view) {
                final RadialPickerLayout timePicker = (RadialPickerLayout) view;

                timePicker.setTime(hours, minutes);
            }

            @Override
            public String getDescription() {
                return "set time";
            }

            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isAssignableFrom(RadialPickerLayout.class), isDisplayed());
            }
        };

    }


}