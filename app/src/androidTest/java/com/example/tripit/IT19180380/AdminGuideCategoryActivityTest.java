package com.example.tripit.IT19180380;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tripit.AddPlacesActivity;
import com.example.tripit.AdminAddNewGuidesActivity;
import com.example.tripit.AdminGuideCategoryActivity;
import com.example.tripit.AdminHome;
import com.example.tripit.GuideHome;
import com.example.tripit.MaintainGuidesActivity;
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

public class AdminGuideCategoryActivityTest
{
    @Rule
    public ActivityTestRule<AdminGuideCategoryActivity> categoryActivityTestRule= new ActivityTestRule<AdminGuideCategoryActivity>(AdminGuideCategoryActivity.class);

    private AdminGuideCategoryActivity categoryActivity = null;

    Instrumentation.ActivityMonitor maintainguide = getInstrumentation().addMonitor(MaintainGuidesActivity.class.getName(),null,false);
    Instrumentation.ActivityMonitor maintainguide2 = getInstrumentation().addMonitor(GuideHome.class.getName(),null,false);
    Instrumentation.ActivityMonitor maintainguideImage = getInstrumentation().addMonitor(AdminHome.class.getName(),null,false);



    @Before
    public void setUp() throws Exception
    {
        categoryActivity= categoryActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchMaintainGuideOnButtonClick()
    {
        assertNotNull(categoryActivity.findViewById(R.id.guideMaintain));

        onView(withId(R.id.guideMaintain)).perform(click());
        Activity maintainguide1 = getInstrumentation().waitForMonitorWithTimeout(maintainguide,5000);

        assertNotNull(maintainguide1);
        maintainguide1.finish();
    }

    @Test
    public void testLaunchMaintainGuide2OnButtonClick()
    {
        assertNotNull(categoryActivity.findViewById(R.id.guideView));

        onView(withId(R.id.guideView)).perform(click());
        Activity maintaingu = getInstrumentation().waitForMonitorWithTimeout(maintainguide2,5000);

        assertNotNull(maintaingu);
        maintaingu.finish();
    }

    @Test
    public void testLaunchBackHomeActivityOnButtonClick()
    {
        assertNotNull(categoryActivity.findViewById(R.id.admin_backto_home));

        onView(withId(R.id.admin_backto_home)).perform(click());
        Activity backToHomeActivityGuide = getInstrumentation().waitForMonitorWithTimeout(maintainguideImage,5000);

        assertNotNull(backToHomeActivityGuide);
        backToHomeActivityGuide.finish();
    }


    @Test
    public void launchFirstImage()
    {
        View view = categoryActivity.findViewById(R.id.withVehicals);
        assertNotNull(view);
    }

    @Test
    public void launchSecondImage()
    {
        View view2 = categoryActivity.findViewById(R.id.withOutVehicals);
        assertNotNull(view2);
    }



    @After
    public void tearDown() throws Exception
    {
        categoryActivity= null;
    }
}