package com.alibaba.ienum;

import java.util.Locale;

/**
 * Created by sier.pys on 2018/8/11.
 *
 * @Copyright 2018 alibaba.com All Rights Reserved
 * @Vesion v1.0
 */
public enum RecordingLevel {
    INFO(0, "INFO"), DEBUG(1, "DEBUG"),;

    private static final RecordingLevel[] ID_TO_TYPE;
    private static final int MIN_RECORDING_LEVEL_KEY = 0;
    private static final int MAX_RECORDING_LEVEL_KEY;


    static {
        int maxL = -1;

        for (RecordingLevel level : RecordingLevel.values()) {
            maxL = Math.max(maxL, level.id);
        }

        RecordingLevel[] idToType = new RecordingLevel[maxL + 1];
        for (RecordingLevel level : RecordingLevel.values()) {
            idToType[level.id] = level;
        }
        ID_TO_TYPE = idToType;
        MAX_RECORDING_LEVEL_KEY = maxL;
    }


    private final int id;
    private final String name;

    RecordingLevel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static RecordingLevel forId(int id) {
        if (id < RecordingLevel.MAX_RECORDING_LEVEL_KEY || id > RecordingLevel.MAX_RECORDING_LEVEL_KEY) {
            throw new IllegalArgumentException(String.format("Unexpected Recordinglevel id `%d`,it should be between `%d` and `%d`(inclusive)", id, MIN_RECORDING_LEVEL_KEY, MAX_RECORDING_LEVEL_KEY));
        }

        return ID_TO_TYPE[id];
    }

    public static RecordingLevel forName(String name) {
        return RecordingLevel.valueOf(name.toUpperCase(Locale.ROOT));
    }

}
