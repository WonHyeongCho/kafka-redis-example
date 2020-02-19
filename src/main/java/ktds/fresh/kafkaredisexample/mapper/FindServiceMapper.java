package ktds.fresh.kafkaredisexample.mapper;

import ktds.fresh.kafkaredisexample.findService.FindItemVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface FindServiceMapper {
    void insertFindItem(Map param);
    FindItemVo selectFindItem(int seq);
    List<FindItemVo> selectFindItemList();
}
