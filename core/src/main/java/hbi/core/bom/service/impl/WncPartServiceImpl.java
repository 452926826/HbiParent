package hbi.core.bom.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.bom.mapper.WncPartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.WncPart;
import hbi.core.bom.service.IWncPartService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WncPartServiceImpl extends BaseServiceImpl<WncPart> implements IWncPartService{

    @Autowired
    private WncPartMapper wncPartMapper;

    @Override
    public List<WncPart> queryBasic(IRequest request, WncPart wncPart, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return wncPartMapper.queryBasic(wncPart);
    }
}