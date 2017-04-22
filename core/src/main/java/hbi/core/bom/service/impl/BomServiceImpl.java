package hbi.core.bom.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.Bom;
import hbi.core.bom.service.IBomService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BomServiceImpl extends BaseServiceImpl<Bom> implements IBomService{
}