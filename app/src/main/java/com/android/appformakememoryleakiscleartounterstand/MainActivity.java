package com.android.appformakememoryleakiscleartounterstand;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {

    public static final String TAG ="MainActivity";
    private MyAsyncTask myAsyncTask;
    private MyAsyncTask2 myAsyncTask2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myAsyncTask !=null){
                    finish();
                }
                myAsyncTask = new MyAsyncTask(MainActivity.this);
                myAsyncTask.execute();

            }
        });
        findViewById(R.id.go2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(myAsyncTask2 !=null){
                    finish();
                }
                myAsyncTask2 = new MyAsyncTask2(MainActivity.this);
                myAsyncTask2.execute();

            }
        });
    }

    private static class MyAsyncTask extends AsyncTask<Void,Void,Void>{

        WeakReference<Context> context;
        //private Context context;
        public MyAsyncTask(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.get().getResources(),R.drawable.ic_launcher_background);
            try {
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
    }
    private static class MyAsyncTask2 extends AsyncTask<Void,Void,Void>{

       // WeakReference<Context> context;

        private Context context;
        public MyAsyncTask2(Context context) {
            //this.context = new WeakReference<>(context);
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
           // Bitmap bitmap = BitmapFactory.decodeResource(context.get().getResources(),R.drawable.ic_launcher_background);
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_launcher_background);
            try {
                Thread.sleep(5000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: activity is Destroyed.");
    }
}
