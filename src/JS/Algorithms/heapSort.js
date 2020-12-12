// let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];

class HeapSort {
    static sort(array) {
        array.push(array[0]);
        let N = array.length - 1;
        for (let k = Math.floor(N / 2); k >= 1; k--) {
            this._sink(array, k, N);
        }

        while (N > 1) {
            this._swap(array, 1, N--);
            this._sink(array, 1, N);
        }

        array.shift();
        return array;
    }

    static _sink(array, index, end) {
        let j;
        while ((j = index * 2) <= end) {
            if (j + 1 <= end && array[j] < array[j + 1]) j++;
            if (array[j] > array[index]) this._swap(array, index, j);
            index = j;
        }
    }

    static _swap(array, a, b) {
        let temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
// array = initialArray.slice();
// result = HeapSort.sort(array);
// console.log("HeapSort     ", result);