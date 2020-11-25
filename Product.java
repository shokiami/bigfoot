import java.util.*;

public class Product {
    private String name;
    private double weight; //kilograms
    private List<ProductTrait> traits;
    
    public Product() {
      traits = new ArrayList<ProductTrait>();
    }
    
    public <Trait extends ProductTrait> Trait getTrait(Class<Trait> clazz) {
      for (ProductTrait trait : traits) {
        if (clazz.isInstance(trait)) {
          return clazz.cast(trait);
        }
      }
      return null;
    }
    
    public <Trait extends ProductTrait> void setTrait(Trait newtrait) {
      for (int i = 0; i < traits.size(); i ++) {
        if (traits.get(i).getClass() == newtrait.getClass()) {
          traits.set(i, (ProductTrait) newtrait);
          return;
        }
      }
      traits.add((ProductTrait) newtrait);
      System.out.println(traits.size());
    }

    public String getName(){
        return name;
    }

    public double getWeight() {
        return weight;
    }
}
