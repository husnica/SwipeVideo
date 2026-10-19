package com.example.swipevideo;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Shows each video on its own page in the ViewPager2
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    /**
     * The list of videos
     */
    private List<VideoItem> videoItems;

    /**
     * Creates the adapter
     * @param videoItems
     */
    public VideoAdapter(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    /**
     *
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_video,parent,false));
    }

    /**
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        holder.setVideoData(videoItems.get(position));
    }

    /**
     * Gets how many videos there are.
     * @return
     */
    @Override
    public int getItemCount() {
        return videoItems.size();
    }

    /**
     * Holds the views for one video page.
     */
    static class VideoViewHolder extends RecyclerView.ViewHolder {
        /**
         * The text views for the title, description, and ID.
         */
        TextView textVideoTitle1, textVideoDescription1, textVideoID;
        /**
         * Plays the video.
         */
        VideoView videoView;
        /**
         * Shows while the video is loading.
         */
        ProgressBar progressBar;

        /**
         *
         * @param itemView
         */

        /**
         * Finds the views in the page
         * @param itemView
         */
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            textVideoTitle1 = itemView.findViewById(R.id.textVideoTitle);
            textVideoDescription1 = itemView.findViewById(R.id.textVideoDescription);
            textVideoID = itemView.findViewById(R.id.textVideoID);
            progressBar = itemView.findViewById(R.id.videoProgressBar);
        }

        /**
         * Shows the video info and plays the video.
         * @param videoItem
         */
        void setVideoData(VideoItem videoItem) {
            textVideoTitle1.setText(videoItem.videoTitle);
            textVideoDescription1.setText(videoItem.videoDescription);
            textVideoID.setText("ID: " + videoItem.videoID);
            videoView.setVideoPath(videoItem.videoURL);

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                /**
                 * Runs when the video is ready. Hides the loading circle.
                 * @param mp the MediaPlayer that is ready for playback
                 */
                @Override
                public void onPrepared(MediaPlayer mp) {
                    progressBar.setVisibility(View.GONE);
                    mp.start();

                    float videoRatio = mp.getVideoWidth() / (float) mp.getVideoHeight();
                    float screenRatio = videoView.getWidth()/(float) videoView.getHeight();

                    float scale = videoRatio / screenRatio;
                    if (scale >= 1f) {
                        videoView.setScaleX(scale);
                    } else {
                        videoView.setScaleY(1f / scale);
                    }
                }

            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                /**
                 * Runs when the video ends.
                 * @param mp the MediaPlayer that reached the end of the file
                 */
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.start();
                }
            });
        }

    }
}
