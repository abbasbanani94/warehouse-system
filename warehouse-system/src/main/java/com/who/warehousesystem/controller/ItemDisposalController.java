package com.who.warehousesystem.controller;

import com.who.warehousesystem.service.ItemDisposalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemDisposals")
public class ItemDisposalController {

    @Autowired
    ItemDisposalService itemDisposalService;


}
