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
import za.co.drinkup.api.entity.Venue;
import za.co.drinkup.api.response.ResponseHandler;
import za.co.drinkup.api.service.VenueService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venues")
public class VenueController {

    @Autowired private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "Get venue list", description = "", tags = {"venues"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @GetMapping(value = "", produces = {"application/json"})
    public ResponseEntity<Object> getVenues(){

        return ResponseHandler.responseBuilder(null, HttpStatus.OK, venueService.getVenues());

    }

    @Operation(summary = "Add new venue", description = "", tags = {"venues"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @PostMapping(value = "", produces = {"application/json"})
    public ResponseEntity<Object> createVenue(@RequestBody Venue venue, @RequestBody List<HappyHourDetails> happyHourDetails){

        return ResponseHandler.responseBuilder(null, HttpStatus.OK, venueService.createVenue(venue,happyHourDetails));

    }

    @Operation(summary = "Get venue happy hours", description = "", tags = {"venues"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @GetMapping("/venues/{id}/happy-hour")
    public ResponseEntity<Object> getHappyHourDetails(@PathVariable long id) {
        return ResponseHandler.responseBuilder(null, HttpStatus.OK, venueService.getHappyHoursByVenueId(id));

    }
    @Operation(summary = "Search for venue", description = "", tags = {"venues"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = VenueDto.class)))})
    @GetMapping("/search")
    public ResponseEntity<Object> searchVenues(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "typeOfSpecial", required = false) String typeOfSpecial,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "rating", required = false) Double rating,
            @RequestParam(value = "proximity", required = false) Double proximity) {

        return ResponseHandler.responseBuilder(null, HttpStatus.OK, venueService.searchVenues(name, typeOfSpecial, city, rating, proximity));

    }

}
