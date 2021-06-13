public class Resource{
    private int resourcePool;
    private int resourceAmount;

    public Resource(int resourcePool, int resourceAmount) {
        this.resourceAmount = resourceAmount;
        this.resourcePool = resourcePool;
    }
    public int getResourcePool(){ return resourcePool;}
    public int getResourceAmount(){return resourceAmount;}
    public void setResourcePool(int resourcePool){this.resourceAmount = resourceAmount;}
    public void setResourceAmount(int resourceAmount){this.resourceAmount = resourceAmount;}
    public void addHealthPool(int playerLevel){
        resourcePool = playerLevel * 10 + resourcePool;
    }
    public void takeDamage(int Damage) {
        resourceAmount = resourceAmount - Damage;
    }
}
