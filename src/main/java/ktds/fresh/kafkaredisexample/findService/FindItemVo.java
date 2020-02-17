package ktds.fresh.kafkaredisexample.findService;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class FindItemVo {
    private int seq;
    private String name;
    private int quantity;
    private String find_user_id;
    private String find_location;
    private Date find_time;
    private String category;
    private String description;
    private String reg_id;
    private Date reg_date;
    private String upd_id;
    private Date upd_date;
}
