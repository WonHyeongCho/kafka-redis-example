package ktds.fresh.kafkaredisexample.alarmService;

import ktds.fresh.kafkaredisexample.kafka.KafkaController;
import ktds.fresh.kafkaredisexample.kafka.MyProducer;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AlarmService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);

    @Autowired
    Sender sender;

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

        String lostServiceUrl = "35.194.113.156:8080/lost/item/list";

        RestTemplate restTemplate = new RestTemplate(factory);

        List<LostItemVo> lostItemVoList = restTemplate.getForObject(lostServiceUrl, List.class);

        Map<String, Object> payload = new HashMap<>();
        payload.put("service", "AlarmService");
        payload.put("key", "alarm");

        if(lostItemVoList != null && lostItemVoList.size() > 0){
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
    }
}
