package ktds.fresh.kafkaredisexample.lostService;

import ktds.fresh.kafkaredisexample.mapper.LostServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LostService {

    @Autowired
    LostServiceMapper lostServiceMapper;

    public void insertLostItem(Map<String, Object> param){
        lostServiceMapper.insertLostItem(param);
    }

    public LostItemVo selectLostItem(int seq){
        return lostServiceMapper.selectLostItem(seq);
    }

    public List<LostItemVo> selectLostItemList(Map<String, Object> param){
        return lostServiceMapper.selectLostItemList(param);
    }
}
