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
import za.co.drinkup.api.entity.Location;
import za.co.drinkup.api.response.ResponseHandler;
import za.co.drinkup.api.service.LocationService;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @Operation(summary = "Create new Location", description = "", tags = {"location"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @PostMapping(value = "", produces = {"application/json"})
    public ResponseEntity<Object> createLocation(@RequestBody Location location){

        return ResponseHandler.responseBuilder("Location has been created successfully ", HttpStatus.OK, locationService.createLocation(location));

    }

    @Operation(summary = "Update new Location", description = "", tags = {"location"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Location.class)))})
    @PutMapping("/{locationId}")
    public ResponseEntity<Object> updateLocation(@PathVariable Long locationId, @RequestBody Location location) {
        location.setId(locationId);
        return ResponseHandler.responseBuilder("Location has been updated successfully", HttpStatus.OK, locationService.updateLocation(location));

    }

    @Operation(summary = "Delete Location ", description = "", tags = {"location"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Location.class)))})
    @DeleteMapping("/{locationId}")
    public ResponseEntity<Object> deleteLocation(@PathVariable Long locationId) {
        return ResponseHandler.responseBuilder("Location deleted successfully", HttpStatus.OK, locationService.deleteLocation(locationId));

    }

    @Operation(summary = "Get Location ", description = "", tags = {"location"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Location.class)))})
    @GetMapping("/{locationId}")
    public ResponseEntity<Object> getLocation(@PathVariable Long locationId) {
        return ResponseHandler.responseBuilder(null, HttpStatus.OK, locationService.getLocation(locationId));

    }

    @Operation(summary = "ge list of Location ", description = "", tags = {"location"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = Location.class)))})
    public ResponseEntity<Object> getAllLocations() {
        return ResponseHandler.responseBuilder(null, HttpStatus.OK, locationService.getAllLocations());
    }


}
