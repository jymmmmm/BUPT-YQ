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
        List<PolyList> poly_lists = new ArrayList<>();
        tableService.test_table();
        List<PolyString> polyarea = polyAreaMapper.getAllArea();
        if(polyarea.size() == 0){
            List<RiskyPersonArea> area=riskyAreaService.getRiskyArea();
            HashMap<Location,Integer> map = new HashMap<>();
            int poly_id=1;
            for(RiskyPersonArea a : area){
                Location location = new Location();
                int x = (int)a.getLat();
                int y = (int)a.getLon();
                String s=judge_level(a.getInfected_count(),a.getClosed_count());
                location.setLon(y).setLat(x).setStatus(s);
                map.put(location,0);
            }

            for(Location key:map.keySet())
            {
                PolyList poly_list=new PolyList().setList_data(generate_location(key));
                poly_list.setId(poly_id);
                poly_list.setStatus(key.getStatus());
                tableService.insert_info_table(poly_list);
                poly_lists.add(poly_list);
                poly_id++;
            }
        }
        else{
            for(PolyString a: polyarea){
                PolyList pl = conversion.ps_to_pl(a);
                poly_lists.add(pl);
            }
        }
        return poly_lists;
    }

}
