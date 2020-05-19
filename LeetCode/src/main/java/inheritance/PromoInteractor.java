package src.main.java.inheritance;


public class PromoInteractor {

    public void interact(BasePromo basePromo){
        basePromo.method();
        ChildPromo childPromo = (ChildPromo) basePromo;
        childPromo.newMethod();
    }
}
