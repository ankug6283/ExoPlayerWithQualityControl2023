package com.anku6283.exoplayerlatest2023full;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;


public class MoviePlayerActivity extends AppCompatActivity {
    private boolean isShowingTrackSelectionDialog;
    private DefaultTrackSelector trackSelector;
    String[] speed = {"0.25x", "0.5x", "Normal", "1.5x", "2x"};

    String urlVideoMain = "http://192.168.79.104/vid/DEMO/master.m3u8";

    TextView qualityTxt;

    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        //hide notch and fill full screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().getAttributes().layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
        }
        setContentView(R.layout.activity_movie_player);

//
//        Bundle bundle = getIntent().getExtras();
//
//        String id = bundle.getString("id");
//        String cat = bundle.getString("Category");
//


//        urlVideoMain = BaseUrl + "movies/" + cat + "/" + id + "/" + bundle.getString("Video");


        Log.d("Abhiiiii", "URL " + urlVideoMain);
//

        trackSelector = new DefaultTrackSelector(this);


        TrackSelectionParameters newParameters = trackSelector.getParameters()
                .buildUpon()
                .setForceLowestBitrate(true)
                .build();


        trackSelector.setParameters((DefaultTrackSelector.Parameters) newParameters);

        simpleExoPlayer = new SimpleExoPlayer.Builder(this).setTrackSelector(trackSelector).build();



        playerView = findViewById(R.id.exoPlayerView);
        playerView.setPlayer(simpleExoPlayer);
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);


        MediaItem mediaItem = null;

        mediaItem = MediaItem.fromUri(urlVideoMain);


//        MediaItem mediaItem2 = MediaItem.fromUri(url2);
        simpleExoPlayer.addMediaItem(mediaItem);
//        simpleExoPlayer.addMediaItem(mediaItem2);
        simpleExoPlayer.prepare();
        playerView.hideController();
        simpleExoPlayer.play();


        ImageView farwordBtn = playerView.findViewById(R.id.fwd);
        ImageView rewBtn = playerView.findViewById(R.id.rew);
        ImageView setting = playerView.findViewById(R.id.exo_track_selection_view);
        ImageView speedBtn = playerView.findViewById(R.id.exo_playback_speed);
        TextView speedTxt = playerView.findViewById(R.id.speed);

        ImageView exoplayer_resize1 = playerView.findViewById(R.id.exoplayer_resize1);
        ImageView exoplayer_resize2 = playerView.findViewById(R.id.exoplayer_resize2);
        ImageView exoplayer_resize3 = playerView.findViewById(R.id.exoplayer_resize3);
        ImageView exoplayer_resize4 = playerView.findViewById(R.id.exoplayer_resize4);
        ImageView exoplayer_resize5 = playerView.findViewById(R.id.exoplayer_resize5);
        ImageView exoplayer_back = playerView.findViewById(R.id.backExo);
        qualityTxt = playerView.findViewById(R.id.qualityTxt);

        TextView titleExo = playerView.findViewById(R.id.titleMoviePlayer);
        titleExo.setText("Example Video Title");

        exoplayer_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        exoplayer_resize1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoplayer_resize1.setVisibility(View.GONE);
                exoplayer_resize2.setVisibility(View.VISIBLE);
                exoplayer_resize3.setVisibility(View.GONE);
                exoplayer_resize4.setVisibility(View.GONE);
                exoplayer_resize5.setVisibility(View.GONE);

                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
                Toast.makeText(MoviePlayerActivity.this, "Fill Mode", Toast.LENGTH_SHORT).show();


            }
        });
        exoplayer_resize2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoplayer_resize1.setVisibility(View.GONE);
                exoplayer_resize2.setVisibility(View.GONE);
                exoplayer_resize3.setVisibility(View.VISIBLE);
                exoplayer_resize4.setVisibility(View.GONE);
                exoplayer_resize5.setVisibility(View.GONE);

                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIT);
                Toast.makeText(MoviePlayerActivity.this, "Fit Mode", Toast.LENGTH_SHORT).show();


            }
        });
        exoplayer_resize3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoplayer_resize1.setVisibility(View.GONE);
                exoplayer_resize2.setVisibility(View.GONE);
                exoplayer_resize3.setVisibility(View.GONE);
                exoplayer_resize4.setVisibility(View.VISIBLE);
                exoplayer_resize5.setVisibility(View.GONE);

                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_ZOOM);
                Toast.makeText(MoviePlayerActivity.this, "Zoom Mode", Toast.LENGTH_SHORT).show();

            }
        });
        exoplayer_resize4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoplayer_resize1.setVisibility(View.GONE);
                exoplayer_resize2.setVisibility(View.GONE);
                exoplayer_resize3.setVisibility(View.GONE);
                exoplayer_resize4.setVisibility(View.GONE);
                exoplayer_resize5.setVisibility(View.VISIBLE);
                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);
                Toast.makeText(MoviePlayerActivity.this, "Fixed Height", Toast.LENGTH_SHORT).show();

            }
        });
        exoplayer_resize5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exoplayer_resize1.setVisibility(View.VISIBLE);
                exoplayer_resize2.setVisibility(View.GONE);
                exoplayer_resize3.setVisibility(View.GONE);
                exoplayer_resize4.setVisibility(View.GONE);
                exoplayer_resize5.setVisibility(View.GONE);

                playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH);
                Toast.makeText(MoviePlayerActivity.this, "Fixed Width", Toast.LENGTH_SHORT).show();

            }
        });


        speedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MoviePlayerActivity.this);
                builder.setTitle("Set Speed");
                builder.setItems(speed, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // the user clicked on colors[which]

                        if (which == 0) {

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("0.25X");
                            PlaybackParameters param = new PlaybackParameters(0.5f);
                            simpleExoPlayer.setPlaybackParameters(param);


                        }
                        if (which == 1) {

                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("0.5X");
                            PlaybackParameters param = new PlaybackParameters(0.5f);
                            simpleExoPlayer.setPlaybackParameters(param);


                        }
                        if (which == 2) {

                            speedTxt.setVisibility(View.GONE);
                            PlaybackParameters param = new PlaybackParameters(1f);
                            simpleExoPlayer.setPlaybackParameters(param);


                        }
                        if (which == 3) {
                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("1.5X");
                            PlaybackParameters param = new PlaybackParameters(1.5f);
                            simpleExoPlayer.setPlaybackParameters(param);

                        }
                        if (which == 4) {


                            speedTxt.setVisibility(View.VISIBLE);
                            speedTxt.setText("2X");

                            PlaybackParameters param = new PlaybackParameters(2f);
                            simpleExoPlayer.setPlaybackParameters(param);


                        }


                    }
                });
                builder.show();


            }
        });


        farwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() + 10000);


            }
        });
        rewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long num = simpleExoPlayer.getCurrentPosition() - 10000;
                if (num < 0) {

                    simpleExoPlayer.seekTo(0);


                } else {

                    simpleExoPlayer.seekTo(simpleExoPlayer.getCurrentPosition() - 10000);

                }


            }
        });

        ImageView fullscreenButton = playerView.findViewById(R.id.fullscreen);
        fullscreenButton.setVisibility(View.GONE);
//        fullscreenButton.setOnClickListener(new View.OnClickListener() {
//            @SuppressLint("SourceLockedOrientationActivity")
//            @Override
//            public void onClick(View view) {
//
//
//                int orientation = MoviePlayerActivity.this.getResources().getConfiguration().orientation;
//                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//                    // code for portrait mode
//
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
//
//                } else {
//                    // code for landscape mode
//
//                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//
//                }
//
//
//            }
//        });


        findViewById(R.id.exo_play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleExoPlayer.play();

            }
        });
        findViewById(R.id.exo_pause).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                simpleExoPlayer.pause();

            }
        });


        simpleExoPlayer.addListener(new Player.Listener() {

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                Player.Listener.super.onTracksChanged(trackGroups, trackSelections);


                if (trackSelections.get(0) != null) {
                    qualityTxt.setText(trackSelections.get(0).getFormat(0).height +"p");
                }


                if (trackSelections.get(0).getType()== C.TRACK_TYPE_VIDEO){



                }


            }

            @Override
            public void onPlaybackStateChanged(int state) {
                if (state == ExoPlayer.STATE_ENDED) {

                }

            }
        });


        playerView.setControllerVisibilityListener(new PlayerControlView.VisibilityListener() {
            @Override
            public void onVisibilityChange(int visibility) {

            }
        });


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isShowingTrackSelectionDialog && TrackSelectionDialog.willHaveContent(trackSelector)) {
                    isShowingTrackSelectionDialog = true;
                    TrackSelectionDialog trackSelectionDialog = TrackSelectionDialog.createForTrackSelector(trackSelector,
                            /* onDismissListener= */ dismissedDialog -> isShowingTrackSelectionDialog = false);
                    trackSelectionDialog.show(getSupportFragmentManager(), /* tag= */ null);
                }




            }
        });


    }


    protected void releasePlayer() {

        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
            trackSelector = null;
        }

    }

    protected void onPause() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.setPlayWhenReady(false);

        }
        super.onPause();
    }


//    @Override
//    public void onStop() {
//        super.onStop();
//
//        releasePlayer();
//    }
//


    public boolean vpn() {
        String iface = "";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iface = networkInterface.getName();
                Log.d("DEBUG", "IFACE NAME: " + iface);
                if (iface.contains("tun") || iface.contains("ppp") || iface.contains("pptp")) {
                    return true;
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }

        return false;
    }

    @Override
    protected void onResume() {


        if (vpn()) {


            new AlertDialog.Builder(this)
                    .setTitle("Vpn Blocked")
                    .setMessage("We Dont Allow to use VPN!")
                    .setCancelable(false)
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();

                        }
                    }).show();


        } else {

            if (simpleExoPlayer != null) {
                simpleExoPlayer.setPlayWhenReady(true);
                super.onResume();
            }
        }

        super.onResume();
    }


    @Override
    public void onBackPressed() {

        releasePlayer();
        finish();
    }
}