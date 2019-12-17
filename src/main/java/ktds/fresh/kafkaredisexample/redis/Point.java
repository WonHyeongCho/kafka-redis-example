package ktds.fresh.kafkaredisexample.redis;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@RedisHash("point")
public class Point implements Serializable {

    @Id
    private String id;
    private int amount;
    private LocalDateTime refreshTime;

    @Builder
    public Point(String id, int amount, LocalDateTime refreshTime) {
        this.id = id;
        this.amount = amount;
        this.refreshTime = refreshTime;
    }

    public void refresh(int amount, LocalDateTime refreshTime){
        if(refreshTime.isAfter(this.refreshTime)){ // 저장된 데이터보다 최신 데이터일 경우
            this.amount = amount;
            this.refreshTime = refreshTime;
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                ", refreshTime=" + refreshTime +
                '}';
    }
}
