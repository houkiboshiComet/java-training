package jpl.ch04.ex02;

interface SortInterface {
    SortMetrics sort(Object[] data);
}

abstract class SortHarness implements SortInterface {
    private Object values[];
    private final SortMetrics curMetrics = new SortMetrics(); /* privateなのでインターフェースにできない */

    protected final int compare(int i, int j) {
        curMetrics.incrementCompareCnt();
        if(values[i].equals( values[j] )) {
            return 0;
        } else {
            return (values[i].hashCode() < values[j].hashCode() ? -1 : 1);
        }
    }

    public final SortMetrics sort(Object[] data) {
        values = data.clone();
        curMetrics.init();
        doSort();
        return getMetrics();
    }
    protected final Object[] getValues() {
        return values.clone();
    }
    public final SortMetrics getMetrics() {
        return curMetrics.clone();
    }
    public final int getDataLength() {
        return values.length;
    }
    protected final Object probe(int i) {
        curMetrics.incrementProbeCnt();
        return values[i];
    }
    protected final void swap(int i, int j) {
        curMetrics.incrementSwapCnt();
        Object tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }
    protected abstract void doSort();
}

class SortMetrics implements Cloneable {
    private long probeCnt, compareCnt, swapCnt;
    public void init() {
        probeCnt = compareCnt = swapCnt = 0;
    }
    public String toString() {
        return probeCnt + " probeCnt"
            + compareCnt + " compareCnt"
            + swapCnt + " swapCnt";
    }
    public SortMetrics clone() {
        try {
            return (SortMetrics) super.clone();
        } catch( CloneNotSupportedException e) {
            throw new InternalError(e.toString());
        }
    }
    public long probeCnt() {
        return probeCnt;
    }
    public long swapCnt() {
        return swapCnt;
    }
    public long compareCnt() {
        return compareCnt;
    }
    public void incrementProbeCnt() {
        probeCnt++;
    }
    public void incrementSwapCnt() {
        swapCnt++;
    }
    public void incrementCompareCnt() {
        compareCnt++;
    }
}
