package com.CricketAnalyser;

public class IPLAnalyserException  extends Exception {

        enum ExceptionType {
            IPL_FILE_PROBLEM ,NO_CENSUS_DATA;
        }
        ExceptionType type;


        public IPLAnalyserException(String message, ExceptionType type) {
            super(message);
            this.type=type;

        }
        public IPLAnalyserException(String message, String name) {
            super(message);
            this.type=ExceptionType.valueOf(name);

        }
    }


