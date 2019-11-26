package edu.self.simpleasynctasks;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import java.net.URL;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button b1;
    private Button b2;
    private Button b3;
    private TextView t1;
    private TextView t2;
    private TextView t3;
    private int lenght = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startTask (View view) {
        new SimpleAsyncTask().execute(lenght);
    }

    public static String random() {
        Random generator = new Random();
        StringBuilder randomStringBuilder = new StringBuilder();
        int randomLength = generator.nextInt(20);
        char tempChar;
        for (int i = 0; i < randomLength; i++){
            tempChar = (char) (generator.nextInt(96) + 32);
            randomStringBuilder.append(tempChar);
        }
        return randomStringBuilder.toString();
    }
    public void generateString (View view) {
        TextView t3 = findViewById(R.id.textView3);
        String s = random();
        t3.setText(s);
    }

    public void taskSync (View view){
        TextView t2 = findViewById(R.id.textView2);
            try {
                Thread.sleep(2000);
                t2.setText("Finish!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private class SimpleAsyncTask extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... ints) {
            int duration = ints[0];//lenght=2000
            Integer percentage = 0;
            publishProgress(percentage);
            for (int i = 0; i < 101; i++) {
                try {
                    Thread.sleep(100);
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Task completed!!!";
        }

        protected void onPostExecute(String result) {
            //show final result…
        }
        protected void onProgressUpdate(Integer... progress) {
            TextView t1 = findViewById(R.id.textView);
            t1.setText(String.valueOf(progress[0]));
        }
    }


}
