package za.co.drinkup.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.co.drinkup.api.dto.VenueDto;
import za.co.drinkup.api.response.ResponseHandler;
import za.co.drinkup.api.service.MapService;

@RestController
@RequestMapping("/api/v1/map")
public class MapController {

    @Autowired private final MapService mapService;

    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @Operation(summary = "Get list of venues in proximity", description = "", tags = {"map"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @GetMapping("")
    public ResponseEntity<Object> getVenuesInProximity(@RequestParam double latitude, @RequestParam double longitude,
                                                       @RequestParam int radius) {


        return ResponseHandler.responseBuilder(null, HttpStatus.OK, mapService.getVenuesInProximity(latitude, longitude, radius));

    }
}
