package hbi.core.bom.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hap.core.IRequest;
import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.bom.dto.WncPart;
import hbi.core.bom.mapper.EffectObjectMapper;
import hbi.core.bom.mapper.WncPartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.EffectObject;
import hbi.core.bom.service.IEffectObjectService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class EffectObjectServiceImpl extends BaseServiceImpl<EffectObject> implements IEffectObjectService{

    @Autowired
    private EffectObjectMapper effectObjectMapper;

    @Autowired
    private WncPartMapper wncPartMapper;

    @Override
    public List<EffectObject> queryBasic(IRequest request, EffectObject effectObject, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return effectObjectMapper.queryBasic(effectObject);
    }


    StringBuffer errMess;
    @Override
    @Transactional(noRollbackFor = Exception.class)
    public void insterExect(IRequest request,List<List<String>>excelList,Long chginfoId) {
            errMess=new StringBuffer();
            List <EffectObject> effectObjects=new ArrayList<EffectObject>();
            List<WncPart> wncParts = new ArrayList<WncPart>();

            for (int i=0;i<excelList.size();i++) {
                List <String>list=excelList.get(i);
                EffectObject effectObject=new EffectObject();
                WncPart wncPart = new WncPart();
                //WNC系统对象
                wncPart.setOid(list.get(0).trim()); //oid
                wncPart.setNumber(list.get(1).trim());//编号
                wncPart.setName(list.get(2).trim());//名称
                wncPart.setType(list.get(3).trim());//类型
                wncPart.setVersion((list.get(4)).trim());//版本
                wncPart.setState((list.get(5)).trim());//状态
                wncPart.setModifyer(null);
                wncPart.setIsauthen((list.get(12)).trim());//是否认证产品
                wncPart.setAuthenpart((list.get(13)).trim());//是否认证产品
                wncPart.setObjectVersionNumber(1L);
                wncPart.setCreatedBy(-1L);
                wncPart.setCreationDate(new Date());
                wncPart.setLastUpdatedBy(-1L);
                wncPart.setLastUpdateDate(new Date());
                wncPart.setLastUpdateLogin(-1L);
                wncParts.add(wncPart);
                wncPartMapper.insert(wncPart);

                //受影响对象
                effectObject.setChginfoId(chginfoId);
                effectObject.setOid(list.get(0).trim());//oid
                effectObject.setChgdescription(list.get(6).trim());//变更描述
                effectObject.setInworksol(list.get(7).trim());//在制品处理意见
                effectObject.setStocksol(list.get(8).trim());//库存处理意见
                effectObject.setTestsol(list.get(9).trim());//试验大纲处理意见
                effectObject.setSoftwaresol(list.get(10).trim());//软件处理意见
                effectObject.setIsdeletema(list.get(11).trim());//是否删除物料，“是”、“否”
                effectObject.setDescription(list.get(14).trim());//备注
                effectObject.setObjectVersionNumber(1L);
                effectObject.setCreatedBy(-1L);
                effectObject.setCreationDate(new Date());
                effectObject.setLastUpdatedBy(-1L);
                effectObject.setLastUpdateDate(new Date());
                effectObject.setLastUpdateLogin(-1L);

                effectObjects.add(effectObject);
                effectObjectMapper.insert(effectObject);
            }
    }
}