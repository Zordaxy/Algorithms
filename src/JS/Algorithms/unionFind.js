let id = [...Array(elements.length).keys()];
let size = new Array(elements.length).fill(1);

let root = ind => {
    while (ind !== id[ind]) {
        id[ind] = id[id[ind]];
        ind = id[ind];
    }
    return ind;
}

let join = (a, b) => {
    let i = root(a);
    let j = root(b);
    if (size[i] > size[j])  {
        id[j] = i;
        size[i] += size[j];
    } else {
        id[i] = j;
        size[j] += size[i];
    }
}

let connected = (a, b) => root(a) == root(b);
