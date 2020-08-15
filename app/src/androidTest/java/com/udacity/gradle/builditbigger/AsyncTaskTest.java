package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

//https://developer.android.com/studio/test#test_types_and_location
//https://github.com/udacity/ud867/tree/master/4.09-Demo-AndroidUnitTesting/app/src

import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.concurrent.CountDownLatch;

//https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework

@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest extends AndroidTestCase {

    //https://stackoverflow.com/questions/24319697/java-lang-exception-no-runnable-methods-exception-in-running-junits/24319836#24319836

    @Test
    public void runAsyncTaskTest() {
        final CountDownLatch latch = new CountDownLatch(1);

        String joke = "";
        EndpointsAsyncTask jokesAsyncTask = new EndpointsAsyncTask();
        jokesAsyncTask.execute(new Pair<Context, String>(getContext(), ""));
        try {
            joke = jokesAsyncTask.get();
            Log.d("AsyncTaskTest", "got non empty joke : " + joke);

            latch.countDown();

            assertNotNull("joke is null", joke);
            assertFalse("joke is empty", TextUtils.isEmpty(joke));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //assertNotNull(joke);
    }
}