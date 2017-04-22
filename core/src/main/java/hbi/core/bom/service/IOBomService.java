package hbi.core.bom.service;

import hbi.core.bom.dto.Bom;
import hbi.core.bom.dto.BomSync;

import java.util.List;

/**
 * Created by public1976 on 2017/4/18.
 */
public interface IOBomService {
    List<Bom> getBoms(String id);
    List<Bom> getBomsT(String id);
    List<Bom> getBomsMysql(String id);
    List<BomSync> getBomsSync(String id);
    List<BomSync> getBomsTSync(String id);
}
