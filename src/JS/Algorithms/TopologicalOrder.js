function topoOrder(adj) {
    let visited = new Set();
    let dfsPost = [];
    let nodes = [...adj.keys()];

    // topological sort
    // reversed PostOrder traversal.
    let topoOrder = el => {
        visited.add(el);
        adj.get(el).forEach(child => {
            if (!visited.has(child)) topoOrder(child);
        });
        dfsPost.push(el);
    }

    for (let i = 0; i < nodes.length; i++) {
        if (!visited.has(nodes[i])) topoOrder(nodes[i]);
    }
    return dfsPost.reverse().join('');
}

function checkForCycles(adj) {
    let visited = new Set();

    let dfs = value => {
        if (visited.has(value)) return true;
        visited.add(value);

        let children = adj.get(value);
        for (let i = 0; i < children.length; i++) {
            let result = dfs(children[i]);
            if (result) return true;
        }
        // Clean up after traversal!
        visited.delete(value);
        return false;
    }

    let nodes = [...adj.keys()];
    for (let i = 0; i < nodes.length; i++) {
        let result = dfs(nodes[i]);
        if (result) return true;
    }
    return false;
}