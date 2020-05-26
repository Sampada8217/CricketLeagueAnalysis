package com.CricketAnalyser;

public class IPLAnalyserException  extends Exception {

        enum ExceptionType {
            IPL_FILE_PROBLEM ,INVALID_IPL_SHEET;
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


