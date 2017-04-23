package hbi.core.bom.mapper_hdispatch;

import com.hand.hap.mybatis.common.Mapper;
import hbi.core.bom.dto.Bom;
import hbi.core.bom.dto.BomSync;

import java.util.List;

/**
 * Created by public1976 on 2017/4/18.
 */
public interface OBomMapper{
    List<Bom> getBoms(String id);
    List<Bom> getBomsT(String id);
    List<Bom> getBomsMysql(String id);
    List<BomSync> getBomsMysqlSync(String id);
    List<BomSync> getBomsSync(String id);
    List<BomSync> getBomsTSync(String id);
}
