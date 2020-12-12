// let initialArray = [2, 8, 1, 9, 34, 59, 87, 16, 0, 44, 66, 77, 88, 14];


class MergeSortSameArray {
    static sort(array) {
        this.array = array;
        this.aux = array.slice();
        this._mergeSort(0, array.length - 1);
        return this.array;
    }

    static _mergeSort(start, end) {
        if (end <= start) return;
        let middle = Math.floor((end + start) / 2);
        this._mergeSort(start, middle);
        this._mergeSort(middle + 1, end);
        this._merge(start, middle, end);
    }

    static _merge(start, middle, end) {
        let leftPointer = start, rightPointer = middle + 1;
        this.aux = array.slice(start, end + 1);

        for (let k = start; k <= end; k++) {
            if (leftPointer > middle) this.array[k] = this.aux[rightPointer++ - start];
            else if (rightPointer > end) this.array[k] = this.aux[leftPointer++ - start];
            else if (this.aux[leftPointer - start] < this.aux[rightPointer - start]) this.array[k] = this.aux[leftPointer++ - start];
            else this.array[k] = this.aux[rightPointer++ - start];
        }
    }
}
// array = initialArray.slice();
// result = MergeSortSameArray.sort(array);
// console.log("MS same array", result);