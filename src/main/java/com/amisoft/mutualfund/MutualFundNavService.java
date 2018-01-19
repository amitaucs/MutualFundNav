package com.amisoft.mutualfund;

import com.amisoft.entities.MutualFund;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MutualFundNavService {

    private static final String SCHEMA_CODE = "Scheme Code";
    private static final String ISIN_GROWTH = "ISIN Div Payout/ ISIN Growth";
    private static final String ISIN_DIV_RE_INVEST = "ISIN Div Reinvestment";
    private static final String SCHEMA_NAME = "Scheme Name";
    private static final String NAV = "Net Asset Value";
    private static final String REPURCHASE_PRICE = "Repurchase Price";
    private static final String SALE_PRICE = "Sale Price";
    private static final String DATE = "Date";
    private static final String SEPARATOR = ";";
    private static final String SPACE = "";
    public static final String OPEN = "Open";
    public static final String CLOSE = "Close";
    public static final String FUND = "Fund";

    public void loadMutualFund() throws IOException {

        String urlString = "https://www.amfiindia.com/spages/NAVALL.txt";
        String fileName = "/Users/amitdatta/Amit_Work/Java_Mutual_Fund/navtxt.txt";
        String regex = "\\d+";

        fetchNavFromAmfi(urlString, fileName);

        final String[] fundType = {SPACE};
        final String[] fundManager = {SPACE};

        final List<String> fundManagerList = new ArrayList<>();
        final List<String> fundTypeList = new ArrayList<>();

        final Map<String, Integer> indexMap = new HashMap<>();
        final boolean[] isIndexCreated = {false};

        try {

            Files.lines(Paths.get(fileName))
                    .map(line -> line.split(SEPARATOR))
                    .map(array -> {

                        if (!isIndexCreated[0] && array.length > 1 && !array[0].matches(regex)) {

                            int index = 0;
                            for (String key : array) {
                                indexMap.putIfAbsent(key, index);
                                index++;
                            }

                            isIndexCreated[0] = true;
                        }

                        if (!array[0].matches(regex) && array.length == 1 && (array[0].contains(OPEN) || array[0].contains(CLOSE))) {
                            fundType[0] = array[0];
                            fundTypeList.add(array[0]);
                        }

                        if (!array[0].matches(regex) && array.length == 1 && array[0].contains(FUND)) {

                            fundManager[0] = array[0];
                            fundManagerList.add(array[0]);
                        }

                        if (array[0].matches(regex))
                            return extractMutualFund(fundType, fundManager, indexMap, array);
                        else
                            return null;
                    }).collect(Collectors.toList()).stream().forEach(mutualFund -> {

                if (null != mutualFund) {

                    System.out.println(mutualFund.getSchemaCode());
                    System.out.println(mutualFund.getSchemaName());
                    System.out.println(mutualFund.getNav());
                    System.out.println(mutualFund.getFundManager());
                    System.out.println(mutualFund.getFundType());
                    System.out.println("-------------");
                }
            });


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("--------------");
        fundManagerList.forEach(System.out::println);
        System.out.println("--------------");
        fundTypeList.forEach(System.out::println);


    }

    private MutualFund extractMutualFund(String[] fundType, String[] fundManager, Map<String, Integer> indexMap, String[] array) {

        MutualFund mf = new MutualFund();
        mf.setSchemaCode(array[indexMap.get(SCHEMA_CODE)]);
        mf.setSchemaName(array[indexMap.get(SCHEMA_NAME)]);
        mf.setNav(array[indexMap.get(NAV)]);
        mf.setFundManager(fundManager[0]);
        mf.setFundType(fundType[0]);
        return mf;

    }

    private void fetchNavFromAmfi(String urlString, String fileName) throws IOException {

        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(urlString);
        HttpResponse response = client.execute(httpGet);
        File navtxt = new File(fileName);
        FileUtils.copyInputStreamToFile(response.getEntity().getContent(), navtxt);

    }


}
