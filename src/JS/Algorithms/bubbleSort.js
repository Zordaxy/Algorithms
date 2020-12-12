// let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];

class BubbleSort {
    static sort(array) {
        for (let i = array.length; i > 0; i--) {
            for (let j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) this._swap(array, j, j + 1);
            }
        }
    }

    static _swap(array, a, b) {
        let temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
// let array = initialArray.slice();
// BubbleSort.sort(array);
// console.log("BubbleSort   ", array);