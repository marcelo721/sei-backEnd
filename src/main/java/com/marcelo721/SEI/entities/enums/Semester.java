package com.marcelo721.SEI.entities.enums;

import lombok.Getter;

@Getter
public enum Semester {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5),
    SIXTH(6),
    SEVENTH(7),
    EIGHTH(8),
    NINTH(9),
    TENTH(10);

    private final int value;

    Semester(int value) {
        this.value = value;
    }

    public static Semester fromValue(int value) {
        for (Semester semester : Semester.values()) {
            if (semester.getValue() == value) {
                return semester;
            }
        }
        throw new IllegalArgumentException("Invalid value for Semester: " + value);
    }
}
