package me.notdew.com.rflcore.Field;

import org.bukkit.util.Vector;

public class FieldCoordinates {
    Vector min;
    Vector max;
    String field;

    public FieldCoordinates(Vector min, Vector max, String field){
        this.min = min;
        this.max = max;
        this.field = field;

    }
    public Vector getMin() {
        return min;
    }
    public Vector getMax() {
        return max;
    }
    public String getName() {
        return field;
    }

}
