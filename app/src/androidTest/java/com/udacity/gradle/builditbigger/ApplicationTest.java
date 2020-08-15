package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

//https://developer.android.com/studio/test#test_types_and_location
//https://github.com/udacity/ud867/tree/master/4.09-Demo-AndroidUnitTesting/app/src

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)

public class ApplicationTest extends ApplicationTestCase<Application> {

    public ApplicationTest() {
        super(Application.class);
    }
}
