package com.CricketAnalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyserTest {
    private static final String IPL_FACT_SHEET_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns";
    private static final String IPL_FACT_SHEET_WICKETS_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    private static final String IPL_FACT_SHEET_WICKET_WRONG_FILE = "./src/main/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void givenIPLSheet_withCSVFile_shouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int numOfRecords = iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS, IPL_FACT_SHEET_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSheet_WithWrongFile_ShouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLAnalyserException.class);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS, IPL_WRONG_FILE_PATH);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnAverage_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS, IPL_FACT_SHEET_FILE_PATH);
            String sortedData = iplAnalyser.getAverageWiseSortingOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedData, IPLRunCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) {
        }
    }


    @Test
    public void givenIPLSheet_whenSortedOnStrikingRates_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS, IPL_FACT_SHEET_FILE_PATH);
            String sortedSRData = iplAnalyser.getSRWiseSortingOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedSRData, IPLRunCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) {
        }
    }


    @Test
    public void givenIPLSheet_whenSortedOn6s_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sorted6sData = iplAnalyser.get6sWiseSortingOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sorted6sData, IPLRunCSV[].class);
            Assert.assertEquals("Andre Russell", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOn4s_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sorted4sData = iplAnalyser.get4sWiseSortingOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sorted4sData, IPLRunCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnStrikingRateWith6sAnd4s_shouldReturnBestCricketerName() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sortedSRWith6sAnd4s = iplAnalyser.getSRWiseSortingWith6sAnd4sOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedSRWith6sAnd4s, IPLRunCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) {
        }
    }


    @Test
    public void givenIPLSheet_whenSortedOnAveragesWithStrikingRate_shouldReturnBestCricketerName() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sortedAvgWithSR = iplAnalyser.getAverageWiseSortingWithSROnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedAvgWithSR, IPLRunCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnMaximumRunsWithAverage_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sortedRunsWithAvg = iplAnalyser.getRunsWiseSortingWithAvgOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedRunsWithAvg, IPLRunCSV[].class);
            Assert.assertEquals("David Warner ", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLWktsheet_withCSVFile_shouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int numOfRecords = iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLWktsSheet_withWrongFile_ShouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLAnalyserException.class);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKET_WRONG_FILE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLWicketsSheet_withSortingOnBowlingAverages_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedOnAverages = iplAnalyser.getAverageWiseSortingOnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedOnAverages, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }

    @Test
    public void givenIPLWicketsSheet_whenSortedOnBowlingStrikingRates_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingSRData = iplAnalyser.getSRWiseSortingOnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingSRData, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnBowlingEconomyRate_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingEconData = iplAnalyser.getBowlingEconomyRateWiseSortingOnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingEconData, IPLWktsCSV[].class);
            Assert.assertEquals("Ben Cutting", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnSRWith5wAnd4w_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingSRWith5wAnd4w = iplAnalyser.getBowlingSRWiseSortingWith5wAnd4wOnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingSRWith5wAnd4w, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnAvgWithSR_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingAvgWithSR = iplAnalyser.getAverageWiseSortingWithSROnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingAvgWithSR, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) {
            System.out.println("Exception");
        }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnWicketsWithAvg_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingWicketsWithAvg = iplAnalyser.getWicketsWiseSortingWithAvgOnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingWicketsWithAvg, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }


    @Test
    public void givenIPLWicketsSheet_whenSortedOnBattingAvgAndBowlingAvg_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBattingAndBowlingAvg = iplAnalyser.getSortingOnBattingAndBowlingAvgOnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBattingAndBowlingAvg, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }

    @Test
    public void givenIPLWicketsSheet_whenSortedOnMostRunsAndWickets_shouldReturnAllRounderCricketer() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_FILE_PATH);
            String sortedBattingAndBowlingAvg = iplAnalyser.getSortingOnMostRunsAndWicketsOnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBattingAndBowlingAvg, IPLWktsCSV[].class);
            Assert.assertEquals("David Warner ", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
}


