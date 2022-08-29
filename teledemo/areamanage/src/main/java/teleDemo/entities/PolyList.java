package teleDemo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import teleDemo.util.Pair;

import java.util.List;

/**
 * @Projectname: 项目前后端架构(1)
 * @Filename: poly_list
 * @Author: Jia Yiming
 * @Data:2022/8/24 14:34
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PolyList extends Poly {
    List<Pair<Float,Float>> list_data;

    public PolyList(int id, String status, List<Pair<Float, Float>> string_to_poly) {
        this.setId(id);
        this.setStatus(status);
        this.setList_data(string_to_poly);
    }

    public PolyList() {

    }
}
