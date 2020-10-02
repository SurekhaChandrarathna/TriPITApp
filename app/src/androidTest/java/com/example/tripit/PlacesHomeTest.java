package com.example.tripit;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class PlacesHomeTest
{
    @Rule
    public ActivityTestRule<PlacesHome> plhActivityTestRule= new ActivityTestRule<PlacesHome>(PlacesHome.class);

    private PlacesHome plhActivity = null;

    Instrumentation.ActivityMonitor monitorAddPl = getInstrumentation().addMonitor(AddPlacesActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorViewPl = getInstrumentation().addMonitor(PlaceListAdmin.class.getName(),null,false);
    Instrumentation.ActivityMonitor monitorBackHo = getInstrumentation().addMonitor(AdminHome.class.getName(),null,false);

    @Before
    public void setUp() throws Exception
    {
        plhActivity= plhActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchAddPlaceActivityOnButtonClick()
    {
        assertNotNull(plhActivity.findViewById(R.id.btn_admin_add_places));

        onView(withId(R.id.btn_admin_add_places)).perform(click());
        Activity addplaceActivity = getInstrumentation().waitForMonitorWithTimeout(monitorAddPl,5000);

        assertNotNull(addplaceActivity);
        addplaceActivity.finish();
    }

    @Test
    public void testLaunchViewPlaceActivityOnButtonClick()
    {
        assertNotNull(plhActivity.findViewById(R.id.btn_admin_view_places));

        onView(withId(R.id.btn_admin_view_places)).perform(click());
        Activity viewplaceActivity = getInstrumentation().waitForMonitorWithTimeout(monitorViewPl,5000);

        assertNotNull(viewplaceActivity);
        viewplaceActivity.finish();
    }

    @Test
    public void testLaunchBackHomeActivityOnButtonClick()
    {
        assertNotNull(plhActivity.findViewById(R.id.admin_backto_home));

        onView(withId(R.id.admin_backto_home)).perform(click());
        Activity backToHomeActivity = getInstrumentation().waitForMonitorWithTimeout(monitorBackHo,5000);

        assertNotNull(backToHomeActivity);
        backToHomeActivity.finish();
    }


    @After
    public void tearDown() throws Exception
    {
        plhActivity= null;
    }
}