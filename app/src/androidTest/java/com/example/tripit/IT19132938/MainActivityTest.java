package com.example.tripit.IT19132938;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import com.example.tripit.LoginActivity;
import com.example.tripit.MainActivity;
import com.example.tripit.R;
import com.example.tripit.RegisterActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule= new ActivityTestRule<MainActivity>(MainActivity.class);

    private MainActivity mActivity = null;

    Instrumentation.ActivityMonitor monitor1 = getInstrumentation().addMonitor(LoginActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitor2 = getInstrumentation().addMonitor(RegisterActivity.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mActivity= mActivityTestRule.getActivity();

    }

    @Test
    public void testLaunchLoginActivityOnButtonClick()
    {
        assertNotNull(mActivity.findViewById(R.id.min_login_button));

        onView(withId(R.id.min_login_button)).perform(click());
        Activity loginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor1,5000);

        assertNotNull(loginActivity);
        loginActivity.finish();
    }

    @Test
    public void testLaunchRegisterActivityOnButtonClick()
    {
        assertNotNull(mActivity.findViewById(R.id.min_join_button));

        onView(withId(R.id.min_join_button)).perform(click());
        Activity registerActivity = getInstrumentation().waitForMonitorWithTimeout(monitor2,5000);

        assertNotNull(registerActivity);
        registerActivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mActivity= null;

    }
}