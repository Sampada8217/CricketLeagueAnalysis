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

    public String getAverageWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortAvgIplComparator= Comparator.comparing(ipl -> ipl.avg);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortAvgIplComparator);
        String sortedAvgData = new Gson().toJson(iplRunDAOS);
        return sortedAvgData;
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

    public String getSRWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortSRIplComparator = Comparator.comparing(ipl -> ipl.sr);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortSRIplComparator);
        String sortedSRData = new Gson().toJson(iplRunDAOS);
        return sortedSRData;

    }


    public String get6sWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sort6sIplComparator = Comparator.comparing(ipl -> ipl.six);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sort6sIplComparator);
        String sorted6sData = new Gson().toJson(iplRunDAOS);
        return sorted6sData;
    }


    public String get4sWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sort4sIplComparator = Comparator.comparing(ipl-> ipl.four);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sort4sIplComparator);
        String sorted4sData = new Gson().toJson(iplRunDAOS);
        return sorted4sData;
    }

    public String getSRWiseSortingWith6sAnd4sOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortSRComparator = Comparator.comparing(ipl -> ipl.sr);
        Comparator<IPLCSVDAO> sort6sComparator = sortSRComparator.thenComparing(census -> census.sr);
        Comparator<IPLCSVDAO> sort4sCompartor = sort6sComparator.thenComparing(census -> census.four);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sort4sCompartor);
        String sorted4sData = new Gson().toJson(iplRunDAOS);
        return sorted4sData;
    }

    public String getAverageWiseSortingWithSROnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortAvgComparator = Comparator.comparing(ipl -> ipl.avg);
        Comparator<IPLCSVDAO> sortSRComparator = sortAvgComparator.thenComparing(census -> census.sr);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortSRComparator);
        String sortedAvgData = new Gson().toJson(iplRunDAOS);
        return sortedAvgData;

    }

    public String getRunsWiseSortingWithAvgOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortRunsComparator = Comparator.comparing(ipl -> ipl.runs);
        Comparator<IPLCSVDAO> sortAvgComparator = sortRunsComparator.thenComparing(census -> census.avg);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortAvgComparator);
        String sortedSRData = new Gson().toJson(iplRunDAOS);
        return sortedSRData;
    }

    public String getBowlingEconomyRateWiseSortingOnData() throws IPLAnalyserException {

        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortEconComparator = Comparator.comparing(ipl -> ipl.econ);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                                              .stream()
                                              .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortEconComparator);
        String sortedERData = new Gson().toJson(iplRunDAOS);
        return sortedERData;
    }
    public String getBowlingSRWiseSortingWith5wAnd4wOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortSRComparator = Comparator.comparing(ipl -> ipl.sr);
        Comparator<IPLCSVDAO> sort6sComparator = sortSRComparator.thenComparing(ipl -> ipl.fiveW);
        Comparator<IPLCSVDAO> sort4sCompartor = sort6sComparator.thenComparing(ipl -> ipl.fourW);
        List<IPLCSVDAO> iplWktsDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplWktsDAOS, sort4sCompartor);
        String sorted4sData = new Gson().toJson(iplWktsDAOS);
        return sorted4sData;
    }
    public String getWicketsWiseSortingWithAvgOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortSRComparator = Comparator.comparing(ipl -> ipl.sr);
        Comparator<IPLCSVDAO> sort4wComparator = sortSRComparator.thenComparing(ipl -> ipl.fourW);
        Comparator<IPLCSVDAO> sort5wComparator = sort4wComparator.thenComparing(ipl -> ipl.fiveW);

        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sort5wComparator);
        String sorted4sData = new Gson().toJson(iplRunDAOS);
        return sorted4sData;

    }
    public String getSortingOnBattingAndBowlingAvgOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortBattingAvgComparator = Comparator.comparing(ipl -> ipl.avg);
        Comparator<IPLCSVDAO> sortBowlingAvgComparator = sortBattingAvgComparator.thenComparing(ipl -> ipl.avg);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortBowlingAvgComparator);
        String sortedAvgData = new Gson().toJson(iplRunDAOS);
        return sortedAvgData;
    }
    public String getSortingOnMostRunsAndWicketsOnData() throws IPLAnalyserException {

        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortRunsComparator = Comparator.comparing(ipl -> ipl.runs);
        Comparator<IPLCSVDAO> sortWktsComparator = sortRunsComparator.thenComparing(ipl -> ipl.wkts);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values()
                .stream()
                .collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortWktsComparator);
        String sortedData = new Gson().toJson(iplRunDAOS);
        return sortedData;
    }

}







