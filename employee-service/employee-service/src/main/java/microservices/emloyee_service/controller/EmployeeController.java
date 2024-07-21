package microservices.emloyee_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservices.emloyee_service.dto.APIResponseDTO;
import microservices.emloyee_service.dto.EmployeeDTO;
import microservices.emloyee_service.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Operation(summary = "Post Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Adding the employee",
                    content = { @Content(mediaType = "application/json") })
     })
    //build REST API for adding employee
    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody  @Valid EmployeeDTO employeeDto){
    EmployeeDTO addedEmployee= employeeService.addEmployee(employeeDto);
    return new ResponseEntity<>(addedEmployee, HttpStatus.CREATED);
    }

    @Operation(summary = "Get Employee by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Getting the employee",
                    content = { @Content(mediaType = "application/json") })
    })
    //build REST API for getting employee by id
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<APIResponseDTO> getEmployee(@PathVariable Long id){
        APIResponseDTO apiResponseDTO= employeeService.getEmployee(id);
        return new ResponseEntity<>(apiResponseDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get Employee List")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Getting the employee List",
                    content = { @Content(mediaType = "application/json") })
    })
    //build REST API for getting all employees
    @GetMapping("/")
    public ResponseEntity<List<EmployeeDTO>> getEmployee(){
        return new ResponseEntity<>( employeeService.getAllEmployees(), HttpStatus.OK);
    }
}
