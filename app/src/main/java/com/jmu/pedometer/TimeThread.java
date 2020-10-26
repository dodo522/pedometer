package com.jmu.pedometer;

import android.os.Message;

/**
 * Created by Administrator on 2020/10/22.
 */

public class TimeThread extends Thread {

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
                Message msg = new Message();
                msg.what = 1;
                //mHandler.sendMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
