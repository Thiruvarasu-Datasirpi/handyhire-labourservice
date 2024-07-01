package handyhire.labourservice.service;


import handyhire.labourservice.dto.LabourDTO;
import handyhire.labourservice.enitiy.Labour;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.CacheRequest;
import java.util.List;

public interface LabourService {

    public ResponseEntity<Labour> createLabour( LabourDTO labourDTO)throws Exception ;

    public List<Labour> getLabour(int pageNo, int pageSize) throws Exception;

    public ResponseEntity<Labour> getLabourById(int id) throws Exception;

    public ResponseEntity<Labour> deleteLabourById(int id) throws Exception;

    public ResponseEntity<Labour> updateLabour(LabourDTO labourDTO) throws Exception;
}
