package com.tiquillo.aatreeimplementation.aatree;

public class AANode
{
    AANode left, right;
    int element, level;

    public AANode()
    {
        this.element = 0;
        this.left = this;
        this.right = this;
        this.level = 0;
    }

    public AANode(int elem)
    {
        this(elem, null, null);
    }

    /** Constructor **/
    public AANode(int elem, AANode left, AANode right)
    {
        this.element = elem;
        this.left = left;
        this.right = right;
        this.level = 1;
    }
}
