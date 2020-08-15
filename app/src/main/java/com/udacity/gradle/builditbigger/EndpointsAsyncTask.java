package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.example.android.androidlibrary.AndroidLibraryActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

//https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend

public class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Pair<Context, String>... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            //Once you have deployed your backend to App Engine,
            //you can connect your Android app to it by modifying EndpointsAsyncTask class
            //defined in section 2 above. At this point you should be all set to run your Android app
            //in an emulator or on the physical device,
            //and successfully communicate with your new App Engine backend!

            //MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
            //.setRootUrl("https://android-app-backend.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        context = params[0].first;
        String name = params[0].second;

        try {
            //return myApiService.sayHi(name).execute().getData();
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            //return e.getMessage();
            return "";
        }
    }

    //https://github.com/udacity/ud867/blob/master/4.03-Demo-CreatingAnAndroidLibrary/app/src/main/java/com/udacity/gradle/androidlibrarydemo/MainActivity.java
    //Intent to Android Library Activity

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();

        Intent myIntent = new Intent(context, AndroidLibraryActivity.class);
        myIntent.putExtra("JOKE", result);
        context.startActivity(myIntent);
    }
}