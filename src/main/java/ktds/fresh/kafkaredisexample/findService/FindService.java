package ktds.fresh.kafkaredisexample.findService;

import ktds.fresh.kafkaredisexample.mapper.FindServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FindService {

    @Autowired
    FindServiceMapper findServiceMapper;

    public void insertFindItem(Map<String, Object> param){
        findServiceMapper.insertFindItem(param);
    }

    public FindItemVo selectFindItem(int seq){
        return findServiceMapper.selectFindItem(seq);
    }

    public List<FindItemVo> selectFindItemList(){
        return findServiceMapper.selectFindItemList();
    }
}
