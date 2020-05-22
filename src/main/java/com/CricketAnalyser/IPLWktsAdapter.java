package com.CricketAnalyser;

import java.util.Map;

public class IPLWktsAdapter extends IPLAdapter {

        @Override
        public Map<String, IPLCSVDAO> loadCSVData(String... filePath) throws IPLAnalyserException {
            return super.loadCSVData(IPLWktsCSV.class,filePath[0]);
        }
    }

