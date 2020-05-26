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
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            int numOfRecords = iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSheet_WithWrongFile_ShouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(IPLAnalyserException.class);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS, IPL_WRONG_FILE_PATH);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnAverage_shouldReturnSortedResult() {
        try{
            IPLAnalyser iplAnalyser=new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sortData=iplAnalyser.getSortedDataByField(DataComparator.DataFields.BATTING_AVERAGE);
            IPLRunCSV[] iplRunCSVS=new Gson().fromJson(sortData,IPLRunCSV[].class);
            Assert.assertEquals("MS Dhoni",iplRunCSVS[iplRunCSVS.length-1].player);
        }catch (IPLAnalyserException e){ }
    }


    @Test
    public void givenIPLSheet_whenSortedOnStrikingRates_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS, IPL_FACT_SHEET_FILE_PATH);
            String sortedSRData = iplAnalyser.getSortedDataByField(DataComparator.DataFields.BATTING_STRIKING_RATE);
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedSRData, IPLRunCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[iplRunCSV.length-1].player);
        } catch (IPLAnalyserException e) {
        }
    }


    @Test
    public void givenIPLSheet_whenSortedOn6s_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sorted6sData = iplAnalyser.getSortedDataByField(DataComparator.DataFields.BOWLING_STRIKING_RATE);
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sorted6sData, IPLRunCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[iplRunCSV.length-1].player);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOn4s_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sorted4sData = iplAnalyser.getSortedDataByField(DataComparator.DataFields.FOURS);
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sorted4sData, IPLRunCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunCSV[iplRunCSV.length-1].player);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnStrikingRateWith6sAnd4s_shouldReturnBestCricketerName() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sortedSRWith6sAnd4s = iplAnalyser.getSortedDataByField(DataComparator.DataFields.SR_WITH_SIXES_AND_FOURS);
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedSRWith6sAnd4s, IPLRunCSV[].class);
            Assert.assertEquals("Ishant Sharma", iplRunCSV[iplRunCSV.length-1].player);
        } catch (IPLAnalyserException e) {
        }
    }


    @Test
    public void givenIPLSheet_whenSortedOnAveragesWithStrikingRate_shouldReturnBestCricketerName() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sortedAvgWithSR = iplAnalyser.getSortedDataByField(DataComparator.DataFields.AVG_WITH_SR);
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedAvgWithSR, IPLRunCSV[].class);
            Assert.assertEquals("MS Dhoni", iplRunCSV[iplRunCSV.length-1].player);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLSheet_whenSortedOnMaximumRunsWithAverage_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.RUNS,IPL_FACT_SHEET_FILE_PATH);
            String sortedRunsWithAvg = iplAnalyser.getSortedDataByField(DataComparator.DataFields.RUNS_WITH_AVG);
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sortedRunsWithAvg, IPLRunCSV[].class);
            Assert.assertEquals("David Warner ", iplRunCSV[iplRunCSV.length-1].player);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLWicketsSheet_withCSVFile_shouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.RUNS);
            int numOfRecords = iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            Assert.assertEquals(100, numOfRecords);
        } catch (IPLAnalyserException e) {
        }
    }

    @Test
    public void givenIPLWktsSheet_withWrongFile_ShouldThrowException() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
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
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedOnAverages = iplAnalyser.getSortedDataByField(DataComparator.DataFields.BOWLING_AVERAGE);
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedOnAverages, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[iplWktsCSV.length-1].player);
        } catch (IPLAnalyserException e) { }
    }

    @Test
    public void givenIPLWicketsSheet_whenSortedOnBowlingStrikingRates_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingSRData = iplAnalyser.getSortedDataByField(DataComparator.DataFields.BOWLING_STRIKING_RATE);
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingSRData, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[iplWktsCSV.length-1].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnBowlingEconomyRate_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS, IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingEconData = iplAnalyser.getSortedDataByField(DataComparator.DataFields.ECONOMY_RATE);
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingEconData, IPLWktsCSV[].class);
            Assert.assertEquals("Ben Cutting", iplWktsCSV[iplWktsCSV.length-1].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnSRWith5wAnd4w_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingSRWith5wAnd4w = iplAnalyser.getSortedDataByField(DataComparator.DataFields.SR_WITH_FIVE_AND_FOUR);
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingSRWith5wAnd4w, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[iplWktsCSV.length-1].player);
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnAvgWithSR_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingAvgWithSR = iplAnalyser.getSortedDataByField(DataComparator.DataFields.AVG_WITH_SR);
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingAvgWithSR, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[iplWktsCSV.length-1].player);
        } catch (IPLAnalyserException e) {
            System.out.println("Exception");
        }
    }
    @Test
    public void givenIPLWicketsSheet_whenSortedOnWicketsWithAvg_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBowlingWicketsWithAvg = iplAnalyser.getSortedDataByField(DataComparator.DataFields.AVG_WITH_WICKETS);
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBowlingWicketsWithAvg, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[iplWktsCSV.length-1].player);
        } catch (IPLAnalyserException e) { }
    }


    @Test
    public void givenIPLWicketsSheet_whenSortedOnBattingAvgAndBowlingAvg_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_WICKETS_FILE_PATH,IPL_FACT_SHEET_FILE_PATH);
            String sortedData=iplAnalyser.getSortedDataByField(DataComparator.DataFields.BATTING_AND_BOWLING_AVERAGE);
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedData, IPLWktsCSV[].class);
            Assert.assertEquals("Krishnappa Gowtham", iplWktsCSV[iplWktsCSV.length-1].player);

        } catch (IPLAnalyserException e) { }
    }

    @Test
    public void givenIPLWicketsSheet_whenSortedOnMostRunsAndWickets_shouldReturnAllRounderCricketer() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser(IPLAnalyser.IPLSheet.WKTS);
            iplAnalyser.loadCSVData(IPLAnalyser.IPLSheet.WKTS,IPL_FACT_SHEET_FILE_PATH,IPL_FACT_SHEET_WICKETS_FILE_PATH);
            String sortedBattingAndBowlingAvg = iplAnalyser.getSortedDataByField(DataComparator.DataFields.RUNS_WITH_WICKETS);
            IPLWktsCSV[] iplWktsCSV = new Gson().fromJson(sortedBattingAndBowlingAvg, IPLWktsCSV[].class);
            Assert.assertEquals("David Warner ", iplWktsCSV[iplWktsCSV.length-1].player);
        } catch (IPLAnalyserException e) { }
    }
}
