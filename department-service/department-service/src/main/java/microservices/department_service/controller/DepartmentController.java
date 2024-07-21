package microservices.department_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservices.department_service.dto.DepartmentDTO;
import microservices.department_service.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private DepartmentService departmentService;


    @Operation(summary = "Posting Department")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Department Created",
                    content = { @Content(mediaType = "application/json") })
   })
    //building createDepartment REST API
    @PostMapping("/createDepartment")
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody @Valid DepartmentDTO departmentDTO){
        DepartmentDTO departmentDto=departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity<>(departmentDto,HttpStatus.CREATED);
    }

    @Operation(summary = "Get Department by Code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department from DB",
                    content = { @Content(mediaType = "application/json") })
    })
    //building get department by code REST API
    @GetMapping("/getDepartmentByCode/{code}")
    public ResponseEntity<DepartmentDTO> getDepartmentByCode(@PathVariable String code){
        DepartmentDTO savedDepartmentDto= departmentService.getDepartmentByCode(code);
        return new ResponseEntity<>(savedDepartmentDto,HttpStatus.OK);
    }

    @Operation(summary = "Get Departments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Department List from DB",
                    content = { @Content(mediaType = "application/json") })
    })
    //building get all departments REST API
    @GetMapping("/")
    public ResponseEntity<List<DepartmentDTO>> getDepartments(){
        List<DepartmentDTO> allDepartments= departmentService.getAllDepartments();

        return new ResponseEntity<>(allDepartments,HttpStatus.OK);
    }

}
