package de.doulrion.mc_rp_medieval.areamanager;

public class Area {
    public String name;
    public Location Loc1 = new Location();
    public Location Loc2 = new Location();
    public int level = 0;

    public Area(String name){
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setLoc1(double x, double z) {
        Loc1.X = x;
        Loc1.Z = z;
    }

    public void setLoc2(double x, double z) {
        Loc2.X = x;
        Loc2.Z = z;
    }
}

