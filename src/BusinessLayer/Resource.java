package BusinessLayer;

public class Resource{
    private int resourceMax;
    private int resourceCurrent;

    public Resource(int resourceMax, int resourceCurrent) {
        this.resourceMax = resourceMax;
        this.resourceCurrent = resourceCurrent;
    }
    public int GetResourceMax(){ return resourceMax;}
    public void SetResourceMax(int resourceMax){this.resourceMax = resourceMax;}

    public int GetResourceCurrent(){return resourceCurrent;}
    public void SetResourceCurrent(int resourceCurrent){
        if(resourceCurrent > resourceMax)
            resourceCurrent = resourceMax;
        this.resourceCurrent = resourceCurrent;
    }

    public void AddToResourceCurrent(double amount){
        if(resourceCurrent+amount > resourceMax)
            amount = resourceMax-resourceCurrent;
        resourceCurrent += amount;
    }
    public void AddToResourceMax(int amount){
        if(amount > 0)
            resourceMax += amount;
    }

    public void TakeFromResourceCurrent(int Taken) {
        if(resourceCurrent-Taken < 0)
            resourceCurrent = Taken;
        resourceCurrent -= Taken;
    }
}
