package hbi.core.bom.service;

import com.hand.hap.core.IRequest;
import com.hand.hap.core.ProxySelf;
import com.hand.hap.system.service.IBaseService;
import hbi.core.bom.dto.WncUser;

import java.util.List;

public interface IWncUserService extends IBaseService<WncUser>, ProxySelf<IWncUserService>{
    List<WncUser> queryBasic(IRequest request, WncUser wncUser, int page, int pageSize);
}