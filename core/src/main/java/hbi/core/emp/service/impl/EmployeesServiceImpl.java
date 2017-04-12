package hbi.core.emp.service.impl;

import com.hand.hap.system.service.impl.BaseServiceImpl;
import hbi.core.emp.dto.Employees;
import hbi.core.emp.service.IEmployeesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmployeesServiceImpl extends BaseServiceImpl<Employees> implements IEmployeesService {

}