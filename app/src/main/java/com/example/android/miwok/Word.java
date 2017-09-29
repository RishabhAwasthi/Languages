package com.example.android.miwok;

/**
 * Created by awasthi's on 9/27/2017.
 */

public class Word {
    private String eng,spanish;
   private int imageId=noimage;
    private static final int noimage=-1;
    private int mAudioResourceId;

    public Word(String eng, String spanish, int image,int mAudioResourceId) {
        this.eng = eng;
        this.spanish = spanish;
        this.imageId = image;
        this.mAudioResourceId=mAudioResourceId;
    }

    public Word(String eng, String spanish,int mAudioResourceId) {
        this.eng = eng;
        this.spanish = spanish;
        this.mAudioResourceId=mAudioResourceId;
    }
    public boolean hasimage(){
        return imageId!=noimage;
    }

    public String getEng() {
        return eng;
    }

    public String getSpanish() {
        return spanish;
    }

    public int getImageId() {
        return imageId;
    }

    public int getAudioResourceId() {
        return mAudioResourceId;
    }
}
