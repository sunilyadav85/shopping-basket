package com.interview.cs.enums;

public enum FruitType {

    APPLE("Apple"), BANANA("Banana"), MELON("Melon"), LIME("Lime");

    private String displayName;

    FruitType(String name) {
        this.displayName = name;
    }

    public String getDisplayName() {
        return displayName;
    }
}
