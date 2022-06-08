//Alexa Andoniades with Angela Reyzelman

public class LinkStrand implements IDnaStrand {
    private class Node{
        String info;
        Node next;
        Node(String s, Node link){
            info = s;
            next = link;
        }
    }
    private Node myFirst;
    private Node myLast;
    private long mySize;
    private int myAppends;
    private int myIndex;
    private int myLocalIndex;
    private Node myCurrent;

    public LinkStrand() {
        initialize("");
    }

    public LinkStrand(String s){
        initialize(s);
    }
    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
        myFirst = new Node(source, null);
        myLast = myFirst;
        mySize = source.length();
        myAppends = 0;
        myCurrent = myFirst;
        myIndex = 0;
        myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna,null);
        myLast = myLast.next;
        mySize += dna.length();
        myAppends ++;
        return this;
    }

    @Override
    public IDnaStrand reverse() {
        LinkStrand fin = new LinkStrand();
        Node last = myFirst;
        Node current = myFirst;
        while(current != null){
            StringBuilder ret = new StringBuilder(current.info);
            ret.reverse();
            String s = ret.toString();
            fin.addFirst(s);
            current = current.next;
        }
        fin.mySize = this.mySize;
        fin.myLast = last;
        return fin;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= mySize){
            throw new IndexOutOfBoundsException();
        }
        if (myIndex > index){
            myCurrent = myFirst;
            myIndex = 0;
            myLocalIndex = 0;
        }
        while (myIndex < index){
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex >= myCurrent.info.length()){
                myLocalIndex = 0;
                myCurrent = myCurrent.next;
            }
        }
        return myCurrent.info.charAt(myLocalIndex);
    }
    private void addFirst(String s) {
        myFirst = new Node(s,myFirst);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        Node current = myFirst;
        while (current != null){
            s.append(current.info);
            current = current.next;
        }
        return s.toString();
    }
}
