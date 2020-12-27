function dijkstra(n, adj, ind) {
    let distTo = new Array(n).fill(Number.POSITIVE_INFINITY);
    distTo[ind] = 0;

    let minQ = new MinQ((a, b) => a[1] - b[1]);
    minQ.add([ind, 0]);

    while (!minQ.isEmpty()) {
        let [el, dist] = minQ.delMin();
        if (dist > distTo[el]) continue;

        for (let [child, distToChild] of adj[el]) {
            if (distTo[child] > distToChild + dist) {
                distTo[child] = distToChild + dist;
                minQ.add([child, distTo[child]]);
            }
        }
    }
    return distTo;
}