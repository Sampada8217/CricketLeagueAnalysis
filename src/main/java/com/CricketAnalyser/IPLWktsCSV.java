package com.CricketAnalyser;

import com.opencsv.bean.CsvBindByName;

public class IPLWktsCSV {

    @CsvBindByName(column = "POS")
    public int pos;

    @CsvBindByName(column="PLAYER")
    public String player;

    @CsvBindByName(column = "Mat")
    public int mat;

    @CsvBindByName(column = "Inns" )
    public int inns;

    @CsvBindByName(column = "Ov")
    public double ov;

    @CsvBindByName(column = "Runs")
    public int runs;

    @CsvBindByName(column = "Wkts")
    public int wkts;

    @CsvBindByName(column = "BBI")
    public int bbi;

    @CsvBindByName(column = "Avg")
    public double avg;

    @CsvBindByName(column = "Econ")
    public double econ;

    @CsvBindByName(column = "SR")
    public double sr;

    @CsvBindByName(column = "4w")
    public int fourW;

    @CsvBindByName(column = "5w")
    public int fiveW;


    public IPLWktsCSV() {

    }

    public IPLWktsCSV(int pos, String player, int runs, int wkts, double avg, double sr, int fourW, int fiveW) {
    }


    @Override
    public String toString() {
        return "IPLRunCSV{" +
                " POS='" + pos + '\'' +
                ",PLAYER ='" + player + '\'' +
                ",Inns='" + inns + '\'' +
                ",Mat='" + mat + '\'' +
                ",Ov='" + ov + '\'' +
                ",Runs='" + runs + '\'' +
                ",BBI='" + bbi + '\'' +
                ",Avg='" + avg + '\'' +
                ",Econ='" + econ + '\'' +
                ",SR='" + sr + '\'' +
                ",4w='" + fourW + '\'' +
                ",5w='" + fiveW + '\'' +
                '}';

    }
}
