package hbi.core.bom.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.ReplacePart;
import hbi.core.bom.service.IReplacePartService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReplacePartServiceImpl extends BaseServiceImpl<ReplacePart> implements IReplacePartService{

}