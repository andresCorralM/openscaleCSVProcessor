package org.iampiti.openscale.model;

/**
 * Columns exported by Openscale to a CSV
 *
 * @author Andres
 */
public enum Column {
    BICEPS("biceps"), BONE("bone"), CALIPER1("caliper1"), CALIPER2("caliper2"),
    CALIPER3("caliper3"), CALORIES("calories"), CHEST("chest"), COMMENT("comment"),
    DATETIME("dateTime"), FAT("fat"), HIP("hip"), LBM("lbm"), MUSCLE("muscle"),
    NECK("neck"), THIGH("thigh"), VISCERALFAT("visceralFat"), WAIST("waist"),
    WATER("water"), WEIGHT("weight");

    private final String csvName;

    Column(String csvName) {
        this.csvName = csvName;
    }

    @Override
    public String toString() {
        return csvName;
    }
}
