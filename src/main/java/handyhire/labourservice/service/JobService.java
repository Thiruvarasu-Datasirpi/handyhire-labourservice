package handyhire.labourservice.service;

import handyhire.labourservice.dto.JobDTO;
import handyhire.labourservice.enitiy.Jobs;
import org.springframework.http.ResponseEntity;

public interface JobService  {
    public ResponseEntity<Jobs>createJobs(JobDTO jobDTO) throws Exception;
}
