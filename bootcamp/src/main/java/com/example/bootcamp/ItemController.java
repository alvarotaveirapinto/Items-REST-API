package com.example.bootcamp;

import com.example.bootcamp.exception.ItemNotAvailableException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
public class ItemController {

    @Autowired
    private ItemService service;


    @GetMapping("/get")
    @ApiOperation(value="Getting an Item from the database")
    public List<Item> getAllItems () {
        return service.getAllItems();
    }

    @ApiOperation(value = "Saving an item to the database")
    @PostMapping("/post")
    public Integer addItem (@RequestBody Item item) {
        return service.saveItem(item);
    }

    @ApiOperation(value = "Changing the description and the price of  an item")
    @PutMapping("/update/{itemNumber}")
    public String updateItem(@RequestBody Item item, @PathVariable Integer itemNumber) {
        return service.updateItem(item, itemNumber);
    }
    @ApiOperation(value="Deletes an item by id (itemNumber)")
    @DeleteMapping(value="/delete/{id}")
    public String deleteItemById (@PathVariable int id){
        service.deleteById(id);
        return "Item has been deleted";
    }
    @ExceptionHandler(value = ItemNotAvailableException.class)
    public ResponseEntity<String> itemNotPresent (ItemNotAvailableException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }}
