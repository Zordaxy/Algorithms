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
    if (!root) return null;
    
    let findSuccessor = node => {
        node = node.right;
        while(node.left) node = node.left;
        return node;
    }
    
    let delInSubTree = (node, val) => {
        if (!node) return null;
        if (node.val === val) {
            // case with single child
            // case with no children covered
            // case with 2 shildren
            if (!node.left) return node.right;
            if (!node.right) return node.left;
            
            successor = findSuccessor(node);
            successor.right = delInSubTree(node.right, successor.val);
            successor.left = node.left;
            
            return successor;
        } else if (node.val > key) {
            node.left = delInSubTree(node.left, val);
        } else {
            node.right = delInSubTree(node.right, val);
        }
        return node;
    }
    
    return delInSubTree(root, key);
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

let insertRecursive = (node, value) => {
    if (!node) return new Tree(value);
    if (value > node.value) {
        node.right = insert(node.right, value);
    } else {
        node.left = insert(node.left, value);
    }
    return node;
};

let insertIterative = (root, val) => {
    if (!root) return new TreeNode(val);
    let node = root;
    while (node) {
        if (node.val > val) {
            if (node.left) {
                node = node.left;
            } else {
                node.left = new TreeNode(val);
                break;
            }
        } else {
            if (node.right) {
                node = node.right;
            } else {
                node.right = new TreeNode(val);
                break;
            }
        }
    }
    return root;
}

let find = (node, value) => {
    if (!node) return null;
    if (node.value === value) return node;
    return value > node.value ? find(node.right, value) : find(node.left, value);
};

let isValid = function (node, min, max) {
    if (!node) return true;
    if (min !== null && node.value <= min) return false;
    if (max !== null && node.value >= max) return false;
    return isValid(node.left, min, node.value) && isValid(node.right, node.value, max);
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

console.log("Found:", find(tree, 66).value);
deleteNode(tree, 66);
console.log(tree.breadthFirstTraverse());

console.log("Is height-balanced:", isBalanced(tree));

insert(tree, 55);
console.log(tree.dFInOrderTraverse());

console.log("Is Valid BST:", isValid(tree));
console.log("Maximum depth:", maxDepth(tree));
