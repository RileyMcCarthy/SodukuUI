package soduku;

import java.util.*;

public class CellNode extends Cell {

    //private ArrayList<CellNode> nodes;
    private Queue<CellNode> nodes;
    private int depth = 0;
    private boolean dead = false;

    public CellNode() {
        nodes = new LinkedList<CellNode>();
    }

    public CellNode( Cell theCell, int theDepth) {
        super(theCell.getPos(), theCell.getValue(), theCell.isOriginal());
        nodes = new LinkedList<CellNode>();
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

    public void addNode(CellNode theNode) {
        nodes.add(theNode);
    }

    public CellNode getNextNode() {
        return nodes.poll();
    }


}
