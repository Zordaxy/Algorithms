var preorder = function (root) {
    if (!root) return [];
    return [
        root.val,
        ...preorder(root.left),
        ...preorder(root.right)
    ];
};

var preorderIterative = function (root) {
    // Postorder the same but unshift() instead of pop() and left/right switch
    let stack = [root];
    let arr = [];
    while (stack.length) {
        let node = stack.pop();
        arr.push(node.val);
        if (node.right) stack.push(node.right);
        if (node.left) stack.push(node.left);
    }
    return arr;
};


function inorder(root) {
    let stack = [];
    let result = [];
    let node = root;

    while (stack.length || node) {
        while (node) {
            stack.push(node);
            node = node.left;
        }
        node = stack.pop();
        result.push(node.val);

        node = node.right;
    }

    return result;
}

var postorder = function (root) {
    if (!root) return [];
    return [
        ...postorder(root.left),
        ...postorder(root.right),
        root.val
    ];
};

var levelOrder = function (root) {
    if (!root) return [];
    let queue = [root];
    let result = [];

    while (queue.length) {
        let level = [];
        // do not forget to store length!
        let len = queue.length;
        for (let i = 0; i < len; i++) {
            // shift for queue
            let node = queue.shift();
            level.push(node.val);
            if (node.left) queue.push(node.left);
            if (node.right) queue.push(node.right);
        }
        result.push(level);
    }

    return result;
};
