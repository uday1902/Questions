package src.main.java.inheritance;

public class Main {

    public static void main(String[] args){
        Dummey dummey = new Dummey(100);
        ChildPromo childPromo = new ChildPromo();
        childPromo.setDummy(dummey);
        BasePromo basePromo = childPromo;
        PromoInteractor promoInteractor = new PromoInteractor();
        promoInteractor.interact(basePromo);


    }
}
