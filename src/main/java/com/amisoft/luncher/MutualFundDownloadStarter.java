package com.amisoft.luncher;

import com.amisoft.timer.MutualFundTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MutualFundDownloadStarter {

    @Autowired
    MutualFundTimer timer;

    public  void mutualFundDownloader(int targetHour, int targetMin, int targetSec) throws IOException {

        timer.startExecutionAt(targetHour,targetMin,targetSec);


    }
}
