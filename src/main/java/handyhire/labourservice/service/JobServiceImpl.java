package handyhire.labourservice.service;

import handyhire.labourservice.dto.JobDTO;
import handyhire.labourservice.enitiy.Jobs;
import handyhire.labourservice.repository.JobRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepository;
    @Override
    public ResponseEntity<Jobs> createJobs(JobDTO jobDTO) throws Exception {
        ModelMapper map = new ModelMapper();
        Jobs jobs = map.map(jobDTO, Jobs.class);
        jobRepository.save(jobs);
        return ResponseEntity.ok(jobs);
    }
}
