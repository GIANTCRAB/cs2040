package kattis.teque;

import java.util.HashMap;
import java.util.Map;

public final class TequeJava {
    private final Map<Integer, Integer> frontMap = new HashMap<>();
    private int frontIndexMin;
    private int frontIndexMax = 1;
    private final Map<Integer, Integer> backMap = new HashMap<>();
    private int backIndexMin;
    private int backIndexMax = 1;

    public final void addFront(int element) {
        this.frontMapAddFirst(element);
        this.balance();
    }

    public final void addMiddle(int element) {
        this.frontMapPush(element);
        this.balance();
    }

    public final void addBack(int element) {
        this.backMapPush(element);
        this.balance();
    }


    public final String getElement(int positionIndex) {
        String retrievedElement;
        int relativePosition;
        if (positionIndex < this.frontMap.size()) {
            relativePosition = this.frontIndexMin + 1 + positionIndex;
            retrievedElement = String.valueOf(this.frontMap.get(relativePosition));
        } else {
            relativePosition = this.backIndexMin + 1 + (positionIndex - this.frontMap.size());
            retrievedElement = String.valueOf(this.backMap.get(relativePosition));
        }

        return retrievedElement;
    }

    private final void frontMapPush(int element) {
        this.frontMap.put(this.frontIndexMax, element);
        this.frontIndexMax++;
    }

    private final void frontMapAddFirst(int element) {
        this.frontMap.put(this.frontIndexMin, element);
        this.frontIndexMin += -1;
    }

    private final int frontMapPop() {
        Integer lastElement = (Integer) this.frontMap.get(this.frontIndexMax - 1);
        this.frontMap.remove(this.frontIndexMax - 1);
        this.frontIndexMax += -1;

        return lastElement;
    }

    private final void backMapPush(int element) {
        this.backMap.put(this.backIndexMax, element);
        this.backIndexMax++;
    }

    private final void backMapAddFirst(int element) {
        this.backMap.put(this.backIndexMin, element);
        this.backIndexMin += -1;
    }

    private final int backMapPop() {
        Integer firstElement = (Integer) this.backMap.get(this.backIndexMin + 1);
        this.backMap.remove(this.backIndexMin + 1);
        this.backIndexMin++;

        return firstElement;
    }

    private final void balance() {
        int elementToShift;
        if (this.frontMap.size() > this.backMap.size() + 1) {
            elementToShift = this.frontMapPop();
            this.backMapAddFirst(elementToShift);
        } else if (this.frontMap.size() < this.backMap.size()) {
            elementToShift = this.backMapPop();
            this.frontMapPush(elementToShift);
        }

    }
}
