package com.amisoft.timer;

import com.amisoft.mutualfund.MutualFundNavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class MutualFundTimer  {

    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    @Autowired
    MutualFundNavService mfTask;



    public void startExecutionAt(int targetHour, int targetMin, int targetSec) {

        Runnable taskWrapper = new Runnable(){

            @Override
            public void run()
            {
                try {
                    mfTask.loadMutualFund();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startExecutionAt(targetHour, targetMin, targetSec);
            }

        };

        long delay = computeNextDelay(targetHour, targetMin, targetSec);
        executorService.schedule(taskWrapper, delay, TimeUnit.SECONDS);
    }

    private long computeNextDelay(int targetHour, int targetMin, int targetSec)
    {
        LocalDateTime localNow = LocalDateTime.now();
        ZoneId currentZone = ZoneId.systemDefault();
        ZonedDateTime zonedNow = ZonedDateTime.of(localNow, currentZone);
        ZonedDateTime zonedNextTarget = zonedNow.withHour(targetHour).withMinute(targetMin).withSecond(targetSec);
        if(zonedNow.compareTo(zonedNextTarget) > 0)
            zonedNextTarget = zonedNextTarget.plusDays(1);

        Duration duration = Duration.between(zonedNow, zonedNextTarget);
        return duration.getSeconds();
    }

    public void stop()
    {
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException ex) {
            Logger.getLogger(MutualFundTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
