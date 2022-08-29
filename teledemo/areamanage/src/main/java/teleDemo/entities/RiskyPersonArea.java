package teleDemo.entities;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Date;

@Data
@Accessors(chain = true)
public class RiskyPersonArea implements Serializable {
    private int id;
    private String status;
    private double lat;
    private double lon;
    private int infectedCount;
    private int closedCount;
    private int type;
}
