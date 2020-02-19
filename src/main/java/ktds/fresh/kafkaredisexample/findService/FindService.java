package ktds.fresh.kafkaredisexample.findService;

import com.fasterxml.jackson.core.JsonProcessingException;
import ktds.fresh.kafkaredisexample.kafka.Sender.Sender;
import ktds.fresh.kafkaredisexample.mapper.FindServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FindService {
    private static final Logger logger = LoggerFactory.getLogger(FindService.class);

    @Autowired
    Sender sender;

    @Autowired
    FindServiceMapper findServiceMapper;

    public void insertFindItem(Map<String, Object> param){
        findServiceMapper.insertFindItem(param);

        // 습득물을 카프카로 전송
        String category = param.get("category").toString();
        String topic = "msa_test_20200219";

        Map<String, Object> payload = new HashMap<>();
        payload.put("service", "FindService");
        payload.put("key", "find");
        payload.put("category", category);

        try{
            sender.send(topic, payload);
        } catch(JsonProcessingException e){
            logger.error(e.toString());
        }
    }

    public FindItemVo selectFindItem(int seq){
        return findServiceMapper.selectFindItem(seq);
    }

    public List<FindItemVo> selectFindItemList(Map<String, Object> param){
        return findServiceMapper.selectFindItemList(param);
    }
}
