package hbi.core.bom.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.ReviseFailure;
import hbi.core.bom.service.IReviseFailureService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReviseFailureServiceImpl extends BaseServiceImpl<ReviseFailure> implements IReviseFailureService{

}