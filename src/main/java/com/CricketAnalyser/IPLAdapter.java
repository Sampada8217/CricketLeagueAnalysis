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

public abstract class IPLAdapter {
    public abstract Map<String, IPLCSVDAO> loadCSVData(String... filePath) throws IPLAnalyserException;
    public <E> Map<String,IPLCSVDAO> loadCSVData(Class<E> iplCSVClass, String filePath) throws IPLAnalyserException {
        Map<String, IPLCSVDAO> iplDAOMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> csvIterator = csvBuilder
                                            .getCSVFileIterator(reader, iplCSVClass);
            Iterable<E> csvIterable = () -> csvIterator;
            if (iplCSVClass.getName().equals("com.CricketAnalyser.IPLRunCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                        .map(IPLRunCSV.class::cast)
                        .forEach(iplCSV -> iplDAOMap.put(iplCSV.player, new IPLCSVDAO(iplCSV)));

            } else if (iplCSVClass.getName().equals("com.CricketAnalyser.IPLWktsCSV")) {
                StreamSupport.stream(csvIterable.spliterator(), false)
                            .map(IPLWktsCSV.class::cast)
                            .forEach(iplCSV -> iplDAOMap.put(iplCSV.player, new IPLCSVDAO(iplCSV)));


            }
                return iplDAOMap;

            } catch(IOException | CSVBuilderException e){
                throw new IPLAnalyserException(e.getMessage(),
                        IPLAnalyserException.ExceptionType.IPL_FILE_PROBLEM);
            }
        }

    }

