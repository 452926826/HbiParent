package hbi.core.bom.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.Chginfo;
import hbi.core.bom.service.IChginfoService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChginfoServiceImpl extends BaseServiceImpl<Chginfo> implements IChginfoService{

}