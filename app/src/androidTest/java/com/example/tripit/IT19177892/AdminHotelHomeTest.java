package com.example.tripit.IT19177892;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tripit.AddHotelActivity;
import com.example.tripit.AddPlacesActivity;
import com.example.tripit.AdminHome;
import com.example.tripit.AdminHotelHome;
import com.example.tripit.PlaceListAdmin;
import com.example.tripit.PlacesHome;
import com.example.tripit.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class AdminHotelHomeTest
{
    @Rule
    public ActivityTestRule<AdminHotelHome> AHHActivityTestRule= new ActivityTestRule<AdminHotelHome>(AdminHotelHome.class);

    private AdminHotelHome AHHActivity = null;

    Instrumentation.ActivityMonitor addhotelmonitoe = getInstrumentation().addMonitor(AddHotelActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor adminhomemonitor = getInstrumentation().addMonitor(AdminHome.class.getName(),null,false);

    @Before
    public void setUp() throws Exception
    {
        AHHActivity= AHHActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchBtnAddPlaceOnClick()
    {
        assertNotNull(AHHActivity.findViewById(R.id.btn_admin_add_hotels));

        onView(withId(R.id.btn_admin_add_hotels)).perform(click());
        Activity addhotels = getInstrumentation().waitForMonitorWithTimeout(addhotelmonitoe,5000);

        assertNotNull(addhotels);
        addhotels.finish();
    }

    @Test
    public void testLaunchBtnGoHomeOnClick()
    {
        assertNotNull(AHHActivity.findViewById(R.id.admin_backto_home));

        onView(withId(R.id.admin_backto_home)).perform(click());
        Activity backToHomeActivityhotel = getInstrumentation().waitForMonitorWithTimeout(adminhomemonitor,5000);

        assertNotNull(backToHomeActivityhotel);
        backToHomeActivityhotel.finish();
    }

    @Test
    public void launchFirstImage()
    {
        View view = AHHActivity.findViewById(R.id.addhotels);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception
    {
        AHHActivity= null;
    }
}