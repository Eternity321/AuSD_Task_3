package ru.vsu.cs.Akimushkin;

class ListNode {
    private Integer value;

    private ListNode nextNode;
    private ListNode prevNode;

    public ListNode(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ListNode getNextNode() {
        return nextNode;
    }

    public void setNextNode(ListNode nextNode) {
        this.nextNode = nextNode;
    }

    public ListNode getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(ListNode prevNode) {
        this.prevNode = prevNode;
    }
}
