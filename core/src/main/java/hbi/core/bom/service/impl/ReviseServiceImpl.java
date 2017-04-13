package hbi.core.bom.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.Revise;
import hbi.core.bom.service.IReviseService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviseServiceImpl extends BaseServiceImpl<Revise> implements IReviseService{

}