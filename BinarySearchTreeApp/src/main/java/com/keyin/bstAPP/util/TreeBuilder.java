package com.keyin.bstAPP.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.bstAPP.model.TreeNode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TreeBuilder {

    public String buildTree(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return "{}";
        }
        TreeNode root = null;
        for (Integer number : numbers) {
            root = insertNode(root, number);
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(root);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize tree structure", e);
        }
    }

    private TreeNode insertNode(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.getValue()) {
            node.setLeft(insertNode(node.getLeft(), value));
        } else if (value > node.getValue()) {
            node.setRight(insertNode(node.getRight(), value));
        }
        return node;
    }
}
