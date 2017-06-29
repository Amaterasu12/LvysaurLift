package com.amaterasu.czhang.lvysaurlift;

import java.io.Serializable;

/**
 * Created by czhang on 6/29/2017.
 */

public class StaticMetadataHelper implements Serializable {
    private int mBP;
    private int mSQ;
    private int mOHP;
    private int mDL;
    private int mROW;
    private WorkoutData.WorkoutType mCurrentType;

    public StaticMetadataHelper() {
        mBP = WorkoutData.BP;
        mSQ = WorkoutData.SQ;
        mOHP = WorkoutData.OHP;
        mDL = WorkoutData.DL;
        mROW = WorkoutData.ROW;
        mCurrentType = WorkoutData.currentType;
    }

    public void restoreMetadata() {
        WorkoutData.BP = mBP;
        WorkoutData.SQ = mSQ;
        WorkoutData.OHP = mOHP;
        WorkoutData.DL = mDL;
        WorkoutData.ROW = mROW;
        WorkoutData.currentType = mCurrentType;
    }
}
