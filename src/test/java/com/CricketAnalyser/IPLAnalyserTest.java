package com.CricketAnalyser;

import org.junit.Assert;
import org.junit.Test;

public class IPLAnalyserTest {
    private static final String IPL_FACT_SHEET_FILE_PATH ="./src/test/resources/IPL2019FactsheetMostRuns";


    @Test
    public void givenIPLSheet_withCSVFile_shouldReturnCorrectRecords() {
        try {
            IPLAnalyser iplAnalyser = new IPLAnalyser();
            int censusData = iplAnalyser.loadCSVData(IPL_FACT_SHEET_FILE_PATH);
            Assert.assertEquals(100, censusData);
        } catch(IPLAnalyserException e) {
        }
    }
}
