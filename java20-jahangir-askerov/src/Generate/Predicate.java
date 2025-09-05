package Generate;

@FunctionalInterface
public interface Predicate<T> {
    boolean Test(T t);
}
