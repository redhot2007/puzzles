package org.puzzles.coding.datastructure;
//package com.program.DataStructures;
//
//import com.program.models.TreesAndGraph.TreeNode;
//
//
//// BinarySearchTree class
////
//// CONSTRUCTION: with no initializer
////
//// ******************PUBLIC OPERATIONS*********************
//// void insert( x )       --> Insert x
//// void remove( x )       --> Remove x (unimplemented)
//// Comparable find( x )   --> Return item that matches x
//// Comparable findMin( )  --> Return smallest item
//// Comparable findMax( )  --> Return largest item
//// boolean isEmpty( )     --> Return true if empty; else false
//// void makeEmpty( )      --> Remove all items
//// void printTree( )      --> Print tree in sorted order
//
///**
// * Implements an AVL tree.
// * Note that all "matching" is based on the compareTo method.
// * @author Mark Allen Weiss
// */
//public class AvlTree
//{
//	TreeNode root;
//    /**
//     * Construct the tree.
//     */
//    public AvlTree( )
//    {
//        root = null;
//    }
//
//    /**
//     * Insert into the tree; duplicates are ignored.
//     * @param x the item to insert.
//     */
//    public void insert( Comparable x )
//    {
//        root = insert( x, root );
//    }
//
//    /**
//     * Remove from the tree. Nothing is done if x is not found.
//     * @param x the item to remove.
//     */
//    public void remove( Comparable x )
//    {
//        System.out.println( "Sorry, remove unimplemented" );
//    }
//
//    /**
//     * Find the smallest item in the tree.
//     * @return smallest item or null if empty.
//     */
//    public Comparable findMin( )
//    {
//        return elementAt( findMin( root ) );
//    }
//
//    /**
//     * Find the largest item in the tree.
//     * @return the largest item of null if empty.
//     */
//    public Comparable findMax( )
//    {
//        return elementAt( findMax( root ) );
//    }
//
//    /**
//     * Find an item in the tree.
//     * @param x the item to search for.
//     * @return the matching item or null if not found.
//     */
//    public Comparable find( Comparable x )
//    {
//        return elementAt( find( x, root ) );
//    }
//
//    /**
//     * Make the tree logically empty.
//     */
//    public void makeEmpty( )
//    {
//        root = null;
//    }
//
//    /**
//     * Test if the tree is logically empty.
//     * @return true if empty, false otherwise.
//     */
//    public boolean isEmpty( )
//    {
//        return root == null;
//    }
//
//    /**
//     * Print the tree contents in sorted order.
//     */
//    public void printTree( )
//    {
//        if( isEmpty( ) )
//            System.out.println( "Empty tree" );
//        else
//            printTree( root );
//    }
//
//    /**
//     * Internal method to get element field.
//     * @param t the node.
//     * @return the element field or null if t is null.
//     */
//    private Comparable elementAt( TreeNode t )
//    {
//        return t == null ? null : t.element;
//    }
//
//    /**
//     * Internal method to insert into a subtree.
//     * @param x the item to insert.
//     * @param t the node that roots the tree.
//     * @return the new root.
//     */
//    private TreeNode insert( Comparable x, TreeNode t )
//    {
//        if( t == null )
//            t = new TreeNode( x, null, null );
//        else if( x.compareTo( t.element ) < 0 )
//        {
//            t.left = insert( x, t.left );
//            if( height( t.left ) - height( t.rightChild ) == 2 )
//                if( x.compareTo( t.left.element ) < 0 )
//                    t = rotateWithLeftChild( t );
//                else
//                    t = doubleWithLeftChild( t );
//        }
//        else if( x.compareTo( t.element ) > 0 )
//        {
//            t.rightChild = insert( x, t.rightChild );
//            if( height( t.rightChild ) - height( t.left ) == 2 )
//                if( x.compareTo( t.rightChild.element ) > 0 )
//                    t = rotateWithrightChildChild( t );
//                else
//                    t = doubleWithrightChildChild( t );
//        }
//        else
//            ;  // Duplicate; do nothing
//        t.height = max( height( t.left ), height( t.rightChildChild ) ) + 1;
//        return t;
//    }
//
//    /**
//     * Internal method to find the smallest item in a subtree.
//     * @param t the node that roots the tree.
//     * @return node containing the smallest item.
//     */
//    private TreeNode findMin( TreeNode t )
//    {
//        if( t == null )
//            return t;
//
//        while( t.left != null )
//            t = t.left;
//        return t;
//    }
//
//    /**
//     * Internal method to find the largest item in a subtree.
//     * @param t the node that roots the tree.
//     * @return node containing the largest item.
//     */
//    private TreeNode findMax( TreeNode t )
//    {
//        if( t == null )
//            return t;
//
//        while( t.rightChildChild != null )
//            t = t.rightChildChild;
//        return t;
//    }
//
//    /**
//     * Internal method to find an item in a subtree.
//     * @param x is item to search for.
//     * @param t the node that roots the tree.
//     * @return node containing the matched item.
//     */
//    private TreeNode find( Comparable x, TreeNode t )
//    {
//        while( t != null )
//            if( x.compareTo( t.element ) < 0 )
//                t = t.left;
//            else if( x.compareTo( t.element ) > 0 )
//                t = t.rightChildChild;
//            else
//                return t;    // Match
//
//        return null;   // No match
//    }
//
//    /**
//     * Internal method to print a subtree in sorted order.
//     * @param t the node that roots the tree.
//     */
//    private void printTree( TreeNode t )
//    {
//        if( t != null )
//        {
//            printTree( t.left );
//            System.out.println( t.element );
//            printTree( t.rightChildChild );
//        }
//    }
//
//    /**
//     * Return the height of node t, or -1, if null.
//     */
//    private static int height( TreeNode t )
//    {
//        return t == null ? -1 : t.height;
//    }
//
//    /**
//     * Return maximum of lhs and rhs.
//     */
//    private static int max( int lhs, int rhs )
//    {
//        return lhs > rhs ? lhs : rhs;
//    }
//
//    /**
//     * Rotate binary tree node with left child.
//     * For AVL trees, this is a single rotation for case 1.
//     * Update heights, then return new root.
//     */
//    private static TreeNode rotateWithLeftChild( TreeNode k2 )
//    {
//        TreeNode k1 = k2.left;
//        k2.left = k1.rightChildChild;
//        k1.rightChildChild = k2;
//        k2.height = max( height( k2.left ), height( k2.rightChildChild ) ) + 1;
//        k1.height = max( height( k1.left ), k2.height ) + 1;
//        return k1;
//    }
//
//    /**
//     * Rotate binary tree node with rightChildChild child.
//     * For AVL trees, this is a single rotation for case 4.
//     * Update heights, then return new root.
//     */
//    private static TreeNode rotateWithrightChild( TreeNode k1 )
//    {
//        TreeNode k2 = k1.rightChildChild;
//        k1.rightChild = k2.left;
//        k2.left = k1;
//        k1.height = max( height( k1.left ), height( k1.rightChild ) ) + 1;
//        k2.height = max( height( k2.rightChild ), k1.height ) + 1;
//        return k2;
//    }
//
//    /**
//     * Double rotate binary tree node: first left child
//     * with its rightChild child; then node k3 with new left child.
//     * For AVL trees, this is a double rotation for case 2.
//     * Update heights, then return new root.
//     */
//    private static TreeNode doubleWithLeftChild( TreeNode k3 )
//    {
//        k3.left = rotateWithrightChildChild( k3.left );
//        return rotateWithLeftChild( k3 );
//    }
//
//    /**
//     * Double rotate binary tree node: first rightChild child
//     * with its left child; then node k1 with new rightChild child.
//     * For AVL trees, this is a double rotation for case 3.
//     * Update heights, then return new root.
//     */
//    private static TreeNode doubleWithrightChildChild( TreeNode k1 )
//    {
//        k1.rightChild = rotateWithLeftChild( k1.rightChild );
//        return rotateWithRightChild( k1 );
//    }
//
//      /** The tree root. */
//
//
//
//        // Test program
//    public static void main( String [ ] args )
//    {
//        AvlTree t = new AvlTree( );
//        final int NUMS = 4000;
//        final int GAP  =   37;
//
//        System.out.println( "Checking... (no more output means success)" );
//
//        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
//            t.insert( new MyInteger( i ) );
//
//        if( NUMS < 40 )
//            t.printTree( );
//        if( ((MyInteger)(t.findMin( ))).intValue( ) != 1 ||
//            ((MyInteger)(t.findMax( ))).intValue( ) != NUMS - 1 )
//            System.out.println( "FindMin or FindMax error!" );
//
//        for( int i = 1; i < NUMS; i++ )
//             if( ((MyInteger)(t.find( new MyInteger( i ) ))).intValue( ) != i )
//                 System.out.println( "Find error1!" );
//}
//}