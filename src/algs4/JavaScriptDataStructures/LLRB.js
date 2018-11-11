const RED = true;
const BLACK = false;

class Tree {
    constructor(value, color = BLACK) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.color = color;
    }

    traverse(array = []) {
        this.left && this.left.traverse(array);
        array.push(this.value);
        this.right && this.right.traverse(array);
        return array;
    }
}

let rotateRight = node => {
    let x = node.left;
    node.left = x.right;
    x.right = node;
    x.color = node.color;
    node.color = RED;
    return x;
};

let rotateLeft = node => {
    let x = node.right;
    node.right = x.left;
    x.left = node;
    x.color = node.color;
    node.color = RED;
    return x;
};

let flipColors = node => {
    node.left.color = BLACK;
    node.right.color = BLACK;
    node.color = RED;
};

let isRed = node => {
    if (!node) return false;
    return node.color;
};

let insert = (node, value) => {
    if (!node) return new Tree(value, RED);
    if (node.value === value) return node;
    if (value > node.value) {
        node.right = insert(node.right, value);
    } else {
        node.left = insert(node.left, value);
    }

    if (isRed(node.right) && !isRed(node.left)) node = rotateLeft(node);
    if (isRed(node.left) && isRed(node.left.left)) node = rotateRight(node);
    if (isRed(node.left) && isRed(node.right)) flipColors(node);

    return node;
};


let find = (node, value) => {
    if (!node) return null;
    if (node.value === value) return node;
    return value > node.value ? find(node.right, value) : find(node.left, value);
};

let isBalanced = root => {
    let balanced = true;

    let checkNode = node => {
        if (!node || !balanced) return 0;
        let left = checkNode(node.left);
        let right = checkNode(node.right);
        if (Math.abs(left - right) <= 1) return Math.max(left, right) + 1;
        balanced = false;
        return 0;
    };
    checkNode(root);
    return balanced;
};

let tree = new Tree(11);
tree = insert(tree, 22);
tree = insert(tree, 88);
tree = insert(tree, 66);
tree = insert(tree, 77);
tree = insert(tree, 44);
console.log(tree.traverse());

console.log("Is height-balanced:", isBalanced(tree));
