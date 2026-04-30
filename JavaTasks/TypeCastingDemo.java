public class TypeCastingDemo {
    public static void main(String[] args){
        long x=1_500_000_000L;
        int y=(int)x;
        long z=(long)y;
        System.out.println(x);
        System.out.println(y);
        System.out.println(z);
        System.out.println(x-z);

        /*
        the long 'x' had a very big value, because of that
        after inserting the value to the integer 'y' the value
        of 'x' autometically decreased
        */
    }
}
