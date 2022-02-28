package com.example.controller;

import java.util.List;

import com.example.entity.Item;
import com.example.service.ItemDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemDB itemDB;

    @GetMapping(value = { "/select" })
    public String selectGET(Model model) {
        List<Item> list = itemDB.selectListItem();
        model.addAttribute("list", list);
        return "item/select";
    }

    // 127.0.0.1:8080/item/insert
    @GetMapping(value = { "/insert" })
    public String insertGET() {

        return "item/insert";
    }

    @PostMapping(value = { "/insert" })
    public String insertPOST(
            @ModelAttribute Item item) {
        System.out.println(item.toString());
        itemDB.insertItem(item);

        return "redirect:/item/insert";
    }

}
