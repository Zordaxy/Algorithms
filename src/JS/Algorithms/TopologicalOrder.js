var findOrder = function (numCourses, prerequisites) {
    let adj = new Map();

    // generate adj list
    for (let i = 0; i < prerequisites.length; i++) {
        let [parent, child] = prerequisites[i];

        if (adj.has(parent)) {
            adj.get(parent).push(child);
        } else {
            adj.set(parent, [child])
        }
    }

    let topoOrder = [];
    let visited = new Set();
    let tracked = new Set();

    let dfs = node => {
        visited.add(node);
        // onnce we checked for circle a set of nodes - we don't have to do that again
        tracked.add(node);
        let children = adj.get(node);
        if (children) {
            for (let i = 0; i < children.length; i++) {
                if (tracked.has(children[i])) return true;
                if (!visited.has(children[i])) {
                    let isCircle = dfs(children[i]);
                    if (isCircle) return true;
                }
            }
        }

        tracked.delete(node);
        topoOrder.push(node);
        return false;
    }

    for (let i = 0; i < numCourses; i++) {
        // dfs(i) returns true in case of existing circle
        if (!visited.has(i) && dfs(i)) return [];
    }

    // No reverse as contition is about prerequisites, not dependencies
    return topoOrder;
};