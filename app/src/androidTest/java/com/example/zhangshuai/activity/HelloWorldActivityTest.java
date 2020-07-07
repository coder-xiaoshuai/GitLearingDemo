package com.example.zhangshuai.activity;

import android.content.ActivityNotFoundException;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.zhangshuai.gitlearingdemo.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class HelloWorldActivityTest {
    @Rule
    public ActivityTestRule<HelloWorldActivity> rule = new ActivityTestRule(HelloWorldActivity.class, false, true);

    //    @Rule
//    public ActivityScenarioRule<HelloWorldActivity> activityScenarioRule = new ActivityScenarioRule<HelloWorldActivity>(HelloWorldActivity.class);
    @Test
    public void testUI() {
        onView(withId(R.id.text_hello_world)).check(matches(withText("hello world!")));
    }
}