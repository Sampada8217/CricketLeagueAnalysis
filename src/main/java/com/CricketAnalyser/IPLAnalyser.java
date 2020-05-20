package com.CricketAnalyser;

import com.CommonCSVBuilder.CSVBuilderException;
import com.CommonCSVBuilder.CSVBuilderFactory;
import com.CommonCSVBuilder.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IPLAnalyser {
    List<IPLCSVDAO> iplDAOS = null;
    Map<String, IPLCSVDAO> iplDAOMap = null;

    public IPLAnalyser() {
        iplDAOMap = new HashMap<>();
        iplDAOS = new ArrayList<>();
    }

    public int loadCSVData(String filePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunCSV> csvIterator = csvBuilder
                    .getCSVFileIterator(reader, IPLRunCSV.class);
            Iterable<IPLRunCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplRunCSV -> {
                        iplDAOMap.put(iplRunCSV.player, new IPLCSVDAO(iplRunCSV));
                        iplDAOS.add(new IPLCSVDAO(iplRunCSV));
                    });

            return this.iplDAOMap.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

    public int loadCSVWktsData(String filePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLWktsCSV> csvIterator = csvBuilder
                    .getCSVFileIterator(reader, IPLWktsCSV.class);
            Iterable<IPLWktsCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplWktsCSV -> {
                        iplDAOMap.put(iplWktsCSV.player, new IPLCSVDAO(iplWktsCSV));
                        iplDAOS.add(new IPLCSVDAO(iplWktsCSV));
                    });

            return this.iplDAOMap.size();
        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }


    }


    public String getAverageWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.avg);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sortedData = new Gson().toJson(iplRunDAOS);
        return sortedData;

    }

    public String getSRWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.sr);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sortedSRData = new Gson().toJson(iplRunDAOS);
        return sortedSRData;
    }

    public String get6sWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.six);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sorted6sData = new Gson().toJson(iplRunDAOS);
        return sorted6sData;
    }

    public String get4sWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.four);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sorted6sData = new Gson().toJson(iplRunDAOS);
        return sorted6sData;
    }

    public String getSRWiseSortingWith6sAnd4sOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortSRComparator = Comparator.comparing(census -> census.sr);
        Comparator<IPLCSVDAO> sort6sComparator = sortSRComparator.thenComparing(census -> census.sr);
        Comparator<IPLCSVDAO> sort4sCompartor = sort6sComparator.thenComparing(census -> census.four);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sort4sCompartor);
        String sorted4sData = new Gson().toJson(iplRunDAOS);
        return sorted4sData;
    }

    public String getAverageWiseSortingWithSROnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortAvgComparator = Comparator.comparing(census -> census.avg);
        Comparator<IPLCSVDAO> sortSRComparator = sortAvgComparator.thenComparing(census -> census.sr);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortAvgComparator);
        String sortedAvgData = new Gson().toJson(iplRunDAOS);
        return sortedAvgData;

    }

    public String getRunsWiseSortingWithAvgOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> sortRunsComparator = Comparator.comparing(census -> census.runs);
        Comparator<IPLCSVDAO> sortAvgComparator = sortRunsComparator.thenComparing(census -> census.avg);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, sortAvgComparator);
        String sortedSRData = new Gson().toJson(iplRunDAOS);
        return sortedSRData;
    }

    public String getBowlingEconomyRateWiseSortingOnData() throws IPLAnalyserException {
        if (iplDAOMap == null || iplDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.econ);
        List<IPLCSVDAO> iplRunDAOS = iplDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sortedSRData = new Gson().toJson(iplRunDAOS);
        return sortedSRData;
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






