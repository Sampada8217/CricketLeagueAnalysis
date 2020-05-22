package com.CricketAnalyser;

import com.google.gson.Gson;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IPLAnalyser {
    List<IPLCSVDAO> iplDAOS = null;

    public IPLAnalyser() {
    }

    public enum IPLSheet {RUNS, WKTS}

    private IPLSheet iplSheet;
    Map<String, IPLCSVDAO> iplDAOMap = null;

    public IPLAnalyser(IPLSheet iplSheet) {
        this.iplSheet = iplSheet;
    }

    public int loadCSVData(IPLSheet iplSheet, String... filePath) throws IPLAnalyserException {
        iplDAOMap = IPLAdapterFactory.getIPLData(iplSheet, filePath);
        return iplDAOMap.size();

    }

    public  String  getSortedFunction(Comparator<IPLCSVDAO> iplcsvdaoComparator) throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, iplcsvdaoComparator);
        String sortedAvgData = new Gson().toJson(iplRunDAOS);
        return sortedAvgData;
    }

    public String getAverageWiseSortingOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortAvgIplComparator= Comparator.comparing(ipl -> ipl.avg);
        return getSortedFunction(sortAvgIplComparator);
    }


    public String getSRWiseSortingOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortSRIplComparator = Comparator.comparing(ipl -> ipl.sr);
        return getSortedFunction(sortSRIplComparator);

    }


    public String get6sWiseSortingOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sort6sIplComparator = Comparator.comparing(ipl -> ipl.six);
        return getSortedFunction(sort6sIplComparator);
    }


    public String get4sWiseSortingOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sort4sIplComparator = Comparator.comparing(ipl-> ipl.four);
        return getSortedFunction(sort4sIplComparator);
    }

    public String getSRWiseSortingWith6sAnd4sOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortSRComparator = Comparator.comparing(ipl -> ipl.sr);
        Comparator<IPLCSVDAO> sort6sComparator = sortSRComparator.thenComparing(census -> census.sr);
        Comparator<IPLCSVDAO> sort4sCompartor = sort6sComparator.thenComparing(census -> census.four);
        return getSortedFunction(sort4sCompartor);
    }

    public String getAverageWiseSortingWithSROnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortAvgComparator = Comparator.comparing(ipl -> ipl.avg);
        Comparator<IPLCSVDAO> sortSRComparator = sortAvgComparator.thenComparing(census -> census.sr);
        return getSortedFunction(sortSRComparator);
    }

    public String getRunsWiseSortingWithAvgOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortRunsComparator = Comparator.comparing(ipl -> ipl.runs);
        Comparator<IPLCSVDAO> sortAvgComparator = sortRunsComparator.thenComparing(ipl -> ipl.avg);
        return getSortedFunction(sortAvgComparator);
    }

    public String getBowlingEconomyRateWiseSortingOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortEconComparator = Comparator.comparing(ipl -> ipl.econ);
        return getSortedFunction(sortEconComparator);
    }
    public String getBowlingSRWiseSortingWith5wAnd4wOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortSRComparator = Comparator.comparing(ipl -> ipl.sr);
        Comparator<IPLCSVDAO> sort6sComparator = sortSRComparator.thenComparing(ipl -> ipl.fiveW);
        Comparator<IPLCSVDAO> sort4sCompartor = sort6sComparator.thenComparing(ipl -> ipl.fourW);
        return getSortedFunction(sort4sCompartor);
    }
    public String getWicketsWiseSortingWithAvgOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortSRComparator = Comparator.comparing(ipl -> ipl.sr);
        Comparator<IPLCSVDAO> sort4wComparator = sortSRComparator.thenComparing(ipl -> ipl.fourW);
        Comparator<IPLCSVDAO> sort5wComparator = sort4wComparator.thenComparing(ipl -> ipl.fiveW);
        return getSortedFunction(sort5wComparator);
    }
    public String getSortingOnBattingAndBowlingAvgOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortBattingAvgComparator = Comparator.comparing(ipl -> ipl.avg);
        Comparator<IPLCSVDAO> sortBowlingAvgComparator = sortBattingAvgComparator.thenComparing(ipl -> ipl.avg);
        return getSortedFunction(sortBowlingAvgComparator);
    }
    public String getSortingOnMostRunsAndWicketsOnData() throws IPLAnalyserException {
        Comparator<IPLCSVDAO> sortRunsComparator = Comparator.comparing(ipl -> ipl.runs);
        Comparator<IPLCSVDAO> sortWktsComparator = sortRunsComparator.thenComparing(ipl -> ipl.wkts);
        return getSortedFunction(sortWktsComparator);
    }
    private void sortByDescending(List<IPLCSVDAO> iplRunDAOS, Comparator<IPLCSVDAO> censusComparator) {
        for (int i = 0; i < iplRunDAOS.size() - 1; i++) {
            for (int j = 0; j < iplRunDAOS.size() - i - 1; j++) {
                IPLCSVDAO data1 = iplRunDAOS.get(j);
                IPLCSVDAO data2 = iplRunDAOS.get(j + 1);
                if (censusComparator.compare(data1, data2) < 0) {
                    iplRunDAOS.set(j, data2);
                    iplRunDAOS.set(j + 1, data1);
                }
            }
        }
    }
}







