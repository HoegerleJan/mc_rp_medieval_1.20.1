package de.doulrion.mc_rp_medieval.areamanager;

import java.util.ArrayList;
import java.util.List;

public class AreaBuilder {

    public static List<Area> getAreas(){
        MyConfig src = new MyConfig();
        List<Area> areas = new ArrayList<>();
        for(String s : src.read() ){
            String[] vals = s.split(",");
            if(vals.length < 6){ return areas; }
            Area tempArea = new Area(vals[0]);
            tempArea.setLevel(Integer.parseInt(vals[5]));
            tempArea.setLoc1(Double.parseDouble(vals[1].trim()) , Double.parseDouble(vals[2].trim()));
            tempArea.setLoc2(Double.parseDouble(vals[3].trim()) , Double.parseDouble(vals[4].trim()));
            areas.add(tempArea);
        }
        return areas;
    }

    public static void saveAreas(List<Area> areas){
        List<String> list = new ArrayList<>();
        for (Area a : areas){
            list.add(a.name + "," + a.Loc1.X  + "," + a.Loc1.Z + "," + a.Loc2.X  + "," + a.Loc2.Z + "," + a.level );
        }
        MyConfig src = new MyConfig();
        src.write(list);
    }
}
