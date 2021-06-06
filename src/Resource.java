public class Resource{
    private int resourcePool;
    private int resourceAmount;

    public  Resource(int resourcePool, int resourceAmount) {
        this.resourceAmount = resourceAmount;
        this.resourcePool = resourcePool;
    }
    public int getResourcePool(){ return resourcePool;}
    public int getResourceAmount(){return resourceAmount;}
    public void setResourcePool(int newHealthPool){
        this.resourcePool = newHealthPool;
    }
    public void setResourceAmount(int newHealthAmount){
        this.resourceAmount = newHealthAmount;
    }
}
