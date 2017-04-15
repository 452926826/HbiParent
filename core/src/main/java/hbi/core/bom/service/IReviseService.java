package hbi.core.bom.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.bom.dto.Revise;

import java.util.ArrayList;
import java.util.List;

public interface IReviseService extends IBaseService<Revise>, ProxySelf<IReviseService>{
    List<Revise> queryBasic(IRequest request, Revise revise, int page, int pageSize);

    void insterExect(IRequest requestContext, List<List<String>> excelList, Long chginfoId);
}