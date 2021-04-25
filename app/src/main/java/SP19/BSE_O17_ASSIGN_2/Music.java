package SP19.BSE_O17_ASSIGN_2;

import android.Manifest;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;

public class Music extends Fragment {
    public static ArrayList<Model> videoArrayList;
    RecyclerView recyclerView;
    public static final int PERMISSION_READ = 0;


    public Music() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music, container, false);


        if (checkPermission()) {
            recyclerView = view.findViewById(R.id.videorec);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            videoArrayList = new ArrayList<>();
            VideoAdapter  adapter = new VideoAdapter (getContext(), videoArrayList);
            recyclerView.setAdapter(adapter);


            getVideos();
        }
        return view;

    }

    public boolean checkPermission() {
        int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        if ((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_READ);
            return false;
        }
        return true;
    }
    public void getVideos() {
        //   Context context;

        //        Context
        ContentResolver contentResolver = getContext().getContentResolver();
        //  ContentResolver contentResolver =context.getContentResolver();

        Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);

        //looping through all rows and adding to list
        if (cursor != null && cursor.moveToFirst()) {
            do {

                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE));
                String duration = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION));
                String data = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                Model videoModel = new Model();
                videoModel.setVideoTitle(title);
                videoModel.setVideoUri(Uri.parse(data));
                //     videoModel.setVideoDuration(timeConversion(Long.parseLong(duration)));
                videoArrayList.add(videoModel);

            } while (cursor.moveToNext());

        }


    }

    public String timeConversion(long parseLong) {
        String videoTime;
        int dur = (int) parseLong;
        int hrs = (dur / 3600000);
        int mns = (dur / 60000) % 60000;
        int scs = dur % 60000 / 1000;

        if (hrs > 0) {
            videoTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            videoTime = String.format("%02d:%02d", mns, scs);
        }
        return videoTime;
    }

}