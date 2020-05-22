package com.CricketAnalyser;
import java.util.Map;

public  class IPLRunAdapter extends IPLAdapter {

    @Override
    public Map<String, IPLCSVDAO> loadCSVData(String... filePath) throws IPLAnalyserException {
        return super.loadCSVData(IPLRunCSV.class, filePath[0]);

    }

}
