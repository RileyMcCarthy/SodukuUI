package soduku;

import java.util.*;

public class CellNode extends Cell {

    private ArrayList<CellNode> nodeArray;
    private Queue<CellNode> nodes;
    private int depth = 0;
    private boolean dead = false;

    public CellNode() {
        nodes = new LinkedList<CellNode>();
        nodeArray = new ArrayList<CellNode>();
    }

    public CellNode( Cell theCell, int theDepth) {
        super(theCell.getPos(), theCell.getValue(), theCell.isOriginal());
        nodes = new LinkedList<CellNode>();
        nodeArray = new ArrayList<CellNode>();
        depth = theDepth;
    }

    public void setDepth(int theDepth) {
        depth = theDepth;
    }

    public int getDepth() {
        return depth;
    }

    public void setDead() {
        dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public void addNode(CellNode theNode) {
        nodeArray.add(theNode);
        nodes.add(theNode);
    }

    public ArrayList<CellNode> getNodeArray() {
        return nodeArray;
    }

    public CellNode getNextNode() {
        return nodes.poll();
    }


}
