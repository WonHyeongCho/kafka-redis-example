package ktds.fresh.kafkaredisexample.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Autowired
    PointRedisRepository pointRedisRepository;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/save/string")
    public void saveString(@RequestBody Map<String, Object> param){
        String key = param.get("key").toString();
        String value = param.get("value").toString();

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    @RequestMapping("get/string")
    public String getString(@RequestBody Map<String, Object> param){
        String key = param.get("key").toString();

        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }


    @RequestMapping("/save/point")
    public void savePoint(@RequestBody Map<String, Object> param){
        String id = param.get("id").toString();
        int amount = Integer.parseInt(param.get("amount").toString());
        LocalDateTime localDateTime = LocalDateTime.now();

        Point point = Point.builder()
                .id(id)
                .amount(amount)
                .refreshTime(localDateTime)
                .build();

        pointRedisRepository.save(point);
    }

    @RequestMapping("/get/point")
    public String getPoint(@RequestBody Map<String, Object> param){
        String id = param.get("id").toString();
        Point point = null;

        Optional<Point> optionalPoint = pointRedisRepository.findById(id);

        if(optionalPoint.isPresent()){
            point = optionalPoint.get();
            return point.toString();
        }
        else{
            return optionalPoint.toString();
        }
    }
}
