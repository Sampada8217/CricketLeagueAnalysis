package com.CricketAnalyser;

public class IPLRunCSVDAO {
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


    public IPLRunCSVDAO(IPLRunCSV iplRunCSV) {
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
}
