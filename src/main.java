//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class main {
//    private static BusinessLayer.TileFactory tileFactory = new BusinessLayer.TileFactory();
//    public static void main(String[] args){
////        BusinessLayer.Tile tile = tileFactory.Create(" ",null);
////        System.out.println(tile);
//        // Creating a list of integers
//        BusinessLayer.Position p1 = new BusinessLayer.Position(0,1);
//        BusinessLayer.Position p2 = new BusinessLayer.Position(1,3);
//        BusinessLayer.Position p3 = new BusinessLayer.Position(1,1);
//        BusinessLayer.Position p4 = new BusinessLayer.Position(2,0);
//        BusinessLayer.Position p5 = new BusinessLayer.Position(0,3);
//        List<BusinessLayer.Position> list = Arrays.asList(p1, p2, p3, p4, p5);
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
