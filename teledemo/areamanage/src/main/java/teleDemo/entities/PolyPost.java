package teleDemo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Projectname: 项目前后端架构(1)
 * @Filename: poly_post
 * @Author: Jia Yiming
 * @Data:2022/8/25 09:21
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class PolyPost<T> extends Poly {
    List<T> list_data;
    public PolyPost(int id, String status, List<T> string_to_poly) {
        this.setId(id);
        this.setStatus(status);
        this.setList_data(string_to_poly);
    }

    public PolyPost() {

    }
}
