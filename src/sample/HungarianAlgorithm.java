package sample;

import java.util.Arrays;

public class HungarianAlgorithm {
    public static int getAssignment(int[][] a, int[] ans) {
        int n, m, h, w, n0, m0, m1, delta, cur;
        int[] u, v, p, way, minv;
        boolean[] used;
        h = 1 + a.length;
        w = 1 + a[0].length;
        if (h > w) {
            throw new IllegalArgumentException("Data height must be less than or equal data width.");
        }
        u = new int[h];
        v = new int[w];
        p = new int[w];
        way = new int[w];
        minv = new int[w];
        used = new boolean[w];
        for (n = 1; h > n; n++) {
            p[0] = n;
            m0 = 0;
            Arrays.fill(minv, Integer.MAX_VALUE);//MAX_VALUE
            Arrays.fill(used, false);
            do {
                used[m0] = true;
                n0 = p[m0];
                delta = Integer.MAX_VALUE;//MAX_VALUE
                m1 = 0;
                for (m = 1; w > m; m++) {
                    if (!used[m]) {
                        cur = a[n0 - 1][m - 1] - u[n0] - v[m];
                        if (minv[m] > cur) {
                            minv[m] = cur;
                            way[m] = m0;
                        }
                        if (delta > minv[m]) {
                            delta = minv[m];
                            m1 = m;
                        }
                    }
                }
                for (m = 0; w > m; m++) {
                    if (used[m]) {
                        u[p[m]] += delta;
                        v[m] -= delta;
                    } else {
                        minv[m] -= delta;
                    }
                }
                m0 = m1;
            } while (0 != p[m0]);
            do {
                m1 = way[m0];
                p[m0] = p[m1];
                m0 = m1;
            } while (0 != m0);
        }
        if (null != ans) {
            for (n = 1; w > n; n++) {
                n0 = p[n];
                if (0 != n0) {
                    ans[p[n] - 1] = n - 1;
                }
            }
        }
        return -v[0];
    }
}