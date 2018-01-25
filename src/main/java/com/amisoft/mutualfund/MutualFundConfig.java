package com.amisoft.mutualfund;

import com.amisoft.luncher.MutualFundDownloadStarter;
import com.amisoft.timer.MutualFundTimer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MutualFundConfig {


    @Bean
    public MutualFundNavService mutualFundNavService(){

        return new MutualFundNavService();
    }

    @Bean
    public MutualFundTimer mutualFundTimer(){
        return new MutualFundTimer();
    }

   @Bean
    public MutualFundDownloadStarter mutualFundDownloadStarter(){

        return new MutualFundDownloadStarter();

   }
}
