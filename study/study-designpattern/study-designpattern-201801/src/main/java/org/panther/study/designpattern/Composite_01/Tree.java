package org.panther.study.designpattern.Composite_01;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 下午12:04 18-4-1
 * @Version:
 */
public class Tree {
    TreeNode root = null;

    public Tree(String name){
        root = new TreeNode(name);
    }
}
