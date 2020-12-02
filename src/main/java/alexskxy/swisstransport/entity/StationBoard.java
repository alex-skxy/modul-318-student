package alexskxy.swisstransport.entity;

import lombok.Data;

@Data
public class StationBoard {
    public final String name;
    public final String category;
    public final String number;
    public final String to;
    public final String operator;
    public final Stop stop;
}
