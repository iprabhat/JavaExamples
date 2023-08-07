package com.demo.hackerrank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

enum Color {
    RED, GREEN
}
abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}
class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis
{
    public abstract int getResult();
    public abstract void visitNode(TreeNode node);
    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
	int sumLeaves = 0;
    public int getResult() {
      	//implement this
        return sumLeaves;
    }

    public void visitNode(TreeNode node) {
      	//implement this
    	return;
    }

    public void visitLeaf(TreeLeaf leaf) {
      	//implement this
    	sumLeaves += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
	private long product = 1;
	private final int M = 1000000007;
    public int getResult() {
      	//implement this
        return (int) product;
    }

    public void visitNode(TreeNode node) {
    	if(node.getColor() == Color.RED) {
    		product = (product * node.getValue()) % M;
    	}
    }

    public void visitLeaf(TreeLeaf leaf) {
    	if(leaf.getColor() == Color.RED) {
    		product = (product * leaf.getValue()) % M;
    	}
    }
}

class FancyVisitor extends TreeVis {
	int sumNonLeafEvenDepth = 0;
	int sumGreenLeaf = 0;
    public int getResult() {
      	return Math.abs(sumNonLeafEvenDepth - sumGreenLeaf);
    }

    public void visitNode(TreeNode node) {
    	if((node.getDepth() % 2) == 0) {
    		sumNonLeafEvenDepth += node.getValue();
    	}
    }

    public void visitLeaf(TreeLeaf leaf) {
    	if(leaf.getColor() == Color.GREEN) {
    		sumGreenLeaf += leaf.getValue();
    	}
    }
}

public class JavaVisitorPattern {

	static HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
	static int[] nodeValues;
	static Color[] nodeColors;
	public static Tree solve() {
	        //read the tree from STDIN and return its root as a return value of this function
		 Scanner scan = new Scanner(System.in);
		 int n = scan.nextInt();
		 nodeValues = new int[n];
		 nodeColors = new Color[n];
		 
		 // Save node values and colors 
		 for (int i = 0; i < n; i++) {
			 nodeValues[i] = scan.nextInt();
		 }
		 for (int i = 0; i < n; i++) {
			 nodeColors[i] = scan.nextInt() == 0 ? Color.RED : Color.GREEN;
		 }
		 
		 // save edges
			for (int i = 0; i < n - 1; i++) {
				int u = scan.nextInt();
				int v = scan.nextInt();

				HashSet<Integer> uNeighbours = map.get(u);
				if (uNeighbours == null) {
					uNeighbours = new HashSet<>();
					map.put(u, uNeighbours);
				}
				uNeighbours.add(v);

				/* Edges are undirected: Add 2nd direction */
				HashSet<Integer> vNeighbors = map.get(v);
				if (vNeighbors == null) {
					vNeighbors = new HashSet();
					map.put(v, vNeighbors);
				}
				vNeighbors.add(u);
			}
		 
		 
		 scan.close();
		 
		 // Create Tree
		 if(n == 1) {
			 return new TreeLeaf(nodeValues[0], nodeColors[0], 0);
		 }
		 
		 TreeNode node = new TreeNode(nodeValues[0], nodeColors[0], 0);
		 addChildren2(node, 1);
		 
		 return node;
	 }
	
    /* Recursively adds children of a TreeNode */
    private static void addChildren(TreeNode parent, Integer parentNum) {
        /* Get HashSet of children and loop through them */
        for (Integer treeNum : map.get(parentNum)) {
            map.get(treeNum).remove(parentNum); // removes the opposite arrow direction
            
            /* Add child */
            HashSet<Integer> grandChildren = map.get(treeNum);
            boolean childHasChild = (grandChildren != null && !grandChildren.isEmpty());
            Tree tree;
            if (childHasChild) {
                tree = new TreeNode(nodeValues[treeNum - 1], nodeColors[treeNum - 1], parent.getDepth() + 1);
            } else {
                tree = new TreeLeaf(nodeValues[treeNum - 1], nodeColors[treeNum - 1], parent.getDepth() + 1);
            }
            parent.addChild(tree);

            /* Recurse if necessary */
            if (tree instanceof TreeNode) {
                addChildren((TreeNode) tree, treeNum);
            }
        }
    }
	
	 public static void addChildren2(TreeNode parent, int parentNum) {		 
		 HashSet<Integer> children = map.get(parentNum);
		 //if(children != null && !children.isEmpty()) {		}	 
			 //Iterator<Integer> itr = children.iterator();			 
			 for(Integer child : children) {
				 map.get(child).remove(parentNum); // remove the opposite direction edge
				 Tree tree;
				 HashSet<Integer> h = map.get(child);
				 if(map.get(child) != null  && !map.get(child).isEmpty()) {
					 tree = new TreeNode(nodeValues[child - 1], nodeColors[child - 1], parent.getDepth() + 1);					 					 
				 }
				 else {
					 tree = new TreeLeaf(nodeValues[child - 1], nodeColors[child - 1], parent.getDepth() + 1);					 
				 }
				 parent.addChild(tree);
				 if(tree instanceof TreeNode) {
					 addChildren((TreeNode)tree, child);
				 }
			 }
	 }
	 
	public static void main(String[] args) {
		Tree root = solve();
		SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
      	ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
      	FancyVisitor vis3 = new FancyVisitor();

      	root.accept(vis1);
      	root.accept(vis2);
      	root.accept(vis3);

      	int res1 = vis1.getResult();
      	int res2 = vis2.getResult();
      	int res3 = vis3.getResult();

      	System.out.println(res1);
     	System.out.println(res2);
    	System.out.println(res3);

	}
}

/*
 * Sample Input 
5
4 7 2 5 12
0 1 0 0 1
1 2
1 3
3 4
3 5
 
 Sample Output
	24
	40
	15
 
 * 
 * */
