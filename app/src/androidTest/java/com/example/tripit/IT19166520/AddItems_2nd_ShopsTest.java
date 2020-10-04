package com.example.tripit.IT19166520;

import android.view.View;

import androidx.test.rule.ActivityTestRule;

import com.example.tripit.AddItems_2nd_Shops;
import com.example.tripit.PlacesHome;
import com.example.tripit.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddItems_2nd_ShopsTest
{
    @Rule
    public ActivityTestRule<AddItems_2nd_Shops> shopActivityTestRule= new ActivityTestRule<AddItems_2nd_Shops>(AddItems_2nd_Shops.class);

    private AddItems_2nd_Shops shopActivity = null;

    @Before
    public void setUp() throws Exception
    {
        shopActivity= shopActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchImage1()
    {
        View view = shopActivity.findViewById(R.id.T_shirt);
        assertNotNull(view);
    }

    @Test
    public void testLaunchImage2()
    {
        View view = shopActivity.findViewById(R.id.mug);
        assertNotNull(view);
    }

    @Test
    public void testLaunchImage3()
    {
        View view = shopActivity.findViewById(R.id.bag);
        assertNotNull(view);
    }

    @Test
    public void testLaunchImage4()
    {
        View view = shopActivity.findViewById(R.id.cap);
        assertNotNull(view);
    }

    @Test
    public void testLaunchImage5()
    {
        View view = shopActivity.findViewById(R.id.bottle);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception
    {
        shopActivity= null;
    }
}