package helper;

@FunctionalInterface
public interface Func1Param<T>
{
    boolean invoke(T t);
}
