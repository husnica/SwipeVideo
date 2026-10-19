package com.example.swipevideo;

import android.os.Bundle;
import android.provider.MediaStore;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * Main Screen of the app vertically
 */
public class MainActivity extends AppCompatActivity {
    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Finds the ViewPager2 that lets the user swipe
        final ViewPager2 videoViewPager = findViewById(R.id.VideoViewPager);

        // List that will hold all the videos
        List<VideoItem> videoItemsList = new ArrayList<>();

        // Video 1
        VideoItem videoCR7 = new VideoItem();
        videoCR7.videoID = "V1";
        videoCR7.videoURL = "https://github.com/husnica/VideoDemo/raw/refs/heads/main/youtube-audio.mp3";
        videoCR7.videoTitle = "CR7";
        videoCR7.videoDescription = "The best player of all time";
        videoItemsList.add(videoCR7);

        // Video 2
        VideoItem videoFerrari = new VideoItem();
        videoFerrari.videoID = "V2";
        videoFerrari.videoURL = "https://github.com/husnica/VideoDemo/raw/refs/heads/main/youtube-audio%20(1).mp3";
        videoFerrari.videoTitle = "Ferrari";
        videoFerrari.videoDescription = "Behind the wheel of a Ferrari ";
        videoItemsList.add(videoFerrari);

        // Video 3
        VideoItem videoBMW = new VideoItem();
        videoBMW.videoID = "V3";
        videoBMW.videoURL = "https://github.com/husnica/VideoDemo/raw/refs/heads/main/youtube-audio%20(2).mp3";
        videoBMW.videoTitle = "BMW M5";
        videoBMW.videoDescription = "BMW M5 Take off";
        videoItemsList.add(videoBMW);

        // Video 4
        VideoItem videoMostar = new VideoItem();
        videoMostar.videoID = "V4";
        videoMostar.videoURL = "https://github.com/husnica/VideoDemo/raw/refs/heads/main/youtube-audio%20(3).mp3";
        videoMostar.videoTitle = "Mostar, Bosnia & Herzegovina";
        videoMostar.videoDescription = "Exploring the beauty, history, and culture of my hometown, Mostar";
        videoItemsList.add(videoMostar);

        // Video 5
        VideoItem videoTheWeeknd = new VideoItem();
        videoTheWeeknd.videoID = "V5";
        videoTheWeeknd.videoURL = "https://github.com/husnica/VideoDemo/raw/refs/heads/main/youtube-audio%20(4).mp3";
        videoTheWeeknd.videoTitle = "The Weeknd Live in Paris";
        videoTheWeeknd.videoDescription = "An unforgettable concert experience";
        videoItemsList.add(videoTheWeeknd);

        // Connects the list of videos through adapter
        videoViewPager.setAdapter(new VideoAdapter(videoItemsList));

    }
}
