package nl.han.aim.oosevt.lamport.controllers.employees;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.CreateEmployeeRequestRequestDTO;
import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.employees.dto.UpdateEmployeeRequestRequestDTO;
import nl.han.aim.oosevt.lamport.exceptions.InvalidDtoException;
import nl.han.aim.oosevt.lamport.services.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployees() {
        return new ResponseEntity<>(
                employeeService.getEmployees(),
                HttpStatus.OK
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(@PathVariable("id") int id) {
        return new ResponseEntity<>(
                employeeService.getEmployee(id),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") int id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping()
    public void updateEmployee(@RequestBody UpdateEmployeeRequestRequestDTO employeeDTO) {
        employeeService.updateEmployee(employeeDTO);
    }

    @PostMapping()
    public void createEmployee(@RequestBody CreateEmployeeRequestRequestDTO employeeDTO) {
        employeeService.createEmployee(employeeDTO);
    }

    @ExceptionHandler(InvalidDtoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HashMap<String, List<String>>> handle(InvalidDtoException exception) {
        return new ResponseEntity<>(
                exception.getErrors(),
                HttpStatus.BAD_REQUEST);
    }
}
