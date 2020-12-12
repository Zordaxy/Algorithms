var quickSelect = function (nums, k) {
    if (!nums || k > nums.length) return null;
    // ind - index of desired el in hypotetically sorted array.
    let ind = k - 1;

    return search(nums, 0, nums.length - 1, ind);
};

function search(arr, start, end, ind) {
    let pivot = getPivot(arr, start, end);

    if (pivot === ind) {
        return arr[ind];
    } else if (pivot > ind) {
        return search(arr, start, pivot - 1, ind);
    } else {
        return search(arr, pivot + 1, end, ind);
    }
}

function getPivot(arr, start, end) {
    let pivot = arr[end];
    let i = start;
    let j = end - 1;

    while (i <= j) {
        while (pivot > arr[i]) i++;
        while (pivot < arr[j]) j--;

        if (i <= j) {
            swap(arr, i, j);
            i++;
            j--;
        }
    }
    swap(arr, end, i);
    return i;
}

function swap(arr, i, j) {
    [arr[i], arr[j]] = [arr[j], arr[i]];
}

console.log(quickSelect([10, 80, 90, 40, 30, 60, 70, 50], 2));