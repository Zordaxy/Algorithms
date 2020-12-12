// let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];

function mergeSort(nums) {
    let sort = nums => {
        if (nums.length < 2) return nums;
        let mid = Math.floor(nums.length / 2);

        // splin in range 0 -> mid - 1; mid -> nums.length - 1
        let left = sort(nums.slice(0, mid));
        let right = sort(nums.slice(mid));
        return merge(left, right);
    }

    let merge = (a, b) => {
        let result = [];
        let lInd = 0, rInd = 0;

        let len = a.length + b.length;
        for (let i = 0; i < len; i++) {
            if (lInd === a.length) result[i] = b[rInd++];
            else if (rInd === b.length) result[i] = a[lInd++];
            else if (a[lInd] > b[rInd]) result[i] = b[rInd++];
            else result[i] = a[lInd++];
        }
        return result;
    }

    if (!nums) return [];
    return sort(nums);
}
// console.log(mergeSort([5,2,4,3]), mergeSort([8]), mergeSort([5,1,1,2,2,4])); // [ 2, 3, 4, 5 ] [ 8 ] [ 1, 1, 2, 2, 4, 5 ]