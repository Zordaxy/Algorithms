/**
 * @param {string[][]} equations
 * @param {number[]} values
 * @param {string[][]} queries
 * @return {number[]}
 */
var calcEquation = function(equations, values, queries) {
    let graph = new Map();
    let marked;
    let output = [];
    
    let dfs = (node, target, size) => {
        if (node === target) {
            return size;
        }
        marked.set(node, true);
        if (!node || !graph.has(node)) {
            return;
        }
        let adj = graph.get(node);
        
        for (let i = 0; i < adj.length; i++) {
            let edge = adj[i]
            if (!marked.get(edge.right)) {
                let result = dfs(edge.right, target, size * edge.weight);
                if (result !== null) {
                    return result;
                }
            }
        }
        return null;
    }
        
    for (let i = 0; i < equations.length; i++) {
        let left = equations[i][0];
        let right = equations[i][1]; 
        let leftEdge = new Edge(left, right, values[i]);
        if (graph.has(left)) {
            graph.get(left).push(leftEdge);
        } else {
            graph.set(left, [leftEdge]);
        }
        
        let rightEdge = new Edge(right, left, 1/values[i]);
        if (graph.has(right)) {
            graph.get(right).push(rightEdge);
        } else {
            graph.set(right, [rightEdge]);
        }
    }
    
    for (let i = 0; i < queries.length; i++) {
        let left = queries[i][0];
        let right = queries[i][1];
        
        if (!graph.has(left) || !graph.has(right)) {
            output.push(-1.0);
            continue;
        }
            
        marked = new Map();
        let size = dfs(queries[i][0], queries[i][1], 0);
        output.push(size === null ? -1.0 : size);
    }
    
    return output;
};

class Edge {
    constructor(left, right, weight)  {
        this.left = left;
        this.right = right;
        this.weight = weight;
    }
}
calcEquation([["a","b"],["b","c"]], [2.0,3.0], [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]);