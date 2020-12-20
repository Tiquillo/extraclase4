package com.tiquillo.aatreeimplementation.aatree;

public class AATree
{
    private AANode root;
    private static AANode newerNode = new AANode();

    public AATree()
    {
        root = newerNode;
    }

    public boolean isEmpty()
    {
        return root == newerNode;
    }

    public void clear()
    {
        root = newerNode;
    }

    public void insert(int X)
    {
        root = insert(X, root);
    }
    private AANode insert(int X, AANode T)
    {
        if (T == newerNode)
            T = new AANode(X, newerNode, newerNode);
        else if ( X < T.element )
            T.left = insert(X, T.left);
        else if ( X > T.element)
            T.right = insert(X, T.right);
        else
            return T;

        T = skew(T);
        T = split(T);
        return T;
    }

    private AANode skew(AANode T)
    {
        if (T == newerNode)
            return newerNode;
        else if (T.left == newerNode)
            return T;
        else if (T.left.level == T.level)
        {
            AANode L = T.left;
            T.left = L.right;
            L.right = T;
            return L;
        }
        else
            return T;
    }

    private AANode split(AANode T)
    {
        if (T == newerNode)
            return newerNode;
        else if (T.right == newerNode || T.right.right == newerNode)
            return T;
        else if (T.level == T.right.right.level)
        {
            AANode R = T.right;
            T.right = R.left;
            R.left = T;

            R.level = R.level + 1;
            return R;
        }
        else
            return T;
    }

    private AANode decreaseLevel(AANode T)
    {
        int shouldBe = Math.min(T.left.level, T.right.level) + 1;
        if (shouldBe < T.level)
        {
            T.level = shouldBe;
            if (shouldBe < T.right.level)
                T.right.level = shouldBe;
        }
        return T;
    }

    public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(AANode r)
    {
        if (r == newerNode)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    public boolean search(int val)
    {
        return search(root, val);
    }

    private boolean search(AANode r, int val)
    {
        boolean found = false;
        while ((r != newerNode) && !found)
        {
            int rval = r.element;
            if (val < rval)
                r = r.left;
            else if (val > rval)
                r = r.right;
            else
            {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
}