package ktds.fresh.kafkaredisexample.lostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lost")
public class LostController {

    @Autowired
    LostService lostService;

    /**
     * 분실물 등록 API
     * @param param {
     *  name,
     *  quantity,
     *  lost_user_id,
     *  lost_location,
     *  lost_time,
     *  category,
     *  description,
     *  reg_id
     * }
     */
    @RequestMapping(method = RequestMethod.POST, value = "/item/insert")
    public void insertLostItem(@RequestBody Map<String, Object> param){
        lostService.insertLostItem(param);
    }

    /**
     * 분실물 조회 API
     * @param param{
     *  seq 분신물 조회 번호
     * }
     * @return 분실물
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/item/get")
    public @ResponseBody LostItemVo selectLostItem(@RequestBody Map<String, Object> param){
        int seq = (int)param.get("seq");
        return lostService.selectLostItem(seq);
    }

    /**
     * 분실물 리스트 조회 API
     * @return 분실물 리스트
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/item/list")
    public @ResponseBody List<LostItemVo> selectLostItemList(@RequestBody Map<String, Object> param){
        return lostService.selectLostItemList(param);
    }
}
