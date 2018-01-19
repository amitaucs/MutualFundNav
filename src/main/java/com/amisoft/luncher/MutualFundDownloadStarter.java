package com.amisoft.luncher;

import com.amisoft.mutualfund.MutualFundNavService;
import com.amisoft.timer.MutualFundTimer;

import java.io.IOException;

public class MutualFundDownloadStarter {

    public static void main(String[] args) throws IOException {

        MutualFundTimer fundTimer = new MutualFundTimer(new MutualFundNavService());
        fundTimer.startExecutionAt(23,14,00);


    }
}
