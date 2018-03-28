package interfaceprimjeri;

public class AB implements A, B{
    @Override
    public void info() {
        A.super.info();
        B.super.info();

    }
}
