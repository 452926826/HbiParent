package hbi.core.bom.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.bom.mapper.WncUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.WncUser;
import hbi.core.bom.service.IWncUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WncUserServiceImpl extends BaseServiceImpl<WncUser> implements IWncUserService{

    @Autowired
    private WncUserMapper wncUserMapper;

    @Override
    public List<WncUser> queryBasic(IRequest request, WncUser wncUser, int page, int pageSize){
        PageHelper.startPage(page, pageSize);
        return wncUserMapper.queryBasic(wncUser);
    }
}