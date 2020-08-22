package stack;

import java.util.Deque;
import java.util.LinkedList;

/*
All operations are O(1)
 */
public class StackWithMin {
    Deque<Integer> st;
    Deque<Integer> minSt;
    /** initialize your data structure here. */
    public StackWithMin() {
        st = new LinkedList<>();
        minSt = new LinkedList<>();
    }

    public void push(int x) {
        if(st.isEmpty() || minSt.peek()>=x){
            minSt.addFirst(x);
        }
        st.addFirst(x);
    }

    public void pop() {
        int ret = st.pollFirst();
        if(minSt.peekFirst() == ret){
            minSt.pollFirst();
        }
    }

    public int top() {
        return st.peekFirst();
    }

    public int getMin() {
        return minSt.peekFirst();
    }
}
