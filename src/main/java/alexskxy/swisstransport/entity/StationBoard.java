package alexskxy.swisstransport.entity;

import lombok.Data;

@Data
public class StationBoard {
    public String name;
    public String category;
    public String number;
    public String to;
    public String operator;
    public Stop stop;
}
