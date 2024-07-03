package handyhire.labourservice.service;


import handyhire.labourservice.dto.LabourDTO;
import handyhire.labourservice.enitiy.Labour;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface LabourService {

    public Labour createLabour(LabourDTO labourDTO)throws Exception ;

    public Map<String,Object> getLabour(int pageNo, int pageSize) throws Exception;

    public Labour getLabourById(int id) throws Exception;

    public void deleteLabourById(int id) throws Exception;

    public Labour updateLabour(LabourDTO labourDTO) throws Exception;
}
