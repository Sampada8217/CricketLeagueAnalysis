package com.CricketAnalyser;

public class IPLAnalyserException  extends Exception {

        enum ExceptionType {
            CENSUS_FILE_PROBLEM;
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



