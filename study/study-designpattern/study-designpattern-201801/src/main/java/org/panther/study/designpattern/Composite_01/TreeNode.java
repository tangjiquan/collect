package org.panther.study.designpattern.Composite_01;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Author: Kevin
 * @Description:
 * @Date: Created in 上午11:59 18-4-1
 * @Version:
 */
public class TreeNode {

    private String name;
    private TreeNode  parent;
    private Vector<TreeNode> children = new Vector<TreeNode>();

    public TreeNode(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }


    public void add(TreeNode node){
        children.add(node);
    }

    public void remove(TreeNode node){
        children.remove(node);
    }

    public Enumeration<TreeNode> getChildren(){
        return children.elements();
    }

}
