package hbi.core.bom.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.bom.mapper.ReviseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.Revise;
import hbi.core.bom.service.IReviseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReviseServiceImpl extends BaseServiceImpl<Revise> implements IReviseService{

    @Autowired
    private ReviseMapper reviseMapper;

    @Override
    public List<Revise> queryBasic(IRequest request, Revise revise, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return reviseMapper.queryBasic(revise);
    }
}