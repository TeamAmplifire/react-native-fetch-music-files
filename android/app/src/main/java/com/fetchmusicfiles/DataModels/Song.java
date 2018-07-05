package com.fetchmusicfiles.DataModels;

import android.content.ContentUris;
import android.net.Uri;
import android.util.Log;

public class Song {
    private long songID;
    private String songName;
    private String albumName;
    private String artistName;
    private String fullPath;
    private long songLength;
    private String albumArt;

    public Song(long songID, String songName, String artistName, String albumName, String fullPath, long songLength) {
        this.songID = songID;
        this.songName = songName;
        this.artistName = artistName;
        this.albumName = albumName;
        this.fullPath = fullPath;
        this.songLength = songLength;
        this.albumArt = ContentUris.withAppendedId(Uri
                        .parse("content://media/external/audio/albumart"),
                this.songID).toString();

        Log.e("albumArt", "albumArt is "+ albumArt);
    }
    public String getAlbumArt() {
        return albumArt;
    }

    public void setAlbumArt(String albumArt) {
        this.albumArt = albumArt;
    }

    public void setSongID(long songID) {
        this.songID = songID;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public void setSongLength(long songLength) {
        this.songLength = songLength;
    }

    public long getSongID() {
        return songID;
    }

    public String getSongName() {
        return songName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getFullPath() {
        return fullPath;
    }

    public long getSongLength() {
        return Long.valueOf(milliSecondsToTimer(songLength));
    }

    public static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString = "";

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }
}
