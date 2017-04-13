package hbi.core.bom.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.bom.mapper.EffectObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.EffectObject;
import hbi.core.bom.service.IEffectObjectService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EffectObjectServiceImpl extends BaseServiceImpl<EffectObject> implements IEffectObjectService{

    @Autowired
    private EffectObjectMapper effectObjectMapper;

    @Override
    public List<EffectObject> queryBasic(IRequest request, EffectObject effectObject, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return effectObjectMapper.queryBasic(effectObject);
    }
}