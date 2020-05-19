package com.CricketAnalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLRunCSV {

    @CsvBindByName(column = "POS", required = true)
    public int pos;

    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int mat;

    @CsvBindByName(column = "Inns", required = true)
    public int inns;

    @CsvBindByName(column = "NO", required = true)
    public int no;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "HS", required = true)
    public int hs;

    @CsvBindByName(column = "Avg", required = true)
    public double avg;

    @CsvBindByName(column = "BF", required = true)
    public int bf;

    @CsvBindByName(column = "SR", required = true)
    public double sr;

    @CsvBindByName(column = "100", required = true)
    public int hundred;

    @CsvBindByName(column = "50", required = true)
    public int fifty;

    @CsvBindByName(column = "4s", required = true)
    public int four;

    @CsvBindByName(column = "6s", required = true)
    public int six;

    public IPLRunCSV() {

    }


    @Override
    public String toString() {
        return "IPLRunCSV{" +
                "POS='" + pos + '\'' +
                ",PLAYER ='" + player + '\'' +
                ",Inns='" + inns + '\'' +
                ",Mat='" + mat + '\'' +
                ",NO='" + no + '\'' +
                ",Runs='" + runs + '\'' +
                ",HS='" + hs + '\'' +
                ",Avg='" + avg + '\'' +
                ",BF='" + bf + '\'' +
                ",SR='" + sr + '\'' +
                ",100='" + hundred + '\'' +
                ",50='" + fifty + '\'' +
                ",4s='" + four + '\'' +
                ",6s='" + six + '\'' +
                '}';

    }
}
