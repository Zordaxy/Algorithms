// let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];

class SelectionSort {
    static sort(array) {
        let minIndex;
        for (let i = 0; i < array.length; i++) {
            minIndex = i;
            for (let j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) minIndex = j;
            }
            this._swap(array, i, minIndex);
        }
    }

    static _swap(array, a, b) {
        let temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
// array = initialArray.slice();
// SelectionSort.sort(array);
// console.log("SelectionSort", array);