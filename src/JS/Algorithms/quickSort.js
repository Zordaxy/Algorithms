// let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];



function quickSort(nums)  {
    if (nums.length < 2) return nums;

    let sort = (lo, hi) => {
        if (lo >= hi) return;
        let pivot = partition(lo, hi);
        sort(lo, pivot - 1);
        sort(pivot + 1, hi);
    };

    let partition = (lo, hi) => {
        let pivot = nums[hi];
        let ind = lo;
        for (let i = lo; i < hi; i++) {
            if (nums[i] < pivot) swap(i, ind++);
        }
        swap(hi, ind);
        return ind;
    };

    let swap = (a, b) => {
        [nums[a], nums[b]] = [nums[b], nums[a]];
    }

    sort(0, nums.length - 1);
    return nums;
}
// console.log(quickSort([5,2,4,3]), quickSort([8]), quickSort([5,1,1,2,2,4])); // [ 2, 3, 4, 5 ] [ 8 ] [ 1, 1, 2, 2, 4, 5 ]