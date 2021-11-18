package nl.han.aim.oosevt.lamport.controllers.function;

import nl.han.aim.oosevt.lamport.controllers.employees.dto.EmployeeResponseDTO;
import nl.han.aim.oosevt.lamport.controllers.function.dto.FunctionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/functions")
@CrossOrigin
public class FunctionController {

    @GetMapping("")
    public ResponseEntity<List<FunctionResponseDTO>> getFunctions() {
        final ArrayList<FunctionResponseDTO> functions = new ArrayList<>();
        functions.add(new FunctionResponseDTO(1, "Reporting"));
        functions.add(new FunctionResponseDTO(2, "Testing"));
        functions.add(new FunctionResponseDTO(3, "Writing Code"));
        functions.add(new FunctionResponseDTO(4, "Financial"));
        functions.add(new FunctionResponseDTO(5, "Hospitality"));

        return new ResponseEntity<>(
                functions,
                HttpStatus.OK
        );
    }
}
