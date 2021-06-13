public interface MessageCallBack {
    public default void send(String s)
    {
        System.out.println(s);
    }
}
