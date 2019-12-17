package ktds.fresh.kafkaredisexample.redis;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping("/save/point")
    public void savePoint(@RequestBody Map<String, Object> param){
        String id = param.get("id").toString();
        Long amount = (Long)param.get("amount");
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
