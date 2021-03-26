package br.com.franciscochaves.screenlock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public static final int RESULT_ENABLE = 1;
    private DevicePolicyManager devicePolicyManager;
    private ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(this, DeviceAdmin.class);

        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, R.string.permission_required);
        startActivityForResult(intent, RESULT_ENABLE);

        boolean active = devicePolicyManager.isAdminActive((componentName));

        if (active) {
            devicePolicyManager.lockNow();
        }

       // devicePolicyManager.removeActiveAdmin(componentName);
    }

    @Override
    protected void onResume() {
        super.onResume();
        finishAffinity();
    }

}