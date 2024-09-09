package com.example.kasukabe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    EditText userIDEditText;
    TextView Heyuser;
    ZegoSendCallInvitationButton voicecallBtn , videocallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        userIDEditText = findViewById(R.id.userID); //cehck userIDexittext TO Id
        Heyuser=findViewById(R.id.Heyuser);
                voicecallBtn = findViewById(R.id.voiceCall_btn);
                videocallBtn = findViewById(R.id.videoCall_btn);

                String userId = getIntent().getStringExtra("userId");
                Heyuser.setText(userId);

                userIDEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        String TargetUser= userIDEditText.getText().toString().trim();
                        setvideocall(TargetUser);
                        setvoicecall(TargetUser);
                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });
    }

    void setvoicecall(String targetUser){
        voicecallBtn.setIsVideoCall(false);
        voicecallBtn.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        voicecallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUser,targetUser)));
    }
    void setvideocall(String targetUser){
        videocallBtn.setIsVideoCall(true);
        videocallBtn.setResourceID("zego_uikit_call"); // Please fill in the resource ID name that has been configured in the ZEGOCLOUD's console here.
        videocallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUser,targetUser)));
    }

}