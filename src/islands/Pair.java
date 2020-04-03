package islands;

import java.util.Objects;

public class Pair<T, E> {
    final T firstData;
    final E secondData;

    Pair(T firstData, E secondData) {
        this.firstData = firstData;
        this.secondData = secondData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstData, pair.firstData) &&
                Objects.equals(secondData, pair.secondData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstData, secondData);
    }
}
