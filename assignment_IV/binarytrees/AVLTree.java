package binarytrees;

public class AVLTree<T extends Comparable<T>> extends BSTree<T> {

    /* creates a new empty Tree and returns a pointer to it */
    public AVLTree()
    {
        //Set the root to NULL using constructor of BSTree
        super();
    }

    /* Returns the root node of this tree */
    @SuppressWarnings("unchecked")
    public AVLNode<T> getRoot()
    {
        return (AVLNode<T>)this.root;
    }

    /* Creates a new node for the given info and inserts it into the Tree */
    @Override
    public void insert( T info )
    {
        AVLNode<T> newNode = new AVLNode<T>( info );
        insert( newNode ); 
        rebalanceTree( newNode );     
    }

    /* Get the balance of the given node */
    public int getBalance( AVLNode<T> x ) {
        return AVLNode.getHeight(x.getLeft()) - AVLNode.getHeight(x.getRight());
    }

    /* Checks if this Tree is correctly formatted.  Outputs the errors as detected if print parameter is true. */
    @Override
    public long checkTreeStructure( boolean print ){
        if( this.getRoot()==null )
            return 0; //no tree to check

        long errors = this.getRoot().checkNode( print );
        return errors;
    }

    /* TODO
     * rebalanceTree
     * input: node to start fixing the tree from (climbing up towards the root)
     * output: none
     * 
     * Modifies the tree to ensure the balance of every node above x is -1, 0, or 1
     *
     * Here are some already implemented methods that will help create your solution:
     * getBalance (in AVLTree.java)
     * getTallerChild (in AVLNode.java)
     * leftRotate (in Tree.java)
     * rightRotate (in Tree.java)
     * getParent (in AVLNode.java)
     */
    void rebalanceTree( AVLNode<T> x ) {
      int xCurrentBalance = getBalance(x);
      AVLNode<T> z;  // Child node with greater balance
      int zCurrentBalance;
      //
      while (x != null) { 
        // Check if current node is unbalanced (which is not -1, 0, or 1)
        if (xCurrentBalance <= -2 || xCurrentBalance >= 2) {
          z = x.getTallerChild();
          zCurrentBalance = getBalance(z);
          if ((xCurrentBalance > 0 && zCurrentBalance < 0) || (xCurrentBalance < 0 && zCurrentBalance > 0)) {
            if (zCurrentBalance > 0) {
              rightRotate(z);
            }
            else {
              leftRotate(z);
            }    
          }

          // Balance of x >= 2, left is higher, so rotate right on x
          if (xCurrentBalance >= 2) {
            rightRotate(x);
          }

          // Balance of x <= -2, right is higher, so rotate left on x
          else {
            leftRotate(x);
          }
        }

        // Go to the next parent node
        x = x.getParent();
      }
    }
}
