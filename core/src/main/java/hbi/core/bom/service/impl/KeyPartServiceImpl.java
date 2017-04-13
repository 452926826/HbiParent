package hbi.core.bom.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.KeyPart;
import hbi.core.bom.service.IKeyPartService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class KeyPartServiceImpl extends BaseServiceImpl<KeyPart> implements IKeyPartService{

}