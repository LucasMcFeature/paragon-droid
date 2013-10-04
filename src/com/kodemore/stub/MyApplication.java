package com.kodemore.stub;

import java.io.PrintStream;

import org.acra.annotation.ReportsCrashes;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;

import com.kodemore.acra.KmAcra;
import com.kodemore.file.KmApplicationFilePath;
import com.kodemore.file.KmSharedFilePath;
import com.kodemore.io.KmLogErrorStream;
import com.kodemore.io.KmLogPrintStream;
import com.kodemore.utility.Kmu;

/**
 * The ReportsCrashes annotation is required by ACRA. 
 */
@ReportsCrashes(formKey = "")
public class MyApplication
    extends Application
{
    //##################################################
    //# create
    //##################################################

    @Override
    public void onCreate()
    {
        super.onCreate();

        installGlobals();
        installBridge();
        installSystemOut();
        installDeviceUid();
        installBaseFolders();
        installAcra();
    }

    private void installGlobals()
    {
        MyGlobals.setApplicationContext(getApplicationContext());
        MyGlobals.setBluetoothAdapter(BluetoothAdapter.getDefaultAdapter());
    }

    private void installBridge()
    {
        MyBridge.install();
    }

    private void installSystemOut()
    {
        System.setOut(new PrintStream(new KmLogPrintStream()));
        System.setErr(new PrintStream(new KmLogErrorStream()));
    }

    private void installDeviceUid()
    {
        MyPreferenceController p = new MyPreferenceController();
        String uid = p.getDeviceUid();

        if ( uid == null )
            p.setDeviceUid(Kmu.newUid());
    }

    private void installBaseFolders()
    {
        new KmApplicationFilePath().createFolder();
        new KmSharedFilePath().createFolder();
    }

    private void installAcra()
    {
        KmAcra.installOn(this);
    }
}
