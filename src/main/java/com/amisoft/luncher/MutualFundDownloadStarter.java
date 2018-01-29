package com.amisoft.luncher;

import com.amisoft.timer.MutualFundTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class MutualFundDownloadStarter {

    @Autowired
    MutualFundTimer timer;

    public  void mutualFundDownloader(int targetHour, int targetMin, int targetSec) throws IOException {

        if(targetHour==0 && targetMin ==0 && targetSec ==0){

            targetHour = LocalDateTime.now().getHour();
            targetMin = LocalDateTime.now().getMinute();
            targetSec = LocalDateTime.now().getSecond();
        }

        timer.startExecutionAt(targetHour,targetMin,targetSec);


    }
}
