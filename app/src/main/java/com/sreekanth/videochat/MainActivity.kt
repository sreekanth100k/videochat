package com.sreekanth.videochat;

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.auth.api.signin.*
import com.google.android.gms.auth.api.signin.internal.zzg.getSignInResultFromIntent
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtm.RtmClient
import io.agora.rtm.RtmClientListener


public class MainActivity : Activity() {

    private lateinit var mSignInBtn:SignInButton
    private var RC_SIGN_IN  =   100
    private lateinit var mRtcEngine:RtcEngine
    private val mAppId = "781858e140504b229c3e40411fce65de"


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task: com.google.android.gms.tasks.Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)

            Toast.makeText(this,"Sign in successfull",Toast.LENGTH_LONG).show()
            var googleSignInResultObj: GoogleSignInResult? =  getSignInResultFromIntent(data)
            var intentObj:Intent  = Intent(this,)

            startActivity()
        }



    }

    private fun handleSignInResult(completedTask: com.google.android.gms.tasks.Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
        } catch (e: ApiException) {
            android.util.Log.w("TAG", "signInResult:failed code=" + e.statusCode)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        var mGoogleSignInClient:GoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mSignInBtn = findViewById(R.id.id_signin_btn)

        mSignInBtn.setOnClickListener(View.OnClickListener {
            val signInIntent: Intent = mGoogleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)
        });



    }

    override fun onStart() {
        super.onStart()
        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(this)
    }



}
