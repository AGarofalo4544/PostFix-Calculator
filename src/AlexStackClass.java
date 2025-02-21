/*
    This is Alex Garofalo's Stack Class. It uses Generics.
    */
public class AlexStackClass<G>
{
    private class Node<g>
    {
        G content;
        Node<G> next;

        public Node(G content, Node<G> next)
        {
            this.content = content;
            this.next = next;
        }
    }

    private Node<G> gTop;
    private int gSize;

    public AlexStackClass()
    {
        gTop = null;
        gSize = 0;
    }

    public boolean isEmpty()
    {
        return (gTop == null);
    }

   public int getSize()
   {
       return gSize;
   }

    public void push(G pickle)
    {
        Node<G> n = new Node<>(pickle, null);

        n.next = gTop;
        gTop = n;
//        gSize++;
    }
    public G pop()
    {
        if (isEmpty())
        {
            return null;
        }
        G pickle = gTop.content;
        gTop = gTop.next;
//        gSize--;
        return pickle;
    }
    public G top() // This functions as the peek() method of the Stack.java class.
    {
        if (isEmpty())
        {
            return null;
        }
        return gTop.content;
    }
}
