package io.github.sskorol.tmp;

import java.util.List;

/**
 * Author: io.github.sskorol
 */
public abstract class Computer {

    private final int ram;
    private final ComputerModel cpuModel;
    private final int hdd;
    private final OS os;
    private final boolean hasCD;
    private final List<VideoCard> videoCards;

    protected Computer(int ram, ComputerModel cpuModel, int hdd, OS os, boolean hasCD, List<VideoCard> videoCards) {
        this.ram = ram;
        this.cpuModel = cpuModel;
        this.hdd = hdd;
        this.os = os;
        this.hasCD = hasCD;
        this.videoCards = videoCards;
    }

    public int getRam() {
        return ram;
    }

    public ComputerModel getCpuModel() {
        return cpuModel;
    }

    public int getHdd() {
        return hdd;
    }

    public OS getOs() {
        return os;
    }

    public boolean isHasCD() {
        return hasCD;
    }

    public List<VideoCard> getVideoCards() {
        return videoCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Computer computer = (Computer) o;

        if (ram != computer.ram) return false;
        if (hdd != computer.hdd) return false;
        if (hasCD != computer.hasCD) return false;
        if (cpuModel != computer.cpuModel) return false;
        if (os != computer.os) return false;
        return videoCards.equals(computer.videoCards);
    }

    @Override
    public int hashCode() {
        int result = ram;
        result = 31 * result + cpuModel.hashCode();
        result = 31 * result + hdd;
        result = 31 * result + os.hashCode();
        result = 31 * result + (hasCD ? 1 : 0);
        result = 31 * result + videoCards.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "ram=" + ram +
                ", cpuModel=" + cpuModel +
                ", hdd=" + hdd +
                ", os=" + os +
                ", hasCD=" + hasCD +
                ", videoCards=" + videoCards +
                '}';
    }
}
