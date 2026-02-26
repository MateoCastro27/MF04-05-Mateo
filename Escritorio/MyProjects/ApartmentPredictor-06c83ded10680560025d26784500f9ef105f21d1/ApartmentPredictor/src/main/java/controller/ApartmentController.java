package controller;
import com.example.apartment_predictor.service.PopulateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApartmentController {

    @Autowired
    private PopulateService populateService;

    @PostMapping("/populate")
    public ResponseEntity<String> populateDatabase() {
        populateService.populateAll();
        return ResponseEntity.ok("Base de datos poblada correctamente con datos de prueba para la práctica.");
    }
}