package observer;

import java.util.ArrayList;

public class GroupAdmin implements Sender {
    private final ArrayList<Member> memberList;
    private final UndoableStringBuilder current;

    public GroupAdmin() {
        memberList = new ArrayList<Member>();
        current = new UndoableStringBuilder();
    }
    public GroupAdmin(String str) {
        memberList = new ArrayList<Member>();
        current = new UndoableStringBuilder(str);
    }


    @Override
    public void register(Member obj) {
        if (!memberList.contains(obj)) {
            memberList.add(obj);
        }
    }

    @Override
    public void unregister(Member obj) {
        memberList.remove(obj);
    }

    @Override
    public void insert(int offset, String obj) {
        current.insert(offset, obj);
        notifyMembers();
    }

    @Override
    public void append(String obg) {
        current.append(obg + " ");
        notifyMembers();
    }

    @Override
    public void delete(int start, int end) {
        current.delete(start, end);
        notifyMembers();
    }

    @Override
    public void undo() {
        current.undo();
        notifyMembers();
    }


    public void notifyMembers() {
        for (Member member : memberList) {
            member.update(current);
        }
    }
    public String getCurrentData(){
        return current.toString();
    }

    public ArrayList<Member> getMembers(){
        return memberList;
    }

}
