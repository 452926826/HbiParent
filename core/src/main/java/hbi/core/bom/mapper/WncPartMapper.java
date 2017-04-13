package hbi.core.bom.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.bom.dto.WncPart;

import java.util.List;

public interface WncPartMapper extends Mapper<WncPart>{
    List<WncPart> queryBasic(WncPart wncPart);
}