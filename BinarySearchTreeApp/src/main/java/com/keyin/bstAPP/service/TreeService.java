package com.keyin.bstAPP.service;

import com.keyin.bstAPP.model.TreeEntry;
import com.keyin.bstAPP.repository.TreeEntryRepository;
import com.keyin.bstAPP.util.TreeBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeService {

    private final TreeBuilder treeBuilder;
    private final TreeEntryRepository repository;

    public TreeService(TreeBuilder treeBuilder, TreeEntryRepository repository) {
        this.treeBuilder = treeBuilder;
        this.repository = repository;
    }

    public TreeEntry createTree(List<Integer> numbers) {
        String treeStructure = treeBuilder.buildTree(numbers);
        TreeEntry treeEntry = new TreeEntry(numbers.toString(), treeStructure);
        return repository.save(treeEntry);
    }
}
