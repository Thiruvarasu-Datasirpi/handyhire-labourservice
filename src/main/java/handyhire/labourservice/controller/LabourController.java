package handyhire.labourservice.controller;


import handyhire.labourservice.response.Response;
import handyhire.labourservice.dto.LabourDTO;
import handyhire.labourservice.enitiy.Labour;
import handyhire.labourservice.service.LabourService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/labour")
public class LabourController {

    @Autowired
    private LabourService labourService;


    @PostMapping("/create-labour")
    public ResponseEntity<Object>createlabour(  @RequestBody @Valid LabourDTO labourDTO)throws Exception {
       Labour labour = labourService.createLabour(labourDTO);
        return  ResponseEntity.ok(new Response(HttpStatus.OK.value(),
                "success",labour));
    }

    @GetMapping("/get-labour")
    public ResponseEntity<Object>getlabour(@RequestParam int pageNo, int pageSize )throws Exception {
        Map<String, Object>labour = labourService.getLabour(pageNo,pageSize);
        return  ResponseEntity.ok(new Response(HttpStatus.OK.value(),
                "success",labour));
    }

    @GetMapping("/get-labourby-id/{id}")
    public ResponseEntity<Object> getLabourById(@PathVariable int id)throws Exception {
        Labour labour = labourService.getLabourById(id);
        return  ResponseEntity.ok(new Response(HttpStatus.OK.value(),
                "success",labour));
    }

    @PutMapping("/update-labour")
    public ResponseEntity<Object> updateLabour(@RequestBody @Valid LabourDTO labourDTO)throws Exception{
        Labour labour = labourService.updateLabour(labourDTO);
        return  ResponseEntity.ok(new Response(HttpStatus.OK.value(),
                "User Updated Successfully",labour));
    }

    @DeleteMapping("/delete-labourby-id/{id}")
    public ResponseEntity<Response> deleteLabour(@PathVariable int id) throws Exception {
            labourService.deleteLabourById(id);
            return ResponseEntity.ok(new Response(HttpStatus.OK.value(), "User Deleted Successfully"));
    }

}

