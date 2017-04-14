package hbi.core.bom.mapper;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.bom.dto.Revise;

import java.util.List;

public interface ReviseMapper extends Mapper<Revise>{
    List<Revise> queryBasic(Revise revise);
}