package de.doulrion.mc_rp_medieval.areamanager;

import java.util.ArrayList;
import java.util.List;

public class AreaHandler {
    private static AreaHandler instance;
    private List<Area> list;

    private AreaHandler(){
        list = initAreas();
    }

    public static AreaHandler getInstance() {
        if (instance == null) {
            instance = new AreaHandler();
        }
        return instance;
    }

    public void createArea(String name){
        list.add(new Area(name) );
        save();
    }
    public void removeArea(String name){
        int counter = 0;
        for(Area a : list ){
            if(a.name.equals(name.trim())){
                list.remove(counter);
                save();
                return;
            }

            counter++;
        }
    }
    public void editAreaPos1(String name, int x, int y){
        int counter = 0;
        for(Area a : list ){
            if(a.name.equals(name.trim())){
                a.setLoc1(x, y);
                list.set(counter,a);
                save();
                return;
            }

            counter++;
        }
    }
    public void editAreaPos2(String name, int x, int y){
        int counter = 0;
        for(Area a : list ){
            if(a.name.equals(name.trim())){
                a.setLoc2(x, y);
                list.set(counter,a);
                save();
                return;
            }

            counter++;
        }
    }
    public void editName(String name, String newName){
        for(Area a : list ){
            if(a.name.equals(name.trim())){
                a.name = newName;
                save();
                return;
            }
        }
    }
    public List<String> listAreas(){

        List<String> names = new ArrayList<>();
        for(Area a : list)
            names.add(a.name);

        return names;
    }

    public int getAreaLevel(double x, double y){
        for(Area a : list){
            double highX;
            double lowX;
            if(a.Loc1.X > a.Loc2.X){highX = a.Loc1.X; lowX = a.Loc2.X;}
            else{highX = a.Loc2.X; lowX = a.Loc1.X;}
            if(x > highX || x < lowX ){continue;}

            double highY;
            double lowY;
            if(a.Loc1.Z > a.Loc2.Z){highY = a.Loc1.Z; lowY = a.Loc2.Z;}
            else{highY = a.Loc2.Z; lowY = a.Loc1.Z;}
            if(y > highY || y < lowY ){continue;}

            return a.level;
        }
        return 0;
    }


    private List<Area> initAreas(){
        return AreaBuilder.getAreas();
    }
    private void save(){
        AreaBuilder.saveAreas(list);
    }
}
