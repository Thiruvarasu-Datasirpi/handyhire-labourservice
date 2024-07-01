package handyhire.labourservice.controller;


import handyhire.labourservice.dto.JobDTO;
import handyhire.labourservice.enitiy.Jobs;
import handyhire.labourservice.response.Response;
import handyhire.labourservice.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
@CrossOrigin("*")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/create-job")
//    @ApiOperation(value = "CREATE API", notes = "creates a job")
    public ResponseEntity<Object>createJobs(@RequestBody @Valid JobDTO jobDTO) throws Exception {
        Jobs jobs = jobService.createJobs(jobDTO).getBody();
        return new ResponseEntity<Object>(new Response(HttpStatus.OK.value(),
                "Success",jobs),HttpStatus.OK);
    }
}
