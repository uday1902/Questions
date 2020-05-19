package src.main.java.inheritance;

public class ChildPromo extends BasePromo {

    private Dummey dummy;

    public void setDummy(Dummey dummy){
        this.dummy = dummy;
    }

    public void method(){
        System.out.println("ChildPromo Class method");
    }

    public void newMethod(){
        System.out.println("ChildPromo Class newMethod -> " + dummy.val);
    }
}
