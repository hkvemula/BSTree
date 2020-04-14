package com.linkedlist.example;

import java.util.*;
/**
 * 
 * @author Harika K Vemula
 *
 */

class BSTree
{
   public static void main(String[] args)
   {   
       Scanner sn=new Scanner(System.in);
       System.out.print("Enter a sentence: ");
       String inputs[]=sn.nextLine().replaceAll("[^a-zA-Z]"," ").split(" ");
       BinarySearchTree bst=new BinarySearchTree();
       for(String input:inputs)
       {
           bst.root=BinarySearchTree.insertNode(bst.root,input);
       }
       System.out.print("The words and their frequencies in the BST:");
       BinarySearchTree.inorderTraversal(bst.root);
       
       System.out.print("\nEnter the word to be searched : ");
       String key=sn.nextLine();
       bst.root=BinarySearchTree.searchUpdateDeleteNodeFrequency(bst.root,key);
       sn.close();
   }
}

class Node
{
   String word;
   int frequency;
   Node left,right;

   Node(String word,int frequency)
   {
       this.word=word;
       this.frequency=frequency;
       this.left=null;
       this.right=null;
   }
}
/**
 * This method is used in performing the BinarySearchTree Process.
 *
 */

class BinarySearchTree
{
   Node root;
   
   public BinarySearchTree()
   {
       root=null;
   }
   /**
    * Here the entered word is searched, deleted and also finds its frequency.
    * @param root
    * @param searchData
    * @return
    */
   
   public static Node searchUpdateDeleteNodeFrequency(Node root,String searchData)
   {
       if(root==null)
       {
           System.out.println("The word is not found.");
       }
       else if(root.word.compareTo(searchData)==0)
       {
           if(root.frequency>1)
           {
               root.frequency--;
               System.out.println("The frequency of "+root.word+" is now "+root.frequency);
           }
           else if(root.frequency==1)
           {
               System.out.println("The word is removed from the BST.");
               root=deleteNode(root,searchData);
           }
       }
       else if(root.word.compareTo(searchData)>0)
       {
           root.left=searchUpdateDeleteNodeFrequency(root.left,searchData);
       }
       else
       {
           root.right=searchUpdateDeleteNodeFrequency(root.right,searchData);
       }
       return root;
   }
   /**
    * Here it performs the insertion process.
    * @param root
    * @param insertData
    * @return
    */
   
   public static Node insertNode(Node root,String insertData)
   {
       if(root==null)
       {
           root=new Node(insertData,1);
       }
       else if(root.word.compareTo(insertData)==0)
       {
           root.frequency++;
       }
       else if(root.word.compareTo(insertData)>0)
       {
           root.left=insertNode(root.left,insertData);
       }
       else
       {
           root.right=insertNode(root.right,insertData);
       }
       return root;
   }
   /**
    * Here it performs the deletion of word process.
    * @param root
    * @param key
    * @return
    */
   
   public static Node deleteNode(Node root, String key)
   {
	   if (root == null) return root;
	   if (key.compareTo(root.word)<0)
		   root.left = deleteNode(root.left, key);
	else if (key.compareTo(root.word)>0)
		   root.right = deleteNode(root.right, key);
	else
   {
		if (root.left == null)
	    return root.right;
	else if (root.right == null)
		return root.left;
			root.word = minValue(root.right);
			root.right = deleteNode(root.right, root.word);
   }
	   return root;
   }

   
   public Node getRoot()
   {
       return this.root;
   }
   
   
   public void setRoot(Node root)
   {
       this.root=root;
   }
   
   /**
    * Here it helps in getting the frequency of words.
    * @param root
    */
   public static void inorderTraversal(Node root)
   {
       if(root==null)
           return;
       inorderTraversal(root.left);
       System.out.print(" "+root.word+","+root.frequency+";");
       inorderTraversal(root.right);
   }
   /**
    * Here it gets the minimum value and helps in deleting the word.
    * @param root
    * @return
    */
   
   public static String minValue(Node root)
	{
		String minval = root.word;
		while (root.left != null)
	{
		minval = root.left.word;
		root = root.left;
	}
		return minval;
	}


}
