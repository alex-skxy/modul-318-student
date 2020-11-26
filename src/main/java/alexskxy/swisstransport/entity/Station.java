package alexskxy.swisstransport.entity;

import lombok.Data;

@Data
public class Station {
    public String id;
    public String name;
    public Integer score;
    public Coordinate coordinate;
    public Double distance;
}
