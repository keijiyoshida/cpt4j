import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UnionFindTest {
    @Test
    public void testGetRoot() {
        int n = 3;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            assertEquals(i, unionFind.getRoot(i));
        }
    }

    @Test
    public void testGetSize() {
        int n = 3;
        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            assertEquals(1, unionFind.getSize(i));
        }
    }

    @Test
    public void testMerge() {
        UnionFind unionFind = new UnionFind(5);
        boolean hasMerged = unionFind.merge(0, 1);
        assertEquals(true, hasMerged);
        assertEquals(0, unionFind.getRoot(0));
        assertEquals(0, unionFind.getRoot(1));
        assertEquals(2, unionFind.getRoot(2));
        hasMerged = unionFind.merge(0, 1);
        assertEquals(false, hasMerged);
    }
}
