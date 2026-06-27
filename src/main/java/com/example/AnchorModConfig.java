package com.example;

public class AnchorModConfig {
    public static boolean enabled = true;
    public static int minDelay = 55;
    public static int maxDelay = 99;
    public static Mode switchMode = Mode.TOTEM;

    public enum Mode {
        TOTEM, ANCHOR
    }
}
