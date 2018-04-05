package Helper;

@FunctionalInterface
public interface Func1Param<T>
{
    boolean invoke(T t);
}
