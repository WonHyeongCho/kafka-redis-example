package ktds.fresh.kafkaredisexample.alarmService;

import com.fasterxml.jackson.core.JsonProcessingException;
import ktds.fresh.kafkaredisexample.kafka.KafkaController;
import ktds.fresh.kafkaredisexample.kafka.Sender.Sender;
import ktds.fresh.kafkaredisexample.lostService.LostItemVo;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

@Service
public class AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    Sender sender;

    // LostService 에 Rest API 조회를 통해 해당 카테고리의 분실물자 정보 조회
    public void getLostItemInfo(String category){
        // Rest Template 생성
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnPerRoute(5)
                .setMaxConnTotal(100)
                .build();
        factory.setHttpClient(httpClient);

        // URL
        String lostServiceUrl = "http://35.194.113.156:8080/lost/item/list";
        URI uri = URI.create(lostServiceUrl);

        RestTemplate restTemplate = new RestTemplate(factory);

        Map<String, Object> param = new HashMap<>();
        param.put("category", category);

        // REST POST
        LostItemVo[] lostItemVoArray = restTemplate.postForObject(uri, param, LostItemVo[].class);

        // List 로 변환
        List<LostItemVo> lostItemVoList = new LinkedList<>();
        if(lostItemVoArray != null && lostItemVoArray.length > 0){
            lostItemVoList = Arrays.asList(lostItemVoArray);
        }

        // 카프카 파라미터
        String topic = "msa_test_20200219";
        Map<String, Object> payload = new HashMap<>();
        payload.put("service", "AlarmService");
        payload.put("key", "alarm");

        if(lostItemVoList.size() > 0){
            StringBuilder lost_user_id = new StringBuilder();
            for(LostItemVo lostItemVo : lostItemVoList) {
                logger.info("물건을 잃어 버린 사람의 ID: " + lostItemVo.getLost_user_id());
                lost_user_id.append(lostItemVo.getLost_user_id()).append(",");
            }
            payload.put("lost_user_id", lost_user_id);
        }
        else{
            logger.info("물건이 잃어 버린 사람이 없습니다.");
        }

        // 카프카로 전송
        try{
            sender.send(topic, payload);
        }
        catch (JsonProcessingException e){
            logger.error(e.toString());
        }
    }
}
