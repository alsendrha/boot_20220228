package com.example.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.example.entity.Item;
import com.example.service.ItemDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/item")
public class ItemController {

    @Autowired
    private ItemDB itemDB;

    // resources/하위폴더의 파일을 읽기위한 객체생성
    // ResourceLoader resource = new ResourceLoader();
    @Autowired
    private ResourceLoader resource;

    // 127.0.0.1:8080/item/insert
    @GetMapping(value = "/insert")
    public String insertGET() {

        // item 폴더의 insert.jsp를 표시
        return "item/insert";
    }

    @PostMapping(value = "/insert")
    public String insertPOST(
            @ModelAttribute Item item,
            @RequestParam(name = "image") MultipartFile file)
            throws IOException {

        if (file.isEmpty() == false) {// 첨부됨
            item.setFilename(file.getOriginalFilename());
            item.setFilesize(file.getSize());
            item.setFiletype(file.getContentType());
            item.setFiledata(file.getBytes()); // 오류나면 import
        }

        System.out.println(item.toString());
        int result = itemDB.insertItem(item);
        if (result == 1) {
            // 주소가 바뀌는지만 확인
            return "redirect:/item/selectlist";
        }

        // GET으로 변경된 127.0.0.1:8080/item/insert
        return "redirect:/item/insert";
    }

    // @RequestMapping(value = "/selectlist", method = RequestMethod.GET) 사용가능
    @GetMapping(value = "/selectlist")
    public String selectlistGET(
            Model model,
            @RequestParam(name = "page", defaultValue = "0") int page) {

        if (page == 0) { // ?page=1을 추가하는 부분
            return "redirect:/item/selectlist?page=1";
        }

        // 1 페이지 => 0
        // 2 페이지 => 1

        // 1. 목록
        Pageable pageable = PageRequest.of(page - 1, 10);
        List<Item> list = itemDB.selectListItem(pageable);

        // 2. 페이지네이션 개수
        long pages = itemDB.selectItemCount();

        // 목록
        model.addAttribute("list", list);
        // 10 -> 1
        // 11 -> 2
        // 22 -> 3
        model.addAttribute("pages", (pages - 1) / 10 + 1);
        return "/item/selectlist";
    }

    @GetMapping(value = "/delete")
    public String deleteGET(@RequestParam(name = "code") long code) {

        int result = itemDB.deleteItem(code);
        if (result == 1L) {
            return "redirect:/item/selectlist";
        }

        return "redirect:/item/selectlist";
    }

    // 127.0.0.1:8080/item/image?code=20
    @GetMapping(value = "/image")
    public ResponseEntity<byte[]> imageGET(
            @RequestParam(name = "code") long code) {
        try {
            Item item = itemDB.selectOneItemImage(code);
            if (item.getFilesize() > 0) { // 이미지가 있음
                HttpHeaders headers = new HttpHeaders();
                if (item.getFiletype().equals("image/jpeg")) {
                    headers.setContentType(MediaType.IMAGE_JPEG);
                } else if (item.getFiletype().equals("image/png")) {
                    headers.setContentType(MediaType.IMAGE_PNG);
                } else if (item.getFiletype().equals("image/gif")) {
                    headers.setContentType(MediaType.IMAGE_GIF);
                }

                // 생성자(실제데이터, headers, 200)
                ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(item.getFiledata(), headers,
                        HttpStatus.OK);
                return response;
            } else { // 이미지가 없는 경우
                InputStream stream = resource.getResource("classpath:/static/img/default.jpg").getInputStream();
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG);
                ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(stream.readAllBytes(), headers,
                        HttpStatus.OK);
                return response;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping(value = "/update")
    public String updateGET(Model model, @RequestParam(name = "code") long code) {

        Item item = itemDB.selectOneItem(code);

        // jsp로 전달
        model.addAttribute("item", item);

        // jsp 표시
        return "/item/update";
    }

    @PostMapping(value = "/update")
    public String updatePOST(@ModelAttribute Item item, @RequestParam(name = "image") MultipartFile file)
            throws IOException {

        if (!file.isEmpty()) {
            item.setFilename(file.getOriginalFilename());
            item.setFilesize(file.getSize());
            item.setFiletype(file.getContentType());
            item.setFiledata(file.getBytes());
        }

        int result = itemDB.updateItem(item);

        if (result == 1) {
            return "redirect:/item/selectlist";
        }

        return "redirect:/item/update?code=" + item.getCode();
    }

}
