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
    List<IPLRunCSVDAO> iplRunDAOS = null;
    Map<String, IPLRunCSVDAO> iplRunDAOMap = null;

    public IPLAnalyser() {
        iplRunDAOMap = new HashMap<>();
        iplRunDAOS = new ArrayList<IPLRunCSVDAO>();
    }

    public int loadCSVData(String filePath) throws IPLAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunCSV> csvIterator = csvBuilder
                    .getCSVFileIterator(reader, IPLRunCSV.class);
            Iterable<IPLRunCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplRunCSV -> {
                        iplRunDAOMap.put(iplRunCSV.player, new IPLRunCSVDAO(iplRunCSV));
                        iplRunDAOS.add(new IPLRunCSVDAO(iplRunCSV));
                    });

            return iplRunDAOMap.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

    public String getAverageWiseSortingOnData() throws IPLAnalyserException {
        if (iplRunDAOMap == null || iplRunDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLRunCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.avg);
        List<IPLRunCSVDAO> iplRunDAOS = iplRunDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sortedData = new Gson().toJson(iplRunDAOS);
        return sortedData;

    }
    public String getSRWiseSortingOnData() throws IPLAnalyserException {
        if (iplRunDAOMap == null || iplRunDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLRunCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.sr);
        List<IPLRunCSVDAO> iplRunDAOS = iplRunDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sortedSRData = new Gson().toJson(iplRunDAOS);
        return sortedSRData;
    }
    public String get6sWiseSortingOnData() throws IPLAnalyserException {
        if (iplRunDAOMap == null || iplRunDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLRunCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.six);
        List<IPLRunCSVDAO> iplRunDAOS = iplRunDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sorted6sData = new Gson().toJson(iplRunDAOS);
        return sorted6sData;
    }
    public String get4sWiseSortingOnData() throws IPLAnalyserException {
        if (iplRunDAOMap == null || iplRunDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IPLRunCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.four);
        List<IPLRunCSVDAO> iplRunDAOS = iplRunDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sorted6sData = new Gson().toJson(iplRunDAOS);
        return sorted6sData;
    }

    private void sortByDescending(List<IPLRunCSVDAO> iplRunDAOS,Comparator<IPLRunCSVDAO> censusComparator) {
        for (int i = 0; i < iplRunDAOS.size() - 1; i++) {
            for (int j = 0; j < iplRunDAOS.size() - i - 1; j++) {
                IPLRunCSVDAO census1 = iplRunDAOS.get(j);
                IPLRunCSVDAO census2 = iplRunDAOS.get(j + 1);
                if (censusComparator.compare(census1, census2) < 0) {
                    iplRunDAOS.set(j, census2);
                    iplRunDAOS.set(j + 1, census1);
                }
            }
        }

    }
}






