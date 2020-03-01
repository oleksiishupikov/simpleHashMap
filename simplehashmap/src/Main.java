public class Main {
    public static void main(String[] args)
    {
        SimpleHashMap map = new SimpleHashMap();
        map.put(1, 12L);
        map.put(17, 15L);
        map.put(7, 12L);
        map.put(1, 13L);

        System.out.println(map.get(17));
        System.out.println(map.get(2));
        System.out.println(map.get(1));

        System.out.println(map.size());
    }
}
