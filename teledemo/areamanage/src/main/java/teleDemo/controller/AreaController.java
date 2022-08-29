package teleDemo.controller;


import org.springframework.web.bind.annotation.*;
import teleDemo.entities.*;
import teleDemo.service.PolyAreaService;
import teleDemo.service.RiskyAreaService;
import teleDemo.service.TableService;

import javax.annotation.Resource;
import java.util.List;

import static teleDemo.util.conversion.pp_to_pl;

@RestController
public class AreaController {
    @Resource
    RiskyAreaService riskyAreaService;
    @Resource
    PolyAreaService polyAreaService;
    @Resource
    TableService tableService;

    @ResponseBody
    @GetMapping("/v1/area")
    public GetVo getRiskyArea(){
        List<RiskyPersonArea> areas= riskyAreaService.getRiskyArea();
        GetVo<RiskyPersonArea> getVo=new GetVo<>(0,"获取数据成功！",1,areas);
        return getVo;
    }

    @ResponseBody
    @GetMapping("/v1/poly")
    public GetVo getRiskyPoly(){
        List<PolyList> polyarea =polyAreaService.getpolyArea();
        GetVo<PolyList> getVo = new GetVo<>(0,"获取数据成功！",1,polyarea);
        return getVo;
    }

    @PostMapping("/v1/polyy")
    public PolyPost postRiskyArea(@RequestBody PolyPost poly_post){
        PolyList poly_list=pp_to_pl(poly_post);
        tableService.update_info_table(poly_list);
        return poly_post;
    }
}
