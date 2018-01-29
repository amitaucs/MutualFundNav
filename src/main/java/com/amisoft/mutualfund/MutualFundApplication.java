package com.amisoft.mutualfund;

import com.amisoft.luncher.MutualFundDownloadStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
@EntityScan("com.amisoft.entities")
public class MutualFundApplication {


    @Autowired
    MutualFundDownloadStarter mutualFundDownloadStarter;

    static private int startHour ;
    static private int startMinute;
    static private int startSecond;

    public static void main(String[] args) {

        if (args.length < 2){

            startHour = 00;
            startMinute = 00;
            startSecond = 00;
        }

        else {
            startHour = Integer.parseInt(args[0]);
            startMinute = Integer.parseInt(args[1]);
            startSecond = Integer.parseInt(args[2]);
        }


        SpringApplication.run(MutualFundApplication.class, args);


    }


    @PostConstruct
    public void loadMutualFundNav() throws IOException {

        mutualFundDownloadStarter.mutualFundDownloader(startHour,startMinute,startSecond);

    }

}
