package teleDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teleDemo.entities.Location;
import teleDemo.entities.RiskyPersonArea;
import teleDemo.mapper.RiskyAreaMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class RiskyAreaService {
    @Autowired
    RiskyAreaMapper riskyAreaMapper;

    public List<RiskyPersonArea> getRiskyArea(){
        List<RiskyPersonArea> area = riskyAreaMapper.getAllArea();
        HashMap<Location,Integer> map = new HashMap<>();
        HashMap<Location,Integer> repeatMap=new HashMap<>();
        HashMap<Location,Integer> countInfectedMap = new HashMap<>();
        HashMap<Location,Integer> countClosedMap = new HashMap<>();
        for(RiskyPersonArea a : area){
            Location location = new Location();
            int x = (int)a.getLat();
            int y = (int)a.getLon();
            String s =a.getStatus();
            location.setLon(y).setLat(x).setStatus(s);
            if (map.containsKey(location)){
                if(location.getStatus().equals("3")){
                countInfectedMap.put(location,countInfectedMap.get(location) + 1);
                }
                else{
                    countClosedMap.put(location,countClosedMap.get(location) + 1);
                }
                continue;
            }
            map.put(location,a.getId());
            if(location.getStatus().equals("3"))
            {
                countInfectedMap.put(location,1);
            }
            else{
                countClosedMap.put(location,1);
            }
        }
        List<RiskyPersonArea> numArea = new ArrayList<>();
        Location tempLocation=new Location();
        Location storedLocation=new Location();
        for (Location key:map.keySet() ){
            if(!repeatMap.containsKey(key)){
            RiskyPersonArea r = new RiskyPersonArea().setLat(key.getLat())
                    .setLon(key.getLon())
                    .setId(0)
                    .setStatus(null);
            if(key.getStatus().equals("3"))
            {
                r.setInfectedCount(countInfectedMap.get(key));
                tempLocation.setLat(key.getLat()).setLon(key.getLon()).setStatus("2");
                if(map.containsKey(tempLocation)){
                    r.setClosedCount(countClosedMap.get(tempLocation));
                    repeatMap.put(storedLocation.setLat(tempLocation.getLat()).setLon(tempLocation.getLon()).setStatus(tempLocation.getStatus()),0);
                }
            }
            else{
                r.setClosedCount(countClosedMap.get(key));
                tempLocation.setLat(key.getLat()).setLon(key.getLon()).setStatus("3");
                if(map.containsKey(tempLocation)){
                    r.setInfectedCount(countInfectedMap.get(tempLocation));
                    repeatMap.put(storedLocation.setLat(tempLocation.getLat()).setLon(tempLocation.getLon()).setStatus(tempLocation.getStatus()),0);
                }
            }
            numArea.add(r);
            }
        }
        return numArea;

    }
}
