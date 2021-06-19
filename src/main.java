//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class main {
//    private static TileFactory tileFactory = new TileFactory();
//    public static void main(String[] args){
////        Tile tile = tileFactory.Create(" ",null);
////        System.out.println(tile);
//        // Creating a list of integers
//        Position p1 = new Position(0,1);
//        Position p2 = new Position(1,3);
//        Position p3 = new Position(1,1);
//        Position p4 = new Position(2,0);
//        Position p5 = new Position(0,3);
//        List<Position> list = Arrays.asList(p1, p2, p3, p4, p5);
////        List<Pair(Integer, Integer)> list = Arrays.asList((0,0),(3,0),(1,3),(0,1),(0,2));
////        List<(Integer, Integer)> list = Arrays.asList((0,0),(3,0),(1,3),(0,1),(0,2));
////        List<{Integer, Integer}> list = Arrays.asList((0,0),(3,0),(1,3),(0,1),(0,2));
//
//
//        System.out.println("The sorted stream is : ");
//
//        // displaying the stream with elements
//        // sorted in natural order
//        String result = list.stream().sorted().map(t -> t.toChar() + t.GetPosition().getX()==XTop ? "/n" : "").collect(Collectors.joining(""));
//        System.out.println(result);
//    }
//}
