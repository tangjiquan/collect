package org.panther.study.designpattern.Composite_01;

/**
 * @Author: Kevin
 * @Description:
 * 将多个对象组合在一起进行操作，常用于表示树形结构中，例如二叉树，数等。
 *
 * @Date: Created in 下午12:03 18-4-1
 * @Version:
 */
public class TreeMain {


    public static void main(String[] args){

        Tree root = new Tree("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");
        nodeB.add(nodeC);
        root.root.add(nodeB);

        System.out.println("build the tree finished!");

    }
}
