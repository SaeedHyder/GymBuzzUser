package com.app.gymbuzz.entities;

/**
 * Created on 5/28/2018.
 */


public class ExserciseLogEnt {

    String excerciseSubType;
    String reps;
    String kgs;
    String sets;

    public ExserciseLogEnt(String excerciseSubType, String reps, String kgs, String sets){

        setExcerciseSubType(excerciseSubType);
        setReps(reps);
        setKgs(kgs);
        setSets(sets);

    }

    public String getExcerciseSubType() {
        return excerciseSubType;
    }

    public void setExcerciseSubType(String excerciseSubType) {
        this.excerciseSubType = excerciseSubType;
    }

    public String getReps() {
        return reps;
    }

    public void setReps(String reps) {
        this.reps = reps;
    }

    public String getKgs() {
        return kgs;
    }

    public void setKgs(String kgs) {
        this.kgs = kgs;
    }

    public String getSets() {
        return sets;
    }

    public void setSets(String sets) {
        this.sets = sets;
    }
}
