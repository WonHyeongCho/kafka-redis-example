package ktds.fresh.kafkaredisexample.mapper;

import ktds.fresh.kafkaredisexample.lostService.LostItemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface LostServiceMapper {
    void insertLostItem(Map param);
    LostItemVo selectLostItem(int seq);
    List<LostItemVo> selectLostItemList();
}
