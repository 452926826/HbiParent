package hbi.core.bom.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.bom.dto.EffectObject;

import java.util.List;

public interface EffectObjectMapper extends Mapper<EffectObject>{
    List<EffectObject> queryBasic(EffectObject effectObject);
}