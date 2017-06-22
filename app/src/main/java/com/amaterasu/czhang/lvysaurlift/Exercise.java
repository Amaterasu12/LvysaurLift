package com.amaterasu.czhang.lvysaurlift;

/**
 * Created by czhang on 6/13/2017.
 */

public class Exercise {
    private String fullName;
    private String shortName;
    private int weightLB;
    private int sets;
    private int reps;

    public static final int AMRAP = 999;
    public static final int CHIN_UP = -1;

    public Exercise(String _fullName,
                    String _shortName,
                    int _weightLB,
                    int _sets,
                    int _reps) {

        fullName = _fullName;
        shortName = _shortName;
        weightLB = _weightLB;
        sets = _sets;
        reps = _reps;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public int getWeightLB() {
        return weightLB;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setWeightLB(int weightLB) {
        this.weightLB = weightLB;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }
}
