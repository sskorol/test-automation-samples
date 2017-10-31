package io.github.sskorol.tmp;

import java.util.List;

/**
 * Author: io.github.sskorol
 */
public class Laptop extends Computer {

    public Laptop(int ram, ComputerModel cpuModel, int hdd, OS os, boolean hasCD, List<VideoCard> videoCards) {
        super(ram, cpuModel, hdd, os, hasCD, videoCards);
    }
}
