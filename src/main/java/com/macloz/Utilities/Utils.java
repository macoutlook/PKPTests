package com.macloz.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by maciek on 2017-05-10.
 */
public class Utils {


    public static String parsePdfFile(String resultFilePath) {
        PdfParser pdfParser;
        String stringResultFromFile;
        pdfParser = PdfParser.getInstance();
        pdfParser.initializePdfParser(resultFilePath);
        stringResultFromFile = pdfParser.parsePdfDocument();

        return stringResultFromFile;
    }

    /**
     * Compare whether hour set in firstTimeString in before hour set in secondTimeString
     * @param firstTimeString
     * @param secondTimeString
     * @return true if hour set in firstTimeString is before hour set in secondTimeString
     */
    public static boolean timeComparison(String firstTimeString, String secondTimeString){
        boolean result = false;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Calendar firstTime = Calendar.getInstance();
        Calendar secondTime = Calendar.getInstance();

        String[] partsOfTime = firstTimeString.split(":");
        firstTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(partsOfTime[0]));
        firstTime.set(Calendar.MINUTE, Integer.parseInt(partsOfTime[1]));

        partsOfTime = secondTimeString.split(":");
        secondTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(partsOfTime[0]));
        secondTime.set(Calendar.MINUTE, Integer.parseInt(partsOfTime[1]));

        if (firstTime.before(secondTime)){
            result = true;
        }
        return result;
    }

    public static List<String> extractTimePairsFromPdfOutput(String pdfOutput, String firstTime, String secondTime){
        List<String> allMatches = new ArrayList<String>();
        List<String> resultList = new ArrayList<String>();
        String patternString = ".*\\d{2}\\.\\d{2}\\.\\d{2} (\\d{2}:\\d{2})";
        int ind = 0;

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(pdfOutput);

        while (matcher.find()) {
            allMatches.add(matcher.group(1));
        }

        do{
            if (Utils.timeComparison(allMatches.get(ind), firstTime) ||
                Utils.timeComparison(secondTime, allMatches.get(ind))) {
                allMatches.remove(ind);
                allMatches.remove(ind);
            }
            else {
                ind += 2;
            }
        }while(ind < allMatches.size());

        for(ind = 0; ind < allMatches.size(); ind++){
            if (ind % 2 ==0)
                resultList.add(allMatches.get(ind) + ' ' + allMatches.get(ind+1));
        }
        return resultList;
    }
}
