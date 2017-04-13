package hbi.core.bom.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.PartBasicAtt;
import hbi.core.bom.service.IPartBasicAttService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PartBasicAttServiceImpl extends BaseServiceImpl<PartBasicAtt> implements IPartBasicAttService{

}