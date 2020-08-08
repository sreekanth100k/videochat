package com.sreekanth.videochat;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import io.agora.rtm.RtmClient;
import io.agora.rtm.RtmClientListener;
import io.agora.rtm.RtmMessage;

public class Test extends Activity {

    private String appId = "781858e140504b229c3e40411fce65de";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RtmClient rtmclientObj = RtmClient.createInstance(this, appId, new RtmClientListener() {
            @Override
            public void onConnectionStateChanged(int state, int reason){
                for (RtmClientListener listener : mListenerList) {
                    listener.onConnectionStateChanged(state, reason);
                }
            }@Override
            public void onMessageReceived(RtmMessage rtmMessage, String peerId) {

            }

            @Override
            public void onTokenExpired() {

            }
        });
    }
}
