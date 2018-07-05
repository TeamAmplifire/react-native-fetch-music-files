package com.fetchmusicfiles.Fetch;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import com.fetchmusicfiles.DataModels.Song;
import com.fetchmusicfiles.DataModels.SongCollection;

public class FetchSongList {

    private static FetchSongList demoDataProvider;

    private FetchSongList() {
    }

    public static FetchSongList getInstance() {
        if (null == demoDataProvider) {
            demoDataProvider = new FetchSongList();
        }
        return demoDataProvider;
    }

    public void getAllSongs(Context context) {

        SongCollection.getInstance().getListOfSongs().clear();

        String[] STAR = null;
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, STAR,
                MediaStore.Audio.Media.IS_MUSIC + " != 0", null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    SongCollection.getInstance().getListOfSongs().add(new Song(
                            cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)),
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)),
                            cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))));


                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
}
