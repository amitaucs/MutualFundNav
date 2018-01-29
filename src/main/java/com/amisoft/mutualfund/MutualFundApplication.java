package com.amisoft.mutualfund;

import com.amisoft.luncher.MutualFundDownloadStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.annotation.PostConstruct;
import java.io.IOException;

@SpringBootApplication
@EntityScan("com.amisoft.entities")
public class MutualFundApplication {


    @Autowired
    MutualFundDownloadStarter mutualFundDownloadStarter;

    static private int startHour = 21;
    static private int startMinute = 40;
    static private int startsec = 00;

    public static void main(String[] args) {

        if (args.length < 2){

            System.out.println("No parameter passed, started with default parameter");
        }

        else {
            startHour = Integer.parseInt(args[0]);
            startMinute = Integer.parseInt(args[1]);
            startsec = Integer.parseInt(args[2]);
        }


        SpringApplication.run(MutualFundApplication.class, args);


    }


    @PostConstruct
    public void loadMutualFundNav() throws IOException {

        mutualFundDownloadStarter.mutualFundDownloader(startHour,startMinute,startsec);

    }

}
