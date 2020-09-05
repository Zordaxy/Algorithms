class Graph {
    constructor(noOfVertices) {
        this.noOfVertices = noOfVertices;
        this.AdjList = new Map();
    }

    addVertex(v) {
        this.AdjList.set(v, []);
    }

    addEdge(v, w) {
        this.AdjList.get(v).push(w);
        this.AdjList.get(w).push(v);
    }

    printGraph() {
        console.log(this.AdjList);
    }

    dfs(v) {
        let result = [];
        let marked = [];
        Array.from(this.AdjList.keys()).forEach(key => {
            if (!marked[key]) {
                this.dfsUtil(key, marked, result);
            }
        })
        console.log(`dfs: ${result}`);
    }

    dfsUtil(v, marked, result) {
        marked[v] = true;
        result.push(v);
        for (let x of this.AdjList.get(v)) {
            if (!marked[x]) {
                this.dfsUtil(x, marked, result);
            }
        }
    }

    bfs(v) {
        let result = [];
        let marked = [];
        // Use ArrayList(internal JS array implementation) as queue with push()/shift();
        let queue = [];

        queue.push(v);
        marked[v] = true;

        while (queue.length > 0) {
            let node = queue.shift();
            result.push(node);
            for (let x of this.AdjList.get(node)) {
                if (!marked[x]) {
                    marked[x] = true;
                    queue.push(x);
                }
            }
        }
        console.log(`bfs: ${result}`);
    }
}


let graph = new Graph(6);
['A', 'B', 'C', 'D', 'E', 'F'].forEach(x => graph.addVertex(x));

graph.addEdge('A', 'B');
graph.addEdge('A', 'D');
graph.addEdge('A', 'E');
graph.addEdge('B', 'C');
graph.addEdge('D', 'E');
graph.addEdge('E', 'F');
graph.addEdge('E', 'C');
graph.addEdge('C', 'F');

graph.printGraph();
graph.dfs('A');
graph.bfs('A');