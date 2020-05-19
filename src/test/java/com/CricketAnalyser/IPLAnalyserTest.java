package com.CricketAnalyser;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IPLAnalyserTest {
    private static final String IPL_FACT_SHEET_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns";
    private static final String INDIA_STATE_WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns";

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
            iplAnalyser.loadCSVData(INDIA_STATE_WRONG_FILE_PATH);
        } catch (IPLAnalyserException e) {
            Assert.assertEquals(IPLAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
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
}

