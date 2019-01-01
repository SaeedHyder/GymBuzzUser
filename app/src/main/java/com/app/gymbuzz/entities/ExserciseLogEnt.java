package com.app.gymbuzz.entities;

/**
 * Created on 5/28/2018.
 */


public class ExserciseLogEnt {

    private String excerciseSubType;
    private String reps;
    private String kgs;
    private String sets;

    public ExserciseLogEnt(String excerciseSubType, String reps, String kgs, int sets) {

        setExcerciseSubType(excerciseSubType);
        setReps(reps + "");
        setKgs(kgs + "");
        setSets(sets + "");

    }

    public String getExcerciseSubType() {
        return excerciseSubType;
    }

    private void setExcerciseSubType(String excerciseSubType) {
        this.excerciseSubType = excerciseSubType;
    }

    public String getReps() {
        return reps;
    }

    private void setReps(String reps) {
        this.reps = reps;
    }

    public String getKgs() {
        return kgs;
    }

    private void setKgs(String kgs) {
        this.kgs = kgs;
    }

    public String getSets() {
        return sets;
    }

    private void setSets(String sets) {
        this.sets = sets;
    }
}
