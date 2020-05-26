package com.CricketAnalyser;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyser {

    public IPLAnalyser() {
    }


    public enum IPLSheet {RUNS, WKTS}

    public IPLSheet iplSheet;
    Map<String, IPLCSVDAO> iplDAOMap = null;

    public IPLAnalyser(IPLSheet iplSheet) {
        this.iplSheet = iplSheet;
    }

    public int loadCSVData(IPLSheet iplSheet, String... filePath) throws IPLAnalyserException {
        iplDAOMap = IPLAdapterFactory.getIPLData(iplSheet, filePath);
        return iplDAOMap.size();

    }

    public String getSortedDataByField(DataComparator.DataFields fields) {
        Comparator<IPLCSVDAO> sortComparator = DataComparator.getCompareDataField(fields);
        List<IPLCSVDAO> sortData = iplDAOMap.values()
                                              .stream()
                                              .sorted(sortComparator)
                                              .collect(Collectors.toList());
        String sortedData=new Gson().toJson(sortData);
        return sortedData;
    }

}
