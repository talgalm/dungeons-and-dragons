public class Health{
    private int healthPool;
    private int healthAmount;

    public  Health(int healthPool, int healthAmount) {
        this.healthAmount = healthAmount;
        this.healthPool = healthPool;
    }
    public int getHealthPool(){ return healthPool;}
    public int getHealthAmount(){return healthAmount;}
    public void setHealthPool(int newHealthPool){
        this.healthPool = newHealthPool;
    }
    public void setHealthAmount(int newHealthAmount){
        this.healthAmount = newHealthAmount;
    }
}
