package com.example.android.javalibrary;

//https://github.com/udacity/ud867/blob/master/4.01-Demo-CreatingAJavaLibrary/javaJokes/src/main/java/com/udacity/gradle/jokes/Joker.java

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class JokeLibrary {

    //https://stackoverflow.com/questions/290884/what-is-the-reason-behind-non-static-method-cannot-be-referenced-from-a-static

    public static List<String> mJokeLibrary;

    //http://pun.me/pages/funny-riddles.php

    public JokeLibrary () {

        mJokeLibrary = Arrays.asList(
                "What two things can you never eat for breakfast? Lunch and dinner",
                "What has a face and two hands but no arms or legs? A clock",
                "What kind of room has no doors or windows? A mushroom",
                "During what month do people sleep the least? February, it is the shortest month",
                "What goes up and never comes down? Your age",
                "What word becomes shorter when you add two letters to it? Short",
                "Two children are born on the same day from the same mother but they are not twins. How is that possible? They are triplets",
                "What gets wetter the more it dries? A towel",
                "Which word in the dictionary is spelled incorrectly? Incorrectly",
                "You can't keep this until you have given it.... A promise",
                "What grows when it eats, but dies when it drinks? Fire",
                "What spends all the time on the floor but never gets dirty? Your shadow"
        );

    }

    //https://stackoverflow.com/questions/290884/what-is-the-reason-behind-non-static-method-cannot-be-referenced-from-a-static

    public static String getJoke() {

        Random rand = new Random();
        int randomInt = rand.nextInt(mJokeLibrary.size());
        return mJokeLibrary.get(randomInt).toString();

        //return "This is totally a funny joke";
    }
}
