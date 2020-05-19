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
        this.iplRunDAOMap = new HashMap<>();
    }

    public int loadCSVData(String filePath) throws IPLAnalyserException {
        if (iplRunDAOMap == null || iplRunDAOMap.size() == 0) {
            throw new IPLAnalyserException("No Census Data", IPLAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IPLRunCSV> csvIterator = csvBuilder
                    .getCSVFileIterator(reader, IPLRunCSV.class);
            Iterable<IPLRunCSV> csvIterable = () -> csvIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .forEach(iplRunCSV -> iplRunDAOMap.put(iplRunCSV.player, new IPLRunCSVDAO(iplRunCSV)));
            return iplRunDAOMap.size();

        } catch (IOException | CSVBuilderException e) {
            throw new IPLAnalyserException(e.getMessage(),
                    IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
        }
    }

    public String getAverageWiseSortingOnData() throws IPLAnalyserException {

        Comparator<IPLRunCSVDAO> censusCSVComparator = Comparator.comparing(census -> census.avg);
        List<IPLRunCSVDAO> iplRunDAOS = iplRunDAOMap.values().stream().collect(Collectors.toList());
        this.sortByDescending(iplRunDAOS, censusCSVComparator);
        String sortedData = new Gson().toJson(iplRunDAOS);
        return sortedData;

    }


    private void sortByDescending(List<IPLRunCSVDAO> censusDAOS, Comparator<IPLRunCSVDAO> censusCSVComparator) {
        for (int i = 0; i < censusDAOS.size() - 1; i++) {
            for (int j = 0; j < censusDAOS.size() - i - 1; j++) {
                IPLRunCSVDAO csvDAO1 = iplRunDAOS.get(j);
                IPLRunCSVDAO csvDAO2 = iplRunDAOS.get(j + 1);
                if (censusCSVComparator.compare(csvDAO1, csvDAO2) < 0) {
                    iplRunDAOS.set(j, csvDAO1);
                    iplRunDAOS.set(j + 1, csvDAO2);

                }


            }
        }
    }
}





