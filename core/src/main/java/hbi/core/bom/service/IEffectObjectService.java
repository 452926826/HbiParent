package hbi.core.bom.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.bom.dto.EffectObject;

import java.util.ArrayList;
import java.util.List;

public interface IEffectObjectService extends IBaseService<EffectObject>, ProxySelf<IEffectObjectService>{
    List<EffectObject> queryBasic(IRequest request, EffectObject effectObject, int page, int pageSize);


    void insterExect(IRequest request,List<List<String>>excelList,Long chginfoId);
}