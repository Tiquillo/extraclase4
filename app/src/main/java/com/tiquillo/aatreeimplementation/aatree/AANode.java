package com.tiquillo.aatreeimplementation.aatree;


/**
 *  Java Program to Implement AA Tree
 */

/** Class AANode **/
public class AANode
{
    AANode left, right;
    int element, level;

    /** Constructor **/
    public AANode()
    {
        this.element = 0;
        this.left = this;
        this.right = this;
        this.level = 0;
    }

    /** Constructor **/
    public AANode(int ele)
    {
        this(ele, null, null);
    }

    /** Constructor **/
    public AANode(int ele, AANode left, AANode right)
    {
        this.element = ele;
        this.left = left;
        this.right = right;
        this.level = 1;
    }
}
