package com.delta.project.project;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{

    @Override
    public void onTokenRefresh() {
        String refreshTokenId = FirebaseInstanceId.getInstance().getToken();
        Log.d("token",""+refreshTokenId);
    }
}
