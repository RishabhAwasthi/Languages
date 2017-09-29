package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AUDIOFOCUS_LOSS_TRANSIENT || focusChange==AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
                // Pause playback
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                releaseMediaPlayer();
            }
        }
    };
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_numbers);
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


            // Create a list of words
           final ArrayList<Word> words = new ArrayList<Word>();
            words.add(new Word("red", "Rojo",R.drawable.color_red,R.raw.red));
            words.add(new Word("yellow", "Amarillo",R.drawable.color_mustard_yellow,R.raw.yellow));
            words.add(new Word("Dusty yellow", "Amarillo polvoriento",R.drawable.color_dusty_yellow,R.raw.dustyyellow));
            words.add(new Word("green", "Verde",R.drawable.color_green,R.raw.green));
            words.add(new Word("brown", "Marron",R.drawable.color_brown,R.raw.brown));
            words.add(new Word("gray", "Gris",R.drawable.color_gray,R.raw.gray));
            words.add(new Word("black", "Negro",R.drawable.color_black,R.raw.black));
            words.add(new Word("white", "Blanco",R.drawable.color_white,R.raw.white));


            // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
            // adapter knows how to create list items for each item in the list.

            WordAdapter itemsAdapter = new WordAdapter(this, words);

            ListView listView = (ListView) findViewById(R.id.list);

            listView.setAdapter(itemsAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    // Get the {@link Word} object at the given position the user clicked on
                    Word word = words.get(position);
                    releaseMediaPlayer();
                    // Request audio focus for playback
                    int result = audioManager.requestAudioFocus(afChangeListener,
                            // Use the music stream.
                            AudioManager.STREAM_MUSIC,
                            // Request permanent focus.
                            AudioManager.AUDIOFOCUS_GAIN);

                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                        // Start playback.


                        // Create and setup the {@link MediaPlayer} for the audio resource associated
                        // with the current word
                        mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioResourceId());

                        // Start the audio file
                        mMediaPlayer.start();
                        mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    }
                }
            });
        }
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            audioManager.abandonAudioFocus(afChangeListener);
        }
    }



}

