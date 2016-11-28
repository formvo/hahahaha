package com.example.com.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by com on 2016-11-16.
 */

public class CYoutubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtubeplayer);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.view);
        youTubePlayerView.initialize(CDeveloperKey.DEVELOPER_KEY, this);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored)
    {
        /** add listeners to YouTubePlayer instance **/
        player.setPlayerStateChangeListener(playerStateChangeListener);
        player.setPlaybackEventListener(playbackEventListener);

        /** Start buffering **/
        if (!wasRestored)
        {
            Intent intent = getIntent();
            //player.cueVideo(url);
            player.cueVideo(intent.getExtras().getString("url"));
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Failured to Initialize!", Toast.LENGTH_LONG).show();
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener()
    {
        @Override
        public void onBuffering(boolean arg0)
        {
        }

        @Override
        public void onPaused()
        {
        }

        @Override
        public void onPlaying()
        {
        }

        @Override
        public void onSeekTo(int arg0)
        {
        }

        @Override
        public void onStopped()
        {
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener()
    {
        @Override
        public void onAdStarted()
        {
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0)
        {
        }

        @Override
        public void onLoaded(String arg0)
        {
        }

        @Override
        public void onLoading()
        {
        }

        @Override
        public void onVideoEnded()
        {
        }

        @Override
        public void onVideoStarted()
        {
        }
    };
}
