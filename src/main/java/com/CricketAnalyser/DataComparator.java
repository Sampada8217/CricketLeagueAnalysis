package com.CricketAnalyser;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class DataComparator {

    public static Map<DataFields, Comparator> fieldComparator = new HashMap<>();

    public enum DataFields {

        BATTING_AVERAGE, BATTING_STRIKING_RATE, SIXES, FOURS, SR_WITH_SIXES_AND_FOURS,
        AVG_WITH_SR, RUNS_WITH_AVG, BOWLING_AVERAGE, BOWLING_STRIKING_RATE, ECONOMY_RATE, AVG_WITH_WICKETS, SR_WITH_FIVE_AND_FOUR,
       BOWLING_AVG_WITH_SR, BATTING_AND_BOWLING_AVERAGE, RUNS_WITH_WICKETS

    }

    public DataComparator() {
    }

    static{

        Comparator<IPLCSVDAO> sortAvgIplComparator = Comparator.comparing(ipl -> ipl.avg);
        fieldComparator.put(DataFields.BATTING_AVERAGE, sortAvgIplComparator);

        Comparator<IPLCSVDAO> sortSRIplComparator = Comparator.comparing(ipl -> ipl.sr);
        fieldComparator.put(DataFields.BATTING_STRIKING_RATE, sortSRIplComparator);

        Comparator<IPLCSVDAO> sort6sIplComparator = Comparator.comparing(ipl -> ipl.six);
        fieldComparator.put(DataFields.SIXES, sort6sIplComparator);

        Comparator<IPLCSVDAO> sort4sIplComparator = Comparator.comparing(ipl -> ipl.four);
        fieldComparator.put(DataFields.FOURS, sort4sIplComparator);

        Comparator<IPLCSVDAO> sortSRComparator = Comparator.comparing(ipl -> ipl.sr);
        Comparator<IPLCSVDAO> sort6sComparator = sortSRComparator.thenComparing(ipl -> ipl.six);
        Comparator<IPLCSVDAO> sort4sComparator = sort6sComparator.thenComparing(ipl -> ipl.four);
        fieldComparator.put(DataFields.SR_WITH_SIXES_AND_FOURS, sort4sComparator);

        Comparator<IPLCSVDAO> sortAvgComparator = Comparator.comparing(ipl -> ipl.avg);
        Comparator<IPLCSVDAO> sortSRBattingComparator = sortAvgComparator.thenComparing(ipl-> ipl.sr);
        fieldComparator.put(DataFields.AVG_WITH_SR, sortSRBattingComparator);

        Comparator<IPLCSVDAO> sortRunsComparator = Comparator.comparing(ipl -> ipl.runs);
        Comparator<IPLCSVDAO> sortRunWithAvgComparator = sortRunsComparator.thenComparing(ipl -> ipl.avg);
        fieldComparator.put(DataFields.RUNS_WITH_AVG, sortRunWithAvgComparator);

        Comparator<IPLCSVDAO> sortEconComparator = Comparator.comparing(ipl -> ipl.econ);
        fieldComparator.put(DataFields.ECONOMY_RATE, sortEconComparator);

        Comparator<IPLCSVDAO> sortSRBowlingWicketComparator = Comparator.comparing(ipl -> ipl.sr);
        fieldComparator.put(DataFields.BOWLING_STRIKING_RATE, sortSRBowlingWicketComparator);

        Comparator<IPLCSVDAO> sortBowlingAverageComparator = Comparator.comparing(ipl -> ipl.avg);
        fieldComparator.put(DataFields.BOWLING_AVERAGE, sortBowlingAverageComparator);

        Comparator<IPLCSVDAO> sortBowlingAvComparator = Comparator.comparing(ipl -> ipl.avg);
        Comparator<IPLCSVDAO> sortBowlingSRComparator = sortBowlingAvComparator.thenComparing(ipl -> ipl.sr);
        fieldComparator.put(DataFields.BOWLING_AVG_WITH_SR, sortBowlingSRComparator);


        Comparator<IPLCSVDAO> sortStrikingComparator = Comparator.comparing(ipl -> ipl.sr);
        Comparator<IPLCSVDAO> sort4wComparator = sortStrikingComparator.thenComparing(ipl -> ipl.fourW);
        Comparator<IPLCSVDAO> sort5wComparator = sort4wComparator.thenComparing(ipl -> ipl.fiveW);
        fieldComparator.put(DataFields.SR_WITH_FIVE_AND_FOUR, sort5wComparator);

        Comparator<IPLCSVDAO> sortBattingAvgComparator = Comparator.comparing(ipl -> ipl.avg);
        Comparator<IPLCSVDAO> sortBowlingAvgComparator = sortBattingAvgComparator.thenComparing(ipl -> ipl.avg);
        fieldComparator.put(DataFields.BATTING_AND_BOWLING_AVERAGE, sortBowlingAvgComparator);

        Comparator<IPLCSVDAO> sortRunComparator = Comparator.comparing(ipl -> ipl.runs);
        Comparator<IPLCSVDAO> sortWktsComparator = sortRunComparator.thenComparing(ipl -> ipl.wkts);
        fieldComparator.put(DataFields.RUNS_WITH_WICKETS, sortWktsComparator);

        Comparator<IPLCSVDAO> sortComparator = Comparator.comparing(ipl -> ipl.avg);
        Comparator<IPLCSVDAO> sortWicketsComparator = sortComparator.thenComparing(ipl -> ipl.wkts);
        fieldComparator.put(DataFields.AVG_WITH_WICKETS, sortWicketsComparator);

    }

    public static Comparator getCompareDataField(DataFields field) {
        Comparator<IPLCSVDAO> iplComparator = fieldComparator.get(field);
        return iplComparator;
    }
}




