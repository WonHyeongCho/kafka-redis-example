package ktds.fresh.kafkaredisexample.findService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/find")
public class FindController {

    @Autowired
    FindService findService;

    /**
     * 습득물 등록 API
     * @param param {
     *  name,
     *  quantity,
     *  find_user_id,
     *  find_location,
     *  find_time,
     *  category,
     *  description
     *  reg_id
     * }
     */
    @RequestMapping(method = RequestMethod.POST, value = "/item/insert")
    public void insertFindItem(@RequestBody Map<String, Object> param){
        findService.insertFindItem(param);
    }

    /**
     * 습득물 조회 API
     * @param param{
     *  seq 습득물 조회 번호
     * }
     * @return 습득물
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/item/get")
    public @ResponseBody FindItemVo selectLostItem(@RequestBody Map<String, Object> param){
        int seq = (int)param.get("seq");
        return findService.selectFindItem(seq);
    }

    /**
     * 습듣물 리스트 조회 API
     * @return 습득물 리스트
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, value = "/item/list")
    public @ResponseBody List<FindItemVo> selectLostItemList(@RequestBody Map<String, Object> param){
        return findService.selectFindItemList(param);
    }
}
