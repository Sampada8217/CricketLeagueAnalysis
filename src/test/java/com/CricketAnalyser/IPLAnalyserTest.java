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
            int numOfRecords = iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
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
            iplAnalyser.loadCSVData(IPL_WRONG_FILE_PATH);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnAverage_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
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
            iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
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
            iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
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
            iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
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
            iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
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
            iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
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
            iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
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
            int numOfRecords = iplAnalyser.loadCSVWktsData(IPL_FACT_SHEET_WICKETS_FILE_PATH);
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
            iplAnalyser.loadCSVWktsData(IPL_FACT_SHEET_WICKET_WRONG_FILE);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLWktsSheet_withWrongFile_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVWktsData(IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedOnAverages = iplAnalyser.getAverageWiseSortingOnData();
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedOnAverages, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnBowlingStrikingRates_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVWktsData(IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingSRData = iplAnalyser.getSRWiseSortingOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedBowlingSRData, IPLRunCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnBowlingEconomyRate_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVWktsData(IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingEconData = iplAnalyser.getBowlingEconomyRateWiseSortingOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedBowlingEconData, IPLRunCSV[].class);
            Assert.assertEquals("Ben Cutting", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnSRWith5wAnd4w_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVWktsData(IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingEconData = iplAnalyser.getBowlingSRWiseSortingWith5wAnd4wOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedBowlingEconData, IPLRunCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
}


