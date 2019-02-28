package com.panther.study.algorithm.algorithm02.array;


/**
 * @author: Kevin
 * @date: created in 上午8:52 2019-02-22
 * @version: V1.0
 */
public class Array0007ConstructBinaryTreefromPreorderandInorderTraversal {


	int p = 0;
	int[] preorder;
	int[] inorder;

	public TreeNode buildTree(int[] preorder, int[] inorder){

		return new TreeNode(1);
	}

	TreeNode buildTree(int start, int end){
		return new TreeNode(1);

	}


	public static void main(String[] args){

	}



	class TreeNode{
		TreeNode left;
		TreeNode right;
		int value;

		public TreeNode(int value){
			this.value = value;
		}
	}

}
