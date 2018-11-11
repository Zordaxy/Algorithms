class Tree {
    constructor(value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    insert(value) {
        if (value <= this.value) {
            if (!this.left) this.left = new Tree(value);
            else this.left.insert(value);
        } else {
            if (!this.right) this.right = new Tree(value);
            else this.right.insert(value);
        }
    }

    get(value) {
        if (value === this.value) return this;
        if (value <= this.value) {
            return this.left ? this.left.get(value) : null;
        } else {
            return this.right ? this.right.get(value) : null;
        }
    }

    contains(value) {
        if (value === this.value) return true;
        if (value < this.value) {
            if (!this.left) return false;
            else return this.left.contains(value);
        } else {
            if (!this.right) return false;
            else return this.right.contains(value);
        }
    }

    depthFirstTraverse(order, array = []) {
        order === "pre" && array.push(this.value);
        this.left && this.left.depthFirstTraverse(order, array);
        order === "in" && array.push(this.value);
        this.right && this.right.depthFirstTraverse(order, array);
        order === "post" && array.push(this.value);
        return array;
    }

    dFInOrderTraverse(array = []) {
        this.left && this.left.dFInOrderTraverse(array);
        console.log(this.value);
        array.push(this.value);
        this.right && this.right.dFInOrderTraverse(array);
        return array;
    }

    breadthFirstTraverse(array = []) {
        const queue = [this];
        while (queue.length) {
            const root = queue.shift();
            array.push(root.value);
            root.left && queue.push(root.left);
            root.right && queue.push(root.right);
        }
        return array;
    }

    getMinValue() {
        if (this.left) return this.left.getMinValue();
        return this.value;
    }

    getMaxValue() {
        if (this.right) return this.right.getMaxValue();
        return this.value;
    }
}

let deleteNode = (root, key) => {
    let deleteNode = node => {
        if (!node) return null;
        if (key > node.value) {
            node.right = deleteNode(node.right);
        } else if (key < node.value) {
            node.left = deleteNode(node.left);
        } else {
            if (node.left === null) return node.right;
            if (node.right === null) return node.left;

            let temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        return node;
    };

    let min = node => {
        if (node.left !== null) return min(node.left);
        return node;
    };

    let deleteMin = node => {
        if (!node) return null;
        if (node.left === null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    };

    root = deleteNode(root);
    return root;
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

let insert = (root, value) => {
    if (!root) return new Tree(value);

    let insert = node => {
        if (!node) return new Tree(value);
        if (value > node.value) {
            node.right = insert(node.right);
        } else {
            node.left = insert(node.left);
        }
        return node;
    };
    insert(root);
    return root;
};

let search = (root, value) => {
    get = node => {
        if (!node) return null;
        if (node.value === value) return node;
        return value > node.value ? get(node.right) : get(node.left);
    };
    return get(root);
};

let isValid = function(root) {
    function isValid(node, min, max) {
        if (!node) return true;
        if (min !== null && node.value <= min) return false;
        if (max !== null && node.value >= max) return false;
        return isValid(node.left, min, node.value) && isValid(node.right, node.value, max);
    }

    return isValid(root);
};

let maxDepth = function(root) {
    if(!root) return 0;
    let leftDepth = root.left ? maxDepth(root.left) : 0;
    let rightDepth = root.right ? maxDepth(root.right) : 0;

    return Math.max(leftDepth, rightDepth) + 1;
};

let tree = new Tree(11);
tree.insert(22);
tree.insert(88);
tree.insert(66);
tree.insert(77);
tree.insert(44);
console.log(tree.dFInOrderTraverse());

console.log("Found:", tree.get(77).value);
deleteNode(tree, 77);
console.log(tree.depthFirstTraverse('in'));

console.log("Found:", search(tree, 66).value);
deleteNode(tree, 66);
console.log(tree.breadthFirstTraverse());

console.log("Is Balanced:", isBalanced(tree));

insert(tree, 55);
console.log(tree.dFInOrderTraverse());

console.log("Is Valid BST:", isValid(tree));
console.log("Maximum depth:", maxDepth(tree));
