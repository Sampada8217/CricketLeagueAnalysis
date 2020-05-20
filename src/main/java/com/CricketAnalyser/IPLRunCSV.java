package com.CricketAnalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLRunCSV {

    @CsvBindByName(column = "POS")
    public int pos;

    @CsvBindByName(column="PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat")
    public int mat;

    @CsvBindByName(column = "Inns" )
    public int inns;

    @CsvBindByName(column = "NO")
    public int no;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "HS")
    public int hs;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "BF")
    public int bf;

    @CsvBindByName(column = "SR")
    public double sr;

    @CsvBindByName(column = "100")
    public int hundred;

    @CsvBindByName(column = "50")
    public int fifty;

    @CsvBindByName(column = "4s")
    public int four;

    @CsvBindByName(column = "6s")
    public int six;

    public IPLRunCSV() {

    }


    @Override
    public String toString() {
        return "IPLRunCSV{" +
                " POS='" + pos + '\'' +
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
