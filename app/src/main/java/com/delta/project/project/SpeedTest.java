//package com.delta.project.project;
//
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.TextView;
//
//import java.math.BigDecimal;
//
//import fr.bmartel.speedtest.SpeedTestReport;
//import fr.bmartel.speedtest.SpeedTestSocket;
//import fr.bmartel.speedtest.inter.ISpeedTestListener;
//import fr.bmartel.speedtest.model.SpeedTestError;
//
//public class SpeedTest extends AppCompatActivity {
//    private TextView textView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_speed_test);
//        textView = (TextView) findViewById(R.id.txt);
//        textView.setText("");
//        SpeedTestApi speedTestApi = new SpeedTestApi();
//        speedTestApi.execute();
//
//    }
//
//    public class SpeedTestApi extends AsyncTask<String,String,String> {
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//        }
//
//        @Override
//        protected void onProgressUpdate(String... values) {
//            textView.setText(values[0]+"Mbps");
//        }
//
//        @Override
//        protected String doInBackground(String... params) {
//            final SpeedTestSocket speedTestSocket = new SpeedTestSocket();
//         final BigDecimal bigDecimal = new BigDecimal(1000*1000);
//
//            // add a listener to wait for speedtest completion and progress
//            speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {
//
//                @Override
//                public void onCompletion(SpeedTestReport report) {
//                    // called when download/upload is finished
////                    publishProgress(""+report.getTransferRateOctet());
//                   publishProgress( "[COMPLETED] rate in bit/s   : " + report.getTransferRateBit().divide(bigDecimal).divide(bigDecimal));
//                }
//
//                @Override
//                public void onError(SpeedTestError speedTestError, String errorMessage) {
//                    // called when a download/upload error occur
//                }
//
//                @Override
//                public void onProgress(float percent, SpeedTestReport report) {
//                   BigDecimal bigDecimals = new BigDecimal(Float.toString(percent));
//                    bigDecimals = bigDecimals.setScale(2,BigDecimal.ROUND_HALF_UP);
//                    // called to notify download/upload progress
////                        publishProgress(""+report.getTransferRateOctet());
//                    Log.v("speedtest", "[PROGRESS] progress : " + percent + "%");
////                    Log.v("speedtest",""+speedTestSocket.getLiveDownloadReport());
//                     publishProgress(bigDecimals+"%: "+ report.getTransferRateBit().divide(bigDecimal).setScale(2,BigDecimal.ROUND_HALF_UP));
//                }
//
//                @Override
//                public void onInterruption() {
//                    // triggered when forceStopTask is called
//                }
//            });
//
//            speedTestSocket.startDownload("http://speedtest.tele2.net/1MB.zip");
//
//            return "";
//
//        }
//
//        }
//
//}
