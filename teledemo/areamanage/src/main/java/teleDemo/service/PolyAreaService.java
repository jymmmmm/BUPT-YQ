package teleDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teleDemo.entities.Location;
import teleDemo.entities.PolyList;
import teleDemo.entities.PolyString;
import teleDemo.entities.RiskyPersonArea;
import teleDemo.mapper.PolyAreaMapper;
import teleDemo.mapper.RiskyAreaMapper;
import teleDemo.util.conversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static teleDemo.util.area_policy.generate_location;
import static teleDemo.util.area_policy.judge_level;

/**
 * @Projectname: 项目前后端架构(1)
 * @Filename: polyAreaService
 * @Author: Jia Yiming
 * @Data:2022/8/24 14:09
 */

@Service
public class PolyAreaService {
    @Autowired
    PolyAreaMapper polyAreaMapper;

    @Autowired
    RiskyAreaMapper riskyAreaMapper;

    @Autowired
    TableService tableService;

    @Autowired
    RiskyAreaService riskyAreaService;

    public List<PolyList> getpolyArea(){
        List<PolyList> polyLists = new ArrayList<>();
        tableService.test_table();
        List<PolyString> polyArea = polyAreaMapper.getAllArea();
        if(polyArea.size() == 0){
            List<RiskyPersonArea> area=riskyAreaService.getRiskyArea();
            HashMap<Location,Integer> map = new HashMap<>();
            int polyId=1;
            for(RiskyPersonArea a : area){
                Location location = new Location();
                int x = (int)a.getLat();
                int y = (int)a.getLon();
                String s=judge_level(a.getInfectedCount(),a.getClosedCount());
                location.setLon(y).setLat(x).setStatus(s);
                map.put(location,0);
            }

            for(Location key:map.keySet())
            {
                PolyList polyList=new PolyList().setList_data(generate_location(key));
                polyList.setId(polyId);
                polyList.setStatus(key.getStatus());
                tableService.insert_info_table(polyList);
                polyLists.add(polyList);
                polyId++;
            }
        }
        else{
            for(PolyString a: polyArea){
                PolyList pl = conversion.ps_to_pl(a);
                polyLists.add(pl);
            }
        }
        return polyLists;
    }

}
