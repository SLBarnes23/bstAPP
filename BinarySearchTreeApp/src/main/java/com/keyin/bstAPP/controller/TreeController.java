package com.keyin.bstAPP.controller;

import com.keyin.bstAPP.model.TreeEntry;
import com.keyin.bstAPP.service.TreeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tree")
public class TreeController {

    private final TreeService treeService;

    public TreeController(TreeService treeService) {
        this.treeService = treeService;
    }

    @PostMapping("/build")
    public ResponseEntity<TreeEntry> buildTree(@RequestBody List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(treeService.createTree(numbers));
    }
}
