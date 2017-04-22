package hbi.core.bom.service.impl;

import hbi.core.bom.dto.Bom;
import hbi.core.bom.dto.BomSync;
import hbi.core.bom.mapper_hdispatch.OBomMapper;
import hbi.core.bom.service.IOBomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional("hdispatchTM")
public class OBomServiceImpl implements IOBomService {
@Autowired
private OBomMapper mapper;
    @Override
    public List<Bom> getBoms(String id) {
        return mapper.getBoms(id);
    }

    @Override
    public List<Bom> getBomsT(String id) {
        return mapper.getBomsT(id);
    }

    @Override
    public List<Bom> getBomsMysql(String id) {
        return mapper.getBomsMysql(id);
    }

    @Override
    public List<BomSync> getBomsSync(String id) {
        return mapper.getBomsSync(id);
    }

    @Override
    public List<BomSync> getBomsTSync(String id) {
        return mapper.getBomsTSync(id);
    }
}