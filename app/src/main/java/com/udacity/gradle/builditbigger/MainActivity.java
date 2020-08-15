package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Pair;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.javalibrary.JokeLibrary;

//https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/77e9910911d5412e5efede5fa681ec105a0f02ad/HelloEndpoints#2-connecting-your-android-app-to-the-backend

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

        //https://github.com/udacity/ud867/blob/master/4.01-Demo-CreatingAJavaLibrary/app/src/main/java/com/udacity/gradle/javalibrarydemo/MainActivityFragment.java

        TextView textView = (TextView) findViewById(R.id.instructions_text_view);
        JokeLibrary myJoke = new JokeLibrary();
        textView.setText(myJoke.getJoke());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();

        //https://stackoverflow.com/questions/9570237/android-check-internet-connection
        //http://www.androidhive.info/2012/07/android-detect-internet-connection-status/

        if (!isNetworkAvailable(this)) {
            Toast.makeText(this, "Your device is not online, " +
                            "check wifi and try again!",
                    Toast.LENGTH_LONG).show();
        } else {

            new EndpointsAsyncTask().execute(new Pair<Context, String>(this, "Manfred"));

        }

        //https://developer.android.com/guide/components/intents-filters.html
        //https://github.com/udacity/ud867/blob/master/4.03-Demo-CreatingAnAndroidLibrary/app/src/main/java/com/udacity/gradle/androidlibrarydemo/MainActivity.java

        //Intent intent = new Intent(this, AndroidLibraryActivity.class);
        //intent.putExtra(AndroidLibraryActivity.JOKE_KEY, true);
        //this.startActivity(intent);

    }

    //https://stackoverflow.com/questions/9570237/android-check-internet-connection
    //http://www.androidhive.info/2012/07/android-detect-internet-connection-status/

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
