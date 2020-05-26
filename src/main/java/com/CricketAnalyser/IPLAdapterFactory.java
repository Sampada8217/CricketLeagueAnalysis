package com.CricketAnalyser;

import java.util.Map;

public class IPLAdapterFactory {
    public static Map<String, IPLCSVDAO> getIPLData(IPLAnalyser.IPLSheet iplSheet, String... filePath) throws IPLAnalyserException {
        if(iplSheet.equals(IPLAnalyser.IPLSheet.WKTS))
            return new IPLWktsAdapter().loadCSVData(filePath);
        else
            if(iplSheet.equals(IPLAnalyser.IPLSheet.RUNS))
            return new IPLRunAdapter().loadCSVData(filePath);
        throw new IPLAnalyserException("Unknown IPLSheet",IPLAnalyserException.ExceptionType.INVALID_IPL_SHEET);
    }
}
