package hbi.core.bom.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.bom.dto.WncUser;

import java.util.List;

public interface WncUserMapper extends Mapper<WncUser>{
    List<WncUser> queryBasic(WncUser wncUser);
}