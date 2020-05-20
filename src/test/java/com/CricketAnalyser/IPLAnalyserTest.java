package com.CricketAnalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyserTest {
    private static final String IPL_FACT_SHEET_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns";

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
    public void givenIndiaCensusData_WithWrongFile_ShouldThrowException() {
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
        } catch (IPLAnalyserException e) { }
    }
    @Test
    public void givenIPLSheet_whenSortedOn4s_shouldReturnSortedResult() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
            String sorted4sData = iplAnalyser.get4sWiseSortingOnData();
            IPLRunCSV[] iplRunCSV = new Gson().fromJson(sorted4sData, IPLRunCSV[].class);
            Assert.assertEquals("Shikhar Dhawan", iplRunCSV[0].player);
        } catch (IPLAnalyserException e) { }
    }
}


