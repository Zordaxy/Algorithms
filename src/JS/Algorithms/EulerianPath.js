// Eulerian path
// Reversed DFS Postorder O(E + V)
var findItinerary = function(tickets) {
    let map = new Map();
    for (let i = 0; i < tickets.length; i++) {
        let [dest, arr] = tickets[i];
        if (!map.has(dest)) {
            map.set(dest, [arr]);
        } else {
            map.get(dest).push(arr);
        }
        if (!map.has(arr)) map.set(arr, []);
    }
    map.forEach((value, key) => value.sort((a, b) => a < b ? -1 : 1));
    let path = [];
    
    let dfs = node => {
        let children = map.get(node);
        while(children.length) dfs(children.shift());
        path.push(node);
    }
    dfs('JFK');
    
    return path.reverse();
};