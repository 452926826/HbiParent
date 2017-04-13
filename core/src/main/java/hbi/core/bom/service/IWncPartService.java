package hbi.core.bom.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.bom.dto.WncPart;

import java.util.List;

public interface IWncPartService extends IBaseService<WncPart>, ProxySelf<IWncPartService>{
    List<WncPart> queryBasic(IRequest request, WncPart wncPart, int page, int pageSize);
}