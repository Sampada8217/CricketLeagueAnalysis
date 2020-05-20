package com.CricketAnalyser;

public class IPLCSVDAO {
    public int pos;
    public String player;
    public int mat;
    public int inns ;
    public int no;
    public int runs;
    public int hs;
    public double avg;
    public int  bf;
    public double sr;
    public int hundred;
    public int fifty;
    public int four;
    public int six;
    public double ov;
    public int wkts;
    public int bbi;
    public double econ;
    public int fourW;
    public int fiveW;

    public IPLCSVDAO(IPLRunCSV iplRunCSV) {
        pos= iplRunCSV.pos;
        player =iplRunCSV.player;
        mat = iplRunCSV.mat;
        inns=iplRunCSV.inns;
        no=iplRunCSV.no;
        runs= iplRunCSV.runs;
        player =iplRunCSV.player;
        hs = iplRunCSV.hs;
        avg=iplRunCSV.avg;
        bf=iplRunCSV.bf;
        sr = iplRunCSV.sr;
        hundred=iplRunCSV.hundred;
        fifty=iplRunCSV.fifty;
        four=iplRunCSV.four;
        six=iplRunCSV.six;

    }


    public IPLCSVDAO(IPLWktsCSV iplWktsCSV) {
        pos= iplWktsCSV.pos;
        player =iplWktsCSV.player;
        mat = iplWktsCSV.mat;
        inns=iplWktsCSV.inns;
        ov=iplWktsCSV.ov;
        runs= iplWktsCSV.runs;
        wkts= iplWktsCSV.wkts;
        bbi=iplWktsCSV.bbi;
        avg=iplWktsCSV.avg;
        econ=iplWktsCSV.econ;
        sr = iplWktsCSV.sr;
        fourW=iplWktsCSV.fourW;
        fiveW=iplWktsCSV.fiveW;
    }
}

