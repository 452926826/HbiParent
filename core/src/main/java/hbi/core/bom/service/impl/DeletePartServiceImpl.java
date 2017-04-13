package hbi.core.bom.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import hbi.core.bom.dto.DeletePart;
import hbi.core.bom.service.IDeletePartService;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeletePartServiceImpl extends BaseServiceImpl<DeletePart> implements IDeletePartService{

}