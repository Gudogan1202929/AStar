
public class Queue {

    private Vertex[] Nodes;
    private int position;

    /////
    public Queue(int size) {
        Nodes = new Vertex[size];
    }

    /////
    private int getParent(int child) {
        return (child - 1) / 2;
    }

    private boolean isLeaf(int index) {
        return index >= position / 2;
    }

    private int getLeftChild(int parent) {
        return parent * 2 + 1;
    }

    private int getRightChild(int parent) {
        return parent * 2 + 2;
    }

    private boolean RightChildExists(int parent) {
        return getRightChild(parent) < position;
    }

    private boolean isRoot(int index) {
        return index == 0;
    }

    private void swap(int index1, int index2) {
        int i1 = Nodes[index1].getHeapIndex();
        int i2 = Nodes[index2].getHeapIndex();
        Vertex temp = Nodes[index1];
        Nodes[index1] = Nodes[index2];
        Nodes[index2] = temp;
        Nodes[index1].setHeapIndex(index2);
        Nodes[index2].setHeapIndex(index1);
    }

    public void add(Vertex newNode) {
        Nodes[position] = newNode;
        Nodes[position].setHeapIndex(position);
        position++;
        heapifyup();
    }

    public Vertex poll() {
        Vertex o = Nodes[0];
        o.setHeapIndex(-1);
        Nodes[0] = Nodes[position - 1];
        position--;
        heapifydown();
        return o;
    }

    private void heapifyup() {
        int index = position - 1;
        while (!isRoot(index) && (Nodes[index]).compareTo(Nodes[getParent(index)]) < 0) {
            swap(index, getParent(index));
            index = getParent(index);
        }
    }

    private void heapifydown() {
        int index = 0;
        while (!isLeaf(index)) {

            if (((Nodes[getRightChild(index)]).compareTo(Nodes[getLeftChild(index)])) < 0) {
                if ((Nodes[getRightChild(index)].compareTo(Nodes[index]) < 0)) {
                    swap(index, getRightChild(index));
                    index = getRightChild(index);
                } else
                    break;

            } else {
                if ((Nodes[getLeftChild(index)].compareTo(Nodes[index])) < 0) {
                    swap(index, getLeftChild(index));
                    index = getLeftChild(index);
                } else
                    break;
            }
        }
    }

    public void modifyPosition(Vertex v) {
        int index = v.getHeapIndex();
        while (!isRoot(index) && (Nodes[index]).compareTo(Nodes[getParent(index)]) < 0) {
            swap(index, getParent(index));
            index = getParent(index);
        }
    }

    public boolean isEmpty() {
        return position == 0;
    }

    public int size() {
        return position;
    }
}