package hbi.core.bom.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.bom.dto.WncPart;
import hbi.core.bom.mapper.ReviseMapper;
import hbi.core.bom.mapper.WncPartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.Revise;
import hbi.core.bom.service.IReviseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ReviseServiceImpl extends BaseServiceImpl<Revise> implements IReviseService{

    @Autowired
    private ReviseMapper reviseMapper;

    @Autowired
    private WncPartMapper wncPartMapper;

    @Override
    public List<Revise> queryBasic(IRequest request, Revise revise, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return reviseMapper.queryBasic(revise);
    }

    StringBuffer errMess;
    @Override
    @Transactional(noRollbackFor = Exception.class)
    public void insterExect(IRequest request,List<List<String>>excelList,Long chginfoId) {
        errMess=new StringBuffer();
        List <Revise> revises=new ArrayList<Revise>();
        List<WncPart> wncParts = new ArrayList<WncPart>();

        for (int i=0;i<excelList.size();i++) {
            List <String>list=excelList.get(i);
            Revise revise=new Revise();
            WncPart wncPart = new WncPart();
            //WNC系统对象
            wncPart.setOid(list.get(0).trim()); //oid
            wncPart.setNumber(list.get(1).trim());//编号
            wncPart.setName(list.get(2).trim());//名称
            wncPart.setType(list.get(3).trim());//类型
            wncPart.setVersion((list.get(4)).trim());//版本
            wncPart.setState((list.get(5)).trim());//状态
            wncPart.setModifyer((list.get(6)).trim());//修改者
            wncPart.setObjectVersionNumber(1L);
            wncPart.setCreatedBy(-1L);
            wncPart.setCreationDate(new Date());
            wncPart.setLastUpdatedBy(-1L);
            wncPart.setLastUpdateDate(new Date());
            wncPart.setLastUpdateLogin(-1L);
            wncParts.add(wncPart);
            wncPartMapper.insert(wncPart);

            //产生对象
            revise.setChginfoId(chginfoId);
            revise.setOid(list.get(0).trim());//oid
            revise.setSoid(list.get(0).trim());//oid
            revise.setDescription(list.get(7).trim());//备注
            revise.setObjectVersionNumber(1L);
            revise.setCreatedBy(-1L);
            revise.setCreationDate(new Date());
            revise.setLastUpdatedBy(-1L);
            revise.setLastUpdateDate(new Date());
            revise.setLastUpdateLogin(-1L);
            revises.add(revise);
            reviseMapper.insert(revise);
        }
    }
}