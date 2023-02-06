package za.co.drinkup.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.drinkup.api.dto.VenueDto;
import za.co.drinkup.api.entity.HappyHourDetails;
import za.co.drinkup.api.response.ResponseHandler;
import za.co.drinkup.api.service.HappyHourDetailsService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/happy-hours")
public class HappyHourDetailsController {

    @Autowired
    private final HappyHourDetailsService happyHourDetailsService;

    public HappyHourDetailsController(HappyHourDetailsService happyHourDetailsService) {
        this.happyHourDetailsService = happyHourDetailsService;
    }

    @Operation(summary = "Get list of happy hours", description = "", tags = {"profile"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @GetMapping("")
    public ResponseEntity<Object> getHappyHours() {
        return ResponseHandler.responseBuilder(null, HttpStatus.OK, happyHourDetailsService.getAllHappyHours());

    }

    @Operation(summary = "Get happy hour details", description = "", tags = {"profile"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @GetMapping("/{id}")
    public ResponseEntity<Object> getHappyHourById(@PathVariable Long id) {
        return ResponseHandler.responseBuilder(null, HttpStatus.OK, happyHourDetailsService.getHappyHourById(id));

    }

    @Operation(summary = "Add new happy hour details", description = "", tags = {"profile"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @PostMapping("")
    public ResponseEntity<Object> addHappyHour(@RequestBody HappyHourDetails happyHourDetails) {
        return ResponseHandler.responseBuilder("New Happy our added successfully.", HttpStatus.OK, happyHourDetailsService.addHappyHour(happyHourDetails));

    }

    @Operation(summary = "Update new happy hour details", description = "", tags = {"profile"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHappyHour(@PathVariable Long id, @RequestBody HappyHourDetails happyHourDetails) {
        return ResponseHandler.responseBuilder("New Happy our updated successfully.", HttpStatus.OK, happyHourDetailsService.updateHappyHour(id, happyHourDetails));

    }

    @Operation(summary = "Delete happy hour", description = "", tags = {"profile"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHappyHour(@PathVariable Long id) {
        return ResponseHandler.responseBuilder("Deleted successfully.", HttpStatus.OK, happyHourDetailsService.deleteHappyHour(id));

    }
}
