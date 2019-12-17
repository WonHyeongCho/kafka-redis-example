package ktds.fresh.kafkaredisexample.redis;

import org.springframework.data.repository.CrudRepository;

public interface PointRedisRepository extends CrudRepository<Point, String> {
}
