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

import java.util.List;

@RestController
@RequestMapping("/labour")
public class LabourController {

    @Autowired
    private LabourService labourService;


    @PostMapping("/create-labour")
    public ResponseEntity<Object>createlabour(  @RequestBody @Valid LabourDTO labourDTO)throws Exception {
       Labour labour = labourService.createLabour(labourDTO).getBody();
        return new ResponseEntity<Object>(new Response(HttpStatus.OK.value(),
                "success",labour), HttpStatus.OK);
    }

    @GetMapping("/get-labour")
    public ResponseEntity<Object>getlabour(@RequestParam int pageNo, int pageSize )throws Exception {
        List<Labour> labour = labourService.getLabour(pageNo,pageSize);
        return new ResponseEntity<Object>(new Response(HttpStatus.OK.value(),
                "success",labour), HttpStatus.OK);
    }

    @GetMapping("/get-labourby-id/{id}")
    public ResponseEntity<Object> getLabourById(@PathVariable int id)throws Exception {
        Labour labour = labourService.getLabourById(id).getBody();
        return new ResponseEntity<Object>(new Response(HttpStatus.OK.value(),
                "success",labour), HttpStatus.OK);
    }

    @PutMapping("/update-labour")
    public ResponseEntity<Object> updateLabour(@RequestBody @Valid LabourDTO labourDTO)throws Exception{
        Labour labour = labourService.updateLabour(labourDTO).getBody();
        return new ResponseEntity<Object>(new Response(HttpStatus.OK.value(),
                "User Updated Successfully",labour), HttpStatus.OK);
    }

    @DeleteMapping("/delete-labourby-id/{id}")
    public ResponseEntity<Object> deleteLabour(@PathVariable int id)throws Exception{
        Labour labour = labourService.deleteLabourById(id).getBody();
        return new ResponseEntity<Object>(new Response(HttpStatus.OK.value(),
                "User Deleted Successfully"), HttpStatus.OK);
    }

}

