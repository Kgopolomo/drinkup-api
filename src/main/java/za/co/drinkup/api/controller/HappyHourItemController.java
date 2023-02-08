package za.co.drinkup.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.drinkup.api.entity.HappyHourItem;
import za.co.drinkup.api.response.ResponseHandler;
import za.co.drinkup.api.service.HappyHourItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/happyhouritems")
public class HappyHourItemController {

    @Autowired
    private HappyHourItemService happyHourItemService;

    @GetMapping
    public ResponseEntity<Object> getAllHappyHourItems() {
        return ResponseHandler.responseBuilder(null, HttpStatus.OK, happyHourItemService.getAllHappyHourItems());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHappyHourItemById(@PathVariable Long id) {
        Optional<HappyHourItem> happyHourItem = happyHourItemService.getHappyHourItemById(id);
        if (!happyHourItem.isPresent()) {
            return ResponseHandler.responseBuilder("Item not found", HttpStatus.NOT_FOUND, null);
        }
        return ResponseHandler.responseBuilder(null, HttpStatus.OK, happyHourItem.get());

}

    @PostMapping
    public ResponseEntity<Object> addHappyHourItem(@RequestBody HappyHourItem happyHourItem) {
        return ResponseHandler.responseBuilder("Item added successfully", HttpStatus.OK, happyHourItemService.addHappyHourItem(happyHourItem));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHappyHourItem(@PathVariable Long id, @RequestBody HappyHourItem happyHourItem) {
        if (!happyHourItemService.getHappyHourItemById(id).isPresent()) {

            return ResponseHandler.responseBuilder("Item not found", HttpStatus.NOT_FOUND, null);
        }
        return ResponseHandler.responseBuilder("updated successfully ", HttpStatus.OK, happyHourItemService.updateHappyHourItem(id, happyHourItem));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHappyHourItem(@PathVariable Long id) {
        if (!happyHourItemService.getHappyHourItemById(id).isPresent()) {
            return ResponseHandler.responseBuilder("Item not found", HttpStatus.OK, null);
        }
        return ResponseHandler.responseBuilder("Deleted successfully", HttpStatus.OK, happyHourItemService.deleteHappyHourItem(id));

    }
}