package alexskxy.swisstransport.entity;

import com.google.api.client.util.DateTime;
import lombok.Data;

@Data
public class Stop {
    public Station station;
    public String departure;
}
