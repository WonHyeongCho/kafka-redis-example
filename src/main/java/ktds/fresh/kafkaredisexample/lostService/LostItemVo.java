package ktds.fresh.kafkaredisexample.lostService;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class LostItemVo {
    private int seq;
    private String name;
    private int quantity;
    private String lost_user_id;
    private String lost_location;
    private Date lost_time;
    private String category;
    private String description;
    private String reg_id;
    private Date reg_date;
    private String upd_id;
    private Date upd_date;
}