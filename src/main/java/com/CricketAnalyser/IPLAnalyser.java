package com.CricketAnalyser;

import com.CommonCSVBuilder.CSVBuilderException;
import com.CommonCSVBuilder.CSVBuilderFactory;
import com.CommonCSVBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;


public class IPLAnalyser {
    public int loadCSVData(String filePath) throws IPLAnalyserException {

            try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
                Map<String,IPLRunCSVDAO> iplRunDAOMap=new HashMap<>();
                ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
                Iterator<IPLRunCSV> csvIterator=csvBuilder
                                            .getCSVFileIterator(reader,IPLRunCSV.class);
                Iterable<IPLRunCSV> csvIterable=()->csvIterator;
                StreamSupport.stream(csvIterable.spliterator(),false)
                             .forEach(iplRunCSV -> iplRunDAOMap.put(iplRunCSV.player,new IPLRunCSVDAO(iplRunCSV)));
                return iplRunDAOMap.size();

            } catch (IOException | CSVBuilderException e) {
                throw new IPLAnalyserException(e.getMessage(),
                        IPLAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
            }
    }
}





